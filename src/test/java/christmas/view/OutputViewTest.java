package christmas.view;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import christmas.domain.OrderDetails;
import christmas.domain.constants.Menu;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutputViewTest {

    @DisplayName("줄바꿈 정상 적용")
    @Test
    void print() {
        LocalDate date = LocalDate.now();
        OutputView.printPreviewOrderDetails(date);
        System.out.println("rrrr");
    }

    @DisplayName("메뉴 정상 출력")
    @Test
    void printOrder() {
        OrderDetails orderDetails = OrderDetails.create();
        orderDetails.addOrder(Menu.CHAMPAGNE, 3);
        orderDetails.addOrder(Menu.BBQ_RIBS, 1);
        orderDetails.addOrder(Menu.CAESAR_SALAD, 2);
        orderDetails.addOrder(Menu.COKE_ZERO, 3);
        orderDetails.addOrder(Menu.TAPAS, 1);
        orderDetails.addOrder(Menu.CHRISTMAS_PASTA, 1);
        orderDetails.addOrder(Menu.ICE_CREAM, 4);
        assertDoesNotThrow(() -> OutputView.printOrderMenu(orderDetails));
    }

    @DisplayName("총 구매 금액 정상 출력")
    @Test
    void printOrderPrice() {
        OrderDetails orderDetails = OrderDetails.create();
        orderDetails.addOrder(Menu.CHAMPAGNE, 3);
        orderDetails.addOrder(Menu.BBQ_RIBS, 1);
        orderDetails.addOrder(Menu.CAESAR_SALAD, 2);
        orderDetails.addOrder(Menu.COKE_ZERO, 3);
        orderDetails.addOrder(Menu.TAPAS, 1);
        orderDetails.addOrder(Menu.CHRISTMAS_PASTA, 1);
        orderDetails.addOrder(Menu.ICE_CREAM, 4);
        assertDoesNotThrow(() -> OutputView.printTotalOrderPrice(orderDetails));
    }
}
