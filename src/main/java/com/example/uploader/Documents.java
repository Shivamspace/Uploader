package com.example.uploader;

import java.util.UUID;


public class Documents {

    private UUID id;
    private String name;
    private String type;
    private long timestamp;

    public Documents(UUID id, String name, String type, long timestamp) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.timestamp = timestamp;
    }

    public Documents() {

    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Documents{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
