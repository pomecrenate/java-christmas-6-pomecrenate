package christmas.domain;

import static christmas.domain.constants.MenuCategory.BEVERAGE;

import christmas.domain.constants.Menu;
import christmas.domain.constants.MenuCategory;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class OrderDetails {
    private final Map<Menu, Integer> menuQuantities;

    private OrderDetails() {
        this.menuQuantities = new HashMap<>();
    }

    public static OrderDetails create() {
        return new OrderDetails();
    }

    public void addOrder(Menu menu, int quantity) {
        menuQuantities.put(menu, quantity);
    }

    // 중복 추가 가능
    public void addGift(Menu menu, int quantity) {
        menuQuantities.merge(menu, quantity, Integer::sum);
    }

    public Map<String, Integer> getOrder() {
        Map<String, Integer> order = new HashMap<>();
        menuQuantities.forEach((menu, quantity) -> order.put(menu.getMenuName(), quantity));
        return order;
    }

    public Map<MenuCategory, Integer> getOrderCategory() {
        Map<MenuCategory, Integer> orderCategory = new HashMap<>();
        menuQuantities.forEach((menu, quantity) -> orderCategory.put(menu.getMenuCategory(), quantity));
        return orderCategory;
    }

    public Map<String, Integer> getOrderCategoryName() {
        Map<String, Integer> orderCategoryName = new HashMap<>();
        menuQuantities.forEach(
                (menu, quantity) -> orderCategoryName.put(menu.getMenuCategory().getMenuCategoryName(), quantity));
        return orderCategoryName;
    }

    public BigDecimal getTotalOrder() {
        BigDecimal totalOrder = BigDecimal.ZERO;

        for (Map.Entry<Menu, Integer> entry : menuQuantities.entrySet()) {
            Menu menu = entry.getKey();
            BigDecimal menuPrice = menu.getMenuPrice();
            int quantity = entry.getValue();
            totalOrder = totalOrder.add(menuPrice.multiply(BigDecimal.valueOf(quantity)));
        }

        return totalOrder;
    }

    public int getTotalQuantity() {
        int quantity = 0;
        for (Integer value : menuQuantities.values()) {
            quantity += value;
        }
        return quantity;
    }

    public boolean hasMenu(Menu menu) {
        return menuQuantities.containsKey(menu);
    }

    public boolean hasOnlyBeverage() {
        return menuQuantities.keySet().stream()
                .allMatch(menu -> menu.getMenuCategory() == BEVERAGE);
    }
}
