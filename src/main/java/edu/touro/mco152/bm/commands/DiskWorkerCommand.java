package edu.touro.mco152.bm.commands;

import edu.touro.mco152.bm.UIInterface;

/**
 * This interface is required to be implemented by classes that execute commands based on the command pattern
 */
public interface DiskWorkerCommand {

    boolean execute(UIInterface uiInterface);
}
