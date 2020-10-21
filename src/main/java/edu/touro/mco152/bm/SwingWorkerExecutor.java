package edu.touro.mco152.bm;

import javax.swing.*;

public class SwingWorkerExecutor extends SwingWorker<Boolean, DiskMark> implements UIInterface {

    DiskWorker diskWorker;

    SwingWorkerExecutor() {
        diskWorker = new DiskWorker();
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        return diskWorker.doInBackground(SwingWorkerExecutor.this);
    }

    @Override
    public void setProgressAmount(int percentComplete) {
        setProgress(percentComplete);
    }

    @Override
    public void publish(DiskMark diskMark) {
        super.publish(diskMark);
    }
}
