package christmas.service;

import christmas.domain.OrderDetails;
import christmas.domain.constant.Menu;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventManagerTest {
    @DisplayName("할인이 날짜와 주문에 따라 바르게 적용되는지 확인")
    @ValueSource(ints = {1, 19, 25, 26, 31})
    @ParameterizedTest
    void preciseDiscount(int input) {
        LocalDate reservationDate = Parser.parseIntToLocalDate(input);
        OrderDetails orderDetails = OrderDetails.create();
        orderDetails.addOrder(Menu.BBQ_RIBS, 3);
        System.out.println(EventManager.applyEventDiscount(reservationDate, orderDetails));
    }

    @DisplayName("혜택이 날짜와 주문에 따라 바르게 적용되는지 확인")
    @ValueSource(ints = {1, 19, 25, 26, 31})
    @ParameterizedTest
    void preciseBenefit(int input) {
        LocalDate reservationDate = Parser.parseIntToLocalDate(input);
        OrderDetails orderDetails = OrderDetails.create();
        orderDetails.addOrder(Menu.BBQ_RIBS, 3);
        System.out.println(EventManager.applyEventBenefit(reservationDate, orderDetails)
                .subtract(EventManager.applyEventDiscount(reservationDate, orderDetails)));
    }
}
