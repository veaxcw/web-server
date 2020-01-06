package http;

/**
 * @author chengwei
 */
public interface HttpResponseData extends HttpData {

    /**
     * 获取响应状态码
     *
     * @return
     */
    short getHttpStatus();


    /**
     * 获取响应信息
     *
     * @return
     */
    String getHttpMessage();


}
