package cache.provider;

import cache.model.LevelCacheData;
import cache.model.ReadResponse;
import cache.model.WriteResponse;

import java.util.Collections;
import java.util.List;

public class DefaultLevelCache<Key,Value> implements ILevelCache<Key,Value>{
    private final CacheProvider<Key,Value> cacheProvider;
    private final LevelCacheData levelCacheData;
    private final ILevelCache<Key, Value> next;

    public DefaultLevelCache(LevelCacheData levelCacheData,CacheProvider<Key, Value> cacheProvider,  ILevelCache<Key, Value> next) {
        this.cacheProvider = cacheProvider;
        this.levelCacheData = levelCacheData;
        this.next = next;
    }

    @Override
    public WriteResponse set(Key key, Value value) {
        Double curTime=0.0;
        Value curLevelValue=cacheProvider.get(key);
        curTime+=levelCacheData.getReadTime();
        if(!value.equals(curLevelValue)){
            cacheProvider.put(key,value);
            curTime+=levelCacheData.getWriteTime();
        }

        curTime+=next.set(key,value).getTimeTaken();
        return new WriteResponse(curTime);
    }

    @Override
    public ReadResponse get(Key key) {
        Double curTime=0.0;
        Value curLevelValue = cacheProvider.get(key);
        curTime += levelCacheData.getReadTime();
        if (curLevelValue == null) {
            ReadResponse<Value> nextResponse = next.get(key);
            curTime += nextResponse.getTotalTime();
            curLevelValue = nextResponse.getValue();
            if (curLevelValue != null) {
                cacheProvider.put(key, curLevelValue);
                curTime += levelCacheData.getWriteTime();
            }
        }

        return new ReadResponse<>(curLevelValue, curTime);
    }

    @Override
    public List<Double> getUsages() {
        final List<Double> usages;
        if(next==null)
            usages= Collections.emptyList();
        else
            usages=next.getUsages();

        usages.add(0,cacheProvider.getCurrentUsage());
        return usages;
    }
}
