package christmas.domain;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import java.time.LocalDate;

public class EventMonth {
    public static final int THIS_YEAR = 2023;
    private static final int THIS_MONTH = 12;
    private static final int FIRST_DAY = 1;
    private static final LocalDate EVENT_MONTH = LocalDate.of(THIS_YEAR, THIS_MONTH, FIRST_DAY);
    public static final LocalDate FIRST_DATE = EVENT_MONTH.withDayOfMonth(FIRST_DAY);
    private static final int LAST_DAY = EVENT_MONTH.lengthOfMonth();
    public static final LocalDate LAST_DATE = EVENT_MONTH.withDayOfMonth(LAST_DAY);
    private static final LocalDate CHRISTMAS = LocalDate.of(THIS_YEAR, 12, 25);
    private final int reservationDay;

    private EventMonth(final int reservationDay) {
        this.reservationDay = reservationDay;
    }

    public static EventMonth from(final int reservationDay) {
        return new EventMonth(reservationDay);
    }

    public static boolean isDDay(LocalDate reservationDate) {
        return reservationDate.isAfter(EVENT_MONTH.minusDays(FIRST_DAY)) &&
                reservationDate.isBefore(CHRISTMAS.plusDays(FIRST_DAY));
    }

    public static boolean isThisMonth(LocalDate reservationDate) {
        return reservationDate.isAfter(EVENT_MONTH.minusDays(FIRST_DAY)) &&
                reservationDate.isBefore(LAST_DATE.plusDays(FIRST_DAY));
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

    public LocalDate getReservationDate() {
        return LocalDate.of(THIS_YEAR, THIS_MONTH, reservationDay);
    }
}
