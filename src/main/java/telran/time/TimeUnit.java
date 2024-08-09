package telran.time;

public enum TimeUnit {
SECOND(1), MINUTE(60), HOUR(3600);
private int valueOfSeconds;
TimeUnit(int valuOfSeconds) {
    this.valueOfSeconds = valuOfSeconds;
}
public int getValueOfSeconds(){
    return valueOfSeconds;
}
public float between(TimePoint start, TimePoint end) {
    float startInSec = start.getAmount() * start.getTimeUnit().getValueOfSeconds();
    float endInSec = end.getAmount() * end.getTimeUnit().getValueOfSeconds();
    float diffInSec = endInSec - startInSec;
    return diffInSec / this.getValueOfSeconds();
}
}
