package com.gy.common.util;

import com.gy.common.util.entity.MatchCounterInfo;
import com.gy.common.util.impl.MatchCounter;
import com.gy.common.util.impl.ReplayManage;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hcj on 3/12/17.
 */
public class Main {
    public static void main(String args[]) {
        try {
            //Scanner in = new Scanner(System.in);
            //System.out.println("base directory");
            //String directory = in.nextLine();
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
            // TODO: 3/20/17 14:00 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/template/";
            // TODO: 3/20/17 15:00 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/template/";
            // TODO: 3/20/17 15:20 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/task/";
            // TODO: 3/20/17 15:45 暂时不执行了,别的同事手动改了
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/stock/";
            // TODO: 3/23/17 11:35 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/ic/";
            // TODO: 3/23/17 13:35 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/finance/trade/";
            // TODO: 3/23/17 14:25 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/finance/purchase/";
            // TODO: 3/23/17 15:22 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/admin/finance/";
            // TODO: 3/23/17 15:22 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/admin/order/";
            // TODO: 3/23/17 15:22 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/admin/";
            // TODO: 3/23/17 16:24 已经执行过这个目录了，不要在执行，注释掉
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/index/";
            // TODO: 3/24/17 14:00 准备执行
            String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/info/";
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/info/express/";
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/info/email/";
            //String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/info/platform/";

            //String version = "";
            String version = "-v2.0";
            //String version = "-finance-v1.0";
            //String version = "-admin-v1.0";

            //String keywordString = "^[^//*]*[\\u4e00-\\u9fa5]+";
            //input file dir
            File dirFile = new File(directory);
            //output file
            String file = System.getProperty("user.dir") + "/temp/" + dirFile.getName() + "-outputfile" + version + ".txt";

            MatchCounterInfo matchCounterInfo = new MatchCounterInfo();
            matchCounterInfo.setDirectoryFile(dirFile);
            // cerp.[程序].[模块].[功能].[key]
            //matchCounterInfo.setProgramName("web");
            //matchCounterInfo.setModuleName("index");
            //matchCounterInfo.setFeatures("manage");

            ExecutorService pool = Executors.newCachedThreadPool();//线程池
            MatchCounter dataArrayList = new MatchCounter(pool, matchCounterInfo);
            Future<ArrayList<String>> resultFuture = pool.submit(dataArrayList);//获取结果

            //输出结果
            int i = 0;
            FileOutputStream fos = new FileOutputStream(file, true);
            for (String string : resultFuture.get()) {
                i++;
                fos.write(string.getBytes());
                //System.out.print(string);
                if (i % 8 == 0) {
                    //System.out.println();
                    fos.write(System.getProperty("line.separator").getBytes());
                }
            }
            fos.close();
            pool.shutdown();
            try {
                System.out.println("Thread sleep 5s ....");
                Thread.sleep(5000);
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }
            // ReplaySpringMessage Main:
            //String outFile = System.getProperty("user.dir") + "/temp/" + dirFile.getName() + "-flag-messages" + version + ".txt";
            //String oneOutFile = System.getProperty("user.dir") + "/temp/" + dirFile.getName() + "-messages" + version + ".properties";
            String outFile = System.getProperty("user.dir") + "/temp/" + dirFile.getName() + "-messages" + version + ".txt";
            String oneOutFile = System.getProperty("user.dir") + "/temp/" + dirFile.getName() + "-common-messages" + version + ".txt";

            Boolean isDir = matchCounterInfo.getProgramName() != null ? true : false;
            //Boolean isDir = true;

            ReplayManage replayManage = new ReplayManage(file, outFile, oneOutFile, isDir);
            replayManage.doReplay(dirFile.getName());

            System.exit(0);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
