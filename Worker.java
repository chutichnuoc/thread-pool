package com.hung;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Worker extends Thread {

    private final int id;

    private boolean isStopped = false;

    private final BlockingQueue<Runnable> taskQueue;

    public Worker(int id) {
        this.id = id;
        this.taskQueue = new LinkedBlockingQueue<>();
    }

    public void enqueue(Runnable runnable) {
        taskQueue.add(runnable);
    }

    public void doStop() {
        this.isStopped = true;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(String.format("worker-%d", id));
        while (!isStopped) {
            Runnable runnable = taskQueue.poll();
            if (runnable != null) {
                runnable.run();
            }
        }
    }
}
