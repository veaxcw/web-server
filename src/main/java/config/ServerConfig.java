package config;

import lombok.Data;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import utils.Constants;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author chengwei
 */
@Data
public class ServerConfig {

    private Integer port;

    private String host;

    private String protocol;


    public ServerConfig() throws DocumentException, UnknownHostException {
        init();
    }


    private void init() throws DocumentException, UnknownHostException {

        SAXReader reader = new SAXReader();

        Document document = reader.read(this.getClass().getClassLoader().getResourceAsStream("server.xml"));

        Element root = document.getRootElement();

        Element connector = root.element(Constants.CONNECTOR);

        this.port = Integer.valueOf(connector.attribute(Constants.PORT).getValue());

        this.protocol = connector.attribute(Constants.PROTOCOL).getValue();

        this.host = InetAddress.getLocalHost().getHostAddress();


    }




}
