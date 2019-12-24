package com.example;

import com.example.firstspringbootdemo.controller.HelloWorldController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicExample6 {
    private static Logger log = LoggerFactory.getLogger(AtomicExample6.class);
    // 总的请求个数
    public static final int requestTotal = 10000;
    // 同一时刻最大的并发线程的个数
    public static final int concurrentThreadNum = 20;
    private static AtomicBoolean isHappened = new AtomicBoolean(false);
    private static Boolean isHappened_ = false;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(concurrentThreadNum);
        CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
        Semaphore semaphore = new Semaphore(concurrentThreadNum);
        for (int i = 0; i< requestTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("请求完成");
    }

    private static void test() {
        if (isHappened.compareAndSet(false, true)) {  // 这段代码可以保证执行执行一次
            log.info("happen 1");
        }
        synchronized (AtomicExample6.class){
            if (isHappened_ == false) {
                isHappened_ = true;
                log.info("happen 1");

            }
        }
    }

}
