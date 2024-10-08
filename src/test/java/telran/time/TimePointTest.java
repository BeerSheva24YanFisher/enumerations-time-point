package telran.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TimePointTest {

    private TimePoint timePointSeconds;
    private TimePoint timePointMinutes;
    private TimePoint timePointHours;
    private TimeUnit secondUnit;
    private TimeUnit minuteUnit;
    private TimeUnit hourUnit;

    @BeforeEach
    public void setUp() {
        secondUnit = TimeUnit.SECOND;
        minuteUnit = TimeUnit.MINUTE;
        hourUnit = TimeUnit.HOUR;

        timePointSeconds = new TimePoint(120, secondUnit);
        timePointMinutes = new TimePoint(2, minuteUnit);
        timePointHours = new TimePoint(1, hourUnit);
    }

    @Test
    public void testConvertToSeconds() {
        TimePoint result = timePointMinutes.convert(secondUnit);
        assertEquals(120, result.getAmount());
        assertEquals(secondUnit, result.getTimeUnit());
    }

    @Test
    public void testConvertToMinutes() {
        TimePoint result = timePointSeconds.convert(minuteUnit);
        assertEquals(2, result.getAmount());
        assertEquals(minuteUnit, result.getTimeUnit());
    }

    @Test
    public void testConvertToHours() {
        TimePoint result = timePointSeconds.convert(minuteUnit);
        assertEquals(2, result.getAmount());
        assertEquals(minuteUnit, result.getTimeUnit());
    }

    @Test
    public void testCompareTo() {
        TimePoint otherPoint = new TimePoint(1, hourUnit); // 1 час
        assertFalse(timePointSeconds.compareTo(otherPoint) > 0);
        assertTrue(timePointMinutes.compareTo(timePointSeconds) == 0);
        assertFalse(timePointHours.compareTo(timePointMinutes) < 0);
    }

    @Test
    public void testEquals() {
        TimePoint samePoint = new TimePoint(120, secondUnit);
        assertTrue(timePointSeconds.equals(samePoint));
        TimePoint differentPoint = new TimePoint(60, secondUnit);
        assertFalse(timePointSeconds.equals(differentPoint));
    }

    @Test
    public void testWithAdjuster() {
        TimePointAdjuster adjuster = new PlusTimePointAdjuster(10, secondUnit);
        TimePoint result = timePointSeconds.with(adjuster);
        assertEquals(130, result.getAmount());
        assertEquals(secondUnit, result.getTimeUnit());
    }

    @Test
    public void testWithAdjusterKeepsSameTimeUnit() {
        TimePoint original = new TimePoint(60, TimeUnit.SECOND);
        TimePointAdjuster adjuster = timePoint -> new TimePoint(timePoint.getAmount()/60, TimeUnit.MINUTE);
        TimePoint adjusted = original.with(adjuster).convert(secondUnit);
        assertEquals(TimeUnit.SECOND, adjusted.getTimeUnit());
        assertEquals(60, adjusted.getAmount());
    }


    @Test
    public void testBetween_SameUnits() {
        TimePoint start = new TimePoint(1, secondUnit);
        TimePoint end = new TimePoint(3, secondUnit);
        assertEquals(2.0, secondUnit.between(start, end), 0.001);

        start = new TimePoint(1, minuteUnit);
        end = new TimePoint(20, secondUnit);
        assertEquals(-40.0, secondUnit.between(start, end), 0.001);
    }
}