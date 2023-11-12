package christmas.service;

import christmas.domain.EventMonth;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Parser {
    public static int parseStringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
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
        int intInput = parseStringToInt(input);
        EventMonth eventMonthInput = EventMonth.from(intInput);
        return eventMonthInput.getReservationDate();
    }

    public static String parseLocalDateToString(LocalDate input) {
        int intInput = parseLocalDateToInt(input);
        return parseIntToString(intInput);
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
        int intInput = parseLocalDateToInt(input);
        return new BigDecimal(intInput);
    }

    public static LocalDate parseBigDecimalToLocalDate(BigDecimal input) {
        int intInput = parseBigDecimalToInt(input);
        EventMonth eventMonthInput = EventMonth.from(intInput);
        return eventMonthInput.getReservationDate();
    }
}
