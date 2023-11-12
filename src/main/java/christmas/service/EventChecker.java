package christmas.service;

import christmas.domain.ChristmasEvent;
import christmas.domain.EventMonth;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EventChecker {
    private static final BigDecimal NOT_DISCOUNT = new BigDecimal("0");

    public static BigDecimal holdEvent(LocalDate reservationDate) {
        BigDecimal reservationDay = Parser.parseLocalDateToBigDecimal(reservationDate);
        if (!EventMonth.isDDay(reservationDate)) {
            return NOT_DISCOUNT;
        }
        return ChristmasEvent.discountChristmas(reservationDay);
    }
}
