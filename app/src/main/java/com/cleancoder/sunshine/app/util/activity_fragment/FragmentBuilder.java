package com.cleancoder.sunshine.app.util.activity_fragment;

import java.io.Serializable;

/**
 * Created by Leonid on 16.09.2014.
 */
public abstract class FragmentBuilder implements Serializable {
    public abstract FragmentHelper build();
}
