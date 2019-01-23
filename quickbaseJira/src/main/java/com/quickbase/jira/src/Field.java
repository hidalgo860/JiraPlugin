package com.quickbase.jira.src;

public class Field
{
    private String Id;
    private String Value;

    public Field(String id, String val)
    {
        Id = id;
        Value = val;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}