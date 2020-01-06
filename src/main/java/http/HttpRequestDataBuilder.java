package http;

/**
 * @author chengwei
 */
public interface HttpRequestDataBuilder extends HttpRequestData {

    /**
     * 获取响应状态码
     *
     */
    void setRequestURI(String uri);


}
