package com.hung;

public class Main {

    private static final ThreadPool threadPool = new ThreadPool(10);

    // tasks with same key will always be executed in the same thread, which means they will need to wait for each other
    // meanwhile tasks with difference keys can be executed at the same time
    // this can ensure that same key go to same thread, but can not ensure difference keys go to difference threads
    // that depends on the hashing algorithm and the key
    public static void main(String[] args) throws InterruptedException {
        execute("le");
        execute("duy");
        execute("hung");
        execute("le");
        execute("duy");
        execute("hung");
        Thread.sleep(5_000);
        stop();
    }

    private static void execute(String key) {
        threadPool.execute(key, () -> System.out.println("key " + key + " running in thread " + Thread.currentThread().getName()));
    }

    private static void stop() {
        threadPool.stop();
    }
}
