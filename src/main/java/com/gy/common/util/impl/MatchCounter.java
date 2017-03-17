package com.gy.common.util.impl;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.gy.common.util.entity.MatchCounterInfo;
import com.gy.common.util.tool.ToolUtil;

import java.io.File;
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

    /**
     * 线程
     */
    private ExecutorService pool;
    /**
     * 匹配的对象
     */
    private MatchCounterInfo info;

    public MatchCounter(ExecutorService pool, MatchCounterInfo info) {
        this.pool = pool;
        this.info = info;
    }

    @Override
    public ArrayList<String> call() throws Exception {
        // TODO Auto-generated method stub
        ArrayList<String> dataArrayList = new ArrayList<String>();
        ArrayList<String> temp = null;
        try {
            File directoryFile = new File(info.getDirectoryFile());
            File[] files = directoryFile.listFiles();
            ArrayList<Future<ArrayList<String>>> results = new ArrayList<Future<ArrayList<String>>>();

            for (File file : files) {
                Pattern pattern = Pattern.compile("[\\d-]");
                Matcher match = pattern.matcher(file.getName());
                if (!match.find()) {
                    if (file.isDirectory()) {//查找所有文件，加入数组
                        MatchCounter counter = new MatchCounter(pool, info);
                        Future<ArrayList<String>> resultFuture = pool.submit(counter);
                        results.add(resultFuture);
                    } else {//查找文件内容
                        //System.out.println(file.getName());
                        String fileStr = ToolUtil.readToString(file);
                        //String patterKey = "<(!|#)--(.|[\r\n])*?-->";
                        String patterKey = "(<(!|#)--(.|[\r\n])*?-->)|(//.*)";
                        Pattern pattern2 = Pattern.compile(patterKey);
                        Matcher matcher2 = pattern2.matcher(fileStr);
                        String replayStr = "";
                        if (matcher2.find()) {
                            replayStr = matcher2.replaceAll("");
                        }
                        if (replayStr.length() > 0) {
                            temp = search(file, replayStr);
                        }
                        if (temp != null) {
                            dataArrayList.addAll(temp);
                        }
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

    private ArrayList<String> search(File file, String string) throws InterruptedException {
        ArrayList<String> data = new ArrayList<String>();
        //String key = "[\\u4e00-\\u9fa5]+";
        String key = "[\\u4e00-\\u9fa5].*[\\u4e00-\\u9fa5]+(\\))?";
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String matchStr2 = matcher.group();
            //System.out.println(matcher.group());
            String keywordString2 = "[\"<>']+";
            Pattern con2 = Pattern.compile(keywordString2);
            Matcher matcher2 = con2.matcher(matchStr2);
            if (matcher2.find()) {
                String keywordString3 = "[\\u4e00-\\u9fa5]+";
                Pattern con3 = Pattern.compile(keywordString3);
                Matcher matcher3 = con3.matcher(matchStr2);
                while (matcher3.find()) {
                    saveData(file, data, matcher3);
                }
            } else {
                saveData(file, data, matcher);
            }
        }
        return data;
    }

    private void saveData(File file, ArrayList<String> data, Matcher matcher) {
        String filePath = file.getPath();
        String[] folder = filePath.split("/");
        //System.out.println(matcher.group());
        //System.out.println(matcher.groupCount());
        String pyString;
        try {
            pyString = getPinYin(matcher.group());
        } catch (Exception ex) {
            pyString = "error";
            ex.printStackTrace();
        }
        data.add(info.getProgramName() != null ? "cerp." + info.getProgramName() + "." : "cerp.web.");
        data.add(info.getModuleName() != null ? info.getModuleName() : folder[folder.length - 3]);//tc|info
        data.add(".");
        data.add(info.getFeatures() != null ? info.getFeatures() : folder[folder.length - 2]);//上级文件夹
        data.add(".");
        data.add(pyString);//中文转换为拼音的节点
        data.add("=");
        data.add(matcher.group());//中文内容
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
        String key = "[^\\u4e00-\\u9fa5]+";
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(text);
        String tmpText = matcher.replaceAll("");
        int leng = tmpText.length();
        if (leng > 10) {
            res = PinyinHelper.getShortPinyin(tmpText);
        } else {
            //WITH_TONE_NUMBER: 加上音标识别 WITHOUT_TONE:不识别
            res = PinyinHelper.convertToPinyinString(tmpText, "", leng < 4 ? PinyinFormat.WITH_TONE_NUMBER : PinyinFormat.WITHOUT_TONE);
        }
        if (res.length() > 25) {
            return res.substring(0, 25);
        }
        return res;
    }

}