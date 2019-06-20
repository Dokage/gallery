package com.dokage.gallery.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private final int THREAD_COUNT = 3;
    private final Executor networkIO;
    private final Executor mainThread;

    static class AppExecutorsHolder {
        static final AppExecutors instance = new AppExecutors();
    }

    public static AppExecutors getInstance() {
        return AppExecutorsHolder.instance;
    }

    private AppExecutors() {
        networkIO = Executors.newFixedThreadPool(THREAD_COUNT);
        mainThread = new MainThreadExecutor();
    }

    public Executor getNetworkIO() {
        return networkIO;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
