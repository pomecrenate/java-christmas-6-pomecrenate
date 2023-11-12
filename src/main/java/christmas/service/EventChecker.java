package christmas.service;

import static christmas.domain.constant.MenuCategory.DESSERT;
import static christmas.domain.constant.MenuCategory.MAIN;

import christmas.domain.ChristmasEvent;
import christmas.domain.DecemberEvent;
import christmas.domain.EventMonth;
import christmas.domain.OrderDetails;
import christmas.domain.constant.Menu;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class EventChecker {
    private static final BigDecimal NOT_DISCOUNT = BigDecimal.ZERO;
    private static final BigDecimal LOWER_LIMIT_CHAMPAGNE = new BigDecimal("120000");
    private static final BigDecimal LOWER_LIMIT_STAR_BADGE = new BigDecimal("5000");
    private static final BigDecimal LOWER_LIMIT_TREE_BADGE = new BigDecimal("10000");
    private static final BigDecimal LOWER_LIMIT_SANTA_BADGE = new BigDecimal("20000");
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final String NOT_APPLICABLE = "없음";

    // 전체 할인 적용
    public static BigDecimal applyEventDiscount(LocalDate reservationDate, OrderDetails orderDetails) {
        BigDecimal christmasDiscount = checkChristmasDiscount(reservationDate);
        BigDecimal decemberDiscount = checkDecemberDiscount(reservationDate, orderDetails);
        BigDecimal specialDiscount = checkSpecialEvent(reservationDate);
        return christmasDiscount.add(decemberDiscount).add(specialDiscount);
    }

    private static BigDecimal checkChristmasDiscount(LocalDate reservationDate) {
        if (!EventMonth.isDDay(reservationDate)) {
            return NOT_DISCOUNT;
        }
        BigDecimal reservationDay = Parser.parseLocalDateToBigDecimal(reservationDate);
        return ChristmasEvent.discountChristmas(reservationDay);
    }

    private static BigDecimal checkDecemberDiscount(LocalDate reservationDate, OrderDetails orderDetails) {
        BigDecimal weekdayDiscount = checkWeekdayEvent(reservationDate, orderDetails);
        BigDecimal weekendDiscount = checkWeekendEvent(reservationDate, orderDetails);
        return weekdayDiscount.add(weekendDiscount);
    }

    private static BigDecimal checkWeekdayEvent(LocalDate reservationDate, OrderDetails orderDetails) {
        if (!EventMonth.isWeekday(reservationDate)) {
            return NOT_DISCOUNT;
        }
        Map<String, Integer> orderCategory = orderDetails.getOrderCategoryName();
        BigDecimal dessertQuantity = new BigDecimal(orderCategory.getOrDefault(DESSERT.getMenuCategoryName(), ZERO));
        return DecemberEvent.discountDecember(dessertQuantity);
    }

    private static BigDecimal checkWeekendEvent(LocalDate reservationDate, OrderDetails orderDetails) {
        if (!EventMonth.isWeekend(reservationDate)) {
            return NOT_DISCOUNT;
        }
        Map<String, Integer> orderCategory = orderDetails.getOrderCategoryName();
        BigDecimal mainQuantity = new BigDecimal(orderCategory.getOrDefault(MAIN.getMenuCategoryName(), ZERO));
        return DecemberEvent.discountDecember(mainQuantity);
    }

    private static BigDecimal checkSpecialEvent(LocalDate reservationDate) {
        if (!EventMonth.isSpecialDay(reservationDate)) {
            return NOT_DISCOUNT;
        }
        return DecemberEvent.discountSpecialDay();
    }

    // 샴페인 증정
    public static String checkChampagneEvent(OrderDetails orderDetails) {
        BigDecimal totalOrder = orderDetails.getTotalOrder();
        if (isLowerLimit(LOWER_LIMIT_CHAMPAGNE, totalOrder)) {
            Menu champagne = DecemberEvent.giftChampagne();
            orderDetails.addGift(champagne, ONE);
            return champagne.getMenuName();
        }
        return NOT_APPLICABLE;
    }

    // 배지 부여
    public static String checkBadgeEvent(BigDecimal totalBenefit) {
        if (isLowerLimit(LOWER_LIMIT_SANTA_BADGE, totalBenefit)) {
            return DecemberEvent.giveSantaBadge();
        }
        if (isLowerLimit(LOWER_LIMIT_TREE_BADGE, totalBenefit)) {
            return DecemberEvent.giveTreeBadge();
        }
        if (isLowerLimit(LOWER_LIMIT_STAR_BADGE, totalBenefit)) {
            return DecemberEvent.giveStarBadge();
        }
        return NOT_APPLICABLE;
    }

    private static boolean isLowerLimit(BigDecimal lowerLimitAmount, BigDecimal amount) {
        return lowerLimitAmount.compareTo(amount) <= ZERO;
    }
}
