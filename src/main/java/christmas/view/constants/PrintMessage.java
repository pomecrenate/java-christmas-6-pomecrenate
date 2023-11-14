package christmas.view.constants;

import christmas.service.Parser;
import java.math.BigDecimal;

public enum PrintMessage {
    NEW_LINE("\r"),
    INTRODUCE_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    REQUEST_RESERVATION_DAY("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    REQUEST_RESERVATION_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    RESPONSE_PREVIEW_ORDER_DETAILS("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    RESPONSE_ORDER_MENU("<주문 메뉴>"),
    RESPONSE_TOTAL_ORDER_PRICE("<할인 전 총주문 금액>"),
    RESPONSE_GIFT_MENU("<증정 메뉴>"),
    RESPONSE_BENEFIT_DETAILS("<혜택 내역>"),
    RESPONSE_TOTAL_BENEFIT_PRICE("<총혜택 금액>"),
    RESPONSE_TOTAL_PAYMENT_PRICE("<할인 후 예상 결제 금액>"),
    RESPONSE_EVENT_BADGE("<12월 이벤트 배지>"),
    PRINT_MENU("%s %d개"),
    PRINT_GIFT("%s 1개"),
    PRINT_NORMAL("%s"),
    PRINT_PRICE("%s원"),
    PRINT_BENEFIT("%s: " + PRINT_PRICE.getMessage()),
    ;
    private final String message;

    PrintMessage(String message) {
        this.message = message;
    }

    public static void printNotice(PrintMessage printMessage) {
        System.out.println(printMessage.getMessage());
    }

    public static void printNotice(PrintMessage printMessage, Object value) {
        System.out.printf((printMessage.getMessage()) + "\n", value);
    }

    public static void printNotice(PrintMessage printMessage, Object value1, Object value2) {
        System.out.printf((printMessage.getMessage()) + "\n", value1, value2);
    }

    public static void printNotice(PrintMessage printMessage, BigDecimal value) {
        System.out.printf((printMessage.getMessage()) + "\n", Parser.parsePrice(value));
    }

    public static void printNotice(PrintMessage printMessage, String value1, BigDecimal value2) {
        System.out.printf((printMessage.getMessage()) + "\n", value1, Parser.parsePrice(value2));
    }

    public String getMessage() {
        return message;
    }
}
