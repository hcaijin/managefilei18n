package com.gy.common.util.impl;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

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
                Pattern pattern = Pattern.compile("[\\d-]");
                Matcher match = pattern.matcher(file.getName());
                if (!match.find()) {
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
            String string;
            while ((string = inScanner.readLine()) != null) {
                //正则表达式匹配，选出行
                Pattern pattern = Pattern.compile(keyword);
                Matcher matcher = pattern.matcher(string);
                if (matcher.find()) {
                    String keywordString2 = "[\\u4e00-\\u9fa5]+((\\d-)?[\\x00-\\xff]?)?[\\u4e00-\\u9fa5]+";
                    Pattern con = Pattern.compile(keywordString2);
                    //再匹配一次，挑出内容
                    Matcher matcher2 = con.matcher(string);
                    System.out.println(string);
                    while (matcher2.find()) {
                        String filePath = file.getPath();
                        String[] folder = filePath.split("/");
                        System.out.println(matcher2.group());
                        System.out.println(matcher2.groupCount());
                        String pyString;
                        try {
                            pyString = getPinYin(matcher2.group());
                        } catch (Exception ex) {
                            pyString = "";
                            ex.printStackTrace();
                        }
                        data.add("cerp.web.");
                        data.add(folder[folder.length - 3]);//tc|info
                        data.add(".");
                        data.add(folder[folder.length - 2]);//文件夹
                        data.add(".");
                        data.add(pyString);//中文转换为拼音的节点
                        data.add("=");
                        data.add(matcher2.group());//中文内容
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

    /**
     * 1、需要转拼音的字符大于10就返回字符的首字母,否则取全拼
     * 2、转换成拼音以后的字符大于20则返回前20个字符
     *
     * @param text
     * @return
     * @throws Exception
     */
    private String getPinYin(String text) throws Exception {
        String res;
        Pattern pattern = Pattern.compile("[\\-+/()]+");
        Matcher matcher = pattern.matcher(text);
        String tmpText = matcher.replaceAll("");
        int leng = tmpText.length();
        if (leng > 10) {
            res = PinyinHelper.getShortPinyin(tmpText);
        } else {
            //WITH_TONE_NUMBER: 加上音标识别 WITHOUT_TONE:不识别
            res = PinyinHelper.convertToPinyinString(tmpText, "", leng < 4 ? PinyinFormat.WITH_TONE_NUMBER : PinyinFormat.WITHOUT_TONE);
        }
        if (res.length() > 20) {
            return res.substring(0, 20);
        }
        return res;
    }
}