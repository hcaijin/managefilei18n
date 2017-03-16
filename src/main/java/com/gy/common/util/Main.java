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
            // TODO: 3/15/17 写死
            String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/delivery/";
            String keywordString = "^[^//*]*[\\u4e00-\\u9fa5]+";
            File dirFile = new File(directory);

            //output file
            String file = System.getProperty("user.dir") + "/temp/" + dirFile.getName() + "-outputfile.txt";

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
                    fos.write("\r\n".getBytes());
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
            String outFile = System.getProperty("user.dir") + "/temp/messages.properties";
            ReplayManage replayManage = new ReplayManage(file, outFile);
            replayManage.doReplay(dirFile.getName());

            System.exit(0);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
