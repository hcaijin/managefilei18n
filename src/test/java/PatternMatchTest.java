import org.junit.Test;

import java.io.File;
import java.util.Properties;
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
        //String key = "[\\-+/()]+";
        String key = "[^\\u4e00-\\u9fa5]+";
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(text);
        String result = matcher.replaceAll("");
        System.out.println(result);
    }

    @Test
    public void test5() {
        String fileStr = "<#assign ctx=\"${rc.contextPath}\">\n" +
                "<script type=\"text/javascript\" src=\"${qiniuCdnUrl}/static/new/js/center_common.js?v=20151022\"></script>\n" +
                "<script type=\"text/javascript\">\n" +
                "    function deliveryOrderSearch() {\n" +
                "        var data = $(\"#frmDeliveryOrderSearch\").serializeObject();\n" +
                "        data.delivery = true;\n" +
                "        $('#deliveryOrderGrid').datagrid('load', data);\n" +
                "    }\n" +
                "    function deliveryOrderCancel() {\n" +
                "        $(\"#frmDeliveryOrderSearch\").resetForm();\n" +
                "    }\n" +
                "\n" +
                "    $(document).unbind('keydown').keydown(function (e) {\n" +
                "        if (e.keyCode == 13) {\n" +
                "            deliveryOrderSearch();\n" +
                "            return false;\n" +
                "        }\n" +
                "    });\n" +
                "</script>\n" +
                "\n" +
                "<!-- 表单编辑区域 -->\n" +
                "<dl id=\"actEdit\" class=\"action-area\">\n" +
                "    <dt class=\"cf\">\n" +
                "    <div class=\"form-name\">搜索</div>\n" +
                "    <span class=\"arrow\"></span>\n" +
                "    </dt>\n" +
                "    <dd>\n" +
                "        <form id=\"frmDeliveryOrderSearch\" method=\"post\">\n" +
                "            <table class=\"form-tb\">\n" +
                "                <tr>\n" +
                "                    <th class=\"tb-lab\">单据时间</th>\n" +
                "                    <td>\n" +
                "                        <select id=\"dateType\" name=\"dateType\" class=\"easyui-combobox combo-w\">\n" +
                "                            <option value=\"0\">最近七天</option>\n" +
                "                            <option value=\"1\">七天以前(已完成)</option>\n" +
                "                        </select>\n" +
                "                    </td>\n" +
                "                    <th class=\"tb-lab\">单据编号</th>\n" +
                "                    <td><input class=\"easyui-validatebox text\" type=\"text\" id=\"code\" name=\"code\"\n" +
                "                               data-options=\"required:false\"/></td>\n" +
                "                    <th class=\"tb-lab\">平台单号</th>\n" +
                "                    <td><input class=\"easyui-validatebox text\" type=\"text\" id=\"platformCode\" name=\"platformCode\"\n" +
                "                               data-options=\"required:false\"/></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <th class=\"tb-lab\">会员名称</th>\n" +
                "                    <td><input class=\"easyui-validatebox text\" type=\"text\" id=\"vipName\" name=\"vipName\"\n" +
                "                               data-options=\"required:false\"/></td>\n" +
                "                    <th class=\"tb-lab\">收货人</th>\n" +
                "                    <td><input class=\"easyui-validatebox text\" type=\"text\" id=\"receiverName\" name=\"receiverName\"\n" +
                "                               data-options=\"required:false\"/></td>\n" +
                "                    <th class=\"tb-lab\">物流公司</th>\n" +
                "                    <td><input class=\"easyui-combobox combo-w\" id=\"expressId\" name=\"expressId\"></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <th class=\"tb-lab\">物流单号</th>\n" +
                "                    <td><input class=\"easyui-validatebox text\" type=\"text\" id=\"mailNo\" name=\"mailNo\"\n" +
                "                               data-options=\"required:false\"/></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td colspan=\"3\">\n" +
                "                        <input class=\"btn btn-primary\" type=\"button\" onclick=\"deliveryOrderSearch()\" value=\"搜索\"/>\n" +
                "                        <input class=\"btn\" type=\"button\" onclick=\"deliveryOrderCancel()\" value=\"重置条件\"/>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </form>\n" +
                "    </dd>\n" +
                "</dl>\n" +
                "\n" +
                "<table id=\"deliveryOrderGrid\"\n" +
                "       data-options=\"\n" +
                "                url:'${ctx}/tc/delivery/delivery_order_header/data/list',\n" +
                "                queryParams:{delivery: true},\n" +
                "                rownumbers: true,\n" +
                "                singleSelect:true,\n" +
                "                 pagination:true,\n" +
                "                idField: 'id'\">\n" +
                "    <thead>\n" +
                "    <tr>\n" +
                "        <th data-options=\"field:'code'\">单据编号</th>\n" +
                "        <th data-options=\"field:'mailNo'\">物流单号</th>\n" +
                "        <th data-options=\"field:'shopName', width:80\">店铺</th>\n" +
                "        <th data-options=\"field:'vipName', width:60\">会员</th>\n" +
                "        <th data-options=\"field:'warehouseName', width:80\">仓库</th>\n" +
                "        <th data-options=\"field:'expressName', width:60\">物流公司</th>\n" +
                "        <th data-options=\"field:'buyerMemo', width:100\">买家留言</th>\n" +
                "        <th data-options=\"field:'sellerMemo', width:80\">卖家备注</th>\n" +
                "        <th data-options=\"field:'receiverName', width:60\">收货人</th>\n" +
                "        <#--<th data-options=\"field:'receiverPhone', width:80\">收货人电话</th>-->\n" +
                "        <#--<th data-options=\"field:'receiverMobile', width:80\">收货人手机</th>-->\n" +
                "        //<th data-options=\"field:'receiverAddress', width:140\">收货地址</th>\n" +
                "        //<th data-options=\"field:'createName', width:80\">制单人</th>\n" +
                "    </tr>\n" +
                "    </thead>\n" +
                "</table>\n";

        //String key1 = "[\\u4e00-\\u9fa5]+";
        String key1 = "[\\u4e00-\\u9fa5].*[\\u4e00-\\u9fa5]+(\\))?";
        String key2 = "(<(!|#)--(.|[\r\n])*?-->)|(//.*)";
        Pattern pattern2 = Pattern.compile(key2);
        Matcher matcher2 = pattern2.matcher(fileStr);
        String replayStr = "";
        if (matcher2.find()) {
            replayStr = matcher2.replaceAll("");
            System.out.println(replayStr);
        }

        if (!"".equals(replayStr)) {
            Pattern pattern1 = Pattern.compile(key1);
            Matcher matcher1 = pattern1.matcher(replayStr);
            while (matcher1.find()) {
                System.out.println(matcher1.group());
            }
        }
    }

    @Test
    public void test6() {
        Properties props = System.getProperties(); //系统属性
        System.out.println("Java的运行环境版本：" + props.getProperty("java.version"));
        System.out.println("Java的运行环境供应商：" + props.getProperty("java.vendor"));
        System.out.println("Java供应商的URL：" + props.getProperty("java.vendor.url"));
        System.out.println("Java的安装路径：" + props.getProperty("java.home"));
        System.out.println("Java的虚拟机规范版本：" + props.getProperty("java.vm.specification.version"));
        System.out.println("Java的虚拟机规范供应商：" + props.getProperty("java.vm.specification.vendor"));
        System.out.println("Java的虚拟机规范名称：" + props.getProperty("java.vm.specification.name"));
        System.out.println("Java的虚拟机实现版本：" + props.getProperty("java.vm.version"));
        System.out.println("Java的虚拟机实现供应商：" + props.getProperty("java.vm.vendor"));
        System.out.println("Java的虚拟机实现名称：" + props.getProperty("java.vm.name"));
        System.out.println("Java运行时环境规范版本：" + props.getProperty("java.specification.version"));
        System.out.println("Java运行时环境规范供应商：" + props.getProperty("java.specification.vender"));
        System.out.println("Java运行时环境规范名称：" + props.getProperty("java.specification.name"));
        System.out.println("Java的类格式版本号：" + props.getProperty("java.class.version"));
        System.out.println("Java的类路径：" + props.getProperty("java.class.path"));
        System.out.println("加载库时搜索的路径列表：" + props.getProperty("java.library.path"));
        System.out.println("默认的临时文件路径：" + props.getProperty("java.io.tmpdir"));
        System.out.println("一个或多个扩展目录的路径：" + props.getProperty("java.ext.dirs"));
        System.out.println("操作系统的名称：" + props.getProperty("os.name"));
        System.out.println("操作系统的构架：" + props.getProperty("os.arch"));
        System.out.println("操作系统的版本：" + props.getProperty("os.version"));
        System.out.println("文件分隔符：" + props.getProperty("file.separator"));   //在 unix 系统中是＂／＂
        System.out.println("路径分隔符：" + props.getProperty("path.separator"));   //在 unix 系统中是＂:＂
        System.out.println("行分隔符：" + props.getProperty("line.separator"));   //在 unix 系统中是＂/n＂
        System.out.println("用户的账户名称：" + props.getProperty("user.name"));
        System.out.println("用户的主目录：" + props.getProperty("user.home"));
        System.out.println("用户的当前工作目录：" + props.getProperty("user.dir"));
    }
}
