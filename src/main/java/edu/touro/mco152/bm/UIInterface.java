package edu.touro.mco152.bm;

public interface UIInterface {

    boolean isCancelled();

    void setProgressAmount(int percentComplete);

    void publish(DiskMark wMark);
}
