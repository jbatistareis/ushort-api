package com.jbatista.ushort.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("configuration")
public class Configuration {

    @JsonIgnore
    @Id
    private String id = "1";
    private int ttlHours = 24;
    @JsonIgnore
    private String admUser;
    @JsonIgnore
    private String admPassword;

    public Configuration() {
    }

    public Configuration(String admUser, String admPassword) {
        this.admUser = admUser;
        this.admPassword = admPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = "1";
    }

    public int getTtlHours() {
        return ttlHours;
    }

    public void setTtlHours(int ttlHours) {
        this.ttlHours = ttlHours;
    }

    public String getAdmUser() {
        return admUser;
    }

    public void setAdmUser(String admUser) {
        this.admUser = admUser;
    }

    public String getAdmPassword() {
        return admPassword;
    }

    public void setAdmPassword(String admPassword) {
        this.admPassword = admPassword;
    }

}
