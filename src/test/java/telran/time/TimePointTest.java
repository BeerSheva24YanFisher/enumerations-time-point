package telran.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TimePointTest {

    @Test
    public void testConstructor() {
        TimePoint point = new TimePoint(5, TimeUnit.MINUTE);
        assertEquals(5, point.getAmount());
        assertEquals(TimeUnit.MINUTE, point.getTimeUnit());
    }

    @Test
    public void testConvert() {
        TimePoint point = new TimePoint(1, TimeUnit.HOUR);
        TimePoint convertedPoint = TimePoint.convert(point, TimeUnit.MINUTE);
        assertEquals(60, convertedPoint.getAmount());
        assertEquals(TimeUnit.MINUTE, convertedPoint.getTimeUnit());
    }

    @Test
    public void testWith() {
        TimePoint point = new TimePoint(5, TimeUnit.MINUTE);
        TimePointAdjuster adjuster = tp -> new TimePoint(tp.getAmount() + 10, tp.getTimeUnit());
        TimePoint adjustedPoint = point.with(adjuster);
        assertEquals(15, adjustedPoint.getAmount());
        assertEquals(TimeUnit.MINUTE, adjustedPoint.getTimeUnit());
    }

    @Test
    public void testEquals() {
        TimePoint point1 = new TimePoint(5, TimeUnit.MINUTE);
        TimePoint point2 = new TimePoint(5, TimeUnit.MINUTE);
        TimePoint point3 = new TimePoint(1, TimeUnit.HOUR);
        assertEquals(point1, point2);
        assertNotEquals(point1, point3);
    }

    @Test
    public void testCompareTo() {
        TimePoint point1 = new TimePoint(5, TimeUnit.MINUTE);
        TimePoint point2 = new TimePoint(1, TimeUnit.HOUR);
        TimePoint point3 = new TimePoint(5, TimeUnit.MINUTE);

        assertTrue(point1.compareTo(point2) < 0);
        assertTrue(point1.compareTo(point3) == 0);
        assertTrue(point2.compareTo(point1) > 0);
    }

}
