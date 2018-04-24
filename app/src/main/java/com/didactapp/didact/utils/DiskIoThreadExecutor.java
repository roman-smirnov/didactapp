package com.didactapp.didact.utils;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * database (disk) thread handler (used by app threadpool executor )
 **/
public class DiskIoThreadExecutor implements Executor {

    private final Executor mDiskIO;

    public DiskIoThreadExecutor() {
        mDiskIO = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mDiskIO.execute(command);
    }
}
