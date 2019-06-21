package com.dokage.gallery.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static final int THREAD_COUNT = 1;
    private final Executor mNetworkIO;
    private final Executor mMainThread;

    static class AppExecutorsHolder {
        static final AppExecutors INSTANCE = new AppExecutors();
    }

    public static AppExecutors getInstance() {
        return AppExecutorsHolder.INSTANCE;
    }

    private AppExecutors() {
        mNetworkIO = Executors.newFixedThreadPool(THREAD_COUNT);
        mMainThread = new MainThreadExecutor();
    }

    public Executor getNetworkIO() {
        return mNetworkIO;
    }

    public Executor getMainThread() {
        return mMainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
