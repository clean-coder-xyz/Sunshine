package com.cleancoder.sunshine.app.util;

import android.os.AsyncTask;

/**
 * Created by Leonid on 31.05.2014.
 */
public abstract class SimpleAsyncTask<TaskResult> extends AsyncTask<Void,Void,TaskResult> {

    public static void start(final Runnable runnable) {
        SimpleAsyncTask<Void> task = new SimpleAsyncTask<Void>() {
            @Override
            protected Void doWork() {
                runnable.run();
                return null;
            }

            @Override
            protected void onTaskResult(Void aVoid) {
                // skip
            }
        };
        task.execute();
    }

    @Override
    protected final TaskResult doInBackground(Void... params) {
        return doWork();
    }

    protected abstract TaskResult doWork();

    @Override
    protected final void onPostExecute(TaskResult taskResult) {
        super.onPostExecute(taskResult);
        onTaskResult(taskResult);
    }

    protected abstract void onTaskResult(TaskResult taskResult);

}
