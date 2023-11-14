package christmas.domain;

public class Customer {
    private final String eventBadge;
    private int id = 0;

    private Customer(String eventBadge) {
        ++id;
        this.eventBadge = eventBadge;
    }

    public static Customer create(String eventBadge) {
        return new Customer(eventBadge);
    }
}
