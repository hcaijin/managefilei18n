package com.gy.common.util;

import com.gy.common.util.impl.ReplayManage;

import java.io.IOException;

/**
 * Created by hcj on 3/13/17.
 */
public class ReplaySpringMessage {
    public static void main(String args[]) {
        try {
            String file = "/home/hcj/Work/data/managefilei18n/outputfile.txt";
            String outFile = "/home/hcj/Work/data/managefilei18n/messages.properties";
            ReplayManage replayManage = new ReplayManage(file, outFile);
            replayManage.doReplay();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }
}
