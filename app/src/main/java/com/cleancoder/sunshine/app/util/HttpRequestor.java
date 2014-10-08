package com.cleancoder.sunshine.app.util;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonid on 26.05.2014.
 */
public class HttpRequestor {

    public static interface ArgumentsProvider {
        List<? extends NameValuePair> getArguments();
    }

    private static final HttpClient httpClient = new DefaultHttpClient();

    private final List<? extends NameValuePair> arguments;
    private final String url;

    public HttpRequestor(String url, ArgumentsProvider argumentsProvider) {
        this.url = url;
        this.arguments = argumentsProvider.getArguments();
    }

    public HttpRequestor(String url, List<? extends NameValuePair> arguments) {
        this.url = url;
        this.arguments = arguments;
    }

    public HttpRequestor(String url) {
        this.url = url;
        this.arguments = new ArrayList<NameValuePair>();
    }

    public String request() throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(arguments));
        HttpResponse httpResponse = httpClient.execute(httpPost);
        return EntityUtils.toString(httpResponse.getEntity());
    }

}
