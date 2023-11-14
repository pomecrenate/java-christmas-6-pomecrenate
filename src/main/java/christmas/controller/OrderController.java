package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Customer;
import christmas.domain.OrderDetails;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderController {
    public static void open() {
        OutputView.printIntroduce();

        LocalDate reservationDate = InputView.readReservationDate();
        OrderDetails reservationOrder = InputView.readReservationOrder();

        printOrderDetails(reservationDate, reservationOrder);

        Console.close();
    }

    private static void printOrderDetails(LocalDate reservationDate, OrderDetails reservationOrder) {
        OutputView.printPreviewOrderDetails(reservationDate);
        OutputView.printOrderMenu(reservationOrder);
        OutputView.printTotalOrderPrice(reservationOrder);
        String gift = OutputView.printGiftMenu(reservationOrder);
        OutputView.printBenefitDetails(reservationDate, reservationOrder, gift);
        BigDecimal totalBenefitPrice = OutputView.printTotalBenefitPrice(reservationDate, reservationOrder, gift);
        OutputView.printTotalPaymentPrice(reservationDate, reservationOrder, gift);
        // 새해 이벤트를 위해 고객 정보 저장
        Customer customer = OutputView.printEventBadge(totalBenefitPrice);
    }
}
