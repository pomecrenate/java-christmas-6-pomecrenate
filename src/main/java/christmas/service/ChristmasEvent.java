package christmas.service;

import java.math.BigDecimal;

public class ChristmasEvent {
    private static final BigDecimal D_DAY_DISCOUNT = new BigDecimal("-100");
    private static final BigDecimal FIRST_DAY_DISCOUNT = new BigDecimal("-1000");

    public static BigDecimal discountChristmas(BigDecimal reservationDay) {
        return D_DAY_DISCOUNT.multiply(reservationDay).subtract(D_DAY_DISCOUNT)
                .add(FIRST_DAY_DISCOUNT);
    }
}
