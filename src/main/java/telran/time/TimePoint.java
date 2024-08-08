package telran.time;

public class TimePoint implements Comparable<TimePoint>{
    private final float amount;
    private final TimeUnit timeUnit;

    public TimePoint(float amount, TimeUnit timeUnit) {
        this.amount = amount;
        this.timeUnit = timeUnit;
    }

    public float getAmount() {
        return amount;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    private float convertToMilli() {
        return amount * timeUnit.getValueOfMilli();
    }

    @Override
    public int compareTo(TimePoint o) {
        return Float.compare(this.convertToMilli(), o.convertToMilli());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TimePoint other = (TimePoint) obj;
        return convertToMilli() == other.convertToMilli();
    }

    @Override
    public int hashCode() {
        return Float.hashCode(convertToMilli());
    }

    public static TimePoint convert(TimePoint point, TimeUnit newUnit) {
        float milliAmount = point.convertToMilli();
        float newAmount = milliAmount / newUnit.getValueOfMilli();
        return new TimePoint(newAmount, newUnit);
    }

    public TimePoint with(TimePointAdjuster adjuster) {
        return adjuster.adjust(this);
    }

}
