package christmas.view.constants;

public enum PrintFormat {
    PRINT_MENU("%s %d개"),
    PRINT_PRICE("%s원"),
    PRINT_BENEFIT("%s: " + PRINT_PRICE.getFormat()),
    ;

    private final String format;

    PrintFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
