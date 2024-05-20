package cache.factories;

import cache.provider.CacheProvider;
import cache.policies.LRUEvictionPolicy;
import cache.storage.HashMapBasedStorage;

public class CacheFactory<Key,Value> {
    public CacheProvider<Key,Value> defaultCache(final int capacity){
       return new CacheProvider<Key,Value>(new LRUEvictionPolicy<Key>(),
                           new HashMapBasedStorage<Key,Value>(capacity));
    }
}
