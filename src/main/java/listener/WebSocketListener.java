package listener;

import http.HttpResponseDataBuilder;

import java.net.Socket;

/**
 * 服务端都必须 实现该接口
 * @author chengwei
 */
public interface WebSocketListener {

    /**
     *
     * 当收到Http 请求时
     *
     * @param conn 连接
     * @return
     */
    HttpResponseDataBuilder onHttpRequestReceived(Socket conn);



}
