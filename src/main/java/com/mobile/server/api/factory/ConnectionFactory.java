package com.mobile.server.api.factory;

import com.mobile.server.api.httpconnection.MovieApiConnection;

public class ConnectionFactory {
    public MovieApiConnection build(String url, String key) {
        return new MovieApiConnection(url, key);
    }

    public MovieApiConnection build(String url) {
        return new MovieApiConnection(url);
    }
}