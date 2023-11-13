package christmas.view;

import static christmas.view.constants.PrintMessage.INTRODUCE_MESSAGE;
import static christmas.view.constants.PrintMessage.NEW_LINE;

import christmas.view.constants.PrintMessage;

public class OutputView {
    public static void printIntroduce() {
        PrintMessage.printNotice(NEW_LINE);
        PrintMessage.printNotice(INTRODUCE_MESSAGE);
    }

    public static void printOrder() {

    }
}
