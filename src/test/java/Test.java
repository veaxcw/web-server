import config.ServerConfig;
import org.dom4j.DocumentException;
import server.BIOServerSocket;

import java.net.UnknownHostException;

public class Test {
    public static void main(String[] args) {

        try {
            ServerConfig serverConfig = new ServerConfig();
            BIOServerSocket server = new BIOServerSocket(serverConfig);
        } catch (DocumentException | UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
