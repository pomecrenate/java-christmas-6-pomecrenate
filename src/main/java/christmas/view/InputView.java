package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.OrderDetails;
import christmas.exception.ErrorMessage;
import christmas.service.Parser;
import christmas.service.Validator;
import java.time.LocalDate;

public class InputView {
    public static LocalDate readReservationDate() {
        LocalDate reservationDate;
        while (true) {
            try {
                OutputView.printReservationDay();
                String reservationDay = Console.readLine();
                reservationDate = Parser.parseReservationDate(reservationDay);
                Validator.validatePeriod(reservationDate);
                break;
            } catch (IllegalArgumentException exception) {
                ErrorMessage.printError(exception);
            }
        }
        return reservationDate;
    }

    public static OrderDetails readReservationOrder() {
        OrderDetails reservationOrder;
        while (true) {
            try {
                OutputView.printReservationOrder();
                String reservationOrderForm = Console.readLine();
                reservationOrder = Parser.parseReservationOrder(reservationOrderForm);
                break;
            } catch (IllegalArgumentException exception) {
                ErrorMessage.printError(exception);
            }
        }
        return reservationOrder;
    }
}
