package christmas.service;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventCheckerTest {
    @DisplayName("크리스마스디데이할인이 날짜에 따라 바르게 적용되는지 확인")
    @ValueSource(ints = {1, 25, 31})
    @ParameterizedTest
    void preciseDiscount(int input) {
        LocalDate reservationDate = Parser.parseIntToLocalDate(input);
        System.out.println(EventChecker.holdEvent(reservationDate));
    }
}
