package christmas.domain.constants;

public enum EventBadge {
    STAR("⭐"),
    TREE("🎄"),
    SANTA("🎅"),
    ;
    private final String badge;

    EventBadge(String badge) {
        this.badge = badge;
    }

    public String getBadge() {
        return badge;
    }
}
