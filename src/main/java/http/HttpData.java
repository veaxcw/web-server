package http;

import java.util.Iterator;

/**
 * @author chengwei
 */
public interface HttpData {


    /**
     * 获取http 请求所有字段迭代器
     *
     * @return 获取http 请求所有字段迭代器
     */
    Iterator<String> getFieldsIterator();

    /**
     * 获取指定字段value
     *
     * @param field 字段名称
     *
     * @return
     */
    String getFieldValue(String field);


    /**
     * 返回是否包含指定字段
     *
     * @param field 字段
     * @return
     */
    Boolean hasField(String field);


    /**
     * 获取http 内容
     *
     * @return 内容
     */
    byte[] getContent();





}
