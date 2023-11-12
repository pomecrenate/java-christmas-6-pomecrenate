package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.constant.Menu;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderDetailsTest {
    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of(0, Menu.CHRISTMAS_PASTA),
                Arguments.of(1, Menu.CHOCOLATE_CAKE),
                Arguments.of(20, Menu.COKE_ZERO),
                Arguments.of(21, Menu.MUSHROOM_SOUP)
        );
    }

    @DisplayName("총 주문 금액이 메뉴와 개수에 따라 바르게 계산되는지 확인")
    @MethodSource("provideTestData")
    @ParameterizedTest
    void preciseDiscount(int input, Menu menu) {
        OrderDetails orderDetails = OrderDetails.create();
        orderDetails.addOrder(menu, input);
        BigDecimal expectedTotal = menu.getMenuPrice().multiply(BigDecimal.valueOf(input));
        assertEquals(expectedTotal, orderDetails.getTotalOrder());
        System.out.println(orderDetails.getTotalOrder());
    }
}
