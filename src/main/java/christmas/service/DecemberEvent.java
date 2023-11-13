package christmas.service;

import static christmas.domain.constant.EventBadge.SANTA;
import static christmas.domain.constant.EventBadge.STAR;
import static christmas.domain.constant.EventBadge.TREE;
import static christmas.domain.constant.Menu.CHAMPAGNE;
import static christmas.service.EventMonth.THIS_YEAR;

import christmas.domain.constant.Menu;
import java.math.BigDecimal;

public class DecemberEvent {
    private static final BigDecimal DECEMBER_DISCOUNT = new BigDecimal(-THIS_YEAR);
    private static final BigDecimal SPECIAL_DAY_DISCOUNT = new BigDecimal("-1000");

    public static BigDecimal discountDecember(BigDecimal quantity) {
        return DECEMBER_DISCOUNT.multiply(quantity);
    }

    public static BigDecimal discountSpecialDay() {
        return SPECIAL_DAY_DISCOUNT;
    }

    public static Menu giftChampagne() {
        return CHAMPAGNE;
    }

    public static String giveSantaBadge() {
        return SANTA.getBadge();
    }

    public static String giveTreeBadge() {
        return TREE.getBadge();
    }

    public static String giveStarBadge() {
        return STAR.getBadge();
    }
}
