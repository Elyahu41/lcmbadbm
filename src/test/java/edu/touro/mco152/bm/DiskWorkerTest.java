package edu.touro.mco152.bm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiskWorkerTest {

    @Test
    void doInBackground() {
    }

    @Test
    void process() {
    }

    /**
     * This test makes sure that the DiskWorker class will throw a NullPointerException if the @done() method is
     * called after the done() method has already been called.
     * This test also utilizes the "E" concept of the right-BICEP concept that states
     * "Can you force error conditions to happen?"
     */
    @Test
    void done() {
        DiskWorker diskWorker = new DiskWorker();
        if (diskWorker.isDone())
            assertThrows(NullPointerException.class, diskWorker::done);
    }
}