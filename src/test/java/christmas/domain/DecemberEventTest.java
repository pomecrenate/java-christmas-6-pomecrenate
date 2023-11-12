package christmas.domain;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DecemberEventTest {
    @DisplayName("12월 할인이 개수에 따라 바르게 적용되는지 확인")
    @ValueSource(strings = {"0", "1", "20", "21"})
    @ParameterizedTest
    void preciseDiscount(BigDecimal input) {
        System.out.println(DecemberEvent.discountDecember(input));
    }

    @DisplayName("샴페인 증정이 바르게 적용되는지 확인")
    @Test
    void preciseGift() {
        System.out.println(DecemberEvent.giftChampagne().getMenuName());
    }
}
