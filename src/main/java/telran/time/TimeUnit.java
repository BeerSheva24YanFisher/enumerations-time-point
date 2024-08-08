package telran.time;

public enum TimeUnit {
    MILLI(1), SECOND(1000), MINUTE(60_000), HOUR(3_600_000);

    private int valueOfMilli;

    TimeUnit(int valueOfMilli) {
        this.valueOfMilli = valueOfMilli;
    }

    public int getValueOfMilli() {
        return valueOfMilli;
    }

    public float between(TimePoint start, TimePoint end) {
        float startInMillis = start.getAmount() * start.getTimeUnit().getValueOfMilli();
        float endInMillis = end.getAmount() * end.getTimeUnit().getValueOfMilli();
        float differenceInMillis = endInMillis - startInMillis;
        return differenceInMillis / this.getValueOfMilli();
    }
}
