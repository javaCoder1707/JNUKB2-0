package com.parser.uk;

public class ResultUK {

    private String url;
    private String text;
    private String type;

    public ResultUK(String url, String text, String type){
        this.url = url;
        this.text = text;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.text + "\n" + this.url;
    }
}
