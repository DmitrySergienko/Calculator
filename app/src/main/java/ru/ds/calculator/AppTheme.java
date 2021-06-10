package ru.ds.calculator;

public enum AppTheme {

    CUSTOM(R.style.Theme_Resources_Custom, "custom"),

    DEFAULT(R.style.Theme_Resources, "default");

    AppTheme(int resource, String key) {
        this.resource = resource;
        this.key = key;
    }

    private int resource;

    private String key;

    public int getResource() {
        return resource;
    }

    public String  getKey() {
        return key;
    }
}
