package telran.time;

import java.util.Arrays;

public class FutureProximityAdjuster implements TimePointAdjuster {
    TimePoint[] timePoints;

    public FutureProximityAdjuster(TimePoint[] timePoints) {
        this.timePoints = Arrays.copyOf(timePoints, timePoints.length);
        Arrays.sort(this.timePoints);
    }

    @Override
    public TimePoint adjust(TimePoint timePoint) {
        int start = 0;
        int finish = timePoints.length-1;
        int resultIndex = -1;
        while (start <= finish) {
            int middle = start + (finish - start) / 2;
            if (timePoints[middle].compareTo(timePoint) > 0) {
                resultIndex = middle;
                finish = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return resultIndex != -1  ? timePoints[resultIndex].convert(timePoint.getTimeUnit()) : null;
    }
}