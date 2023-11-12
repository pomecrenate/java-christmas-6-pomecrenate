package christmas.domain.constant;

import static christmas.domain.constant.MenuCategory.APPETIZER;
import static christmas.domain.constant.MenuCategory.BEVERAGE;
import static christmas.domain.constant.MenuCategory.DESSERT;
import static christmas.domain.constant.MenuCategory.MAIN;

import java.math.BigDecimal;

public enum Menu {
    CAESAR_SALAD(APPETIZER, "시저샐러드", new BigDecimal("8000")),
    MUSHROOM_SOUP(APPETIZER, "양송이수프", new BigDecimal("6000")),
    TAPAS(APPETIZER, "타파스", new BigDecimal("5500")),
    BBQ_RIBS(MAIN, "바비큐립", new BigDecimal("54000")),
    CHRISTMAS_PASTA(MAIN, "크리스마스파스타", new BigDecimal("25000")),
    T_BONE_STEAK(MAIN, "티본스테이크", new BigDecimal("55000")),
    SEAFOOD_PASTA(MAIN, "해산물파스타", new BigDecimal("35000")),
    ICE_CREAM(DESSERT, "아이스크림", new BigDecimal("5000")),
    CHOCOLATE_CAKE(DESSERT, "초코케이크", new BigDecimal("15000")),
    RED_WINE(BEVERAGE, "레드와인", new BigDecimal("60000")),
    CHAMPAGNE(BEVERAGE, "샴페인", new BigDecimal("25000")),
    COKE_ZERO(BEVERAGE, "제로콜라", new BigDecimal("3000")),
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
