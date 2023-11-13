package christmas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.OrderDetails;
import christmas.domain.constants.Menu;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventCheckerTest {
    @DisplayName("배지 부여가 가격에 따라 바르게 적용되는지 확인")
    @ValueSource(strings = {"4999", "4999.9", "5000", "9999", "10000", "19999", "20000"})
    @ParameterizedTest
    void preciseGive(BigDecimal input) {
        System.out.println(EventChecker.checkBadgeEvent(input));
    }

    @Test
    @DisplayName("샴페인 증정 이벤트 - 총 주문 금액이 12만원 미만, 기존 샴페인 없음")
    void testCheckChampagneEventBelowLimit() {
        OrderDetails orderDetails = OrderDetails.create();

        orderDetails.addOrder(Menu.CHAMPAGNE, 1);

        Map<String, BigDecimal> result = EventChecker.checkChampagneEvent(orderDetails);

        assertEquals("없음", result.keySet().stream().findFirst().orElse(null), "샴페인이 없어야 합니다.");
        assertEquals(1, orderDetails.getOrder().getOrDefault("샴페인", 0), "샴페인이 1개 있어야 합니다.");
    }

    @Test
    @DisplayName("샴페인 증정 이벤트 - 총 주문 금액이 12만원 미만, 기존 샴페인 있음")
    void testCheckChampagneEventBelowLimitAndExistChampagne() {
        OrderDetails orderDetails = OrderDetails.create();

        orderDetails.addOrder(Menu.RED_WINE, 1);

        Map<String, BigDecimal> result = EventChecker.checkChampagneEvent(orderDetails);

        assertEquals("없음", result.keySet().stream().findFirst().orElse(null), "샴페인이 없어야 합니다.");
    }

    @Test
    @DisplayName("샴페인 증정 이벤트 - 총 주문 금액이 12만원 이상, 기존 샴페인 없음")
    void testCheckChampagneEventAboveLimit() {
        OrderDetails orderDetails = OrderDetails.create();

        orderDetails.addOrder(Menu.RED_WINE, 1);
        orderDetails.addOrder(Menu.BBQ_RIBS, 3);

        Map<String, BigDecimal> result = EventChecker.checkChampagneEvent(orderDetails);

        assertEquals(Menu.CHAMPAGNE.getMenuName(), result.keySet().stream().findFirst().orElse(null), "샴페인이 있어야 합니다.");
        assertEquals(1, orderDetails.getOrder().getOrDefault("샴페인", 0), "샴페인이 1개 있어야 합니다.");
    }

    @Test
    @DisplayName("샴페인 증정 이벤트 - 총 주문 금액이 12만원 이상, 기존 샴페인 있음")
    void testCheckChampagneEventAboveLimitAndExistChampagne() {
        OrderDetails orderDetails = OrderDetails.create();

        orderDetails.addOrder(Menu.CHAMPAGNE, 1);
        orderDetails.addOrder(Menu.BBQ_RIBS, 3);

        Map<String, BigDecimal> result = EventChecker.checkChampagneEvent(orderDetails);

        assertEquals(Menu.CHAMPAGNE.getMenuName(), result.keySet().stream().findFirst().orElse(null), "샴페인이 있어야 합니다.");
        assertEquals(2, orderDetails.getOrder().getOrDefault("샴페인", 0), "샴페인이 2개 있어야 합니다.");
    }
}
