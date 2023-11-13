package christmas.domain.constants;

public enum MenuCategory {
    APPETIZER(1, "애피타이저"),
    MAIN(2, "메인"),
    DESSERT(3, "디저트"),
    BEVERAGE(4, "음료"),
    ;
    private final int menuCategoryIndex;
    private final String menuCategoryName;

    MenuCategory(int menuCategoryIndex, String menuCategoryName) {
        this.menuCategoryIndex = menuCategoryIndex;
        this.menuCategoryName = menuCategoryName;
    }

    public int getMenuCategoryIndex() {
        return menuCategoryIndex;
    }

    public String getMenuCategoryName() {
        return menuCategoryName;
    }
}
