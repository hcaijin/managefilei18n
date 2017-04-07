package com.gy.common.util;

import com.gy.common.util.impl.ReplayManage;

import java.io.File;

/**
 * Created by hcj on 3/24/17.
 */
public class ReplayMain {
    public static void main(String args[]) {
        try {
            // TODO: 3/23/17 17:00 准备执行
            String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/info/";

            String version = "";

            File dirFile = new File(directory);

            Boolean isForech = true;
            //Boolean isForech = false;
            if (isForech) {
                File[] listFiles = dirFile.listFiles();
                for (File listFile : listFiles) {
                    //if (listFile.isDirectory() && "express".equals(listFile.getName())) {
                    if (listFile.isDirectory()) {
                        doExcutor(listFile, version);
                    }
                }
            } else {
                doExcutor(dirFile, version);
            }

            System.exit(0);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void doExcutor(File file, String version) throws Exception {
        //output file
        String dirFile = System.getProperty("user.dir") + "/temp/" + file.getName() + "-outputfile" + version + ".txt";

        String outFile = System.getProperty("user.dir") + "/temp/" + file.getName() + "-messages" + version + ".v1.0file";
        String oneOutFile = System.getProperty("user.dir") + "/temp/" + file.getName() + "-common-messages" + version + ".v1.0file";

        Boolean isDir = true;

        ReplayManage replayManage = new ReplayManage(dirFile, outFile, oneOutFile, isDir);
        replayManage.doReplay(file.getName());
    }
}
