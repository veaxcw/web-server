import config.ServerConfig;
import org.dom4j.DocumentException;
import servlet.ServerSocketLaunch;

public class Test {
    public static void main(String[] args) {

        try {
            ServerConfig serverConfig = new ServerConfig();
            ServerSocketLaunch server = new ServerSocketLaunch(serverConfig);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
}
