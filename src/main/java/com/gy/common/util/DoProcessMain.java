package com.gy.common.util;

import com.gy.common.util.impl.ProcessManage;

/**
 * Created by hcj on 3/16/17.
 */
public class DoProcessMain {
    public static void main(String args[]) {
        try {
            // TODO: 3/15/17 写死
            String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/delivery/";
            String file = System.getProperty("user.dir") + "/temp/messages.properties";
            ProcessManage processManage = new ProcessManage(file, directory);
            processManage.doProcess();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
