package com.gy.common.util.impl;

import com.gy.common.util.tool.ToolUtil;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hcj on 3/16/17.
 */
public class ProcessManage {
    private final static String SHELL_FILE_DIR = System.getProperty("user.dir") + "/bin/";
    private final static String RUNNING_SHELL_FILE = "doSpringI18n.sh";
    /**
     * 输入文件
     */
    private File file;
    /**
     * 操作的目录
     */
    private String fileDirStr;

    public ProcessManage(String file, String fileDirStr) {
        this.file = new File(file);
        this.fileDirStr = fileDirStr;
    }

    public void doProcess() throws InterruptedIOException {
        System.out.println("do process start ing...");
        try {
            if (file.isFile() && file.exists()) {
                BufferedReader inScanner = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String string;
                Map<String, Integer> map = new HashMap<>();
                while ((string = inScanner.readLine()) != null) {
                    dealLines(string, map);
                }
                inScanner.close();
                replaySpringMsg(ToolUtil.sortResult(map));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("do process end.");
    }

    /**
     * 根据字符长度
     *
     * @param str
     * @param map
     */
    private void dealLines(String str, Map map) {
        if (str != null && str.length() > 0) {
            map.put(str, str.length());
        }
    }

    /**
     * 循环替换中文
     *
     * @param list
     */
    private void replaySpringMsg(List<Map.Entry<String, Integer>> list) {
        for (Map.Entry<String, Integer> entry : list) {
            String value = entry.getKey();
            if (value != null && value.length() > 0) {
                String[] str = value.split("@#@");
                if (str.length == 2) {
                    int stat = doProcess(str, fileDirStr, 1);
                    if (stat == 2) {
                        System.out.println(String.format("replay spring message fail: key=%s; name=%s", str[0], str[1]));
                    }
                    //System.out.println(String.format("replay spring message fail: key=%s; name=%s", str[0], str[1]));
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
        //默认2就是返回失败
        int runningStatus = 2;
        String param1 = str[0];
        String param2 = str[1].replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]").replaceAll("#", "\\\\#");
        if (status == 1) {
            ProcessBuilder pb = new ProcessBuilder("./" + RUNNING_SHELL_FILE, param1, param2, param3);
            pb.directory(new File(SHELL_FILE_DIR));
            String s;
            try {
                Process p = pb.start();
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                while ((s = stdInput.readLine()) != null) {
                    System.out.println("stdInput error:" + s);
                }
                while ((s = stdError.readLine()) != null) {
                    System.out.println("stdError error:" + s);
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
