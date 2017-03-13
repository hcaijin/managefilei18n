import org.junit.Test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hcj on 3/12/17.
 */
public class PatternMatchTest {
    @Test
    public void test() {
        String keywordString = "[\\u4e00-\\u9fa5]+";
        //String string = "       <!-- 会员信息明细按钮 -->";
        String string = "<a class='subEditBtn' href='javascript:void(0);'>更换</a> | <a class='subDelBtn' href='javascript:void(0);'>删除</a>";
        Pattern con = Pattern.compile(keywordString);
        Matcher matcher2 = con.matcher(string);
        while (matcher2.find()) {
            System.out.println(matcher2.group());
            //System.out.println(matcher2.groupCount());
        }
    }

    @Test
    public void testDist() {
        String directory = "/home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/delivery/";
        File directoryFile = new File(directory);
        File[] files = directoryFile.listFiles();
        for (File file : files) {
            Pattern pattern = Pattern.compile("[\\d-]");
            Matcher match = pattern.matcher(file.getName());
            if (!match.find()) {
                System.out.println(file.getPath());
            }
        }
    }

    @Test
    public void test2() {
        //String keywordString = "(^\").*[\\u4e00-\\u9fa5].*[\\u4e00-\\u9fa5]+.[\"$]";
        String keywordString = "[\\u4e00-\\u9fa5]+((\\d-)?[\\x00-\\xff]?)?[\\u4e00-\\u9fa5]+";
        //String string = "       <!-- 会员信息明细按钮 -->";
        String string = "<a class='subEditBtn' href='javascript:void(0);'>更换</a> | <a class='subDelBtn' href='javascript:void(0);'>删除</a>";
        String string2 = "\"EMS单号开头和结尾1-2位是字母，中间是9位数字。\"";
        Pattern con = Pattern.compile(keywordString);
        Matcher matcher = con.matcher(string);
        Matcher matcher2 = con.matcher(string2);
        while (matcher.find()) {
            System.out.println(matcher.group());
            //System.out.println(matcher2.groupCount());
        }
        while (matcher2.find()) {
            System.out.println(matcher2.group());
            //System.out.println(matcher2.groupCount());
        }
    }

    @Test
    public void test3() {
        String keywordString = "^[^//*]*[\\u4e00-\\u9fa5]+";
        //String string = "<!-- 会员信息明细按钮 -->";
        String string = "<a class='subEditBtn' href='javascript:void(0);'>更换</a> | <a class='subDelBtn' href='javascript:void(0);'>删除</a>";
        Pattern con = Pattern.compile(keywordString);
        Matcher matcher2 = con.matcher(string);
        while (matcher2.find()) {
            System.out.println(matcher2.group());
            //System.out.println(matcher2.groupCount());
        }
    }

    @Test
    public void test4() {
        String text = "纸1-2张/大+小(厘米)";
        Pattern pattern = Pattern.compile("[\\-+/()]+");
        Matcher matcher = pattern.matcher(text);
        String result = matcher.replaceAll("");
        System.out.println(result);
    }
}
