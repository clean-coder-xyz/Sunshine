package com.cleancoder.sunshine.app.util.activity_fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleancoder.sunshine.app.R;


/**
 * Created by Leonid on 15.07.2014.
 */
public abstract class TaskDialogFragment extends BaseApplicationDialogFragment {

    private final Object LOCK_IS_TASK_STARTED = "LOCK_IS_TASK_STARTED";

    private boolean isTaskStarted = false;
    private boolean wasDismissed = false;

    @Override
    protected boolean hasTitle() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        prepareDialog();
        return inflater.inflate(R.layout.progress_bar_at_center, null);
    }

    private void prepareDialog() {
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
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

    protected void cancel() {
        if (!isDetached()) {
            dismiss();
        }
    }

    @Override
    public void onDestroyView() {
        if ((getDialog() != null) && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        wasDismissed = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasDismissed) {
            dismiss();
        }
    }

    public final void startTask(android.support.v4.app.Fragment fragment) {
        show(fragment);
    }

}
