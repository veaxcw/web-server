package http.impl;

import http.AbstractHttpData;
import http.HttpRequestDataBuilder;
import http.HttpResponseDataBuilder;


/**
 *
 * @author chengwei
 */
public class HttpServletResponseData extends AbstractHttpData implements HttpResponseDataBuilder {


    private short status;

    private String message;


    @Override
    public void setHttpStatus(short status) {
        this.status = status;
    }

    @Override
    public void setHttpMessage(String message) {
        this.message = message;
    }

    @Override
    public short getHttpStatus() {
        return this.status;
    }

    @Override
    public String getHttpMessage() {
        return this.message;
    }
}
