package com.gy.common.util;

import com.gy.common.util.impl.MatchCounter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hcj on 3/12/17.
 */
public class FileSearch {
    public static void main(String args[]) {
        try {
            //Scanner in = new Scanner(System.in);
            //System.out.println("base directory");
            //String directory = in.nextLine();
            String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/delivery/";
            String keywordString = "^[^//*]*[\\u4e00-\\u9fa5]+";

            ExecutorService pool = Executors.newCachedThreadPool();//线程池
            MatchCounter dataArrayList = new MatchCounter(new File(directory), keywordString, pool);
            Future<ArrayList<String>> resultFuture = pool.submit(dataArrayList);//获取结果

            //输出结果
            int i = 0;
            FileOutputStream fos = new FileOutputStream("/home/hcj/Work/data/managefilei18n/outputfile.txt", true);
            for (String string : resultFuture.get()) {
                i++;
                fos.write(string.getBytes());
                System.out.print(string);
                if (i % 8 == 0) {
                    System.out.println();
                    fos.write("\r\n".getBytes());
                }
            }
            fos.close();
            pool.shutdown();
            System.exit(0);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
