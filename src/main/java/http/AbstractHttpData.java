package http;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * @author chengwei
 */
public abstract class AbstractHttpData implements HttpDataBuilder  {

    private byte[] content;

    private TreeMap<String,String> map;

    public AbstractHttpData() {
        this.map = new TreeMap<>();
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }


    @Override
    public void put(String filed, String value) {
        if (null == map) {
            map = new TreeMap<>();
        }
        map.put(filed,value);
    }

    @Override
    public Iterator<String> getFieldsIterator() {
        // 返回只读的set
        return Collections.unmodifiableSet(map.keySet()).iterator();
    }

    @Override
    public String getFieldValue(String field) {
        return map.get(field);
    }

    @Override
    public Boolean hasField(String field) {
        return map.containsKey(field);
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }
}
