package christmas.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import christmas.domain.OrderDetails;
import christmas.domain.constants.Menu;
import christmas.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class ValidatorTest {
    @DisplayName("존재하는 메뉴면 예외 발생하지 않음")
    @Test
    void validateExistMenu_ExistingMenu_NoExceptionThrown() {
        String existingMenu = "레드와인";
        assertDoesNotThrow(() -> Validator.validateExistMenu(existingMenu));
    }

    @DisplayName("존재하지 않는 메뉴면 예외 발생함")
    @Test
    void validateExistMenu_NonExistingMenu_ExceptionThrown() {
        String nonExistingMenu = "NON_EXISTING_MENU";
        Executable executable = () -> Validator.validateExistMenu(nonExistingMenu);
        assertThrows(IllegalArgumentException.class, executable, ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("개수가 1 이상이면 예외 발생하지 않음")
    @Test
    void validateMinimumQuantity_ValidQuantity_NoExceptionThrown() {
        int validQuantity = 1;
        assertDoesNotThrow(() -> Validator.validateMinimumQuantity(validQuantity));
    }

    @DisplayName("개수가 1 미만이면 예외 발생함")
    @Test
    void validateMinimumQuantity_InvalidQuantity_ExceptionThrown() {
        int invalidQuantity = 0;
        Executable executable = () -> Validator.validateMinimumQuantity(invalidQuantity);
        assertThrows(IllegalArgumentException.class, executable, ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("총 개수가 20 이하면 예외 발생하지 않음")
    @Test
    void validateMaximumQuantity_ValidOrderDetails_NoExceptionThrown() {
        OrderDetails validOrderDetails = mock(OrderDetails.class);
        when(validOrderDetails.getTotalQuantity()).thenReturn(20);
        assertDoesNotThrow(() -> Validator.validateMaximumQuantity(validOrderDetails));
    }

    @DisplayName("총 개수가 20개 초과면 예외 발생함")
    @Test
    void validateMaximumQuantity_InvalidOrderDetails_ExceptionThrown() {
        OrderDetails invalidOrderDetails = mock(OrderDetails.class);
        when(invalidOrderDetails.getTotalQuantity()).thenReturn(21);
        Executable executable = () -> Validator.validateMaximumQuantity(invalidOrderDetails);
        assertThrows(IllegalArgumentException.class, executable, ErrorMessage.TOO_MANY_ORDER.getMessage());
    }

    @DisplayName("패턴에 맞으면 예외 발생하지 않음")
    @Test
    void validateOrderPattern_ValidOrder_NoExceptionThrown() {
        String validOrder = "크리스마스파스타-2";
        assertDoesNotThrow(() -> Validator.validateOrderPattern(validOrder));
    }

    @DisplayName("패턴에 맞지 않으면 예외 발생함")
    @Test
    void validateOrderPattern_InvalidOrder_ExceptionThrown() {
        String invalidOrder = "크리스마스파스타-2 ";
        Executable executable = () -> Validator.validateOrderPattern(invalidOrder);
        assertThrows(IllegalArgumentException.class, executable, ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("중복 메뉴가 아니면 예외 발생하지 않음")
    @Test
    void validateDuplicateMenu_NonDuplicateMenu_NoExceptionThrown() {
        OrderDetails orderDetails = mock(OrderDetails.class);
        Menu nonDuplicateMenu = Menu.CAESAR_SALAD;
        when(orderDetails.hasMenu(nonDuplicateMenu)).thenReturn(false);
        assertDoesNotThrow(() -> Validator.validateDuplicateMenu(orderDetails, nonDuplicateMenu));
    }

    @DisplayName("중복 메뉴면 예외 발생함")
    @Test
    void validateDuplicateMenu_DuplicateMenu_ExceptionThrown() {
        OrderDetails orderDetails = mock(OrderDetails.class);
        Menu duplicateMenu = Menu.CAESAR_SALAD;
        when(orderDetails.hasMenu(duplicateMenu)).thenReturn(true);
        Executable executable = () -> Validator.validateDuplicateMenu(orderDetails, duplicateMenu);
        assertThrows(IllegalArgumentException.class, executable, ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("음료만 있지 않으면 예외 발생하지 않음")
    @Test
    void validateMenuOnlyBeverage_OnlyBeverage_NoExceptionThrown() {
        OrderDetails orderDetails = OrderDetails.create();
        orderDetails.addOrder(Menu.CHAMPAGNE, 3);
        orderDetails.addOrder(Menu.BBQ_RIBS, 1);
        assertDoesNotThrow(() -> Validator.validateMenuOnlyBeverage(orderDetails));
    }

    @DisplayName("음료만 있으면 예외 발생함")
    @Test
    void validateMenuOnlyBeverage_NotOnlyBeverage_ExceptionThrown() {
        OrderDetails orderDetails = OrderDetails.create();
        orderDetails.addOrder(Menu.CHAMPAGNE, 3);
        Executable executable = () -> Validator.validateMenuOnlyBeverage(orderDetails);
        assertThrows(IllegalArgumentException.class, executable, ErrorMessage.NOT_ONLY_BEVERAGE_ORDER.getMessage());
    }
}
