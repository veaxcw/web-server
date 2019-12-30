package servlet;

import config.ServerConfig;
import listener.ServerEventListener;
import utils.ThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chengwei
 */
public class ServerSocketLaunch {

    private ServerConfig serverConfig;

    private ThreadPoolExecutor threadPoolExecutor;


    public ServerSocketLaunch(ServerConfig config) {
        this.serverConfig = config;
        this.threadPoolExecutor = ThreadPool.getThreadPool();
        this.init();
    }

    private void init() {

        int port = this.serverConfig.getPort();
        String host = this.serverConfig.getHost();
        System.out.println("server is about to start at port:" + port );
        System.out.println("server is about to start at host:" + host );

        int count = 0;

        while (true) {
            try (ServerSocket server = new ServerSocket(port,50)) {
                Socket accept = server.accept();
                System.out.println( "No." +  ++count  + " connection coming ......");
                this.threadPoolExecutor.execute(new ServerEventListener(accept));
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }



    }

}
