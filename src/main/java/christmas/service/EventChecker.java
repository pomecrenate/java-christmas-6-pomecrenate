package christmas.service;

import static christmas.domain.constants.Menu.CHAMPAGNE;
import static christmas.domain.constants.MenuCategory.DESSERT;
import static christmas.domain.constants.MenuCategory.MAIN;

import christmas.domain.OrderDetails;
import christmas.domain.constants.Menu;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventChecker {
    private static final BigDecimal NOT_DISCOUNT = BigDecimal.ZERO;
    private static final BigDecimal LOWER_LIMIT_CHAMPAGNE = new BigDecimal("120000");
    private static final BigDecimal LOWER_LIMIT_STAR_BADGE = new BigDecimal("5000");
    private static final BigDecimal LOWER_LIMIT_TREE_BADGE = new BigDecimal("10000");
    private static final BigDecimal LOWER_LIMIT_SANTA_BADGE = new BigDecimal("20000");
    private static final BigDecimal LOWER_LIMIT_EVENT = new BigDecimal("10000");
    private static final BigDecimal CHAMPAGNE_PRICE = CHAMPAGNE.getMenuPrice();
    private static final BigDecimal CHAMPAGNE_BENEFIT = CHAMPAGNE_PRICE.multiply(BigDecimal.valueOf(-1));
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int NEGATIVE = -1;
    private static final String CHRISTMAS_DISCOUNT = "크리스마스 디데이 할인";
    private static final String WEEKDAY_DISCOUNT = "평일 할인";
    private static final String WEEKEND_DISCOUNT = "주말 할인";
    private static final String SPECIAL_DISCOUNT = "특별 할인";
    private static final String GIFT_EVENT = "증정 이벤트";
    private static final String NOT_APPLICABLE = "없음";

    // 총 혜택 금액
    public static BigDecimal applyEventBenefit(LocalDate reservationDate, OrderDetails reservationOrder, String gift) {
        BigDecimal discount = applyEventDiscount(reservationDate, reservationOrder);
        BigDecimal giftPrice = NOT_DISCOUNT;
        if (EventChecker.isChampagneEvent(gift)) {
            giftPrice = CHAMPAGNE_PRICE;
        }
        return discount.subtract(giftPrice);
    }

    public static BigDecimal getPaymentPrice(LocalDate reservationDate, OrderDetails reservationOrder,
                                             String gift) {
        BigDecimal totalOrderPrice = reservationOrder.getTotalOrderPrice();
        BigDecimal totalDiscountPrice = EventChecker.applyEventDiscount(reservationDate, reservationOrder);
        if (isChampagneEvent(gift)) {
            totalOrderPrice = totalOrderPrice.subtract(CHAMPAGNE_PRICE);
        }
        return totalOrderPrice.add(totalDiscountPrice);
    }

    private static BigDecimal applyEventDiscount(LocalDate reservationDate, OrderDetails reservationOrder) {
        BigDecimal christmasDiscount = EventChecker.checkChristmasDiscount(reservationDate, reservationOrder);
        BigDecimal weekdayDiscount = EventChecker.checkWeekdayEvent(reservationDate, reservationOrder);
        BigDecimal weekendDiscount = EventChecker.checkWeekendEvent(reservationDate, reservationOrder);
        BigDecimal specialDiscount = EventChecker.checkSpecialEvent(reservationDate, reservationOrder);
        return christmasDiscount.add(weekdayDiscount).add(weekendDiscount)
                .add(specialDiscount);
    }

    public static Map<String, BigDecimal> makeBenefitDetails(LocalDate reservationDate, OrderDetails reservationOrder,
                                                             String gift) {
        Map<String, BigDecimal> benefitDetails = new LinkedHashMap<>(); // 삽입 순서대로 저장
        putBenefit(EventChecker.checkChristmasDiscount(reservationDate, reservationOrder), benefitDetails,
                CHRISTMAS_DISCOUNT);
        putBenefit(EventChecker.checkWeekdayEvent(reservationDate, reservationOrder), benefitDetails, WEEKDAY_DISCOUNT);
        putBenefit(EventChecker.checkWeekendEvent(reservationDate, reservationOrder), benefitDetails, WEEKEND_DISCOUNT);
        putBenefit(EventChecker.checkSpecialEvent(reservationDate, reservationOrder), benefitDetails, SPECIAL_DISCOUNT);
        if (isChampagneEvent(gift)) {
            benefitDetails.put(GIFT_EVENT, CHAMPAGNE_BENEFIT);
        }
        return benefitDetails;
    }

    private static void putBenefit(BigDecimal reservationDate, Map<String, BigDecimal> benefitDetails,
                                   String discount) {
        if (isDiscount(reservationDate)) {
            benefitDetails.put(discount, reservationDate);
        }
    }

    // 디데이 할인
    private static BigDecimal checkChristmasDiscount(LocalDate reservationDate, OrderDetails reservationOrder) {
        if (EventChecker.isNotEventCondition(reservationOrder.getTotalOrderPrice())) {
            return NOT_DISCOUNT;
        }
        if (!EventMonth.isDDay(reservationDate)) {
            return NOT_DISCOUNT;
        }
        BigDecimal reservationDay = Parser.parseLocalDateToBigDecimal(reservationDate);
        return ChristmasEvent.discountChristmas(reservationDay);
    }

    // 평일 할인
    private static BigDecimal checkWeekdayEvent(LocalDate reservationDate, OrderDetails reservationOrder) {
        if (EventChecker.isNotEventCondition(reservationOrder.getTotalOrderPrice())) {
            return NOT_DISCOUNT;
        }
        if (!EventMonth.isWeekday(reservationDate)) {
            return NOT_DISCOUNT;
        }
        Map<String, Integer> orderCategory = reservationOrder.getOrderCategoryName();
        BigDecimal dessertQuantity = new BigDecimal(orderCategory.getOrDefault(DESSERT.getMenuCategoryName(), ZERO));
        return DecemberEvent.discountDecember(dessertQuantity);
    }

    // 주말 할인
    private static BigDecimal checkWeekendEvent(LocalDate reservationDate, OrderDetails reservationOrder) {
        if (EventChecker.isNotEventCondition(reservationOrder.getTotalOrderPrice())) {
            return NOT_DISCOUNT;
        }
        if (!EventMonth.isWeekend(reservationDate)) {
            return NOT_DISCOUNT;
        }
        Map<String, Integer> orderCategory = reservationOrder.getOrderCategoryName();
        BigDecimal mainQuantity = new BigDecimal(orderCategory.getOrDefault(MAIN.getMenuCategoryName(), ZERO));
        return DecemberEvent.discountDecember(mainQuantity);
    }

    // 특별 할인
    private static BigDecimal checkSpecialEvent(LocalDate reservationDate, OrderDetails reservationOrder) {
        if (EventChecker.isNotEventCondition(reservationOrder.getTotalOrderPrice())) {
            return NOT_DISCOUNT;
        }
        if (!EventMonth.isSpecialDay(reservationDate)) {
            return NOT_DISCOUNT;
        }
        return DecemberEvent.discountSpecialDay();
    }

    // 샴페인 증정
    public static String checkChampagneEvent(OrderDetails reservationOrder) {
        String gift = NOT_APPLICABLE;
        if (EventChecker.isNotEventCondition(reservationOrder.getTotalOrderPrice())) {
            return gift;
        }
        BigDecimal totalOrderPrice = reservationOrder.getTotalOrderPrice();
        if (isLowerLimit(LOWER_LIMIT_CHAMPAGNE, totalOrderPrice)) {
            Menu champagne = DecemberEvent.giftChampagne();
            reservationOrder.addGift(champagne, ONE);
            gift = champagne.getMenuName();
            return gift;
        }
        return gift;
    }

    // 배지 부여
    public static String checkBadgeEvent(BigDecimal totalBenefitPrice) {
        BigDecimal positiveTotalBenefitPrice = totalBenefitPrice.multiply(BigDecimal.valueOf(NEGATIVE));
        if (isLowerLimit(LOWER_LIMIT_SANTA_BADGE, positiveTotalBenefitPrice)) {
            return DecemberEvent.giveSantaBadge();
        }
        if (isLowerLimit(LOWER_LIMIT_TREE_BADGE, positiveTotalBenefitPrice)) {
            return DecemberEvent.giveTreeBadge();
        }
        if (isLowerLimit(LOWER_LIMIT_STAR_BADGE, positiveTotalBenefitPrice)) {
            return DecemberEvent.giveStarBadge();
        }
        return NOT_APPLICABLE;
    }

    public static boolean isChampagneEvent(String gift) {
        return !gift.equals(NOT_APPLICABLE);
    }

    private static boolean isLowerLimit(BigDecimal lowerLimitAmount, BigDecimal amount) {
        return lowerLimitAmount.compareTo(amount) <= ZERO;
    }

    private static boolean isNotEventCondition(BigDecimal totalOrderPrice) {
        return LOWER_LIMIT_EVENT.compareTo(totalOrderPrice) > ZERO;
    }

    private static boolean isDiscount(BigDecimal discountPrice) {
        return NOT_DISCOUNT.compareTo(discountPrice) != ZERO;
    }
}
