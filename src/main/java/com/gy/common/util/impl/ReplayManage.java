package com.gy.common.util.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * Created by hcj on 3/13/17.
 */
public class ReplayManage {
    private final Logger logger = LoggerFactory.getLogger(ReplayManage.class);
    private final static String FILENF = System.getProperty("line.separator");
    private final static String SHELL_FILE_DIR = System.getProperty("user.dir") + "/bin/";
    private final static String RUNNING_SHELL_FILE = "doSpringI18n.sh";
    /**
     * 输入文件
     */
    private File file;
    /**
     * 输出文件
     */
    private File outFile;
    /**
     * 操作的目录
     */
    private String fileDirStr;

    public ReplayManage(String fileDirStr, String files, String outFileStr) {
        this.file = new File(files);
        this.outFile = new File(outFileStr);
        this.fileDirStr = fileDirStr;
    }

    public void doReplay(String name) throws InterruptedIOException {
        try {
            if (file.isFile() && file.exists()) {
                BufferedReader inScanner = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String string;
                Map<String, Integer> map = new HashMap<String, Integer>();
                while ((string = inScanner.readLine()) != null) {
                    dealLine(string, map);
                }
                List<Map.Entry<String, Integer>> list = sortResult(map);
                replaySpringMsg(list);
                int totalNum = saveToFile(name, list);
                System.out.println("total line:" + totalNum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 去重，统计重复次数
     *
     * @param str
     * @param map
     */
    private void dealLine(String str, Map map) {
        if (str.length() > 0) {
            Integer num = (Integer) map.get(str);
            if (num == null) {
                num = 0;
            }
            map.put(str, num + 1);
        }
    }

    /**
     * 排序结果
     *
     * @param result
     * @return
     */
    private List<Map.Entry<String, Integer>> sortResult(Map result) {
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(result.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        return list;
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
            String value = entry.getKey();
            if (value != null) {
                String[] arrayStr = value.split("=");
                if (arrayStr.length == 2) {
                    fos.write(value.getBytes());
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

    /**
     * 循环替换中文
     *
     * @param list
     */
    private void replaySpringMsg(List<Map.Entry<String, Integer>> list) {
        for (Map.Entry<String, Integer> entry : list) {
            String value = entry.getKey();
            if (value.length() > 0) {
                String[] str = value.split("=");
                if (str.length == 2) {
                    int stat = doProcess(str, fileDirStr, 1);
                    if (stat == 200) {
                        System.out.println(String.format("replay spring message Success: key=%s; name=%s", str[0], str[1]));
                    }
                }
            }
        }
    }

    /**
     * 执行bash脚本程序,支持两种写法，根据status参数走不同的方法
     *
     * @param str
     * @param param3
     * @param status
     * @return
     */
    private int doProcess(String[] str, String param3, int status) {
        int runningStatus = 0;
        String param1 = str[0];
        String param2 = str[1];
        if (status == 1) {
            ProcessBuilder pb = new ProcessBuilder("./" + RUNNING_SHELL_FILE, param1, param2, param3);
            pb.directory(new File(SHELL_FILE_DIR));
            String s;
            try {
                Process p = pb.start();
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                while ((s = stdInput.readLine()) != null) {
                    logger.error(s);
                }
                while ((s = stdError.readLine()) != null) {
                    logger.error(s);
                }
                try {
                    runningStatus = p.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                Process p = Runtime.getRuntime().exec(SHELL_FILE_DIR + RUNNING_SHELL_FILE + " " + param1 + " " + param2 + " " + param3);
                runningStatus = p.waitFor();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return runningStatus;
    }

}
