package edu.touro.mco152.bm;

public class CMDLineWorker implements UIInterface{

    DiskWorker diskWorker;
    boolean cancel = false;
    boolean bmFinished = false;
    int progressAmount = 0;

    CMDLineWorker() {
        diskWorker = new DiskWorker();
    }

    public void execute() {
        System.out.println("Starting benchmark!");
        diskWorker.doInBackground(this);
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setProgressAmount(int percentComplete) {
        progressAmount = percentComplete;
        System.out.println("Percent complete: "+percentComplete+"%");
        if (percentComplete == 100){
            bmFinished = true;
        }
    }

    @Override
    public void publish(DiskMark diskMark) {
        System.out.println("Results: "+diskMark.toString());
    }

    public int getProgressAmount() {
        return progressAmount;
    }
}
