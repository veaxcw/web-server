package http;

/**
 * @author chengwei
 */
public interface HttpDataBuilder extends HttpData {

    /**
     * Http 内容
     *
     * @param content Http 内容
     */
    void setContent(byte[] content);


    /**
     * 设置 http 字段和值
     *
     * @param filed  字段
     * @param value 值
     */
    void put(String filed,String value);


}
