package com.cleancoder.sunshine.app;

import android.os.Parcel;
import android.os.Parcelable;

import com.cleancoder.sunshine.app.util.HttpRequestor;
import com.google.common.base.Objects;
import com.google.common.base.Optional;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonid on 05.10.2014.
 */
public class WeatherForecastRequest implements HttpRequestor.ArgumentsProvider, Parcelable {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Optional<String> query;
        private Optional<String> units;
        private Optional<String> mode;
        private Optional<String> numberOfDays;

        private Builder() {
            query = Optional.absent();
            units = Optional.absent();
            mode = Optional.absent();
            numberOfDays = Optional.absent();
        }

        public Builder query(String query) {
            this.query = Optional.fromNullable(query);
            return this;
        }

        public Builder units(String units) {
            this.units = Optional.fromNullable(units);
            return this;
        }

        public Builder mode(String mode) {
            this.mode = Optional.fromNullable(mode);
            return this;
        }

        public Builder numberOfDays(int numberOfDays) {
            return numberOfDays(String.valueOf(numberOfDays));
        }

        public Builder numberOfDays(String numberOfDays) {
            this.numberOfDays = Optional.fromNullable(numberOfDays);
            return this;
        }

        public WeatherForecastRequest build() {
            return new WeatherForecastRequest(this);
        }
    }


    public static final Creator<WeatherForecastRequest> CREATOR = new Creator<WeatherForecastRequest>() {
        @Override
        public WeatherForecastRequest createFromParcel(Parcel source) {
            return new WeatherForecastRequest(source);
        }

        @Override
        public WeatherForecastRequest[] newArray(int size) {
            return new WeatherForecastRequest[size];
        }
    };

    private final List<NameValuePair> arguments;

    private WeatherForecastRequest(Builder builder) {
        arguments = new ArrayList<NameValuePair>();
        addArgumentIfNotNull(arguments, ForecastParameters.QUERY, builder.query);
        addArgumentIfNotNull(arguments, ForecastParameters.UNITS, builder.units);
        addArgumentIfNotNull(arguments, ForecastParameters.MODE, builder.mode);
        addArgumentIfNotNull(arguments, ForecastParameters.COUNT, builder.numberOfDays);
    }

    private void addArgumentIfNotNull(List<NameValuePair> arguments, String argumentName, Optional<String> argumentValue) {
        if (argumentValue.isPresent()) {
            arguments.add(new BasicNameValuePair(argumentName, argumentValue.get()));
        }
    }

    @Override
    public List<? extends NameValuePair> getArguments() {
        return new ArrayList<NameValuePair>(arguments);
    }

    public WeatherForecastRequest(Parcel source) {
        int numberOfArguments = source.readInt();
        arguments = new ArrayList<NameValuePair>(numberOfArguments);
        for (int i = 0; i < numberOfArguments; ++i) {
            String name = source.readString();
            String value = source.readString();
            NameValuePair argument = new BasicNameValuePair(name, value);
            arguments.add(argument);
        }
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        int numberOfArguments = arguments.size();
        out.writeInt(numberOfArguments);
        for (int i = 0; i < numberOfArguments; ++i) {
            NameValuePair argument = arguments.get(i);
            out.writeString(argument.getName());
            out.writeString(argument.getValue());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("arguments", arguments)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj == null) || !(obj instanceof WeatherForecastRequest)) {
            return false;
        }
        WeatherForecastRequest other = (WeatherForecastRequest) obj;
        return Objects.equal(arguments, other.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(arguments);
    }
}
