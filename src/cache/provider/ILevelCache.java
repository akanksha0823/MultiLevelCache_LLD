package cache.provider;

import cache.model.ReadResponse;
import cache.model.WriteResponse;

import java.util.List;

public interface ILevelCache<Key,Value> {
    WriteResponse set(Key key, Value value);

    ReadResponse<Value> get(Key key);

   List<Double> getUsages();
}
