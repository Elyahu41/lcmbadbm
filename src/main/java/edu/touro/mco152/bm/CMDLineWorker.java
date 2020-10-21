package edu.touro.mco152.bm;

public class CMDLineWorker implements UIInterface{

    DiskWorker diskWorker;
    boolean cancel = false;
    boolean bmFinished = false;

    CMDLineWorker() {
        diskWorker = new DiskWorker();
    }

    public void execute() {
        diskWorker.doInBackground(this);
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setProgressAmount(int percentComplete) {
        System.out.println("Percent complete: "+percentComplete+"%");
        if (percentComplete == 100){
            bmFinished = true;
        }
    }

    @Override
    public void publish(DiskMark diskMark) {
        System.out.println("Results: "+diskMark.toString());
    }
}
