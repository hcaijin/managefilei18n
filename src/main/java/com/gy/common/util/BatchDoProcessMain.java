package com.gy.common.util;

import com.gy.common.util.impl.ProcessManage;

import java.io.File;
import java.io.InterruptedIOException;

/**
 * Created by hcj on 3/16/17.
 */
public class BatchDoProcessMain {
    public static void main(String args[]) {
        try {
            // TODO: 3/23/17 17:00 准备执行
            String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/info/";

            String dir = "/home/hcj/Work/data/managefilei18n/temp/infov2/";

            doBatch(directory, dir);

            //String version = "";

            //File dirFile = new File(dir);

            //File[] listFiles = dirFile.listFiles();
            //for (File listFile : listFiles) {
            //    if (listFile.isFile()) {
            //        //String file = System.getProperty("user.dir") + "/temp/" + dirFile.getName() + "-common-messages" + version + ".txt";
            //        ProcessManage processManage = new ProcessManage(listFile.getAbsolutePath(), directory + listFile.getName().replace(".properties.bak", "/"));
            //        processManage.doProcess();
            //    }
            //    try {
            //        System.out.println("Thread sleep 5s ....");
            //        Thread.sleep(5000);
            //    } catch (InterruptedException iex) {
            //        iex.printStackTrace();
            //    }
            //}
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private static void doBatch(String directory, String dir) throws InterruptedIOException {
        File dirFile = new File(directory);

        File[] listFiles = dirFile.listFiles();
        for (File listFile : listFiles) {
            String file = dir + listFile.getName() + ".properties.bak";
            if (listFile.isDirectory() && !"platform".equals(listFile.getName())) {
                //if (listFile.isDirectory() && "express".equals(listFile.getName())) {
                ProcessManage processManage = new ProcessManage(file, listFile.getAbsolutePath());
                processManage.doProcess();
            }
            try {
                System.out.println("Thread sleep 5s ....");
                Thread.sleep(5000);
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }
        }
    }
}
