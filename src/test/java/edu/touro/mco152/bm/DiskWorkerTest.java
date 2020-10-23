package edu.touro.mco152.bm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiskWorkerTest {

    /**
     * This test makes sure that the DiskWorker class will throw a NullPointerException if the @done() method is
     * called after the done() method has already been called.
     * This test also utilizes the "E" concept of the right-BICEP concept that states
     * "Can you force error conditions to happen?"
     */
    @Test
    void doInBackground() {
        DiskWorker diskWorker = new DiskWorker();
        assertThrows(NullPointerException.class, () -> diskWorker.doInBackground(null));
    }

    @Test
    void process() {
    }


    @Test
    void done() {
    }
}