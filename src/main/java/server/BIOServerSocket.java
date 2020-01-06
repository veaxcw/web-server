package server;

import config.ServerConfig;
import listener.ServerEventListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author chengwei
 */
public class BIOServerSocket extends AbstractServerSocket {

    public BIOServerSocket(ServerConfig config) {
        super(config);
        this.init();
    }

    private void init() {

        int port = super.serverConfig.getPort();
        String host = super.serverConfig.getHost();
        System.out.println("server is about to start at port:" + port );
        System.out.println("server is about to start at host:" + host );

        int count = 0;

        while (true) {
            try (ServerSocket server = new ServerSocket(port,50)) {
                Socket accept = server.accept();
                System.out.println( "No." +  ++count  + " connection coming ......");
                super.threadPoolExecutor.execute(new ServerEventListener(accept));
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }



    }

}
