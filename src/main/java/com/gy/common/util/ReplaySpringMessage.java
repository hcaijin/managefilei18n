package com.gy.common.util;

import com.gy.common.util.impl.ReplayManage;

import java.io.File;
import java.io.IOException;

/**
 * Created by hcj on 3/13/17.
 */
public class ReplaySpringMessage {
    public static void main(String args[]) {
        try {
            // TODO: 3/15/17 写死 
            String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/delivery/";
            File dirFile = new File(directory);
            String file = System.getProperty("user.dir") + "/temp/" + dirFile.getName() + "-outputfile.txt";
            String outFile = System.getProperty("user.dir") + "/temp/messages.properties";
            ReplayManage replayManage = new ReplayManage(directory, file, outFile);
            replayManage.doReplay("fdsa");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }
}
