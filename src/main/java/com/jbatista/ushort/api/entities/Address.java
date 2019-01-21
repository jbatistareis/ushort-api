package com.jbatista.ushort.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash("address")
public class Address {

    @JsonProperty("shortUrl")
    @Id
    private String id;
    private String fullUrl;
    private Date expires;
    @JsonIgnore
    @TimeToLive(unit = TimeUnit.HOURS)
    private long ttl;

    public Address() {
    }

    public Address(String shortUrl, String fullUrl, int ttl) {
        final Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.HOUR, ttl);

        this.id = shortUrl;
        this.fullUrl = fullUrl;
        this.expires = (ttl >= 0) ? expiration.getTime() : null;
        this.ttl = ttl;

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

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

}
