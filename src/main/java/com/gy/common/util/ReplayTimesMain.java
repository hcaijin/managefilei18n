package com.gy.common.util;

import com.gy.common.util.impl.ReplayManage;

/**
 * Created by hcj on 3/24/17.
 */
public class ReplayTimesMain {
    public static void main(String args[]) {
        try {
            doExcutor();

            System.exit(0);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void doExcutor() throws Exception {
        //output file
        String dirFile = System.getProperty("user.dir") + "/temp/info-message.0324.properties";

        String outFile = System.getProperty("user.dir") + "/temp/info-message.outv2.properties";
        String oneOutFile = System.getProperty("user.dir") + "/temp/info-common-message.outv2.properties";

        Boolean isDir = false;

        ReplayManage replayManage = new ReplayManage(dirFile, outFile, oneOutFile, isDir);
        replayManage.doReplay("do replay times");
    }
}
