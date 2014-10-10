package com.cleancoder.sunshine.app.util.activity_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cleancoder.sunshine.app.R;

import java.io.Serializable;

/**
 * Created by Leonid on 16.09.2014.
 */
public class ExceptionDisplayFragment extends FragmentHelper {


    public static abstract class ExceptionDisplay implements Serializable {
        protected final Throwable exception;
        protected final FragmentBuilder fragmentOnRetryBuilder;

        public ExceptionDisplay(Throwable exception, FragmentBuilder fragmentOnRetryBuilder) {
            this.exception = exception;
            this.fragmentOnRetryBuilder = fragmentOnRetryBuilder;
        }

        protected Throwable getException() {
            return exception;
        }

        protected FragmentHelper buildFragmentOnRetry() {
            return fragmentOnRetryBuilder.build();
        }

        public abstract View prepareView(FragmentHelper exceptionDisplayFragment,
                                         LayoutInflater inflater,
                                         ViewGroup container,
                                         Bundle savedInstanceState);
    }


    public static class DefaultExceptionDisplay extends ExceptionDisplay {
        public DefaultExceptionDisplay(Throwable exception, FragmentBuilder fragmentOnRetryBuilder) {
            super(exception, fragmentOnRetryBuilder);
        }

        @Override
        public View prepareView(FragmentHelper exceptionDisplayFragment,
                                LayoutInflater inflater,
                                ViewGroup container,
                                Bundle savedInstanceState) {
            View contentView = inflater.inflate(R.layout.fragment_exception_display_default_display, null);
            initContentView(exceptionDisplayFragment, contentView);
            return contentView;
        }

        private void initContentView(final FragmentHelper fragment, View contentView) {
            TextView exceptionDescriptionTextView = (TextView) contentView.findViewById(R.id.exception_description);
            exceptionDescriptionTextView.setText(getException().getMessage());
            contentView.findViewById(R.id.retry).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.replaceItself(buildFragmentOnRetry());
                }
            });
        }
    }


    private static final String KEY_EXCEPTION_DISPLAY = "KEY_EXCEPTION_DISPLAY";

    public static ExceptionDisplayFragment newInstance(ExceptionDisplay exceptionDisplay) {
        ExceptionDisplayFragment fragment = new ExceptionDisplayFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_EXCEPTION_DISPLAY, exceptionDisplay);
        fragment.setArguments(args);
        return fragment;
    }

    protected ExceptionDisplay getExceptionDisplay() {
        return (ExceptionDisplay) getArguments().getSerializable(KEY_EXCEPTION_DISPLAY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return getExceptionDisplay().prepareView(this, inflater, container, savedInstanceState);
    }

}
