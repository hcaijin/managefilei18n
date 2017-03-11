package com.gy.common.util.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hcj on 3/12/17.
 */
public class MatchCounter implements Callable<ArrayList<String>> {

    private File directoryFile;
    private String keyword;
    private ExecutorService pool;

    public MatchCounter(File directoryFile, String keyword, ExecutorService pool) {
        this.directoryFile = directoryFile;
        this.keyword = keyword;
        this.pool = pool;
    }

    @Override
    public ArrayList<String> call() throws Exception {
        // TODO Auto-generated method stub
        ArrayList<String> dataArrayList = new ArrayList<String>();
        ArrayList<String> temp;
        try {
            File[] files = directoryFile.listFiles();
            ArrayList<Future<ArrayList<String>>> results = new ArrayList<Future<ArrayList<String>>>();

            for (File file : files) {
                if (file.isDirectory()) {//查找所有文件，加入数组
                    MatchCounter counter = new MatchCounter(file, keyword, pool);
                    Future<ArrayList<String>> resultFuture = pool.submit(counter);
                    results.add(resultFuture);
                } else {//查找文件内容
                    System.out.println(file.getName());
                    if ((temp = search(file)) != null)
                        dataArrayList.addAll(temp);
                }
            }
            //统计结果
            for (Future<ArrayList<String>> resultFuture : results) {
                try {
                    dataArrayList.addAll(resultFuture.get());
                } catch (ExecutionException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dataArrayList;
    }

    public ArrayList<String> search(File file) throws InterruptedException {
        try {
            ArrayList<String> data = new ArrayList<String>();
            BufferedReader inScanner = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            boolean found = false;
            String string;
            while ((string = inScanner.readLine()) != null) {
                //正则表达式匹配，选出行
                Pattern pattern = Pattern.compile(keyword);
                Matcher matcher = pattern.matcher(string);
                if (matcher.find()) {
                    found = true;
                    Pattern con = Pattern.compile("[\u4e00-\u9fa5]+");
                    //再匹配一次，挑出内容
                    Matcher matcher2 = con.matcher(string);
                    System.out.println(string);
                    if (matcher2.find()) {
                        String filePath = file.getPath();
                        String[] folder = filePath.split("/");
                        System.out.println(matcher2.group());
                        System.out.println(matcher2.groupCount());
                        data.add(" |");//大类
                        data.add(folder[folder.length - 3] + "|");//文件夹
                        data.add(" |");//类型
                        data.add(folder[folder.length - 2] + "|");//文件夹
                        data.add(" |");//
                        data.add(matcher2.group() + "|");//中文内容
                    }
                }
            }
            inScanner.close();
            return data;
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
}