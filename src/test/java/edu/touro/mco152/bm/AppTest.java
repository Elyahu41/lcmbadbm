package edu.touro.mco152.bm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void main() {
    }

    /**
     * This test makes sure that the app initializes within 5 seconds. This test also utilizes the "P" concept
     * of the right-BICEP concept that states "Are performance characteristics within the bound?"
     * This test also applies to the T in the CORRECT concept.
     */
    @Test
    @Timeout(5)
    void init() {
        App.init();
    }

    /**
     * This test makes sure that the app version is 0.4. This test also utilizes the "R" concept
     * of the right-BICEP concept that states "Are the results right?".
     * This test also complies with the C in the CORRECT concept.
     * The last AssertNull method is the broken test.
     */
    @Test
    void getVersion() {
        Properties bp = new Properties();
        String version = "0.0";
        try {
            bp.load(new FileInputStream("build.properties"));
            version = bp.getProperty("version");
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals("0.4",version);
    }

    @Test
    void loadConfig() {
    }

    @Test
    void saveConfig() {
    }

    @Test
    void getConfigString() {
    }

    @Test
    void loadSavedRuns() {
    }

    @Test
    void clearSavedRuns() {
    }

    @Test
    void msg() {
    }

    @Test
    void cancelBenchmark() {
    }

    @Test
    void startBenchmark() {
    }

    @Test
    void targetMarkSizeKb() {
    }

    @Test
    void targetTxSizeKb() {
    }

    @Test
    void updateMetrics() {
    }

    @Test
    void resetSequence() {
    }

    @Test
    void resetTestData() {
    }
}