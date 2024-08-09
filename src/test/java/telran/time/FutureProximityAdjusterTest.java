package telran.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FutureProximityAdjusterTest {

    private FutureProximityAdjuster adjuster;
    private TimePoint[] timePoints;

    @BeforeEach
    public void setUp() {
        // Инициализация массива timePoints
        timePoints = new TimePoint[] {
            new TimePoint(0, TimeUnit.SECOND),
            new TimePoint(0, TimeUnit.SECOND),
            new TimePoint(1, TimeUnit.SECOND),
            new TimePoint(1, TimeUnit.SECOND),
            new TimePoint(5, TimeUnit.SECOND),
            new TimePoint(10, TimeUnit.SECOND),
            new TimePoint(20, TimeUnit.SECOND),
            new TimePoint(30, TimeUnit.SECOND)
        };
        adjuster = new FutureProximityAdjuster(timePoints);
    }

    @Test
    public void testAdjust_FutureTimePointFound() {
        TimePoint query = new TimePoint(7, TimeUnit.SECOND);
        TimePoint result = adjuster.adjust(query);
        assertNotNull(result);
        assertEquals(new TimePoint(10, TimeUnit.SECOND), result);
    }

    @Test
    public void testAdjust_FutureTimePointNotFound() {
        TimePoint query = new TimePoint(35, TimeUnit.SECOND);
        TimePoint result = adjuster.adjust(query);
        assertNull(result);
    }

    @Test
    public void testAdjust_NoFutureTimePoints() {
        TimePoint query = new TimePoint(0, TimeUnit.SECOND);
        TimePoint result = adjuster.adjust(query);
        assertNotNull(result);
        assertEquals(new TimePoint(1, TimeUnit.SECOND), result);
    }

    @Test
    public void testAdjust_ExactMatch() {
        TimePoint query = new TimePoint(20, TimeUnit.SECOND);
        TimePoint result = adjuster.adjust(query);
        assertNotNull(result);
        assertEquals(new TimePoint(30, TimeUnit.SECOND), result);
    }
}