package com.example.ITS.Entity.response;

import java.io.Serializable;

public class SparkResponseUsage implements Serializable {
    private static final long serialVersionUID = 2181817132625461079L;

    private SparkTextUsage text;

    public SparkTextUsage getText() {
        return text;
    }

    public void setText(SparkTextUsage text) {
        this.text = text;
    }
}
