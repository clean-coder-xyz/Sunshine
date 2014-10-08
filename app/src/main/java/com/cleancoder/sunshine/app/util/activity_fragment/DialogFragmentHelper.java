package com.cleancoder.sunshine.app.util.activity_fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.cleancoder.sunshine.app.util.TagsProvider;


/**
 * Created by Leonid on 08.07.2014.
 */
public class DialogFragmentHelper extends DialogFragment {

    private static final boolean HAS_TITLE_BY_DEFAULT = true;

    public void show(android.support.v4.app.Fragment fragment) {
        FragmentTransaction transaction = fragment.getFragmentManager().beginTransaction();
        String tag = TagsProvider.getTagFromFragment(this);
        show(transaction, tag);
    }

    public void showAndSetTargetFragment(android.support.v4.app.Fragment fragment) {
        show(fragment);
        setTargetFragment(fragment, 1);
    }

    public Context getContext() {
        return getActivity();
    }

    protected boolean hasTitle() {
        return HAS_TITLE_BY_DEFAULT;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (!hasTitle()) {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        return dialog;
    }

    protected final void showMessage(String message, int toastDuration) {
        FragmentUtils.showMessage(this, message, toastDuration);
    }

}
