package com.cleancoder.sunshine.app.util.activity_fragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.cleancoder.sunshine.app.util.TagsProvider;


/**
 * Created by Leonid on 25.05.2014.
 */
public class FragmentHelper extends android.support.v4.app.Fragment {

    public void replaceItself(android.support.v4.app.Fragment fragment, String tag) {
        replaceFragment(getContainerId(), fragment, tag);
    }

    public void replaceItself(FragmentHelper fragment) {
        replaceFragment(getContainerId(), fragment);
    }

    public int getContainerId() {
        return ((ViewGroup)getView().getParent()).getId();
    }

    public void runOnUiThread(Runnable action) {
        getActivity().runOnUiThread(action);
    }

    public final void replaceFragmentCorrectly(
            int containerId, android.support.v4.app.Fragment fragment, String tag) {
        clearBackStack();
        removeItselfAndAddFragment(containerId, fragment, tag);
    }

    public final void replaceFragmentCorrectly(int containerId, FragmentHelper fragmentHelper) {
        clearBackStack();
        removeItselfAndAddFragment(containerId, fragmentHelper);
    }

    public final void removeItselfAndAddFragment(int containerId, FragmentHelper fragmentHelper) {
        removeItselfAndAddFragment(containerId, fragmentHelper, fragmentHelper.tag());
    }

    public final void removeItselfAndAddFragment(
                            int containerId, android.support.v4.app.Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(this);
        transaction.add(containerId, fragment, tag);
        transaction.commit();
    }

    public void clearBackStack() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public final void replaceFragment(int containerId, FragmentHelper fragment) {
        replaceFragment(containerId, fragment, fragment.tag());
    }

    public final void replaceFragment(int containerId,
                              android.support.v4.app.Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment, tag);
        transaction.commit();
    }

    public final void replaceFragmentAndAddToBackstack(int containerId, FragmentHelper fragment) {
        replaceFragmentAndAddToBackstack(containerId, fragment, fragment.tag());
    }

    public final void replaceFragmentAndAddToBackstack(int containerId,
                              android.support.v4.app.Fragment fragment, String tag) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void replaceFragmentOnTopOfBackstackAndAddToBackstack(int containerId, FragmentHelper fragment) {
        replaceFragmentOnTopOfBackstackAndAddToBackstack(containerId, fragment, fragment.tag());
    }

    public void replaceFragmentOnTopOfBackstackAndAddToBackstack(
                        int containerId, android.support.v4.app.Fragment fragment, String tag) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public FragmentManager getSupportFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    public void popBackStack() {
        getFragmentManager().popBackStack();
    }

    public String tag() {
        return TagsProvider.getTagFromFragment(this);
    }

    public void finishActivity() {
        getActivity().finish();
    }

    public void setActivityTitle(CharSequence title) {
        getActivity().setTitle(title);
    }

    public void setActivityTitle(int titleId) {
        getActivity().setTitle(titleId);
    }

    public String getApplicationPackageName() {
        return getActivity().getApplicationContext().getPackageName();
    }

    public Context getContext() {
        return getActivity();
    }

    protected final void showMessage(String message, int toastDuration) {
        FragmentUtils.showMessage(this, message, toastDuration);
    }

    public String getPathToApplicationData() {
        return "android/data/" + getApplicationPackageName();
    }

}
