package com.bravedroid.dataaccess.model;

public class Config {
    public final static String KEY_BASE_URL = "baseUrl";
    public final static String KEY_PORT = "port";
    public final static String KEY_IS_PROD = "isProd";

    public Config() {
    }

    public Config(String baseUrl, int port, boolean isProd) {
        this.baseUrl = baseUrl;
        this.port = port;
        this.isProd = isProd;
    }

    private String baseUrl;
    private int port;
    private boolean isProd;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isProd() {
        return isProd;
    }

    public void setProd(boolean prod) {
        isProd = prod;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Config
                && ((Config) other).baseUrl.equals(this.baseUrl)
                && ((Config) other).port == this.port
                && ((Config) other).isProd == this.isProd;
    }

    @Override
    public String toString() {
        return "Config{" +
                "baseUrl='" + baseUrl + '\'' +
                ", port=" + port +
                ", isProd=" + isProd +
                '}';
    }
}
