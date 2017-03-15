package com.gy.common.util.impl;


import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hcj on 3/13/17.
 */
public class ReplayManage {
    //private final static Logger logger = LoggerFactory.getLogger(ReplayManage.class);
    private final static String REPLAYSTRING = "<@spring.message \"%s\"/>";
    private final static String ENCODING = "UTF-8";
    private final static String FILENF = System.getProperty("line.separator");
    private final static String SHELL_FILE_DIR = "/home/hcj/bin/";
    private final static String RUNNING_SHELL_FILE = "doSpringI18n.sh";
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

    public void doReplay() throws InterruptedIOException {
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
                int totalNum = saveToFile(list);
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
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        System.out.println(list.size());
        return list;
    }

    /**
     * 保存为properties
     *
     * @param list
     * @throws IOException
     */
    private int saveToFile(List<Map.Entry<String, Integer>> list) throws IOException {
        FileOutputStream fos = new FileOutputStream(outFile, true);
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
        fos.close();
        return list.size();
    }

    //private void replaySpringMsg(List<Map.Entry<String, Integer>> list) {
    //    String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/delivery/";
    //    ExecutorService pool = Executors.newCachedThreadPool();//线程池
    //    pool.submit(new ReplayMsgThead(new File(directory), list));
    //}

    //private void replaySpringMsg(List<Map.Entry<String, Integer>> list) {
    //    File dirFile = new File("/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/delivery/");
    //    //ExecutorService pool = Executors.newCachedThreadPool();//线程池
    //    File[] files = dirFile.listFiles();
    //    for (File file : files) {
    //        Pattern pattern = Pattern.compile("[\\d-]");
    //        Matcher match = pattern.matcher(file.getName());
    //        if (!match.find()) {
    //            //pool.submit(new ReplayMsgThead(file, list));
    //            for (Map.Entry<String, Integer> entry : list) {
    //                String value = entry.getKey();
    //                if (value.length() > 0) {
    //                    String[] str = value.split("=");
    //                    if (str.length == 2) {
    //                        int stat = doProcess(str[0], str[1], file.getAbsolutePath());
    //                        if (stat == 200) {
    //                            System.out.println("Success: " + file.getPath());
    //                            //} else {
    //                            //    System.out.println("Fail: " + file);
    //                        }
    //                    }
    //                }
    //            }
    //        }
    //    }
    //}

    private void replaySpringMsg(List<Map.Entry<String, Integer>> list) {
        String fileStr = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/delivery/";
        //ExecutorService pool = Executors.newCachedThreadPool();//线程池
        for (Map.Entry<String, Integer> entry : list) {
            String value = entry.getKey();
            if (value.length() > 0) {
                String[] str = value.split("=");
                if (str.length == 2) {
                    int stat = doProcess(str[0], str[1], fileStr);
                    if (stat == 200) {
                        System.out.println("Success: " + file.getPath());
                    }
                }
            }
        }
    }

    //private void replaySpringMsg(List<Map.Entry<String, Integer>> list) {
    //    File dirFile = new File("/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/delivery/");
    //    //ExecutorService pool = Executors.newCachedThreadPool();//线程池
    //    File[] files = dirFile.listFiles();
    //    for (File file : files) {
    //        Pattern pattern = Pattern.compile("[\\d-]");
    //        Matcher match = pattern.matcher(file.getName());
    //        if (!match.find()) {
    //            //pool.submit(new ReplayMsgThead(file, list));
    //            for (Map.Entry<String, Integer> entry : list) {
    //                String value = entry.getKey();
    //                if (value.length() > 0) {
    //                    String[] str = value.split("=");
    //                    if (str.length == 2) {
    //                        int stat = doProcess(str[0], str[1], file.getAbsolutePath());
    //                        if (stat == 200) {
    //                            System.out.println("Success: " + file.getPath());
    //                            //} else {
    //                            //    System.out.println("Fail: " + file);
    //                        }
    //                    }
    //                }
    //            }
    //        }
    //    }
    //}

