package christmas.service;

import static christmas.exception.ErrorMessage.INVALID_DATE;

import christmas.domain.OrderDetails;
import christmas.domain.constants.Menu;
import christmas.exception.ErrorMessage;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class Parser {
    public static final String DELIMITER_COMMA = ",";
    private static final String DELIMITER_HYPHEN = "-";
    private static final int FIRST_ELEMENT = 0;
    private static final int SECOND_ELEMENT = 1;

    public static LocalDate parseReservationDate(String reservationDay) {
        int reservationNumber = Parser.parseStringToInt(reservationDay);
        return Parser.parseIntToLocalDate(reservationNumber);
    }

    public static OrderDetails parseReservationOrder(String reservationOrder) {
        Validator.validateEndWithComma(reservationOrder);
        OrderDetails orderDetails = OrderDetails.create();
        String[] orderForms = reservationOrder.split(DELIMITER_COMMA);
        createOrderForm(orderForms, orderDetails);
        Validator.validateMaximumQuantity(orderDetails);
        Validator.validateMenuOnlyBeverage(orderDetails);
        return orderDetails;
    }

    private static void createOrderForm(String[] orderForms, OrderDetails orderDetails) {
        for (String orderForm : orderForms) {
            Validator.validateOrderPattern(orderForm);
            String[] order = orderForm.split(DELIMITER_HYPHEN);
            String menuName = order[FIRST_ELEMENT];
            int quantity = Parser.parseStringToInt(order[SECOND_ELEMENT]);
            Validator.validateMinimumQuantity(quantity);
            Validator.validateExistMenu(menuName);
            Menu menu = Parser.parseStringToMenu(menuName);
            Validator.validateDuplicateMenu(orderDetails, menu);
            orderDetails.addOrder(menu, quantity);
        }
    }

    public static Menu parseStringToMenu(String input) {
        return Arrays.stream(Menu.values()).filter(menu -> menu.getMenuName().equalsIgnoreCase(input)).findFirst()
                .orElse(null);
    }

    public static String parseMenuToString(Menu input) {
        return input.getMenuName();
    }

    public static int parseStringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw ErrorMessage.newIllegalArgumentException(INVALID_DATE);
        }
    }

    public static String parseIntToString(final int input) {
        return String.valueOf(input);
    }

    public static BigDecimal parseStringToBigDecimal(String input) {
        return new BigDecimal(input);
    }

    public static String parseBigDecimalToString(BigDecimal input) {
        return input.toString();
    }

    public static LocalDate parseStringToLocalDate(String input) {
        int intInput = Parser.parseStringToInt(input);
        EventMonth eventMonthInput = EventMonth.from(intInput);
        return eventMonthInput.getReservationDate();
    }

    public static String parseLocalDateToString(LocalDate input) {
        int intInput = Parser.parseLocalDateToInt(input);
        return Parser.parseIntToString(intInput);
    }

    public static BigDecimal parseIntToBigDecimal(final int input) {
        return new BigDecimal(input);
    }

    public static int parseBigDecimalToInt(BigDecimal input) {
        return input.intValue();
    }

    public static LocalDate parseIntToLocalDate(final int input) {
        EventMonth eventMonthInput = EventMonth.from(input);
        return eventMonthInput.getReservationDate();
    }

    public static int parseLocalDateToInt(LocalDate input) {
        return input.getDayOfMonth();
    }

    public static BigDecimal parseLocalDateToBigDecimal(LocalDate input) {
        int intInput = Parser.parseLocalDateToInt(input);
        return new BigDecimal(intInput);
    }

    public static LocalDate parseBigDecimalToLocalDate(BigDecimal input) {
        int intInput = Parser.parseBigDecimalToInt(input);
        EventMonth eventMonthInput = EventMonth.from(intInput);
        return eventMonthInput.getReservationDate();
    }
}
