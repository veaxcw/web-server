package server;

import config.ServerConfig;
import utils.ThreadPool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chengwei
 */
public abstract class AbstractServerSocket {

    ServerConfig serverConfig;

    ThreadPoolExecutor threadPoolExecutor;



    public AbstractServerSocket(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
        this.threadPoolExecutor = ThreadPool.getThreadPool();
    }


}