    //private void replaySpringMsg(List<Map.Entry<String, Integer>> list) {
    //    String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/delivery/";
    //    ExecutorService pool = Executors.newCachedThreadPool();//线程池
    //    for (Map.Entry<String, Integer> entry : list) {
    //        String value = entry.getKey();
    //        if (value.length() > 0) {
    //            String[] str = value.split("=");
    //            if (str.length == 2) {
    //                pool.submit(new ReplayMsgThead(new File(directory), str));
    //            }
    //        }
    //    }
    //}

    //class ReplayMsgThead implements Callable {
    //    private File file;
    //    private String msgKey;
    //    private String msgName;
    //
    //    ReplayMsgThead(File file, String[] str) {
    //        this.file = file;
    //        this.msgKey = str[0];
    //        this.msgName = str[1];
    //    }
    //
    //    @Override
    //    public Object call() throws Exception {
    //        try {
    //            File[] files = file.listFiles();
    //            for (File file : files) {
    //                Pattern pattern = Pattern.compile("[\\d-]");
    //                Matcher match = pattern.matcher(file.getName());
    //                if (!match.find()) {
    //                    //operationFile(file, msgKey, msgName);
    //                }
    //            }
    //        } catch (Exception ex) {
    //            ex.printStackTrace();
    //        }
    //        return null;
    //    }
    //}

    //class ReplayMsgThead implements Callable {
    //    private File file;
    //    private List<Map.Entry<String, Integer>> list;
    //
    //    ReplayMsgThead(File file, List<Map.Entry<String, Integer>> list) {
    //        this.file = file;
    //        this.list = list;
    //    }
    //
    //    @Override
    //    public Object call() throws Exception {
    //        try {
    //            File[] files = file.listFiles();
    //            for (File file : files) {
    //                Pattern pattern = Pattern.compile("[\\d-]");
    //                Matcher match = pattern.matcher(file.getName());
    //                if (!match.find()) {
    //                    //operationFile(file, list);
    //                    String fileStr = readToString(file);
    //                    for (Map.Entry<String, Integer> entry : list) {
    //                        String value = entry.getKey();
    //                        if (value.length() > 0) {
    //                            String[] str = value.split("=");
    //                            if (str.length == 2) {
    //                                String msgKey = str[0];
    //                                String msgName = str[1];
    //                                //replayAll(msgKey, msgName, fileStr);
    //                                Pattern pattern2 = Pattern.compile(msgName);
    //                                Matcher matcher2 = pattern2.matcher(fileStr);
    //                                if (matcher2.find()) {
    //                                    String newFileStr = matcher2.replaceAll(String.format(REPLAYSTRING, msgKey));
    //                                }
    //                            }
    //                        }
    //                    }
    //                }
    //            }
    //        } catch (Exception ex) {
    //            ex.printStackTrace();
    //        }
    //        return null;
    //    }
    //}
    //
    //private void replayAll(String msgKey, String msgName, String fileStr) {
    //    Pattern pattern2 = Pattern.compile(msgName);
    //    Matcher matcher2 = pattern2.matcher(fileStr);
    //    String res = "";
    //    while (matcher2.find()) {
    //        res = matcher2.replaceAll(String.format(REPLAYSTRING, msgKey));
    //    }
    //    Matcher matcher3 = pattern2.matcher(res);
    //    if (matcher3.find()) {
    //        replayAll(msgKey, msgName, res);
    //    }
    //}
    //
    private void operationFile(File file, String msgKey, String msgName) throws InterruptedException {
        try {
            String fileName = file.getName();
            File tmpfile = new File("/home/hcj/Work/data/managefilei18n/temp/" + fileName + ".tmp");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tmpfile));

            BufferedReader inScanner = new BufferedReader(new InputStreamReader(new FileInputStream(file), ENCODING));
            String string;
            boolean flag = false;
            while ((string = inScanner.readLine()) != null) {
                Pattern pattern = Pattern.compile("^//.*");
                Matcher matcher = pattern.matcher(string);
                if (matcher.find()) {
                    String newLine = string.replaceAll(msgName, String.format(REPLAYSTRING, msgKey));
                    writer.write(newLine + FILENF);
                    flag = true;
                } else {
                    writer.write(string + FILENF);
                }
            }
            inScanner.close();

            writer.flush();
            writer.close();

