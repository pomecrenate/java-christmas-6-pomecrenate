package christmas.service;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
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
}
