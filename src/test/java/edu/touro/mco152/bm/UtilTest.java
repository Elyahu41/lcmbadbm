package edu.touro.mco152.bm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilTest {

    @Test
    void deleteDirectory() {
    }

    /**
     * This test makes sure that the randInt() method in the Util class works properly.
     * This test also utilizes the "B" concept of the right-BICEP concept that states
     * "Are the boundary conditions correct?"
     * This test also applies to the R in the CORRECT concept.
     */
    @ParameterizedTest
    @ValueSource(ints = {10, 20, 30, 40})
    void randInt(int int1) {
        int randomInt = Util.randInt(int1,int1+10);
        assertTrue(randomInt > int1 || randomInt < int1+10);
    }

    @Test
    void readPhysicalDrive() {
    }

    @Test
    void sysStats() {
    }

    @Test
    void displayString() {
    }

    @Test
    void getDriveType() {
    }

    @Test
    void getDiskInfo() {
    }

    /**
     * This test makes sure that the hard drive exists. This test also utilizes the "R" concept
     * of the right-BICEP concept that states "Are the results right?"
     */
    @Test
    void getModelFromLetter() {
        String driveLetter = "C";
        String test = Util.getModelFromLetter2(driveLetter);

        assertNotNull(test);
    }

    @Test
    void getModelFromLetter2() {
    }

    @Test
    void getDeviceFromPath() {
    }

    @Test
    void getDeviceModel() {
    }

    @Test
    void getDeviceSize() {
    }

    @Test
    void getDeviceFromPathOSX() {
    }

    @Test
    void getDeviceModelOSX() {
    }
}