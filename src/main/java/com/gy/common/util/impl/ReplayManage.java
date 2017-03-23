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

    public ReplayManage(String files, String outFileStr, String oneOutFileStr) {
        this.file = new File(files);
        this.outFile = new File(outFileStr);
        this.oneOutFile = new File(oneOutFileStr);
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
                List<String> olists = getMapList(maps);
                //for (String olist : olists) {
                //    System.out.println(olist);
                //}
                //List<Map.Entry<String, Integer>> lists = ToolUtil.sortResult(maps);
                //int totalNum = saveToFile(name, lists);

                int totalNum = saveToFile(name, olists);
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
    //private int saveToFiles(String name, List<Map.Entry<String, Integer>> list) throws IOException {
    //    FileOutputStream onefos = new FileOutputStream(oneOutFile, true);
    //    FileOutputStream fos = new FileOutputStream(outFile, true);
    //    String onestartLine = "# =============================" + name + " only one times===============================";
    //    String startLine = "# =================================" + name + "========================================";
    //    onefos.write(onestartLine.getBytes());
    //    onefos.write(FILENF.getBytes());
    //    fos.write(startLine.getBytes());
    //    fos.write(FILENF.getBytes());
    //    for (Map.Entry<String, Integer> entry : list) {
    //        //System.out.println(entry.getValue() + ":" + entry.getKey());
    //        String valueKey = entry.getKey();
    //        Integer value = entry.getValue();
    //        if (valueKey != null && valueKey.length() > 0) {
    //            String[] arrayStr = valueKey.split("@@@@");
    //            if (arrayStr.length == 2) {
    //                //String line = valueKey;
    //                if (value == 1) {
    //                    onefos.write(valueKey.getBytes());
    //                    onefos.write(FILENF.getBytes());
    //                } else {
    //                    //line = valueKey.replaceAll("\\.web\\.", ".common.");
    //                    fos.write((value + "@@@@" + valueKey).getBytes());
    //                    fos.write(FILENF.getBytes());
    //                }
    //            }
    //        }
    //    }
    //    onefos.write("# ==================Only one times is a dignity in the dividing line===============".getBytes());
    //    onefos.write(FILENF.getBytes());
    //    onefos.write(FILENF.getBytes());
    //    onefos.close();
    //
    //    fos.write("# ======================It is a dignity in the dividing line====================".getBytes());
    //    fos.write(FILENF.getBytes());
    //    fos.write(FILENF.getBytes());
    //    fos.close();
    //    return list.size();
    //}

    //private int saveToFile(String name, List<Map.Entry<String, Integer>> list) throws IOException {
    //    FileOutputStream fos = new FileOutputStream(outFile, true);
    //    String startLine = "# =================================" + name + "========================================";
    //    fos.write(startLine.getBytes());
    //    fos.write(FILENF.getBytes());
    //    for (Map.Entry<String, Integer> entry : list) {
    //        System.out.println(entry.getValue() + ":" + entry.getKey());
    //        String valueKey = entry.getKey();
    //        //Integer value = entry.getValue();
    //        if (valueKey != null && valueKey.length() > 0) {
    //            String[] arrayStr = valueKey.split("@@@@");
    //            if (arrayStr.length == 2) {
    //                //line = valueKey.replaceAll("\\.web\\.", ".common.");
    //                fos.write(valueKey.getBytes());
    //                fos.write(FILENF.getBytes());
    //            }
    //        }
    //    }
    //    fos.write("# ======================It is a dignity in the dividing line====================".getBytes());
    //    fos.write(FILENF.getBytes());
    //    fos.write(FILENF.getBytes());
    //    fos.close();
    //    return list.size();
    //}
    private int s_saveToFile(String name, List<Map.Entry<String, Integer>> list) throws IOException {
        FileOutputStream fos = new FileOutputStream(outFile, true);
        String startLine = "# =================================" + name + "========================================";
        fos.write(startLine.getBytes());
        fos.write(FILENF.getBytes());
        Map<String, Integer> maps = new HashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            //System.out.println(entry.getValue() + ":" + entry.getKey());
            String valueKey = entry.getKey();
            //Integer value = entry.getValue();
            if (valueKey != null && valueKey.length() > 0) {
                String[] arrayStr = valueKey.split("@@@@");
                if (arrayStr.length == 2) {
                    //line = valueKey.replaceAll("\\.web\\.", ".common.");
                    String key = arrayStr[0];
                    String keyName = arrayStr[1];
                    dealLine(keyName, maps);
                    fos.write(valueKey.getBytes());
                    fos.write(FILENF.getBytes());
                }
            }
        }
        fos.write("# ======================It is a dignity in the dividing line====================".getBytes());
        fos.write(FILENF.getBytes());
        fos.write(FILENF.getBytes());
        fos.close();
        List<Map.Entry<String, Integer>> lists = ToolUtil.sortResult(maps);
        for (Map.Entry<String, Integer> entrys : lists) {
            System.out.println(entrys.getValue() + ":" + entrys.getKey());
        }
        return list.size();
    }

    //private String dealLine(String key, String keyName, Map map) {
    //    StringBuilder res = new StringBuilder("");
    //    if (keyName != null && keyName.length() > 0) {
    //        Integer num = (Integer) map.get(keyName);
    //        if (num == null) {
    //            map.put(keyName, num + 1);
    //            res = res.append(key).append("=").append(keyName);
    //        } else {
    //            String[] arrKey = key.split("\\.");
    //            String line = key.replace("\\." + arrKey[3] + "\\.", ".").replace("\\.web\\.", ".common.");
    //            res = res.append(line).append("=").append(keyName);
    //        }
    //    }
    //    return res.toString();
    //}

    private List<String> getMapList(List<Map.Entry<String, Integer>> list) {
        Map<String, Integer> maps = new HashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            String valueKey = entry.getKey();
            if (valueKey != null && valueKey.length() > 0) {
                String[] arrayStr = valueKey.split("@@@@");
                if (arrayStr.length == 2) {
                    //String key = arrayStr[0];
                    String keyName = arrayStr[1];
                    dealLine(keyName, maps);
                }
            }
        }
        List<String> stringList = new ArrayList<>();
        //Map<String, Integer> mapList = new HashMap<>();
        maps.forEach((key, value) -> {
            System.out.println(key + ":" + value);
            //list.forEach((entry) -> {
            //    String valueKey = entry.getKey();
            //    if (valueKey != null && valueKey.length() > 0) {
            //        String[] arrayStr = valueKey.split("@@@@");
            //        if (arrayStr.length == 2) {
            //            String keyValue = arrayStr[0];
            //            String keyName = arrayStr[1];
            //            if (key.equals(keyName) && value == 1) {
            //                stringList.add(valueKey);
            //            } else {
            //                String[] arrKey = keyValue.split("\\.");
            //                stringList.add(keyValue.replace("\\." + arrKey[3] + "\\.", ".").replace("\\.web\\.", ".common."));
            //            }
            //        }
            //    }
            //});
        });
        System.out.println(stringList.size());
        return stringList;
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
            //list.forEach((entry) -> {
            //    String valueKey = entry.getKey();
            //    if (valueKey != null && valueKey.length() > 0) {
            //        String[] arrayStr = valueKey.split("@@@@");
            //        if (arrayStr.length == 2) {
            //            String keyValue = arrayStr[0];
            //            String keyName = arrayStr[1];
            //            if (key.equals(keyName) && value == 1) {
            //                stringList.add(valueKey);
            //            } else {
            //                String[] arrKey = keyValue.split("\\.");
            //                stringList.add(keyValue.replace("\\." + arrKey[3] + "\\.", ".").replace("\\.web\\.", ".common."));
            //            }
            //        }
            //    }
            //});
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

    //private int saveToFile(String name, List<String> list) throws IOException {
    //    FileOutputStream onefos = new FileOutputStream(oneOutFile, true);
    //    FileOutputStream fos = new FileOutputStream(outFile, true);
    //
    //    String onestartLine = "# ================================" + name + "========================================";
    //    String startLine = "# ============================" + name + " have @#@ flag===============================";
    //
    //    onefos.write(onestartLine.getBytes());
    //    onefos.write(FILENF.getBytes());
    //
    //    fos.write(startLine.getBytes());
    //    fos.write(FILENF.getBytes());
    //    list.forEach(temp -> {
    //        try {
    //            fos.write(temp.getBytes());
    //            fos.write(FILENF.getBytes());
    //            onefos.write(temp.replaceAll("@#@", "=").getBytes());
    //            onefos.write(FILENF.getBytes());
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //    });
    //    onefos.write("# =======================It is a dignity in the dividing line====================".getBytes());
    //    onefos.write(FILENF.getBytes());
    //    onefos.write(FILENF.getBytes());
    //    onefos.close();
    //
    //    fos.write("# =================It is a dignity in the dividing line has @#@ flag=================".getBytes());
    //    fos.write(FILENF.getBytes());
    //    fos.write(FILENF.getBytes());
    //    fos.close();
    //    return list.size();
    //}

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
