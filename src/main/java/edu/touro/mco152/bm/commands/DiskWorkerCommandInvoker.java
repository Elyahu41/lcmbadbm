package edu.touro.mco152.bm.commands;

import edu.touro.mco152.bm.CMDLineWorker;


/**
 * This is a concrete class for executing Diskworker Commands
 */
public class DiskWorkerCommandInvoker {

    private DiskWorkerCommand diskWorkerCommand;
    private CMDLineWorker cmdLineWorker;

    public DiskWorkerCommandInvoker(DiskWorkerCommand diskWorkerCommand) {
        this.diskWorkerCommand = diskWorkerCommand;
        this.cmdLineWorker = new CMDLineWorker();
    }

    public void startBenchmark() {
        diskWorkerCommand.execute(cmdLineWorker);
    }
}
