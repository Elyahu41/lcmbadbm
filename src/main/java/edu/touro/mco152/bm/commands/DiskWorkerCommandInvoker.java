package edu.touro.mco152.bm.commands;

import edu.touro.mco152.bm.CMDLineWorker;


/**
 * This is a concrete class for executing Diskworker Commands.
 */
public class DiskWorkerCommandInvoker {

    private DiskWorkerCommand diskWorkerCommand;
    private CMDLineWorker cmdLineWorker;

    /**
     * This is the constructor for the concrete class.
     */
    public DiskWorkerCommandInvoker(DiskWorkerCommand diskWorkerCommand) {
        this.diskWorkerCommand = diskWorkerCommand;
        this.cmdLineWorker = new CMDLineWorker();
    }

    /**
     * This method starts the benchmark by calling the execute method of whatever command is passed in.
     */
    public boolean startBenchmark() {
        return diskWorkerCommand.execute(cmdLineWorker);
    }
}
