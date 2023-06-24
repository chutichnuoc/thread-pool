package com.hung;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    private final int numOfWorker;

    private final List<Worker> workers;

    private boolean isStopped = false;

    public ThreadPool(int numOfWorker) {
        this.numOfWorker = numOfWorker;
        workers = new ArrayList<>();
        initWorkers();
    }

    private void initWorkers() {
        for (int i = 0; i < numOfWorker; i++) {
            Worker worker = new Worker(i);
            worker.start();
            workers.add(worker);
        }
    }

    public void execute(String key, Runnable runnable) {
        if (isStopped) {
            return;
        }
        int hashed = Math.abs(key.hashCode() % numOfWorker);
        Worker worker = workers.get(hashed);
        worker.enqueue(runnable);
    }

    public void stop() {
        isStopped = true;
        for (Worker worker : workers) {
            worker.doStop();
        }
    }
}
