package com.gy.common.util.impl;

import java.io.*;
import java.util.*;

/**
 * Created by hcj on 3/13/17.
 */
public class ReplayManage {
    private File file;

    public ReplayManage(File file) {
        this.file = file;
    }

    public void doUnique() throws InterruptedIOException {
        try {
            if (file.isFile() && file.exists()) {
                BufferedReader inScanner = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String string;
                Map<String, Integer> map = new HashMap<String, Integer>();
                while ((string = inScanner.readLine()) != null) {
                    dealLine(string, map);
                }
                sortResult(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dealLine(String str, Map map) {
        if (str.length() > 0) {
            Integer num = (Integer) map.get(str);
            if (num == null) {
                num = 0;
            }
            map.put(str, num + 1);
        }
    }

    private void sortResult(Map result) {
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(result.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getValue() + ":" + entry.getKey());
        }
    }
}
