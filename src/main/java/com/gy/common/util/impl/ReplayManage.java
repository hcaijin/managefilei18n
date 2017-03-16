package com.gy.common.util.impl;


import java.io.*;
import java.util.*;

import static com.gy.common.util.tool.ToolUtil.sortResult;

/**
 * Created by hcj on 3/13/17.
 */
public class ReplayManage {
    private final static String FILENF = System.getProperty("line.separator");
    /**
     * 输入文件
     */
    private File file;
    /**
     * 输出文件
     */
    private File outFile;

    public ReplayManage(String files, String outFileStr) {
        this.file = new File(files);
        this.outFile = new File(outFileStr);
    }

    public void doReplay(String name) throws InterruptedIOException {
        System.out.println("do replay start ing...");
        try {
            if (file.isFile() && file.exists()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String strings;
                Map<String, Integer> maps = new HashMap<>();
                while ((strings = reader.readLine()) != null) {
                    dealLine(strings, maps);
                }
                reader.close();
                List<Map.Entry<String, Integer>> lists = sortResult(maps);
                int totalNum = saveToFile(name, lists);
                System.out.println("total line:" + totalNum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("do replay end.");
    }

    /**
     * 去重，统计重复次数
     *
     * @param str
     * @param map
     */
    private void dealLine(String str, Map map) {
        if (str != null && str.length() > 0) {
            Integer num = (Integer) map.get(str);
            if (num == null) {
                num = 0;
            }
            map.put(str, num + 1);
        }
    }

    /**
     * 保存为properties
     *
     * @param list
     * @throws IOException
     */
    private int saveToFile(String name, List<Map.Entry<String, Integer>> list) throws IOException {
        FileOutputStream fos = new FileOutputStream(outFile, true);
        String startLine = "# =================================" + name + "========================================";
        fos.write(startLine.getBytes());
        fos.write(FILENF.getBytes());
        for (Map.Entry<String, Integer> entry : list) {
            //System.out.println(entry.getValue() + ":" + entry.getKey());
            String valueKey = entry.getKey();
            Integer value = entry.getValue();
            if (valueKey != null && valueKey.length() > 0) {
                String[] arrayStr = valueKey.split("=");
                if (arrayStr.length == 2) {
                    String line = valueKey;
                    if (value > 8) {
                        line = valueKey.replaceAll("\\.web\\.", ".common.");
                    }
                    fos.write(line.getBytes());
                    fos.write(FILENF.getBytes());
                }
            }
        }
        fos.write("# ======================It is a dignity in the dividing line====================".getBytes());
        fos.write(FILENF.getBytes());
        fos.write(FILENF.getBytes());
        fos.close();
        return list.size();
    }

}
