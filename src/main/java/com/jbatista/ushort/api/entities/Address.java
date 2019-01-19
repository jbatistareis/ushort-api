package com.jbatista.ushort.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("address")
public class Address {

    @JsonProperty("shortUrl")
    @Id
    private String id;
    private String fullUrl;
    private Date expires;

    public Address() {
    }

    public Address(String shortUrl, String fullUrl, Date expires) {
        this.id = shortUrl;
        this.fullUrl = fullUrl;
        this.expires = expires;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

}
