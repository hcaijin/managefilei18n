package com.gy.common.util.tool;

import java.io.*;

/**
 * Created by hcj on 3/13/17.
 */
public class ToolUtil {
    private final static String ENCODING = "UTF-8";

    public static String readToString(File file) {
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, ENCODING);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + ENCODING);
            e.printStackTrace();
            return null;
        }
    }
}
