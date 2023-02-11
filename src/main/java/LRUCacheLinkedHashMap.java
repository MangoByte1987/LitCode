import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLinkedHashMap<K,V>{
    private Map<K,V> cache;
    public LRUCacheLinkedHashMap(int capacity){
        cache = new LinkedHashMap<K,V>(capacity,8f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    public V get (K key){
        return cache.getOrDefault(key,null);

    }

    public void put (K key,V val){
        cache.put(key,val);
    }
}
