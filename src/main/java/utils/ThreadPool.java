package utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 手动创建线程池
 *
 * @author chengw
 */
public class ThreadPool {

    public static ThreadPoolExecutor getThreadPool() {
        // 线程池大小
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        // 线程池最大大小
        int maxPoolSize = 100;
        // 线程活动保持时间
        long keepAliveTime = 10000;
        // 线程活动保持时间
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("socket-connection-%d").build();

        return new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                namedThreadFactory);
    }

}
