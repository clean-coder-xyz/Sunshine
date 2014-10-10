package com.cleancoder.sunshine.app;

import com.cleancoder.sunshine.app.util.HttpRequestor;
import com.google.common.base.Objects;
import com.google.common.base.Optional;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonid on 05.10.2014.
 */
public class WeatherForecastRequest implements HttpRequestor.ArgumentsProvider, Serializable {
    private Optional<String> query;
    private Optional<String> units;
    private Optional<String> mode;
    private Optional<String> numberOfDays;

    public WeatherForecastRequest() {
        query = Optional.absent();
        units = Optional.absent();
        mode = Optional.absent();
        numberOfDays = Optional.absent();
    }

    public WeatherForecastRequest query(String query) {
        this.query = Optional.fromNullable(query);
        return this;
    }

    public WeatherForecastRequest units(String units) {
        this.units = Optional.fromNullable(units);
        return this;
    }

    public WeatherForecastRequest mode(String mode) {
        this.mode = Optional.fromNullable(mode);
        return this;
    }

    public WeatherForecastRequest numberOfDays(int numberOfDays) {
        return numberOfDays(String.valueOf(numberOfDays));
    }

    public WeatherForecastRequest numberOfDays(String numberOfDays) {
        this.numberOfDays = Optional.fromNullable(numberOfDays);
        return this;
    }

    @Override
    public List<? extends NameValuePair> getArguments() {
        List<NameValuePair> arguments = new ArrayList<NameValuePair>();
        addArgumentIfNotNull(arguments, ForecastParameters.QUERY, query);
        addArgumentIfNotNull(arguments, ForecastParameters.UNITS, units);
        addArgumentIfNotNull(arguments, ForecastParameters.MODE, mode);
        addArgumentIfNotNull(arguments, ForecastParameters.COUNT, numberOfDays);
        return arguments;
    }

    private static void addArgumentIfNotNull(List<NameValuePair> arguments,
                                             String argumentName, Optional<String> argumentValue) {
        if (argumentValue.isPresent()) {
            arguments.add(new BasicNameValuePair(argumentName, argumentValue.get()));
        }
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("query", query)
                .add("units", units)
                .add("mode", mode)
                .add("numberOfDays", numberOfDays)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WeatherForecastRequest)) {
            return false;
        }
        WeatherForecastRequest other = (WeatherForecastRequest) obj;
        return (Objects.equal(query, other.query) &&
                Objects.equal(units, other.units) &&
                Objects.equal(mode, other.mode) &&
                Objects.equal(numberOfDays, other.numberOfDays));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(query, units, mode, numberOfDays);
    }

}