            if (flag) {
                file.delete();
                tmpfile.renameTo(new File(file.getAbsolutePath()));
            } else {
                tmpfile.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //private void operationFile(File file, String msgKey, String msgName) throws InterruptedException {
    //    try {
    //        // 内存流, 作为临时流
    //        CharArrayWriter tempStream = new CharArrayWriter();
    //
    //        BufferedReader inScanner = new BufferedReader(new InputStreamReader(new FileInputStream(file), ENCODING));
    //        String string;
    //        while ((string = inScanner.readLine()) != null) {
    //            Pattern pattern = Pattern.compile("^//.*");
    //            Matcher matcher = pattern.matcher(string);
    //            if (matcher.find()) {
    //                String newLine = string.replaceAll(msgName, String.format(REPLAYSTRING, msgKey));
    //                tempStream.write(newLine);
    //            } else {
    //                tempStream.write(string);
    //            }
    //            tempStream.append(FILENF);
    //        }
    //        inScanner.close();
    //
    //        // 将内存中的流 写入文件
    //        FileWriter out = new FileWriter(file);
    //        tempStream.writeTo(out);
    //        out.close();
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //}

    //private void doSystemBash(int num) {
    //    try {
    //        BufferedReader inScanner = new BufferedReader(new InputStreamReader(new FileInputStream(outFile), ENCODING));
    //        int lineNum = 0;
    //        while (inScanner.readLine() != null) {
    //            lineNum++;
    //        }
    //        inScanner.close();
    //        if (lineNum == num) {
    //            doProcess(0);
    //        }
    //    } catch (Exception ex) {
    //        ex.printStackTrace();
    //    }
    //}

    //private void doProcess(int status) {
    //    if (status == 1) {
    //        ProcessBuilder pb = new ProcessBuilder("./" + RUNNING_SHELL_FILE, keyword.trim(),
    //                taskId.toString(), fileName);
    //        pb.directory(new File(CASPERJS_FILE_DIR));
    //        int runningStatus;
    //        String s;
    //        try {
    //            Process p = pb.start();
    //            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
    //            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    //            while ((s = stdInput.readLine()) != null) {
    //                logger.error(s);
    //            }
    //            while ((s = stdError.readLine()) != null) {
    //                logger.error(s);
    //            }
    //            try {
    //                runningStatus = p.waitFor();
    //                if (runningStatus == 0) {
    //                    System.out.println("Success");
    //                }
    //            } catch (InterruptedException e) {
    //                e.printStackTrace();
    //            }
    //        } catch (Exception ex) {
    //            ex.printStackTrace();
    //        }
    //    } else {
    //        try {
    //            Process p = Runtime.getRuntime().exec(SHELL_FILE_DIR + RUNNING_SHELL_FILE + " " + param1 + " " + param2 + " " + param3);
    //            p.waitFor();
    //        } catch (InterruptedException ex) {
    //            ex.printStackTrace();
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }
    //}

    private int doProcess(String param1, String param2, String param3) {
        int res = 0;
        try {
            Process p = Runtime.getRuntime().exec(SHELL_FILE_DIR + RUNNING_SHELL_FILE + " " + param1 + " " + param2 + " " + param3);
            res = p.waitFor();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    //class ReplayMsgThead implements Callable {
    //    private File file;
    //    private List<Map.Entry<String, Integer>> list;
    //
    //    ReplayMsgThead(File file, List<Map.Entry<String, Integer>> list) {
    //        this.file = file;
    //        this.list = list;
    //    }
    //
    //    @Override
    //    public Object call() throws Exception {
    //        try {
    //            for (Map.Entry<String, Integer> entry : list) {
    //                String value = entry.getKey();
    //                if (value.length() > 0) {
    //                    String[] str = value.split("=");
    //                    if (str.length == 2) {
    //                        int stat = doProcess(str[0], str[1], file.getAbsolutePath());
    //                        if (stat == 200) {
    //                            System.out.println("Success: " + file.getPath());
    //                            //} else {
    //                            //    System.out.println("Fail: " + file);
    //                        }
    //                    }
    //                }
    //            }
    //        } catch (Exception ex) {
    //            ex.printStackTrace();
    //        }
    //        return null;
    //    }
    //}
}