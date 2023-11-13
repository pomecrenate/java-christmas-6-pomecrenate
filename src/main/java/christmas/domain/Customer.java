package christmas.domain;

import java.math.BigDecimal;
import java.util.Map;

public class Customer {
    private final OrderDetails orderDetails;
    private final BigDecimal totalOrderPrice;
    private final Map<String, BigDecimal> gift;
    private final BigDecimal totalBenefitPrice;
    private final BigDecimal totalPatmentPrice;
    private final String eventBadge;

    private Customer(OrderDetails orderDetails, BigDecimal totalOrderPrice, Map<String, BigDecimal> gift,
                     BigDecimal totalBenefitPrice, BigDecimal totalPatmentPrice, String eventBadge) {
        this.orderDetails = orderDetails;
        this.totalOrderPrice = totalOrderPrice;
        this.gift = gift;
        this.totalBenefitPrice = totalBenefitPrice;
        this.totalPatmentPrice = totalPatmentPrice;
        this.eventBadge = eventBadge;
    }

    public static Customer create(OrderDetails orderDetails, BigDecimal totalOrderPrice,
                                  Map<String, BigDecimal> gift,
                                  BigDecimal totalBenefitPrice, BigDecimal totalPatmentPrice,
                                  String eventBadge) {
        return new Customer(orderDetails, totalOrderPrice, gift, totalBenefitPrice, totalPatmentPrice, eventBadge);
    }
}
