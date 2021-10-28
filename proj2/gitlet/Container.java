package gitlet;
import java.util.Objects;
import java.util.Set;
import java.io.Serializable;
import java.util.HashMap;

public class Container implements Serializable {
    private HashMap<String, String> map;
    public Container() {
        map = new HashMap<>();
    }
    public HashMap<String, String> map() {
        return map;
    }
    public void addMapToContainer(HashMap<String, String> m) {
        map = Objects.requireNonNullElseGet(m, HashMap::new);
    }
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }
    public Set<String> keySet() {
        return map.keySet();
    }
    public void put(String key, String value) {
        map.put(key, value);
    }
    public String remove(String key) {
        return map.remove(key);
    }
    public String get(String key) {
        return map.get(key);
    }
    public void clear() {
        map.clear();
    }
    public boolean isEmpty() {
        return map.isEmpty();
    }
}
