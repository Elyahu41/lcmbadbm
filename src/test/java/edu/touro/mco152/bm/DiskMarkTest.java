package edu.touro.mco152.bm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiskMarkTest {

    @Test
    void testToString() {
    }

    @Test
    void getBwMbSecAsString() {
    }

    @Test
    void getMinAsString() {
    }

    @Test
    void getMaxAsString() {
    }

    @Test
    void getAvgAsString() {
    }

    @Test
    void getMarkNum() {
    }

    @Test
    void setMarkNum() {
    }

    @Test
    void getBwMbSec() {
    }

    @Test
    void setBwMbSec() {
    }

    @Test
    void getCumAvg() {
    }

    @Test
    void setCumAvg() {
    }

    /**
     * This test cross checks between the getter and setter methods of DiskMark.
     * This test also utilizes the "C" concept of the right-BICEP concept that states
     * "Can you check cross-check results using other means?"
     */
    @Test
    void getCumMin() {
        DiskMark diskMark = new DiskMark(DiskMark.MarkType.READ);
        diskMark.setCumMin(0.0);
        assertEquals(0.0,diskMark.getCumMin());
    }

    /**
     * This test cross checks between the getter and setter methods of DiskMark.
     * This test also utilizes the "C" concept of the right-BICEP concept that states
     * "Can you check cross-check results using other means?"
     */
    @Test
    void setCumMin() {
        DiskMark diskMark = new DiskMark(DiskMark.MarkType.READ);
        diskMark.setCumMin(0.0);
        assertEquals(0.0,diskMark.getCumMin());
    }

    @Test
    void getCumMax() {
    }

    @Test
    void setCumMax() {
    }
}