package edu.touro.mco152.bm.commands;

import edu.touro.mco152.bm.App;
import edu.touro.mco152.bm.DiskMark;
import edu.touro.mco152.bm.UIInterface;
import edu.touro.mco152.bm.Util;
import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.persist.EM;
import edu.touro.mco152.bm.ui.Gui;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

import static edu.touro.mco152.bm.App.*;
import static edu.touro.mco152.bm.App.updateMetrics;
import static edu.touro.mco152.bm.DiskMark.MarkType.READ;

public class DiskWorkerReadExecutor {

    int wUnitsComplete = 0,
            rUnitsComplete = 0,
            unitsComplete;

    int wUnitsTotal = App.writeTest ? numOfBlocks * numOfMarks : 0;
    int rUnitsTotal = App.readTest ? numOfBlocks * numOfMarks : 0;
    int unitsTotal = wUnitsTotal + rUnitsTotal;
    float percentComplete;
    int blockSize = blockSizeKb*KILOBYTE;
    byte [] blockArr = new byte [blockSize];

    DiskMark rMark;
    int startFileNum = App.nextMarkNumber;

    public DiskWorkerReadExecutor() {
        for (int b=0; b<blockArr.length; b++) {
            if (b % 2 == 0) {
                blockArr[b] = (byte) 0xFF;
            }
        }
    }

    public void execute(UIInterface uiInterface) {
        read(uiInterface);
    }

    private void read(UIInterface uiInterface) {
        DiskRun run = new DiskRun(DiskRun.IOMode.READ, blockSequence);
        run.setNumMarks(numOfMarks);
        run.setNumBlocks(numOfBlocks);
        run.setBlockSize(blockSizeKb);
        run.setTxSize(targetTxSizeKb());
        run.setDiskInfo(Util.getDiskInfo(dataDir));

        msg("disk info: (" + run.getDiskInfo() + ")");

        Gui.chartPanel.getChart().getTitle().setVisible(true);
        Gui.chartPanel.getChart().getTitle().setText(run.getDiskInfo());

        for (int m = startFileNum; m < startFileNum + numOfMarks && !uiInterface.isCancelled(); m++) {

            if (multiFile) {
                testFile = new File(dataDir.getAbsolutePath()
                        + File.separator + "testdata" + m + ".jdm");
            }
            rMark = new DiskMark(READ);
            rMark.setMarkNum(m);
            long startTime = System.nanoTime();
            long totalBytesReadInMark = 0;

            try (RandomAccessFile rAccFile = new RandomAccessFile(testFile, "r")) {
                for (int b = 0; b < numOfBlocks; b++) {
                    if (blockSequence == DiskRun.BlockSequence.RANDOM) {
                        int rLoc = Util.randInt(0, numOfBlocks - 1);
                        rAccFile.seek(rLoc * blockSize);
                    } else {
                        rAccFile.seek(b * blockSize);
                    }
                    rAccFile.readFully(blockArr, 0, blockSize);
                    totalBytesReadInMark += blockSize;
                    rUnitsComplete++;
                    unitsComplete = rUnitsComplete + wUnitsComplete;
                    percentComplete = (float) unitsComplete / (float) unitsTotal * 100f;
                    uiInterface.setProgressAmount((int) percentComplete);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            long endTime = System.nanoTime();
            long elapsedTimeNs = endTime - startTime;
            double sec = (double) elapsedTimeNs / (double) 1000000000;
            double mbRead = (double) totalBytesReadInMark / (double) MEGABYTE;
            rMark.setBwMbSec(mbRead / sec);
            msg("m:" + m + " READ IO is " + rMark.getBwMbSec() + " MB/s    "
                    + "(MBread " + mbRead + " in " + sec + " sec)");
            updateMetrics(rMark);
            uiInterface.publish(rMark);

            run.setRunMax(rMark.getCumMax());
            run.setRunMin(rMark.getCumMin());
            run.setRunAvg(rMark.getCumAvg());
            run.setEndTime(new Date());
        }

        persistInfo(run);
    }

    private void persistInfo(DiskRun run) {
        /*
         * Persist info about the Write BM Run (e.g. into Derby Database) and add it to a GUI panel
         */
        EntityManager em = EM.getEntityManager();
        em.getTransaction().begin();
        em.persist(run);
        em.getTransaction().commit();

        Gui.runPanel.addRun(run);
    }
}
