package christmas.exception;

public enum ErrorMessage {
    ERROR("[ERROR] "),
    INVALID_DATE(ERROR.getMessage() + "유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER(ERROR.getMessage() + "유효하지 않은 주문입니다. 다시 입력해 주세요."),
    TOO_MANY_ORDER(ERROR.getMessage() + "최대 20개까지 주문할 수 있습니다. 다시 입력해 주세요."),
    NOT_ONLY_BEVERAGE_ORDER(ERROR.getMessage() + "음료만 주문할 수 없습니다. 다시 입력해 주세요."),
    ;
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public static void printError(IllegalArgumentException errorMessage) {
        System.out.println(errorMessage.getMessage());
    }

    public static IllegalArgumentException newIllegalArgumentException(ErrorMessage errorMessage) {
        return new IllegalArgumentException(errorMessage.getMessage());
    }

    public String getMessage() {
        return message;
    }
}
