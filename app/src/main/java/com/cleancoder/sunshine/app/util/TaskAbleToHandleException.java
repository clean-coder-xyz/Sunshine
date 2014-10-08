package com.cleancoder.sunshine.app.util;

import android.os.AsyncTask;

/**
 * Created by Leonid on 13.09.2014.
 */
public abstract class TaskAbleToHandleException<TaskResult> extends AsyncTask<Void,Void,ResultWrapper<TaskResult>> {

    @Override
    protected final ResultWrapper<TaskResult> doInBackground(Void... params) {
        try {
            TaskResult result = doWork();
            return ResultWrapper.create(result);
        } catch (Throwable exception) {
            return ResultWrapper.onException(exception);
        }
    }

    protected abstract TaskResult doWork() throws Exception;

    @Override
    protected final void onPostExecute(ResultWrapper<TaskResult> taskResultWrapper) {
        super.onPostExecute(taskResultWrapper);
        if (taskResultWrapper.containsResult()) {
            onTaskResult(taskResultWrapper.getResult());
        } else {
            onException(taskResultWrapper.getException());
        }
    }

    protected abstract void onTaskResult(TaskResult taskResult);

    protected abstract void onException(Throwable exception);

}
