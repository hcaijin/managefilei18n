package com.gy.common.util.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by hcj on 3/11/17.
 */
public class ManageFile {
    /**
     * 定义爬取的目录
     */
    private String rootPath;

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }


    private static Collection<File> listFiles(File root) {
        List<File> files = new ArrayList<File>();
        listFiles(files, root);
        return files;
    }

    private static void listFiles(List<File> files, File dir) {
        File[] listFiles = dir.listFiles();
        for (File f : listFiles) {
            if (f.isFile()) {
                files.add(f);
            } else if (f.isDirectory()) {
                listFiles(files, f);
            }
        }
    }

    private static void run(Runnable task) {
        long start = System.currentTimeMillis();
        task.run();
        System.out.printf("total time:%s\n", System.currentTimeMillis() - start);
    }

    public void getFileList() {
        final File dir = new File(rootPath);
        run(new Runnable() {
            public void run() {
                Collection<File> files = listFiles(dir);
                for (File file : files) {
                    System.out.println(file.getName());
                }
                System.out.printf("use listFiles() find %s file", files.size());
            }
        });
    }
}
