package christmas.service;

import christmas.domain.OrderDetails;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class EventManager {

    // 총 혜택 금액
    public static BigDecimal applyEventBenefit(LocalDate reservationDate, OrderDetails orderDetails) {
        BigDecimal discount = applyEventDiscount(reservationDate, orderDetails);
        Map<String, BigDecimal> gift = EventChecker.checkChampagneEvent(orderDetails);
        BigDecimal giftPrice = gift.values().stream().findFirst().orElse(null);
        return discount.subtract(giftPrice);
    }

    // 총 할인 금액
    public static BigDecimal applyEventDiscount(LocalDate reservationDate, OrderDetails orderDetails) {
        BigDecimal christmasDiscount = EventChecker.checkChristmasDiscount(reservationDate);
        BigDecimal weekdayDiscount = EventChecker.checkWeekdayEvent(reservationDate, orderDetails);
        BigDecimal weekendDiscount = EventChecker.checkWeekendEvent(reservationDate, orderDetails);
        BigDecimal specialDiscount = EventChecker.checkSpecialEvent(reservationDate);
        return christmasDiscount.add(weekdayDiscount).add(weekendDiscount).add(specialDiscount);
    }
}
