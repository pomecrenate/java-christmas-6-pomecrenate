package christmas.service;

import static christmas.exception.ErrorMessage.INVALID_DATE;
import static christmas.exception.ErrorMessage.INVALID_ORDER;
import static christmas.exception.ErrorMessage.NOT_ONLY_BEVERAGE_ORDER;
import static christmas.exception.ErrorMessage.TOO_MANY_ORDER;
import static christmas.service.Parser.DELIMITER_COMMA;

import christmas.domain.OrderDetails;
import christmas.domain.constants.Menu;
import christmas.exception.ErrorMessage;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final int MINIMUM_EACH_ORDER = 1;
    private static final int MAXIMUM_TOTAL_ORDER = 20;
    private static final Pattern ORDER_FORMAT = Pattern.compile("([가-힣]+)-([0-9]+)");

    public static void validatePeriod(LocalDate reservationDate) {
        if (!EventMonth.isThisMonth(reservationDate)) {
            throw ErrorMessage.newIllegalArgumentException(INVALID_DATE);
        }
    }

    public static void validateExistMenu(String menu) {
        if (!Menu.hasMenu(menu)) {
            throw ErrorMessage.newIllegalArgumentException(INVALID_ORDER);
        }
    }

    public static void validateMinimumQuantity(int quantity) {
        if (quantity < MINIMUM_EACH_ORDER) {
            throw ErrorMessage.newIllegalArgumentException(INVALID_ORDER);
        }
    }

    public static void validateMaximumQuantity(OrderDetails orderDetails) {
        if (MAXIMUM_TOTAL_ORDER < orderDetails.getTotalQuantity()) {
            throw ErrorMessage.newIllegalArgumentException(TOO_MANY_ORDER);
        }
    }

    public static void validateOrderPattern(String order) {
        Matcher matcher = ORDER_FORMAT.matcher(order);
        if (!matcher.matches()) {
            throw ErrorMessage.newIllegalArgumentException(INVALID_ORDER);
        }
    }

    public static void validateDuplicateMenu(OrderDetails orderDetails, Menu menu) {
        if (orderDetails.hasMenu(menu)) {
            throw ErrorMessage.newIllegalArgumentException(INVALID_ORDER);
        }
    }

    public static void validateMenuOnlyBeverage(OrderDetails orderDetails) {
        if (orderDetails.hasOnlyBeverage()) {
            throw ErrorMessage.newIllegalArgumentException(NOT_ONLY_BEVERAGE_ORDER);
        }
    }

    public static void validateEndWithComma(String order) {
        if (Validator.isEndsWithComma(order)) {
            throw ErrorMessage.newIllegalArgumentException(INVALID_ORDER);
        }
    }

    private static boolean isEndsWithComma(String input) {
        return input.endsWith(DELIMITER_COMMA);
    }
}
