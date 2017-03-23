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
            // TODO: 3/20/17 20号11:00 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/vip/vip/";
            // TODO: 3/20/17 15:00 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/template/";
            // TODO: 3/20/17 15:20 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/task/";
            // TODO: 3/20/17 15:45 暂时不执行了
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/stock/";
            // TODO: 3/21/17 17:35 未执行
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/info/";
            // TODO: 3/23/17 11:35 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/ic/";
            // TODO: 3/23/17 13:35 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/finance/trade/";
            // TODO: 3/23/17 13:35 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/finance/purchase/";
            // TODO: 3/23/17 15:22 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/admin/finance/";
            // TODO: 3/23/17 15:22 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/admin/order/";
            // TODO: 3/23/17 15:22 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/admin/";
            // TODO: 3/23/17 16:24 已经执行过这个目录了，不要在执行，注释掉
            String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/index/";

            String version = "";
            //String version = "-v1.0";
            //String version = "-finance-v1.0";
            //String version = "-admin-v1.0";


            File dirFile = new File(directory);
            String file = System.getProperty("user.dir") + "/temp/" + dirFile.getName() + "-messages" + version + ".txt";
            ProcessManage processManage = new ProcessManage(file, directory);
            processManage.doProcess();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
