package com.gy.common.util;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hcj on 3/24/17.
 */
public class SplitMain {
    private final static String FILENF = System.getProperty("line.separator");

    public static void main(String args[]) {
        try {
            doExcutor();

            System.exit(0);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void doExcutor() throws Exception {
        //output file
        String dirFile = System.getProperty("user.dir") + "/temp/info-message.outv1.properties";

        SplitManage replayManage = new SplitManage(dirFile);
        replayManage.doSplit();
    }

    static class SplitManage {
        File file;

        SplitManage(String files) {
            this.file = new File(files);
        }

        private void doSplit() {
            System.out.println("do split start ing...");
            try {
                if (file.isFile() && file.exists()) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                    String strings;
                    Map<String, List<String>> map = new HashMap<>();
                    while ((strings = reader.readLine()) != null) {
                        dealLine(strings, map);
                        //saveFile(strings);
                    }
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("do split end.");
        }

        private void saveFile(String str) throws IOException {
            String[] strArr = str.split("@#@");
            if (strArr.length == 2) {
                String[] keyArr = strArr[0].split(".");
                //String fileName = "";
                String name = keyArr[3];
                String fileName = System.getProperty("user.dir") + "/temp/" + name + "-messages-ok.properties";
                File outFile = new File(fileName);
                FileOutputStream fos = new FileOutputStream(outFile, true);
                String startLine = "# =================================" + name + "========================================";
                fos.write(startLine.getBytes());
                fos.write(FILENF.getBytes());

                fos.write(str.getBytes());
                fos.write(FILENF.getBytes());

                fos.write("# ======================It is a dignity in the dividing line====================".getBytes());
                fos.write(FILENF.getBytes());
                fos.write(FILENF.getBytes());
                fos.close();
            }
        }

        private void dealLine(String str, Map map) {
            String[] strArr = str.split("@#@");
            if (strArr.length == 2) {
                String[] keyArr = strArr[0].split(".");
                String name = keyArr[3];
                if (name != null && name.length() > 0) {
                    Integer num = (Integer) map.get(name);
                    map.put(str, num + 1);
                }
            }
        }
    }
}
