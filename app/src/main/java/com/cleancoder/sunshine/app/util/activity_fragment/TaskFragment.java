package com.cleancoder.sunshine.app.util.activity_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleancoder.sunshine.app.BaseApplicationFragment;
import com.cleancoder.sunshine.app.R;


/**
 * Created by Leonid on 17.07.2014.
 */
public abstract class TaskFragment extends BaseApplicationFragment {

    private final Object LOCK_IS_TASK_STARTED = "LOCK_IS_TASK_STARTED";

    private boolean isTaskStarted = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.progress_bar_at_center, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startTaskIfNotStarted();
    }

    private void startTaskIfNotStarted() {
        synchronized (LOCK_IS_TASK_STARTED) {
            if (isTaskStarted) {
                return;
            }
            isTaskStarted = true;
        }
        startTask();
    }

    protected abstract void startTask();

}
