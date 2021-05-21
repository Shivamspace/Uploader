package com.example.uploader;

public class Documents {

    private int id;
    private String name;
    private String type;
    private String timestamp;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Documents(int id,String name, String type, String timestamp) {
        this.name = name;
        this.type = type;
        this.timestamp = timestamp;
        this.id=id;
    }

    @Override
    public String toString() {
        return "Documents{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
