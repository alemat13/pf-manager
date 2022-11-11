package com.pfmanager.entity;

public class Currency {
    private String name;
    private String code;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    private String description;
    public Currency(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
