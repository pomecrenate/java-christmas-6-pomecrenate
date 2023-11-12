package christmas.domain;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDate;

public class EventMonth {
    private static final int THIS_YEAR = 2023;
    private static final int THIS_MONTH = 12;
    private static final LocalDate EVENT_MONTH = LocalDate.of(THIS_YEAR, THIS_MONTH, 1);
    public static final LocalDate FIRST_DAY = EVENT_MONTH.with(firstDayOfMonth());
    public static final LocalDate LAST_DAY = EVENT_MONTH.with(lastDayOfMonth());
    private static final LocalDate CHRISTMAS = LocalDate.of(THIS_YEAR, 12, 25);
    private int reservationDay = 1;
    private final LocalDate reservationDate = LocalDate.of(THIS_YEAR, THIS_MONTH, reservationDay);

    private EventMonth(final int reservationDay) {
        this.reservationDay = reservationDay;
    }

    public static EventMonth from(final int reservationDay) {
        return new EventMonth(reservationDay);
    }

    public static boolean isWeekday(LocalDate reservationDate) {
        return isSunday(reservationDate) || isMonday(reservationDate) || isTuesday(reservationDate) ||
                isWednesday(reservationDate) || isThursday(reservationDate);
    }

    public static boolean isWeekend(LocalDate reservationDate) {
        return isFriday(reservationDate) || isSaturday(reservationDate);
    }

    public static boolean isSpecialDay(LocalDate reservationDate) {
        return isSunday(reservationDate) || isChristmas(reservationDate);
    }

    private static boolean isMonday(LocalDate reservationDate) {
        return reservationDate.getDayOfWeek() == MONDAY;
    }

    private static boolean isTuesday(LocalDate reservationDate) {
        return reservationDate.getDayOfWeek() == TUESDAY;
    }

    private static boolean isWednesday(LocalDate reservationDate) {
        return reservationDate.getDayOfWeek() == WEDNESDAY;
    }

    private static boolean isThursday(LocalDate reservationDate) {
        return reservationDate.getDayOfWeek() == THURSDAY;
    }

    private static boolean isFriday(LocalDate reservationDate) {
        return reservationDate.getDayOfWeek() == FRIDAY;
    }

    private static boolean isSaturday(LocalDate reservationDate) {
        return reservationDate.getDayOfWeek() == SATURDAY;
    }

    private static boolean isSunday(LocalDate reservationDate) {
        return reservationDate.getDayOfWeek() == SUNDAY;
    }

    private static boolean isChristmas(LocalDate reservationDate) {
        return reservationDate == CHRISTMAS;
    }
}
