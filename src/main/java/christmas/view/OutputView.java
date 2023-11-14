package christmas.view;

import static christmas.view.constants.PrintMessage.INTRODUCE_MESSAGE;
import static christmas.view.constants.PrintMessage.NEW_LINE;
import static christmas.view.constants.PrintMessage.PRINT_BENEFIT;
import static christmas.view.constants.PrintMessage.PRINT_GIFT;
import static christmas.view.constants.PrintMessage.PRINT_MENU;
import static christmas.view.constants.PrintMessage.PRINT_NORMAL;
import static christmas.view.constants.PrintMessage.PRINT_PRICE;
import static christmas.view.constants.PrintMessage.REQUEST_RESERVATION_DAY;
import static christmas.view.constants.PrintMessage.REQUEST_RESERVATION_ORDER;
import static christmas.view.constants.PrintMessage.RESPONSE_BENEFIT_DETAILS;
import static christmas.view.constants.PrintMessage.RESPONSE_EVENT_BADGE;
import static christmas.view.constants.PrintMessage.RESPONSE_GIFT_MENU;
import static christmas.view.constants.PrintMessage.RESPONSE_ORDER_MENU;
import static christmas.view.constants.PrintMessage.RESPONSE_PREVIEW_ORDER_DETAILS;
import static christmas.view.constants.PrintMessage.RESPONSE_TOTAL_BENEFIT_PRICE;
import static christmas.view.constants.PrintMessage.RESPONSE_TOTAL_ORDER_PRICE;
import static christmas.view.constants.PrintMessage.RESPONSE_TOTAL_PAYMENT_PRICE;

import christmas.domain.Customer;
import christmas.domain.OrderDetails;
import christmas.domain.constants.Menu;
import christmas.service.EventChecker;
import christmas.view.constants.PrintMessage;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class OutputView {
    public static void printIntroduce() {
        PrintMessage.printNotice(NEW_LINE);
        PrintMessage.printNotice(INTRODUCE_MESSAGE);
    }

    public static void printReservationDay() {
        PrintMessage.printNotice(NEW_LINE);
        PrintMessage.printNotice(REQUEST_RESERVATION_DAY);
    }

    public static void printReservationOrder() {
        PrintMessage.printNotice(NEW_LINE);
        PrintMessage.printNotice(REQUEST_RESERVATION_ORDER);
    }

    public static void printPreviewOrderDetails(LocalDate reservationDate) {
        PrintMessage.printNotice(NEW_LINE);
        PrintMessage.printNotice(RESPONSE_PREVIEW_ORDER_DETAILS, reservationDate.getDayOfMonth());
    }

    public static void printOrderMenu(OrderDetails reservationOrder) {
        PrintMessage.printNotice(NEW_LINE);
        PrintMessage.printNotice(RESPONSE_ORDER_MENU);
        Map<Menu, Integer> order = reservationOrder.getMenuQuantities();
        for (Map.Entry<Menu, Integer> entry : order.entrySet()) {
            PrintMessage.printNotice(PRINT_MENU, entry.getKey().getMenuName(), entry.getValue());
        }
    }

    public static void printTotalOrderPrice(OrderDetails reservationOrder) {
        PrintMessage.printNotice(NEW_LINE);
        PrintMessage.printNotice(RESPONSE_TOTAL_ORDER_PRICE);
        PrintMessage.printNotice(PRINT_PRICE, reservationOrder.getTotalOrderPrice());
    }

    public static String printGiftMenu(OrderDetails reservationOrder) {
        PrintMessage.printNotice(NEW_LINE);
        PrintMessage.printNotice(RESPONSE_GIFT_MENU);
        String gift = EventChecker.checkChampagneEvent(reservationOrder);
        if (EventChecker.isChampagneEvent(gift)) {
            PrintMessage.printNotice(PRINT_GIFT, gift);
        }
        if (!EventChecker.isChampagneEvent(gift)) {
            PrintMessage.printNotice(PRINT_NORMAL, gift);
        }
        return gift;
    }

    public static void printBenefitDetails(LocalDate reservationDate, OrderDetails reservationOrder,
                                           String gift) {
        PrintMessage.printNotice(NEW_LINE);
        PrintMessage.printNotice(RESPONSE_BENEFIT_DETAILS);
        Map<String, BigDecimal> benefitDetails = EventChecker.makeBenefitDetails(reservationDate, reservationOrder,
                gift);
        if (benefitDetails.isEmpty()) {
            PrintMessage.printNotice(PRINT_NORMAL, gift);
            return;
        }
        for (Map.Entry<String, BigDecimal> entry : benefitDetails.entrySet()) {
            PrintMessage.printNotice(PRINT_BENEFIT, entry.getKey(), entry.getValue());
        }
    }

    public static BigDecimal printTotalBenefitPrice(LocalDate reservationDate, OrderDetails reservationOrder,
                                                    String gift) {
        PrintMessage.printNotice(NEW_LINE);
        PrintMessage.printNotice(RESPONSE_TOTAL_BENEFIT_PRICE);
        BigDecimal totalBenefitPrice = EventChecker.applyEventBenefit(reservationDate, reservationOrder, gift);
        PrintMessage.printNotice(PRINT_PRICE, totalBenefitPrice);
        return totalBenefitPrice;
    }

    public static void printTotalPaymentPrice(LocalDate reservationDate, OrderDetails reservationOrder,
                                              String gift) {
        PrintMessage.printNotice(NEW_LINE);
        PrintMessage.printNotice(RESPONSE_TOTAL_PAYMENT_PRICE);
        PrintMessage.printNotice(PRINT_PRICE, EventChecker.getPaymentPrice(reservationDate, reservationOrder, gift));
    }

    public static Customer printEventBadge(BigDecimal totalBenefitPrice) {
        PrintMessage.printNotice(NEW_LINE);
        PrintMessage.printNotice(RESPONSE_EVENT_BADGE);
        String eventBadge = EventChecker.checkBadgeEvent(totalBenefitPrice);
        PrintMessage.printNotice(PRINT_NORMAL, eventBadge);
        return Customer.create(eventBadge);
    }
}
