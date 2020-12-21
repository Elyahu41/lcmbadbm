package edu.touro.mco152.bm;

import edu.touro.mco152.bm.commands.DiskWorkerReadCommand;
import edu.touro.mco152.bm.commands.DiskWorkerWriteCommand;
import edu.touro.mco152.bm.ui.Gui;

import javax.swing.*;
import java.util.List;

import static edu.touro.mco152.bm.App.*;

/**
 * Run the disk benchmarking as a Swing-compliant thread (only one of these threads can run at
 * once.) Cooperates with Swing to provide and make use of interim and final progress and
 * information, which is also recorded as needed to the persistence store, and log.
 * <p>
 * Depends on static values that describe the benchmark to be done having been set in App and Gui classes.
 * The DiskRun class is used to keep track of and persist info about each benchmark at a higher level (a run),
 * while the DiskMark class described each iteration's result, which is displayed by the UI as the benchmark run
 * progresses.
 * <p>
 * This class only knows how to do 'read' or 'write' disk benchmarks. It is instantiated by the
 * startBenchmark() method.
 * <p>
 * To be Swing compliant this class extends SwingWorker and declares that its final return (when
 * doInBackground() is finished) is of type Boolean, and declares that intermediate results are communicated to
 * Swing using an instance of the DiskMark class.
 */

public class DiskWorker {
    /**
     * We 'got here' because: a) End-user clicked 'Start' on the benchmark UI,
     * which triggered the start-benchmark event associated with the App::startBenchmark()
     * method.  b) startBenchmark() then instantiated a DiskWorker, and called
     * its (super class's) execute() method, causing Swing to eventually
     * call this doInBackground() method.
     */


    protected Boolean doInBackground(UIInterface uiInterface) {
        System.out.println("*** starting new worker thread");
        msg("Running readTest " + readTest + "   writeTest " + writeTest);
        msg("num files: " + numOfMarks + ", num blks: " + numOfBlocks
                + ", blk size (kb): " + blockSizeKb + ", blockSequence: " + blockSequence);

        Gui.updateLegend();  // init chart legend info

        if (autoReset) {
            resetTestData();
            Gui.resetTestData();
        }

        DiskWorkerReadCommand dwre = new DiskWorkerReadCommand();
        DiskWorkerWriteCommand dwwe = new DiskWorkerWriteCommand();

        /*
         * The GUI allows either a write, read, or both types of BMs to be started. They are done serially.
         */
        if (writeTest) {
            dwwe.execute(uiInterface);
        }
        clearDiskCache(uiInterface);

        // Same as above, just for Read operations instead of Writes.
        if (readTest) {
            dwre.execute(uiInterface);
        }
        nextMarkNumber += numOfMarks;
        return true;
    }

    private void clearDiskCache(UIInterface uiInterface) {
        /*
         * Most benchmarking systems will try to do some cleanup in between 2 benchmark operations to
         * make it more 'fair'. For example a networking benchmark might close and re-open sockets,
         * a memory benchmark might clear or invalidate the Op Systems TLB or other caches, etc.
         */
        // try renaming all files to clear catch
        if (App.readTest && App.writeTest && !uiInterface.isCancelled()) {
            JOptionPane.showMessageDialog(Gui.mainFrame,
                    "For valid READ measurements please clear the disk cache by\n" +
                            "using the included RAMMap.exe or flushmem.exe utilities.\n" +
                            "Removable drives can be disconnected and reconnected.\n" +
                            "For system drives use the WRITE and READ operations \n" +
                            "independantly by doing a cold reboot after the WRITE",
                    "Clear Disk Cache Now", JOptionPane.PLAIN_MESSAGE);
        }
    }

    protected void process(List<DiskMark> markList) {
        /*
         * We are passed a list of one or more DiskMark objects that our thread has previously
         * published to Swing. Watch Professor Cohen's video - Module_6_RefactorBadBM Swing_DiskWorker_Tutorial.mp4
         */
        markList.stream().forEach((dm) -> {
            if (dm.type == DiskMark.MarkType.WRITE) {
                Gui.addWriteMark(dm);
            } else {
                Gui.addReadMark(dm);
            }
        });
    }

    protected void done() {
        if (App.autoRemoveData) {
            Util.deleteDirectory(dataDir);
        }
        App.state = App.State.IDLE_STATE;
        Gui.mainFrame.adjustSensitivity();
    }
}
