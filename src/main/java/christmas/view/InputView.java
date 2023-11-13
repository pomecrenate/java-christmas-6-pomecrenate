package christmas.view;

import static christmas.view.constants.PrintMessage.NEW_LINE;
import static christmas.view.constants.PrintMessage.REQUEST_RESERVATION_DAY;
import static christmas.view.constants.PrintMessage.REQUEST_RESERVATION_ORDER;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.OrderDetails;
import christmas.exception.ErrorMessage;
import christmas.service.Parser;
import christmas.service.Validator;
import christmas.view.constants.PrintMessage;
import java.time.LocalDate;

public class InputView {
    public static LocalDate readReservationDate() {
        LocalDate reservationDate;
        while (true) {
            try {
                PrintMessage.printNotice(NEW_LINE);
                PrintMessage.printNotice(REQUEST_RESERVATION_DAY);
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
                PrintMessage.printNotice(NEW_LINE);
                PrintMessage.printNotice(REQUEST_RESERVATION_ORDER);
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
