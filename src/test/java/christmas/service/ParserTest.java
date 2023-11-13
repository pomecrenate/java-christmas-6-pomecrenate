package christmas.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParserTest {
    @DisplayName("int에서 LocalDate로 올바르게 변환되는지 확인")
    @ValueSource(ints = {1, 25, 31})
    @ParameterizedTest
    void preciseDiscount(int input) {
        LocalDate reservationDate = Parser.parseIntToLocalDate(input);
        System.out.println(reservationDate);
    }

    @Test
    void parseReservationOrder_ValidOrderDetails_NoExceptionThrown() {
        String validOrder = "샴페인-2,바비큐립-10";
        assertDoesNotThrow(() -> Parser.parseReservationOrder(validOrder));
    }

    @Test
    void parseReservationOrder_InvalidOrderDetails_ExceptionThrown() {
        String invalidOrder = "InvalidOrder";
        assertThrows(IllegalArgumentException.class, () -> Parser.parseReservationOrder(invalidOrder));
    }
}
