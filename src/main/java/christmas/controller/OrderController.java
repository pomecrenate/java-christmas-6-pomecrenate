package christmas.controller;

import christmas.domain.OrderDetails;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;

public class OrderController {
    public static void open() {
        OutputView.printIntroduce();

        LocalDate reservationDate = InputView.readReservationDate();
        OrderDetails reservationOrder = InputView.readReservationOrder();


    }
}
