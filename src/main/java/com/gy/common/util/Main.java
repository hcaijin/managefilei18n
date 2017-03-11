package com.gy.common.util;

import com.gy.common.util.impl.ManageFile;

/**
 * Created by hcj on 3/11/17.
 */
public class Main {
    public static void main(String[] args) {
        String folder = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/delivery/";
        ManageFile manageFile = new ManageFile();
        manageFile.setRootPath(folder);
        manageFile.getFileList();
    }
}
