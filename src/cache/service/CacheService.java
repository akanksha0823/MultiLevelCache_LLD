package cache.service;

import cache.model.ReadResponse;
import cache.model.StatsResponse;
import cache.model.WriteResponse;
import cache.provider.ILevelCache;

import java.util.ArrayList;
import java.util.List;

public class CacheService<Key,Value>{
    private final ILevelCache<Key, Value> multiLevelCache;
    private final List<Double> lastReadTimes;
    private final List<Double> lastWriteTimes;
    private final int lastCount;

    public CacheService(ILevelCache<Key, Value> multiLevelCache, int lastCount) {
        this.multiLevelCache = multiLevelCache;
        this.lastReadTimes = new ArrayList<>(lastCount);
        this.lastWriteTimes = new ArrayList<>(lastCount);
        this.lastCount = lastCount;
    }

    public WriteResponse set( final Key key,  final Value value) {
        final WriteResponse writeResponse = multiLevelCache.set(key, value);
        addTimes(lastWriteTimes, writeResponse.getTimeTaken());
        return writeResponse;
    }

    private void addTimes(List<Double> times, Double time) {
        if(times.size()==this.lastCount) times.remove(0);
        times.add(time);
    }

    public ReadResponse<Value> get( final Key key) {
        final ReadResponse<Value> readResponse = multiLevelCache.get(key);
        addTimes(lastReadTimes, readResponse.getTotalTime());
        return readResponse;
    }

    public StatsResponse stats() {
        return new StatsResponse(getAvgReadTime(), getAvgWriteTime(), multiLevelCache.getUsages());
    }

    private Double getAvgReadTime() {
        return getSum(lastReadTimes)/lastReadTimes.size();
    }

    private Double getAvgWriteTime() {
        return getSum(lastWriteTimes)/lastWriteTimes.size();
    }

    private Double getSum(List<Double> times) {
        Double sum = 0.0;
        for (Double time: times) {
            sum += time;
        }
        return sum;
    }
}
