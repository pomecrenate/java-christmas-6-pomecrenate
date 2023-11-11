package christmas.domain;

import java.math.BigDecimal;

public enum Menu {
    CAESAR_SALAD(MenuCategory.APPETIZER, "시저샐러드", new BigDecimal("8000")),
    MUSHROOM_SOUP(MenuCategory.APPETIZER, "양송이수프", new BigDecimal("6000")),
    TAPAS(MenuCategory.APPETIZER, "타파스", new BigDecimal("5500")),
    BBQ_RIBS(MenuCategory.MAIN, "바비큐립", new BigDecimal("54000")),
    CHRISTMAS_PASTA(MenuCategory.MAIN, "크리스마스파스타", new BigDecimal("25000")),
    T_BONE_STEAK(MenuCategory.MAIN, "티본스테이크", new BigDecimal("55000")),
    SEAFOOD_PASTA(MenuCategory.MAIN, "해산물파스타", new BigDecimal("35000")),
    ICE_CREAM(MenuCategory.DESSERT, "아이스크림", new BigDecimal("5000")),
    CHOCOLATE_CAKE(MenuCategory.DESSERT, "초코케이크", new BigDecimal("15000")),
    RED_WINE(MenuCategory.BEVERAGE, "레드와인", new BigDecimal("60000")),
    CHAMPAGNE(MenuCategory.BEVERAGE, "샴페인", new BigDecimal("25000")),
    COKE_ZERO(MenuCategory.BEVERAGE, "제로콜라", new BigDecimal("3000")),
    ;
    private final MenuCategory menuCategory;
    private final String menuName;
    private final BigDecimal menuPrice;

    Menu(MenuCategory menuCategory, String menuName, BigDecimal menuPrice) {
        this.menuCategory = menuCategory;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public String getMenuName() {
        return menuName;
    }

    public BigDecimal getMenuPrice() {
        return menuPrice;
    }
}
