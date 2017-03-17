package com.gy.common.util;

import com.gy.common.util.impl.ProcessManage;

import java.io.File;

/**
 * Created by hcj on 3/16/17.
 */
public class DoProcessMain {
    public static void main(String args[]) {
        try {
            // TODO: 3/15/17 15号13:00已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/delivery/";
            // TODO: 3/16/17 16号14:00已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/trade/";
            // TODO: 3/17/17 17号15:00已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/vipjit/";
            // TODO: 3/17/17 17号16:00已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/unpayed/";
            // TODO: 3/17/17 17号17:00 准备开始：
            String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/info/";



            File dirFile = new File(directory);
            String file = System.getProperty("user.dir") + "/temp/" + dirFile.getName() + "-messages.properties";
            ProcessManage processManage = new ProcessManage(file, directory);
            processManage.doProcess();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
