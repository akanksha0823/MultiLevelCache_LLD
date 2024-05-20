package cache.model;

import java.util.List;

public class StatsResponse {
    private final Double avgReadTime;
    private final Double avgWriteTime;
    private final List<Double> usages;

    public StatsResponse(Double avgReadTime, Double avgWriteTime, List<Double> usages) {
        this.avgReadTime = avgReadTime;
        this.avgWriteTime = avgWriteTime;
        this.usages = usages;
    }

    public Double getAvgReadTime() {
        return avgReadTime;
    }

    public Double getAvgWriteTime() {
        return avgWriteTime;
    }

    public List<Double> getUsages() {
        return usages;
    }
}
