package christmas.service;

import static christmas.domain.constants.Menu.CHAMPAGNE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import christmas.domain.OrderDetails;
import christmas.domain.constants.Menu;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventCheckerTest {
    @DisplayName("혜택이 날짜와 주문에 따라 바르게 적용되는지 확인")
    @ValueSource(ints = {1, 19, 25, 26, 31})
    @ParameterizedTest
    void preciseBenefit(int input) {
        LocalDate reservationDate = Parser.parseIntToLocalDate(input);
        OrderDetails orderDetails = OrderDetails.create();
        orderDetails.addOrder(Menu.BBQ_RIBS, 3);
        String gift = EventChecker.checkChampagneEvent(orderDetails);
        System.out.println(EventChecker.applyEventBenefit(reservationDate, orderDetails, gift));
    }

    @Test
    public void testMakeBenefitDetails() {
        OrderDetails orderDetails = mock(OrderDetails.class);
        when(orderDetails.getTotalOrderPrice()).thenReturn(new BigDecimal("10000"));

        LocalDate reservationDate = LocalDate.of(2023, 12, 3);
        String gift = "없음";

        Map<String, BigDecimal> benefitDetails = EventChecker.makeBenefitDetails(reservationDate, orderDetails, gift);

        assertEquals(expectedChristmasDiscount(), benefitDetails.get("크리스마스 디데이 할인"));
        assertEquals(expectedWeekdayDiscount(), benefitDetails.get("평일 할인"));
        assertEquals(expectedWeekendDiscount(), benefitDetails.get("주말 할인"));
        assertEquals(expectedSpecialDiscount(), benefitDetails.get("특별 할인"));
        assertEquals(expectedChampagneBenefit(), benefitDetails.get("증정 이벤트"));
        System.out.println(benefitDetails);
    }

    private BigDecimal expectedChristmasDiscount() {
        return BigDecimal.valueOf(-1200);
    }

    private BigDecimal expectedWeekdayDiscount() {
        return null;
    }

    private BigDecimal expectedWeekendDiscount() {
        return null;
    }

    private BigDecimal expectedSpecialDiscount() {
        return BigDecimal.valueOf(-1000);
    }

    private BigDecimal expectedChampagneBenefit() {
        return null;
    }

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

        orderDetails.addOrder(CHAMPAGNE, 1);

        String result = EventChecker.checkChampagneEvent(orderDetails);

        assertEquals("없음", result, "샴페인이 없어야 합니다.");
        assertEquals(1, orderDetails.getMenuQuantities().getOrDefault(CHAMPAGNE, 0), "샴페인이 1개 있어야 합니다.");
    }

    @Test
    @DisplayName("샴페인 증정 이벤트 - 총 주문 금액이 12만원 미만, 기존 샴페인 있음")
    void testCheckChampagneEventBelowLimitAndExistChampagne() {
        OrderDetails orderDetails = OrderDetails.create();

        orderDetails.addOrder(Menu.RED_WINE, 1);

        String result = EventChecker.checkChampagneEvent(orderDetails);

        assertEquals("없음", result, "샴페인이 없어야 합니다.");
    }

    @Test
    @DisplayName("샴페인 증정 이벤트 - 총 주문 금액이 12만원 이상, 기존 샴페인 없음")
    void testCheckChampagneEventAboveLimit() {
        OrderDetails orderDetails = OrderDetails.create();

        orderDetails.addOrder(Menu.RED_WINE, 1);
        orderDetails.addOrder(Menu.BBQ_RIBS, 3);

        String result = EventChecker.checkChampagneEvent(orderDetails);

        assertEquals(CHAMPAGNE.getMenuName(), result, "샴페인이 있어야 합니다.");
        assertEquals(1, orderDetails.getMenuQuantities().getOrDefault(CHAMPAGNE, 0), "샴페인이 1개 있어야 합니다.");
    }

    @Test
    @DisplayName("샴페인 증정 이벤트 - 총 주문 금액이 12만원 이상, 기존 샴페인 있음")
    void testCheckChampagneEventAboveLimitAndExistChampagne() {
        OrderDetails orderDetails = OrderDetails.create();

        orderDetails.addOrder(CHAMPAGNE, 1);
        orderDetails.addOrder(Menu.BBQ_RIBS, 3);

        String result = EventChecker.checkChampagneEvent(orderDetails);

        assertEquals(CHAMPAGNE.getMenuName(), result, "샴페인이 있어야 합니다.");
        assertEquals(2, orderDetails.getMenuQuantities().getOrDefault(CHAMPAGNE, 0), "샴페인이 2개 있어야 합니다.");
    }
}
