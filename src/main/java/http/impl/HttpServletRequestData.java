package http.impl;

import http.AbstractHttpData;
import http.HttpRequestDataBuilder;


/**
 *
 * @author chengwei
 */
public class HttpServletRequestData extends AbstractHttpData implements HttpRequestDataBuilder {


    private String requestURI;


    @Override
    public void setRequestURI(String uri) {
        this.requestURI = uri;
    }

    @Override
    public String getRequestURI() {
        return this.requestURI;
    }

}
