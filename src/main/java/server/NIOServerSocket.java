package server;

import config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chengwei
 */
public class NIOServerSocket extends AbstractServerSocket implements Runnable {


    private static final Logger LOGGER = LoggerFactory.getLogger(NIOServerSocket.class);

    private ServerSocketChannel serverChannel;

    private Selector selector;

    private InetSocketAddress address;

    private AtomicBoolean isClosed = new AtomicBoolean(false);

    private Thread selectorThread;


    public NIOServerSocket(ServerConfig serverConfig) throws UnknownHostException {
        super(serverConfig);
        this.address = new InetSocketAddress(InetAddress.getByName("localhost"),serverConfig.getPort());
    }

    @Override
    public void run() {

        if (!doEnsureSingleThread()) {
            return;
        }
        if (!doSetUpSelectorThreadAndServerThread()) {
            return;
        }

        LOGGER.info("server is started");
        LOGGER.info("port:{}",this.address.toString());

        try {
            int selectorTimeOut = 0;
            int shutdownCount = 5;


            while (!selectorThread.isInterrupted() && shutdownCount != 0) {
                if (isClosed.get()) {
                    selectorTimeOut = 5;
                }
                if (selector.select(selectorTimeOut) == 0 && isClosed.get()) {
                    shutdownCount--;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isAcceptable() && !doAccepted(key,iterator)) {
                        continue;
                    }
                    if (key.isReadable() && !doRead(key,iterator)) {
                        continue;
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private boolean doAccepted(SelectionKey key, Iterator<SelectionKey> iterator) {

        try {
            SocketChannel channel = serverChannel.accept();

            if (channel == null) {
                return false;
            }
            // 配置socket todo
            Socket socket = channel.socket();
            channel.configureBlocking(false);
            channel.register(selector,SelectionKey.OP_READ);
            iterator.remove();
            return true;
        } catch (IOException e) {
            LOGGER.info("unexpected error",e);
            return false;
        }


    }

    /**
     * 处理读请求
     *
     * @param key key
     * @param iterator iterator
     * @return
     */
    private boolean doRead(SelectionKey key, Iterator<SelectionKey> iterator) {



        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        StringBuilder socketData = new StringBuilder();
        SocketChannel channel =  (SocketChannel)key.channel();

        try {
            while (channel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                LOGGER.info("size:{}",byteBuffer.remaining());
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes);
                socketData.append(new String(bytes, StandardCharsets.UTF_8));
                byteBuffer.clear();
            }
            LOGGER.info("http:{}",socketData);
            key.cancel();
            iterator.remove();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;

    }

    /**
     * 确认web socket server 被启动了一次
     * @return  true 返回server 能被启动，false 表示 server 处于运行中
     */
    private boolean doEnsureSingleThread() {
        synchronized (this) {
            if (selectorThread != null) {
                throw new IllegalStateException(this.getClass().getName() + "仅能启动一次");
            }
            selectorThread = Thread.currentThread();
            if (isClosed.get()) {
                return false;
            }
        }
        return true;
    }

    private boolean doSetUpSelectorThreadAndServerThread() {
        selectorThread.setName("web-socket-selector" + selectorThread.getId());
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.bind(address);

            // 配置 server socket todo
            ServerSocket socket = serverChannel.socket();

            selector = Selector.open();

            // 注册select 启动
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            // todo onstart()

        } catch (IOException e) {
            LOGGER.warn("unexpected error",e);
            return false;
        }
        return true;
    }

}
