package cache.model;

public class ReadResponse<Value> {
    Value value;
    Double totalTime;

    public ReadResponse(Value value, Double totalTime) {
        this.value = value;
        this.totalTime = totalTime;
    }

    public Value getValue() {
        return value;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    @Override
    public String toString() {
        return "ReadResponse{" +
                "value=" + value +
                ", totalTime=" + totalTime +
                '}';
    }
}
