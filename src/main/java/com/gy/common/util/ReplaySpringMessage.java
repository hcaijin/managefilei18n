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
            File file = new File("/home/hcj/Work/data/managefilei18n/outputfile.txt");
            ReplayManage replayManage = new ReplayManage(file);
            replayManage.doUnique();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
