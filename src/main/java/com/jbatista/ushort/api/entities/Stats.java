package com.jbatista.ushort.api.entities;

public class Stats {

    private long reducedUrls = 0;
    private long reducedLeters = 0;
    private long averageUrlSize = 0;
    private long reductionPercentage = 0;

    public long getReducedUrls() {
        return reducedUrls;
    }

    public void setReducedUrls(long reducedUrls) {
        this.reducedUrls = reducedUrls;
    }

    public long getReducedLeters() {
        return reducedLeters;
    }

    public void setReducedLeters(long reducedLeters) {
        this.reducedLeters = reducedLeters;
    }

    public long getAverageUrlSize() {
        return averageUrlSize;
    }

    public void setAverageUrlSize(long averageUrlSize) {
        this.averageUrlSize = averageUrlSize;
    }

    public long getReductionPercentage() {
        return reductionPercentage;
    }

    public void setReductionPercentage(long reductionPercentage) {
        this.reductionPercentage = reductionPercentage;
    }

    public void incrementReducedUrls() {
        reducedUrls++;
    }

    public void incrementReducedLetters(long value) {
        reducedLeters += value;
    }

}
