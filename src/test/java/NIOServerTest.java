import config.ServerConfig;
import org.dom4j.DocumentException;
import org.junit.Test;
import server.NIOServerSocket;
import utils.ThreadPool;

import java.net.UnknownHostException;
import java.util.concurrent.ThreadPoolExecutor;

public class NIOServerTest {

    @Test
    public void doStart() {
        try {
            ThreadPool.getThreadPool().execute(new NIOServerSocket(new ServerConfig()));
        } catch (DocumentException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ThreadPoolExecutor threadPool = ThreadPool.getThreadPool();
            NIOServerSocket nioServerSocket = new NIOServerSocket(new ServerConfig());
            threadPool.execute(nioServerSocket);
        } catch (DocumentException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
