import cache.model.LevelCacheData;
import cache.model.ReadResponse;
import cache.model.WriteResponse;
import cache.provider.CacheProvider;
import cache.factories.CacheFactory;
import cache.provider.DefaultLevelCache;
import cache.provider.NullEffectLevelCache;
import cache.service.CacheService;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        CacheProvider<String, String> c1= new CacheFactory().defaultCache(10);
        CacheProvider<String, String> c2 = new CacheFactory().defaultCache(20);

        LevelCacheData cl1 = new LevelCacheData(1, 3);
        LevelCacheData cl2 = new LevelCacheData(2, 4);

        DefaultLevelCache<String, String> l2Cache = new DefaultLevelCache<>(cl2, c2, new NullEffectLevelCache<>());
        DefaultLevelCache<String, String> l1Cache = new DefaultLevelCache<>(cl1, c1, l2Cache);

        CacheService<String, String> cacheService = new CacheService<>(l1Cache, 5);

        WriteResponse w1 = cacheService.set("k1", "v1");
        WriteResponse w2 = cacheService.set("k2", "v2");

        ReadResponse<String> r1 = cacheService.get("k2");

        c1.put("k1", null);

        ReadResponse<String> r1AfterRemovalFromL1 = cacheService.get("k1");
        System.out.println(r1AfterRemovalFromL1.getTotalTime());


    }
}