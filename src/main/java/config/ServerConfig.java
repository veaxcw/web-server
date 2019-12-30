package config;

import lombok.Data;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author chengwei
 */
@Data
public class ServerConfig {

    private Integer port;

    private String host;


    public ServerConfig() throws DocumentException {
        init();
    }


    private void init() throws DocumentException {

        SAXReader reader = new SAXReader();

        Document document = reader.read(this.getClass().getClassLoader().getResourceAsStream("server.xml"));

        Element root = document.getRootElement();

        this.port = Integer.valueOf(root.attribute("port").getValue());

        this.host = root.attributeValue("host");


    }




}
