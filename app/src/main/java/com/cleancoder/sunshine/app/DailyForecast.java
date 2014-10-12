package com.cleancoder.sunshine.app;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Objects;

/**
 * Created by Leonid on 10.09.2014.
 */
public class DailyForecast implements Parcelable {

    public static final Creator<DailyForecast> CREATOR = new Creator<DailyForecast>() {
        @Override
        public DailyForecast createFromParcel(Parcel source) {
            return new DailyForecast(source);
        }

        @Override
        public DailyForecast[] newArray(int size) {
            return new DailyForecast[size];
        }
    };


    private int min;
    private int max;
    private long dateTime;
    private String description;

    public DailyForecast() {
        // do nothing
    }

    public DailyForecast(Parcel in) {
        min = in.readInt();
        max = in.readInt();
        dateTime = in.readLong();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(min);
        out.writeInt(max);
        out.writeLong(dateTime);
        out.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public DailyForecast min(int min) {
        this.min = min;
        return this;
    }

    public int getMin() {
        return min;
    }

    public DailyForecast max(int max) {
        this.max = max;
        return this;
    }

    public int getMax() {
        return max;
    }

    public DailyForecast dateTime(long dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public long getDateTime() {
        return dateTime;
    }

    public DailyForecast description(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DailyForecast)) {
            return false;
        }
        DailyForecast other = (DailyForecast) obj;
        return ((min == other.min) &&
                (max == other.max) &&
                (dateTime == other.dateTime) &&
                Objects.equal(description, other.description));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(min, max, dateTime, description);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("min", min)
                .add("max", max)
                .add("dateTime", dateTime)
                .add("description", description)
                .toString();
    }

}
