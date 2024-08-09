package telran.time;

public class TimePoint implements Comparable<TimePoint>{
    private float amount;
    private TimeUnit timeUnit;
    
    public TimePoint(float amount, TimeUnit timeUnit) {
        this.amount = amount;
        this.timeUnit = timeUnit;
    }

    @Override
    public int compareTo(TimePoint arg0) {
        return Float.compare(this.inSeconds(), arg0.inSeconds());     
    }

    public float getAmount(){
        return amount;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    @Override
    public boolean equals(Object obj) {
        TimePoint other = (TimePoint) obj;
        return this.compareTo(other) == 0;
    }

    public TimePoint convert(TimeUnit newTimeUnit) {
        float amountInNewUnit = this.inSeconds() / newTimeUnit.getValueOfSeconds();
        return new TimePoint(amountInNewUnit, newTimeUnit);
    }

    public TimePoint with(TimePointAdjuster adjuster) {
        TimePoint adjusted = adjuster.adjust(this);
        return this.getTimeUnit() != adjusted.getTimeUnit() ? adjusted.convert(this.getTimeUnit()) : adjusted;
    }

    private float inSeconds() {
        return amount * timeUnit.getValueOfSeconds();
    }

}