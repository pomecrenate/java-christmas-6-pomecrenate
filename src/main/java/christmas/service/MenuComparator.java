package christmas.service;

import christmas.domain.constants.Menu;
import java.util.Comparator;

public class MenuComparator implements Comparator<Menu> {
    @Override
    public int compare(Menu menu1, Menu menu2) {
        int categoryComparison = Integer.compare(menu1.getMenuCategory().getMenuCategoryIndex(), menu2.getMenuCategory()
                .getMenuCategoryIndex());
        if (categoryComparison == 0) {
            return menu1.getMenuName().compareTo(menu2.getMenuName());
        }
        return categoryComparison;
    }
}
