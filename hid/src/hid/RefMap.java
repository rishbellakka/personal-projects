package hid;

import java.io.Serializable;
import java.util.HashMap;
public class RefMap implements Serializable {
    public HashMap<String, String> map;
    public RefMap() {
        map = new HashMap<>();
    }
    public void put(String key, String val) {
        map.put(key, val);
    }
    public String get(String key) {
        return map.get(key);
    }
    public void remove(String key) {
        map.remove(key);
    }


}
