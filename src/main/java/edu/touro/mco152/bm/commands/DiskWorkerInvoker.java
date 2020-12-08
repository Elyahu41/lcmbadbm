package edu.touro.mco152.bm.commands;

import edu.touro.mco152.bm.UIInterface;

/**
 * This interface will be implemented by classes that execute DiskWorker's tasks like read and write benchmarks
 */
public interface DiskWorkerInvoker {

    void execute(UIInterface uiInterface);
}
