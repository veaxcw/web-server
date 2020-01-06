package http;

/**
 * @author chengwei
 */
public interface HttpRequestData extends HttpData {

    /**
     * 获取响应状态码
     *
     * @return
     */
    String getRequestURI();


}
