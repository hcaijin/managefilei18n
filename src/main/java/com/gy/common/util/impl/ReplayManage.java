package com.gy.common.util.impl;


import com.gy.common.util.tool.ToolUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
     * 没有重复的行的文件
     */
    private File oneOutFile;
    /**
     * 是否自定义目录级别
     */
    private Boolean isDir = false;

    public ReplayManage(String files, String outFileStr, String oneOutFileStr, Boolean isDir) {
        this.file = new File(files);
        this.outFile = new File(outFileStr);
        this.oneOutFile = new File(oneOutFileStr);
        this.isDir = isDir;
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
                int totalNum;
                if (isDir) {
                    List<Map.Entry<String, Integer>> lists = ToolUtil.sortResult(maps);
                    totalNum = saveToFiles(name, lists);
                } else {
                    List<String> olists = getMapList(maps);
                    totalNum = saveToFile(name, olists);
                }
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

    private int saveToFiles(String name, List<Map.Entry<String, Integer>> list) throws IOException {
        FileOutputStream fos = new FileOutputStream(outFile, true);
        String startLine = "# =================================" + name + "========================================";
        fos.write(startLine.getBytes());
        fos.write(FILENF.getBytes());
        for (Map.Entry<String, Integer> entry : list) {
            String valueKey = entry.getKey();
            Integer value = entry.getValue();
            if (valueKey != null && valueKey.length() > 0) {
                String[] arrayStr = valueKey.split("@@@@");
                if (arrayStr.length == 2) {
                    if (value > 5) {
                        valueKey = valueKey.replaceAll("\\.web\\.", ".common.");
                    }
                    valueKey = valueKey.replace("@@@@", "@#@");
                    System.out.println(value + ":" + valueKey);

                    fos.write(valueKey.getBytes());
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

    private List<String> getMapList(Map<String, Integer> map) {
        Map<String, Integer> maps = new HashMap<>();
        Map<String, String> mapss = new HashMap<>();
        map.forEach((key, value) -> {
            String[] arrarStr = key.split("@@@@");
            if (arrarStr.length == 2) {
                dealLine(arrarStr[1], arrarStr[0], maps, mapss);
            }
        });
        List<String> stringList = new ArrayList<>();
        maps.forEach((key, value) -> {
            System.out.println(key + ":" + value);
            String keyValue = mapss.get(key);
            if (value == 1) {
                stringList.add(keyValue + "@#@" + key);
            } else {
                String[] arrKey = keyValue.split("\\.");
                stringList.add(keyValue.replaceAll("\\." + arrKey[3] + "\\.", ".").replaceAll("\\.web\\.", ".common.") + "@#@" + key);
            }
        });
        System.out.println(stringList.size());
        return stringList;
    }

    private void dealLine(String str, String value, Map map, Map maps) {
        if (str != null && str.length() > 0) {
            Integer num = (Integer) map.get(str);
            if (num == null) {
                num = 0;
                maps.put(str, value);
            }
            map.put(str, num + 1);
        }
    }

    private int saveToFile(String name, List<String> list) throws IOException {
        FileOutputStream onefos = new FileOutputStream(oneOutFile, true);
        FileOutputStream fos = new FileOutputStream(outFile, true);

        String onestartLine = "# =============================" + name + " common line===============================";
        String startLine = "# =================================" + name + "========================================";

        onefos.write(onestartLine.getBytes());
        onefos.write(FILENF.getBytes());

        fos.write(startLine.getBytes());
        fos.write(FILENF.getBytes());

        list.forEach(temp -> {
            try {
                if (temp.indexOf("cerp.common.") != -1) {
                    onefos.write(temp.getBytes());
                    onefos.write(FILENF.getBytes());
                } else {
                    fos.write(temp.getBytes());
                    fos.write(FILENF.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        onefos.write("# ==================It is a dignity in the dividing line have common==============".getBytes());
        onefos.write(FILENF.getBytes());
        onefos.write(FILENF.getBytes());
        onefos.close();

        fos.write("# ======================It is a dignity in the dividing line====================".getBytes());
        fos.write(FILENF.getBytes());
        fos.write(FILENF.getBytes());
        fos.close();
        return list.size();
    }
}
