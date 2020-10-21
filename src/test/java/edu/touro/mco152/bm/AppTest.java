package edu.touro.mco152.bm;

import edu.touro.mco152.bm.ui.Gui;
import edu.touro.mco152.bm.ui.MainFrame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    /**
     * Bruteforce setup of static classes/fields to allow DiskWorker to run.
     *
     * @author lcmcohen
     */
    private void setupDefaultAsPerProperties()
    {
        /// Do the minimum of what  App.init() would do to allow to run.
        Gui.mainFrame = new MainFrame();
        App.p = new Properties();
        App.loadConfig();
        System.out.println(App.getConfigString());
        Gui.progressBar = Gui.mainFrame.getProgressBar(); //must be set or get Nullptr

        // configure the embedded DB in .jDiskMark
        System.setProperty("derby.system.home", App.APP_CACHE_DIR);

        // code from startBenchmark
        //4. create data dir reference
        App.dataDir = new File(App.locationDir.getAbsolutePath()+File.separator+App.DATADIRNAME);

        //5. remove existing test data if exist
        if (App.dataDir.exists()) {
            if (App.dataDir.delete()) {
                App.msg("removed existing data dir");
            } else {
                App.msg("unable to remove existing data dir");
            }
        }
        else
        {
            App.dataDir.mkdirs(); // create data dir if not already present
        }
    }

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