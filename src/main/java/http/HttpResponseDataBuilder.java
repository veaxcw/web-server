package http;

/**
 * @author chengwei
 */
public interface HttpResponseDataBuilder extends HttpResponseData {

    /**
     * 设置响应状态码
     *
     */
    void setHttpStatus(short status);


    /**
     * 设置响应信息
     *
     */
    void setHttpMessage(String message);


}
