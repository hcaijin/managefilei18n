package com.gy.common.util;

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
            // TODO: 3/17/17 17号17:00 准备开始：
            String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/info/";

            //input file dir
            File dirFile = new File(directory);
            //output file
            String file = System.getProperty("user.dir") + "/temp/" + dirFile.getName() + "-outputfile.txt";

            String keywordString = "^[^//*]*[\\u4e00-\\u9fa5]+";
            ExecutorService pool = Executors.newCachedThreadPool();//线程池
            MatchCounter dataArrayList = new MatchCounter(dirFile, keywordString, pool);
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
            String outFile = System.getProperty("user.dir") + "/temp/" + dirFile.getName() + "-messages.properties";
            ReplayManage replayManage = new ReplayManage(file, outFile);
            replayManage.doReplay(dirFile.getName());

            System.exit(0);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
