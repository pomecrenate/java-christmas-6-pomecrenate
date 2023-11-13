package christmas.service;

import static christmas.domain.constants.MenuCategory.DESSERT;
import static christmas.domain.constants.MenuCategory.MAIN;

import christmas.domain.OrderDetails;
import christmas.domain.constants.Menu;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
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

    // 디데이 할인
    public static BigDecimal checkChristmasDiscount(LocalDate reservationDate) {
        if (!EventMonth.isDDay(reservationDate)) {
            return NOT_DISCOUNT;
        }
        BigDecimal reservationDay = Parser.parseLocalDateToBigDecimal(reservationDate);
        return ChristmasEvent.discountChristmas(reservationDay);
    }

    // 평일 할인
    public static BigDecimal checkWeekdayEvent(LocalDate reservationDate, OrderDetails orderDetails) {
        if (!EventMonth.isWeekday(reservationDate)) {
            return NOT_DISCOUNT;
        }
        Map<String, Integer> orderCategory = orderDetails.getOrderCategoryName();
        BigDecimal dessertQuantity = new BigDecimal(orderCategory.getOrDefault(DESSERT.getMenuCategoryName(), ZERO));
        return DecemberEvent.discountDecember(dessertQuantity);
    }

    // 주말 할인
    public static BigDecimal checkWeekendEvent(LocalDate reservationDate, OrderDetails orderDetails) {
        if (!EventMonth.isWeekend(reservationDate)) {
            return NOT_DISCOUNT;
        }
        Map<String, Integer> orderCategory = orderDetails.getOrderCategoryName();
        BigDecimal mainQuantity = new BigDecimal(orderCategory.getOrDefault(MAIN.getMenuCategoryName(), ZERO));
        return DecemberEvent.discountDecember(mainQuantity);
    }

    // 특별 할인
    public static BigDecimal checkSpecialEvent(LocalDate reservationDate) {
        if (!EventMonth.isSpecialDay(reservationDate)) {
            return NOT_DISCOUNT;
        }
        return DecemberEvent.discountSpecialDay();
    }

    // 샴페인 증정
    public static Map<String, BigDecimal> checkChampagneEvent(OrderDetails orderDetails) {
        Map<String, BigDecimal> gift = new HashMap<>();
        BigDecimal totalOrder = orderDetails.getTotalOrder();
        if (isLowerLimit(LOWER_LIMIT_CHAMPAGNE, totalOrder)) {
            Menu champagne = DecemberEvent.giftChampagne();
            orderDetails.addGift(champagne, ONE);
            gift.put(champagne.getMenuName(), champagne.getMenuPrice());
            return gift;
        }
        gift.put(NOT_APPLICABLE, BigDecimal.ZERO);
        return gift;
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
