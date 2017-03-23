import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
                "        <th data-options=\"field:'code'\">单据编号id</th>\n" +
                "        <th data-options=\"field:'mailNo'\">EMS物流单号</th>\n" +
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
        String key = "[\\(\\[]?[a-zA-Z]?[\\u4e00-\\u9fa5].*[\\u4e00-\\u9fa5]+[a-zA-Z]?[\\)\\]]?";
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

    @Test
    public void test7() {
        //String fileStr = "<#assign ctx=\"${rc.contextPath}\">\n" +
        //        "<script type=\"text/javascript\" src=\"${qiniuCdnUrl}/static/new/js/center_common.js?v=20151022\"></script>\n" +
        //        "<script type=\"text/javascript\">\n" +
        //        "    function deliveryOrderSearch() {\n" +
        //        "        var data = $(\"#frmDeliveryOrderSearch\").serializeObject();\n" +
        //        "        data.delivery = true;\n" +
        //        "        $('#deliveryOrderGrid').datagrid('load', data);\n" +
        //        "    }\n" +
        //        "    function deliveryOrderCancel() {\n" +
        //        "        $(\"#frmDeliveryOrderSearch\").resetForm();\n" +
        //        "    }\n" +
        //        "\n" +
        //        "    $(document).unbind('keydown').keydown(function (e) {\n" +
        //        "        if (e.keyCode == 13) {\n" +
        //        "            deliveryOrderSearch();\n" +
        //        "            return false;\n" +
        //        "        }\n" +
        //        "    });\n" +
        //        "</script>\n" +
        //        "\n" +
        //        "<!-- 表单编辑区域 -->\n" +
        //        "<dl id=\"actEdit\" class=\"action-area\">\n" +
        //        "    <dt class=\"cf\">\n" +
        //        "    <div class=\"form-name\">搜索</div>\n" +
        //        "    <span class=\"arrow\"></span>\n" +
        //        "    </dt>\n" +
        //        "    <dd>\n" +
        //        "        <form id=\"frmDeliveryOrderSearch\" method=\"post\">\n" +
        //        "            <table class=\"form-tb\">\n" +
        //        "                <tr>\n" +
        //        "                    <th class=\"tb-lab\">单据时间</th>\n" +
        //        "                    <td>\n" +
        //        "                        <select id=\"dateType\" name=\"dateType\" class=\"easyui-combobox combo-w\">\n" +
        //        "                            <option value=\"0\">最近七天</option>\n" +
        //        "                            <option value=\"1\">七天以前(已完成)</option>\n" +
        //        "                        </select>\n" +
        //        "                    </td>\n" +
        //        "                    <th class=\"tb-lab\">单据编号</th>\n" +
        //        "                    <td><input class=\"easyui-validatebox text\" type=\"text\" id=\"code\" name=\"code\"\n" +
        //        "                               data-options=\"required:false\"/></td>\n" +
        //        "                    <th class=\"tb-lab\">平台单号</th>\n" +
        //        "                    <td><input class=\"easyui-validatebox text\" type=\"text\" id=\"platformCode\" name=\"platformCode\"\n" +
        //        "                               data-options=\"required:false\"/></td>\n" +
        //        "                </tr>\n" +
        //        "                <tr>\n" +
        //        "                    <th class=\"tb-lab\">会员名称</th>\n" +
        //        "                    <td><input class=\"easyui-validatebox text\" type=\"text\" id=\"vipName\" name=\"vipName\"\n" +
        //        "                               data-options=\"required:false\"/></td>\n" +
        //        "                    <th class=\"tb-lab\">收货人</th>\n" +
        //        "                    <td><input class=\"easyui-validatebox text\" type=\"text\" id=\"receiverName\" name=\"receiverName\"\n" +
        //        "                               data-options=\"required:false\"/></td>\n" +
        //        "                    <th class=\"tb-lab\">物流公司</th>\n" +
        //        "                    <td><input class=\"easyui-combobox combo-w\" id=\"expressId\" name=\"expressId\"></td>\n" +
        //        "                </tr>\n" +
        //        "                <tr>\n" +
        //        "                    <th class=\"tb-lab\">物流单号</th>\n" +
        //        "                    <td><input class=\"easyui-validatebox text\" type=\"text\" id=\"mailNo\" name=\"mailNo\"\n" +
        //        "                               data-options=\"required:false\"/></td>\n" +
        //        "                </tr>\n" +
        //        "                <tr>\n" +
        //        "                    <td colspan=\"3\">\n" +
        //        "                        <input class=\"btn btn-primary\" type=\"button\" onclick=\"deliveryOrderSearch()\" value=\"搜索\"/>\n" +
        //        "                        <input class=\"btn\" type=\"button\" onclick=\"deliveryOrderCancel()\" value=\"重置条件\"/>\n" +
        //        "                    </td>\n" +
        //        "                </tr>\n" +
        //        "            </table>\n" +
        //        "        </form>\n" +
        //        "    </dd>\n" +
        //        "</dl>\n" +
        //        "\n" +
        //        "<table id=\"deliveryOrderGrid\"\n" +
        //        "       data-options=\"\n" +
        //        "                url:'${ctx}/tc/delivery/delivery_order_header/data/list',\n" +
        //        "                queryParams:{delivery: true},\n" +
        //        "                rownumbers: true,\n" +
        //        "                singleSelect:true,\n" +
        //        "                 pagination:true,\n" +
        //        "                idField: 'id'\">\n" +
        //        "    <thead>\n" +
        //        "    <tr>\n" +
        //        "        <th data-options=\"field:'code'\">单据编号</th>\n" +
        //        "        <th data-options=\"field:'mailNo'\">物流单号</th>\n" +
        //        "        <th data-options=\"field:'shopName', width:80\">店铺</th>\n" +
        //        "        <th data-options=\"field:'vipName', width:60\">会员</th>\n" +
        //        "        <th data-options=\"field:'warehouseName', width:80\">仓库</th>\n" +
        //        "        <th data-options=\"field:'expressName', width:60\">物流公司</th>\n" +
        //        "        <th data-options=\"field:'buyerMemo', width:100\">买家留言</th>\n" +
        //        "        <th data-options=\"field:'sellerMemo', width:80\">卖家备注</th>\n" +
        //        "        <th data-options=\"field:'receiverName', width:60\">收货人</th>\n" +
        //        "        <#--<th data-options=\"field:'receiverPhone', width:80\">收货人电话</th>-->\n" +
        //        "        <#--<th data-options=\"field:'receiverMobile', width:80\">收货人手机</th>-->\n" +
        //        "        //<th data-options=\"field:'receiverAddress', width:140\">收货地址</th>\n" +
        //        "        //<th data-options=\"field:'createName', width:80\">制单人</th>\n" +
        //        "    </tr>\n" +
        //        "    </thead>\n" +
        //        "</table>\n";

        String fileStr = "<#assign ctx=\"${rc.contextPath}\">\n" +
                "<#assign shiro=JspTaglibs[\"/WEB-INF/shiro.tld\"]>\n" +
                "<#assign guanyi=JspTaglibs[\"http://it.gagu.com/web\"]>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>速卖通发货单</title>\n" +
                "<@guanyi.noRepeatSubmit formName=\"deliveryPrintCancel\" />\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"breadcrumb\">订单管理 > 订单管理 > 速卖通发货单</div>\n" +
                "<@guanyi.noRepeatSubmit formName=\"aliecpressDeliveryCancel\" />\n" +
                "<div class=\"hidden\">\n" +
                "<form id=\"frmSearch\" method=\"post\" class=\"reset-box form-inline form-default-col form-lab6 cf\">\n" +
                "    <div class=\"form-item fi-block\">\n" +
                "        <div class=\"form-field\">\n" +
                "            <input class=\"btn btn-primary\" type=\"button\" disabled id=\"searchBtn\" value=\"搜索\"/>\n" +
                "            <input class=\"btn\" id=\"resetBtn\" type=\"button\" disabled value=\"重置条件\"/>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- 基础搜索项 -->\n" +
                "    <div class=\"cf\">\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">发货单号</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input class=\"text\" type=\"text\" id=\"code\" name=\"code\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">仓库名称</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input class=\"text\" id=\"warehouseId\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">会员名称</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input class=\"text\" type=\"text\" id=\"vipName\" name=\"vipName\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">平台单号</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input class=\"text\" type=\"text\" id=\"platformCode\" name=\"platformCode\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">国际运单号</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input class=\"text\" type=\"text\" id=\"internationalLogisticsId\" name=\"internationalLogisticsId\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">店铺名称</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input class=\"text\" id=\"shopId\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"form-item form-col-8\">\n" +
                "            <div class=\"form-field\" id=\"chkStatus\">\n" +
                "                <label class=\"checkbox\"><input type=\"checkbox\" sign=\"onlineDelivery\"/>线上发货</label>\n" +
                "                <label class=\"checkbox\"><input type=\"checkbox\" sign=\"printExpress\"/>物流单打印</label>\n" +
                "                <label class=\"checkbox\"><input type=\"checkbox\" sign=\"delivery\"/>发货</label>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</form>\n" +
                "    <div id=\"tbar\" class=\"tbar\">\n" +
                "\n" +
                "        <a id=\"btnOnlineDelivery\" class=\"gy-l-btn gy-split-btn\" href=\"javascript:void(0);\">\n" +
                "            <i class=\"new-icon-express\"></i><span class=\"gy-l-btn-text\">在线发货</span>\n" +
                "        </a>\n" +
                "\n" +
                "    <@shiro.hasPermission name=\"printLogistics:/tc/delivery/delivery_order_header/toDeliveryPrintPage\">\n" +
                "        <a id=\"btnPrintExpress\" class=\"gy-l-btn\" href=\"javascript:;\"><i class=\"new-icon-print\"></i><span\n" +
                "                class=\"gy-l-btn-text\">物流单打印</span></a>\n" +
                "    </@shiro.hasPermission>\n" +
                "    <@shiro.lacksPermission name=\"printLogistics:/tc/delivery/delivery_order_header/toDeliveryPrintPage\">\n" +
                "        <a class=\"gy-l-btn disabled\" href=\"javascript:;\"><i class=\"new-icon-print\"></i><span\n" +
                "                class=\"gy-l-btn-text\">物流单打印</span></a>\n" +
                "    </@shiro.lacksPermission>\n" +
                "\n" +
                "    <@shiro.hasPermission name=\"printLogistics:/tc/delivery/delivery_order_header/toDeliveryPrintPage\">\n" +
                "        <a id=\"btnPrintDelivery\" class=\"gy-l-btn\" href=\"javascript:void(0);\">\n" +
                "            <i class=\"new-icon-batch\"></i><span class=\"gy-l-btn-print\">发货单打印</span>\n" +
                "        </a>\n" +
                "    </@shiro.hasPermission>\n" +
                "\n" +
                "    <@shiro.lacksPermission name=\"printLogistics:/tc/delivery/delivery_order_header/toDeliveryPrintPage\">\n" +
                "        <a class=\"gy-l-btn disabled\" href=\"javascript:void(0);\">\n" +
                "            <i class=\"new-icon-batch\"></i><span class=\"gy-l-btn-print\">发货单打印</span>\n" +
                "        </a>\n" +
                "    </@shiro.lacksPermission>\n" +
                "\n" +
                "        <a id=\"removeBtn\" class=\"gy-l-btn\" href=\"javascript:;\"><i class=\"new-icon-remove\"></i><span\n" +
                "                class=\"gy-l-btn-text\">作废</span></a>\n" +
                "\n" +
                "        <a id=\"resetLayoutBtn\" class=\"gy-l-btn\" href=\"javascript:;\">\n" +
                "            <i class=\"new-icon-layout\"></i><span class=\"gy-l-btn-text\">恢复布局</span>\n" +
                "        </a>\n" +
                "\n" +
                "    </div>\n" +
                "<#--收货信息-->\n" +
                "    <div id=\"receiverList\" class=\"form-inline form-col-8 form-lab6 form-detail cf\">\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">收货人</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <span data-sign=\"receiverName\"></span>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">收货人电话</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input type=\"hidden\" data-sign=\"receiverPhone\"/>\n" +
                "                <span id=\"showPhone\" class=\"fr blue cursor_dlt\">显示</span>\n" +
                "                <span data-sign=\"receiverPhoneBlur\"></span>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">收货人手机</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input class=\"hidden\" data-sign=\"receiverMobile\"/>\n" +
                "                <span id=\"showMobile\" class=\"fr blue cursor_dlt\">显示</span>\n" +
                "                <span data-sign=\"receiverMobileBlur\"></span>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">收货人邮编</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <span data-sign=\"receiverZip\"></span>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">地区信息</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <span data-sign=\"areaName\"></span>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item form-col-6\">\n" +
                "            <div class=\"form-lab\">收货人地址</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <span data-sign=\"receiverAddress\"></span>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">身份证号</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <span data-sign=\"vipIdCard\"></span>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">真实姓名</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <span data-sign=\"vipRealName\"></span>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">电子邮箱</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <span data-sign=\"vipEmail\"></span>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "<#--线上发货-->\n" +
                "    <form id=\"onlineDeliveryForm\" method=\"post\" class=\"form-inline reset-box\">\n" +
                "        <input type=\"hidden\" id=\"tenantId\" name=\"tenantId\"/>\n" +
                "        <input type=\"hidden\" id=\"shop\" name=\"shopId\"/>\n" +
                "        <div class=\"fi-block cf\" style=\"padding:10px;\">\n" +
                "        <@guanyi.noRepeatSubmit formName=\"aliecpressDelivery\" />\n" +
                "            <span class=\"must\">启用上门揽件注意：<br>\n" +
                "        1、物流方式选择【俄速通】时， 国内快递必须是【中通】，国内单号必填正确的中通运单号；<br>\n" +
                "        2、物流方式选择【4PX新加坡小包】 时，国内快递选择【其他】，快递名称：上门揽件，国内运单号：4PX；<br>\n" +
                "        3、物流方式选择 其他物流， 国内快递选择【其他】，快递名称：上门揽件，国内运单号：None；</span>\n" +
                "        </div>\n" +
                "        <div class=\"cf form-inline form-lab6\">\n" +
                "            <div class=\"form-item\">\n" +
                "                <div class=\"form-lab\">平台单号</div>\n" +
                "                <div class=\"form-field\">\n" +
                "                    <input class=\"text\" type=\"text\" readonly id=\"tradeId\" name=\"tradeId\" value=\"\"/>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"form-item\">\n" +
                "                <div class=\"form-lab\">应付金额</div>\n" +
                "                <div class=\"form-field\">\n" +
                "                    <input class=\"text\" type=\"text\" readonly id=\"amount\" name=\"amount\" value=\"\"/>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"form-item\">\n" +
                "                <div class=\"form-lab\">标准重量</div>\n" +
                "                <div class=\"form-field\">\n" +
                "                    <input class=\"text\" type=\"text\" readonly id=\"itemWeight\" name=\"itemWeight\" value=\"\"/>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"form-item\">\n" +
                "                <div class=\"form-lab\">物流方式</div>\n" +
                "                <div class=\"form-field\">\n" +
                "                    <input class=\"text\" type=\"text\" id=\"warehouseCarrierService\" value=\"\"/>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"form-item\">\n" +
                "                <div class=\"form-field\">\n" +
                "                    <label class=\"checkbox\"><input type=\"checkbox\" id=\"pickup\" />上门揽件</label>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"form-item\">\n" +
                "                <div class=\"form-lab\">国内快递</div>\n" +
                "                <div class=\"form-field\">\n" +
                "                    <input class=\"text\" type=\"text\" id=\"domesticLogisticsCompanyId\" value=\"\"/>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"form-item\">\n" +
                "                <div class=\"form-lab\">国内运单号</div>\n" +
                "                <div class=\"form-field\">\n" +
                "                    <input class=\"text\" type=\"text\" id=\"domesticTrackingNo\" name=\"domesticTrackingNo\" value=\"\"/>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "    </form>\n" +
                "</div>\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "\n" +
                "    var GLB = {\n" +
                "        pageNumber: '${pageNumber!}',\n" +
                "        pageSize: '${pageSize!}',\n" +
                "        tdid: '${tradeId!}',\n" +
                "        userId: '${user.id}',\n" +
                "        printer:{//打印机初始化\n" +
                "            express:-1,//物流\n" +
                "            pickup:-1,//分拣\n" +
                "            delivery:-1//发货\n" +
                "        },\n" +
                "        printProcss:{//打印进程初始化\n" +
                "            jobId:'',//打印任务的索引\n" +
                "            OK:true\n" +
                "        },\n" +
                "        autoFillData:[]//自动填充数据\n" +
                "    };\n" +
                "\n" +
                "    var multiplePrint = '${multiplePrint!}' == '1' ? true : false;\n" +
                "\n" +
                "    var isWave = false,//为后面的物流打印弹出框的判断是否为波茨\n" +
                "            fillLogRows,//物流单打印，单号填充弹出框需要填充的数据\n" +
                "            gridTable;//物流单打印弹出框里的表格需要的js\n" +
                "    var itemColumns = [\n" +
                "        {xtype: \"rownumberer\", width: 20, resizable: true},\n" +
                "        {dataIndex: 'refundType', text: '退款', width: 70, align: \"center\"},\n" +
                "        {dataIndex: 'itemCode', text: '商品代码', width: 100},\n" +
                "        {dataIndex: 'itemName', text: '商品名称', width: 160},\n" +
                "        {dataIndex: 'itemSimpleName', text: '商品简称', width: 120},\n" +
                "        {dataIndex: 'itemSkuCode', text: '规格代码', width: 120},\n" +
                "        {dataIndex: 'itemSkuName', text: '商品规格名称', width: 120},\n" +
                "        {dataIndex: 'platformItemName', text: '平台商品名称', width: 160},\n" +
                "        {dataIndex: 'qty', text: '数量', width: 50},\n" +
                "        {dataIndex: 'discount', text: '折扣', width: 60},\n" +
                "    <@shiro.hasPermission name=\"salesprice:view\">\n" +
                "        {dataIndex: 'originPrice', align: 'right', header: '标准单价', width: 70},\n" +
                "    </@shiro.hasPermission>\n" +
                "    <@shiro.hasPermission name=\"actualprice:view\">\n" +
                "        {dataIndex: 'price', align: 'right', header: '实际单价', width: 70},\n" +
                "    </@shiro.hasPermission>\n" +
                "    <@shiro.hasPermission name=\"salesprice:view\">\n" +
                "        {dataIndex: 'originAmount', align: 'right', header: '标准金额', width: 70},\n" +
                "    </@shiro.hasPermission>\n" +
                "    <@shiro.hasPermission name=\"actualprice:view\">\n" +
                "        {dataIndex: 'amount', align: 'right', header: '实际金额', width: 70},\n" +
                "    </@shiro.hasPermission>\n" +
                "        {dataIndex: 'discountFee', align: 'right', header: '让利金额', width: 70},\n" +
                "        {dataIndex: 'amountAfter', align: 'right', header: '让利后金额', width: 70},\n" +
                "        {dataIndex: 'postFee', align: 'right', header: '物流费用', width: 70},\n" +
                "        {dataIndex: 'taxNo', align: 'right', header: '税号', width: 80},\n" +
                "        {dataIndex: 'taxRate', align: 'right', header: '税率', width: 70},\n" +
                "        {dataIndex: 'taxAmount', align: 'right', header: '税额', width: 70},\n" +
                "        {dataIndex: 'note', text: '备注', width: 100}\n" +
                "    ];\n" +
                "\n" +
                "    if (top.window.location.hostname == \"localhost\" && !(/testmin/.test(top.window.location.href))) {\n" +
                "        var dev = new Date().getTime();\n" +
                "        seajs.config({\n" +
                "            alias: {//别名配置\n" +
                "                'ext': _SEACONFIG.ext,\n" +
                "                'ext-lang': _SEACONFIG.extlang,\n" +
                "                'jquery': _SEACONFIG.jquery,\n" +
                "                'extFunc': '/js/comm/extFunc.js?' + dev,\n" +
                "                'autoSelect': '/js/comm/plugin/autoSelect.js?' + dev,\n" +
                "                'calendar': '/js/comm/plugin/calendar.js?' + dev,\n" +
                "                'validateForm': '/js/comm/plugin/validateForm.js?' + dev,\n" +
                "                'areaWindow': '/js/comm/commwin/areaWindow.js?' + dev,\n" +
                "                'editWindow': '/js/comm/commwin/editWindow.js?' + dev,\n" +
                "                'gridTable': '/js/comm/gridTable.js?' + dev,\n" +
                "                'selectPrtWindow': '/js/comm/commwin/selectPrtWindow.js?' + dev,\n" +
                "                'gyUi': '/js/comm/plugin/gyUi.js?' + dev,\n" +
                "                'LodopFuncs': '/static/lodop/LodopFuncs.js?' + dev,\n" +
                "                'selectPrinterWindow': '/js/comm/commwin/selectPrinterWindow.js?' + dev,\n" +
                "                'aliexpressDeliveryPrintController': '/js/tc/delivery/aliexpressDeliveryPrintController.js?' + dev,\n" +
                "                'aliexpressDeliveryPrintModel': '/js/tc/delivery/aliexpressDeliveryPrintModel.js?' + dev,\n" +
                "                'aliexpressDeliveryPrint': '/js/tc/delivery/aliexpressDeliveryPrint.js?' + dev\n" +
                "            },\n" +
                "            base: '${ctx}'\n" +
                "        })\n" +
                "    } else {\n" +
                "        seajs.config({\n" +
                "            alias: {//别名配置\n" +
                "                'ext': _SEACONFIG.ext,\n" +
                "                'ext-lang': _SEACONFIG.extlang,\n" +
                "                'jquery': _SEACONFIG.jquery,\n" +
                "                'extFunc': _SEACONFIG.extFunc,\n" +
                "                'gyUi': _SEACONFIG.gyUi,\n" +
                "                'areaWindow': _CDNPATH + '/static/js/dist/comm/areaWindow.b3222c13.js',\n" +
                "                'editWindow': _CDNPATH + '/static/js/dist/comm/editWindow.d119523a.js',\n" +
                "                'gridTable': _CDNPATH + '/static/js/dist/comm/gridTable.ebb02b9b.js',\n" +
                "                'selectPrtWindow': _CDNPATH + '/static/js/dist/comm/selectPrtWindow.0aba382d.js',\n" +
                "                'LodopFuncs': _CDNPATH + '/static/js/dist/comm/LodopFuncs.d0f9b2e3.js',\n" +
                "                'selectPrinterWindow': _CDNPATH+'/static/js/dist/comm/selectPrinterWindow.b2a09c40.js?',\n" +
                "                'aliexpressDeliveryPrint': _CDNPATH + '/static/js/dist/aliexpressDeliveryPrint.e9f1ac71.js'\n" +
                "            },\n" +
                "            base: _CDNPATH\n" +
                "        })\n" +
                "    }\n" +
                "    seajs.use('aliexpressDeliveryPrint');\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";
        //String regx1 = "[\"'>]?[\\u4e00-\\u9fa5].*?[\"'<]{1}";
        String regx1 = "(<span.*>|<div.*>){1}[\\u4e00-\\u9fa5]+[\\n]*(</span>|</div>){1}|[\"'>]{1}[\\u4e00-\\u9fa5].*?[\"'<]{1}";
        //String key1 = "[\\u4e00-\\u9fa5].*[\\u4e00-\\u9fa5]+(\\))?";
        //String key2 = "(<(!|#)--(.|[\r\n])*?-->)|(//.*)";
        Pattern pattern = Pattern.compile(regx1);
        Matcher matcher = pattern.matcher(fileStr);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void test8() {
        String str = "cerp.web.tc.delivery.qingshurushangpintiaoma";
        String line = str.replaceAll("\\.web\\.", ".common.");
        System.out.print(line);
    }

    @Test
    public void test9() {
        List<String> strList = new ArrayList<>();
        strList.add("111111111@111111");
        strList.add("222222222@222222");
        strList.add("333333333@333333");
        strList.add("@444444");
        strList.add("555555555@555555");
        strList.add("666666666@666666");
        for (String id : strList) {
            if (isEmpty(id))
                continue;
            String[] temp = id.split("@");
            if (null == temp || temp.length < 2)
                continue;
            String skuId = temp[0];
            String shopId = temp[1];
            if (isEmpty(skuId))
                continue;

            System.out.println("skuId: " + skuId + "\tshopId: " + shopId);
        }
    }

    private static boolean isEmpty(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(value.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test10() {
        //String fileStr = "<#assign ctx=\"${rc.contextPath}\">\n" +
        //        "<script type=\"text/javascript\" src=\"${qiniuCdnUrl}/static/new/js/center_common.js?v=20151022\"></script>\n" +
        //        "<script type=\"text/javascript\">\n" +
        //        "    function deliveryOrderSearch() {\n" +
        //        "        var data = $(\"#frmDeliveryOrderSearch\").serializeObject();\n" +
        //        "        data.delivery = true;\n" +
        //        "        $('#deliveryOrderGrid').datagrid('load', data);\n" +
        //        "    }\n" +
        //        "    function deliveryOrderCancel() {\n" +
        //        "        $(\"#frmDeliveryOrderSearch\").resetForm();\n" +
        //        "    }\n" +
        //        "\n" +
        //        "    $(document).unbind('keydown').keydown(function (e) {\n" +
        //        "        if (e.keyCode == 13) {\n" +
        //        "            deliveryOrderSearch();\n" +
        //        "            return false;\n" +
        //        "        }\n" +
        //        "    });\n" +
        //        "</script>\n" +
        //        "\n" +
        //        "<!-- 表单编辑区域 -->\n" +
        //        "<dl id=\"actEdit\" class=\"action-area\">\n" +
        //        "    <dt class=\"cf\">\n" +
        //        "    <div class=\"form-name\">搜索。</div>\n" +
        //        "    <span class=\"arrow\"></span>\n" +
        //        "    </dt>\n" +
        //        "    <dd>\n" +
        //        "        <form id=\"frmDeliveryOrderSearch\" method=\"post\">\n" +
        //        "            <table class=\"form-tb\">\n" +
        //        "                <tr>\n" +
        //        "                    <th class=\"tb-lab\">单据时间</th>\n" +
        //        "                    <td>\n" +
        //        "                        <select id=\"dateType\" name=\"dateType\" class=\"easyui-combobox combo-w\">\n" +
        //        "                            <option value=\"0\">最近七天</option>\n" +
        //        "                            <option value=\"1\">七天以前(已完成)</option>\n" +
        //        "                        </select>\n" +
        //        "                    </td>\n" +
        //        "                    <th class=\"tb-lab\">单据编号</th>\n" +
        //        "                    <td><input class=\"easyui-validatebox text\" type=\"text\" id=\"code\" name=\"code\"\n" +
        //        "                               data-options=\"required:false\"/></td>\n" +
        //        "                    <th class=\"tb-lab\">平台单号</th>\n" +
        //        "                    <td><input class=\"easyui-validatebox text\" type=\"text\" id=\"platformCode\" name=\"platformCode\"\n" +
        //        "                               data-options=\"required:false\"/></td>\n" +
        //        "                </tr>\n" +
        //        "                <tr>\n" +
        //        "                    <th class=\"tb-lab\">会员名称</th>\n" +
        //        "                    <td><input class=\"easyui-validatebox text\" type=\"text\" id=\"vipName\" name=\"vipName\"\n" +
        //        "                               data-options=\"required:false\"/></td>\n" +
        //        "                    <th class=\"tb-lab\">收货人</th>\n" +
        //        "                    <td><input class=\"easyui-validatebox text\" type=\"text\" id=\"receiverName\" name=\"receiverName\"\n" +
        //        "                               data-options=\"required:false\"/></td>\n" +
        //        "                    <th class=\"tb-lab\">物流公司</th>\n" +
        //        "                    <td><input class=\"easyui-combobox combo-w\" id=\"expressId\" name=\"expressId\"></td>\n" +
        //        "                </tr>\n" +
        //        "                <tr>\n" +
        //        "                    <th class=\"tb-lab\">物流单号</th>\n" +
        //        "                    <td><input class=\"easyui-validatebox text\" type=\"text\" id=\"mailNo\" name=\"mailNo\"\n" +
        //        "                               data-options=\"required:false\"/></td>\n" +
        //        "                </tr>\n" +
        //        "                <tr>\n" +
        //        "                    <td colspan=\"3\">\n" +
        //        "                        <input class=\"btn btn-primary\" type=\"button\" onclick=\"deliveryOrderSearch()\" value=\"搜索\"/>\n" + "                        <input class=\"btn\" type=\"button\" onclick=\"deliveryOrderCancel()\" value=\"重置条件\"/>\n" +
        //        "                    </td>\n" +
        //        "                </tr>\n" +
        //        "            </table>\n" +
        //        "        </form>\n" +
        //        "    </dd>\n" +
        //        "</dl>\n" +
        //        "\n" +
        //        "<table id=\"deliveryOrderGrid\"\n" +
        //        "       data-options=\"\n" +
        //        "                url:'${ctx}/tc/delivery/delivery_order_header/data/list',\n" +
        //        "                queryParams:{delivery: true},\n" +
        //        "                rownumbers: true,\n" +
        //        "                singleSelect:true,\n" +
        //        "                 pagination:true,\n" +
        //        "                idField: 'id'\">\n" +
        //        "    <thead>\n" +
        //        "    <tr>\n" +
        //        "        <th data-options=\"field:'code'\">单据编号id</th>\n" +
        //        "        <th data-options=\"field:'mailNo'\">EMS物流单号</th>\n" +
        //        "        <th data-options=\"field:'shopName', width:80\">店铺</th>\n" +
        //        "        <th data-options=\"field:'vipName', width:60\">会员</th>\n" +
        //        "        <th data-options=\"field:'warehouseName', width:80\">仓库</th>\n" +
        //        "        <th data-options=\"field:'expressName', width:60\">物流公司</th>\n" +
        //        "        <th data-options=\"field:'buyerMemo', width:100\">买家留言</th>\n" +
        //        "        <th data-options=\"field:'sellerMemo', width:80\">卖家备注</th>\n" +
        //        "        <th data-options=\"field:'receiverName', width:60\">收货人</th>\n" +
        //        "        <#--<th data-options=\"field:'receiverPhone', width:80\">收货人电话</th>-->\n" +
        //        "        <#--<th data-options=\"field:'receiverMobile', width:80\">收货人手机</th>-->\n" +
        //        "        //<th data-options=\"field:'receiverAddress', width:140\">收货地址</th>\n" +
        //        "        //<th data-options=\"field:'createName', width:80\">制单人</th>\n" +
        //        "    </tr>\n" +
        //        "    </thead>\n" +
        //        "</table>\n";
        //
        String fileStr = "<#assign ctx=\"${rc.contextPath}\">\n" +
                "<#assign shiro=JspTaglibs[\"/WEB-INF/shiro.tld\"]>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>商品库位对照</title>\n" +
                "    <script type=\"text/javascript\">\n" +
                "        var dataGrid;\n" +
                "\n" +
                "        Ext.onReady(function () {\n" +
                "            $('#pageLoading', parent.document).hide();\n" +
                "            Ext.state.Manager.setProvider(Ext.create(\"Ext.state.LocalStorageProvider\"));\n" +
                "            Ext.define('item_location_model', {\n" +
                "                extend: 'Ext.data.Model',\n" +
                "                fields: [\n" +
                "                    'del', 'itemCode', 'itemName', 'itemSkuCode', 'itemSkuName', 'warehouseName', 'locationName'\n" +
                "                ],\n" +
                "                idProperty: 'id'\n" +
                "            });\n" +
                "            var queryParams = {};\n" +
                "            var jsonSearchParam = $.cookie(\"item_location_search_param_header\");\n" +
                "            if (jsonSearchParam) {\n" +
                "                queryParams = JSON.parse(jsonSearchParam);\n" +
                "                $(\"#frmSearch\").form('load', queryParams);\n" +
                "                setCheckboxValue($(\"#chkDisable\"), queryParams.del);\n" +
                "            } else {\n" +
                "                setCheckboxValue($(\"#chkDisable\"), 0);\n" +
                "            }\n" +
                "            var pageNumber = '${pageNumber!}';\n" +
                "            var pageSize = '${pageSize!}';\n" +
                "            if (pageNumber == '') {\n" +
                "                pageNumber = 1;\n" +
                "                pageSize = 10;\n" +
                "            }\n" +
                "            var store = Ext.create('Ext.data.Store', {\n" +
                "                model: 'item_location_model',\n" +
                "                currentPage: pageNumber,\n" +
                "                pageSize: pageSize,\n" +
                "                remoteSort: true,\n" +
                "                proxy: {\n" +
                "                    extraParams: queryParams,\n" +
                "                    type: 'ajax',\n" +
                "                    getMethod: function () {\n" +
                "                        return 'POST';\n" +
                "                    },\n" +
                "                    url: '${ctx}/stock/stock/item_location_tenantId/data/list',\n" +
                "                    reader: {\n" +
                "                        root: 'rows',\n" +
                "                        totalProperty: 'total'\n" +
                "                    }\n" +
                "                }\n" +
                "            });\n" +
                "\n" +
                "            store.start = (pageNumber - 1) * pageSize;\n" +
                "            var columns = [\n" +
                "                {xtype: \"rownumberer\"},\n" +
                "                {text: \"停用\", dataIndex: 'del', width: 65, renderer: labelCancel, sortable: false},\n" +
                "                {text: \"商品条码\", dataIndex: 'barcode', width: 80, sortable: false},\n" +
                "                {text: \"商品代码\", dataIndex: 'itemCode', width: 80, sortable: false},\n" +
                "                {text: \"商品名称\", dataIndex: 'itemName', width: 100, sortable: false},\n" +
                "                {text: \"规格代码\", dataIndex: 'itemSkuCode', width: 80, sortable: false},\n" +
                "                {text: \"规格名称\", dataIndex: 'itemSkuName', width: 100, sortable: false},\n" +
                "                {text: \"仓库\", dataIndex: 'warehouseName', width: 120, sortable: false},\n" +
                "                {text: \"库位\", dataIndex: 'locationName', width: 100, sortable: false}\n" +
                "            ];\n" +
                "            var buttonBar = Ext.create(\"Ext.toolbar.Toolbar\", {contentEl: \"toolbar\"});\n" +
                "            var north = Ext.create(\"northPanel\", {height: 70});\n" +
                "//        var south = Ext.create(\"detailPanel\");\n" +
                "            dataGrid = Ext.create(\"centerGrid\", {\n" +
                "                store: store,\n" +
                "                stateId: 'itemLocationGrid',\n" +
                "                tbar: buttonBar,\n" +
                "                columns: columns\n" +
                "            });\n" +
                "            dataGrid.store.load();\n" +
                "            dataGrid.store.on('load', function () {\n" +
                "                gridLoadSuccess();\n" +
                "            });\n" +
                "            winLayout(north, dataGrid, null);\n" +
                "\n" +
                "            /* 编辑页面保存后，跳转回来，保持之前的分页数 */\n" +
                "            /**\n" +
                "             * 编辑页面保存后\n" +
                "             * 跳转回来\n" +
                "             * 保持之前的分页数\n" +
                "             */\n" +
                "            Ext.ComponentQuery.query('combo')[0].select(pageSize);\n" +
                "            pageBarText(dataGrid, '${ctx}/stock/stock/item_location_tenantId/data/total');\n" +
                "            $(\"#barcode\").focus();\n" +
                "        });\n" +
                "\n" +
                "        function funParamObj() {\n" +
                "            var data = $(\"#frmSearch\").serializeObject();\n" +
                "            if ($(\"#chkDisable\").data(\"checked\") == 0) {\n" +
                "                data.del = 0;\n" +
                "            } else if ($(\"#chkDisable\").data(\"checked\") == 1) {\n" +
                "                data.del = 1;\n" +
                "            }\n" +
                "            $.cookie('item_location_search_param_header', JSON.stringify(data), {expires: 1});\n" +
                "            return data;\n" +
                "        }\n" +
                "\n" +
                "        function gridLoadSuccess() {\n" +
                "            var tradeId = '${id!}';\n" +
                "            if (tradeId != '') {\n" +
                "                dataGrid.getStore().each(function (record) {\n" +
                "                    var rowId = record.get('id');\n" +
                "                    if (rowId == tradeId) {\n" +
                "                        dataGrid.getSelectionModel().select(this.index - dataGrid.getStore().start);\n" +
                "                        return false;\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        function subDataGrid(index, row) {\n" +
                "        }\n" +
                "\n" +
                "        $(document).ready(function () {\n" +
                "            checkboxInit($(\"#chkDisable\"), 0);\n" +
                "            var div = $(\"#divMsg\", parent.document).delay(2000).fadeOut();\n" +
                "        });\n" +
                "\n" +
                "        function itemWindow() {\n" +
                "            $('<div id=\"dialogDiv\"/>').dialog({\n" +
                "                buttons: [\n" +
                "                    {\n" +
                "                        text: '添加',\n" +
                "                        handler: function () {\n" +
                "                            var dlg = $(this).closest('.window-body');\n" +
                "                            var row = $('#dgItemSelect').datagrid(\"getSelected\");\n" +
                "                            if (!row) {\n" +
                "                                $.messager.alert('提示', '请选择要添加的会员', \"warning\");\n" +
                "                                return;\n" +
                "                            } else {\n" +
                "                                $(\"#skuId\").val(row.id);\n" +
                "                                $(\"#itemName\").find(\"span\").html(row[\"itemName\"]);\n" +
                "                                dlg.dialog('destroy');\n" +
                "                            }\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        text: '取消',\n" +
                "                        handler: function () {\n" +
                "                            var dlg = $(this).closest('.window-body');\n" +
                "                            dlg.dialog('destroy');\n" +
                "                        }\n" +
                "                    }\n" +
                "                ],\n" +
                "                href: '${ctx}/ic/item/data/select?combine=1',\n" +
                "                width: 850,\n" +
                "                height: 495,\n" +
                "                modal: true,\n" +
                "                title: '添加商品',\n" +
                "                onClose: function () {\n" +
                "                    $(this).dialog('destroy');\n" +
                "                }, onLoad: function () {\n" +
                "                    $(\"#dgItemSelect\").datagrid({\n" +
                "                        onDblClickRow: function (index, row) {\n" +
                "                            $(\"#skuId\").val(row.id);\n" +
                "                            $(\"#itemName\").find(\"span\").html(row[\"itemName\"]);\n" +
                "                            $(\"#dialogDiv\").dialog(\"close\");\n" +
                "                        }\n" +
                "                    });\n" +
                "                }\n" +
                "            });\n" +
                "        }\n" +
                "\n" +
                "        function add() {\n" +
                "            window.location.href = \"${ctx}/stock/stock/item_location_tenantId/form/add\";\n" +
                "        }\n" +
                "\n" +
                "        function edit() {\n" +
                "            var lastSelected = dataGrid.getSelectionModel().getLastSelected();\n" +
                "            if (lastSelected) {\n" +
                "                var row = lastSelected.raw;\n" +
                "                if (row.del) {\n" +
                "                    successMsg(\"商品库位已停用，不能修改\");\n" +
                "                    return false;\n" +
                "                }\n" +
                "                var row = lastSelected.data;\n" +
                "                var pageNumber = dataGrid.store.currentPage;\n" +
                "                var pageSize = dataGrid.store.pageSize;\n" +
                "                window.location.href = \"${ctx}/stock/stock/item_location_tenantId/form/edit?id=\" + row.id + \"&page=\" + pageNumber + \"&rows=\" + pageSize;\n" +
                "            } else {\n" +
                "                $.messager.alert('提示', \"请选择要编辑商品\", \"warning\");\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        function stop(type) {\n" +
                "            var rows = dataGrid.getSelectionModel().getSelection();\n" +
                "            var postParam = {};\n" +
                "            console.log(funParamObj());\n" +
                "            if (type == '1') {\n" +
                "                if (rows.length > 0) {\n" +
                "                    var ids = new Array();\n" +
                "                    for (var i = 0; i < rows.length; i++) {\n" +
                "                        ids[i] = rows[i].get('id');\n" +
                "                    }\n" +
                "                } else {\n" +
                "                    $.messager.alert('提示', '请选择要停用的商品库存', \"warning\");\n" +
                "                }\n" +
                "                postParam = {\n" +
                "                    ids: ids.join(','),\n" +
                "                    type: 1,\n" +
                "                    selectCondition: 0\n" +
                "                }\n" +
                "            } else if (type == \"2\") {\n" +
                "                postParam = funParamObj();\n" +
                "                postParam.type = 1;\n" +
                "                postParam.selectCondition = 1;\n" +
                "            }\n" +
                "            $.messager.confirm('确认', '是否确定停用?', function (r) {\n" +
                "                if (r) {\n" +
                "                    $.post('${ctx}/stock/stock/item_location_tenantId/data/delete', postParam, function (msg) {\n" +
                "                        successMsg(msg);\n" +
                "                        dataGrid.getSelectionModel().clearSelections();\n" +
                "                        dataGrid.getStore().reload();\n" +
                "                    });\n" +
                "                }\n" +
                "            });\n" +
                "        }\n" +
                "\n" +
                "        function enable(type) {\n" +
                "            var rows = dataGrid.getSelectionModel().getSelection();\n" +
                "            var postParam = {};\n" +
                "            console.log(funParamObj());\n" +
                "            if (type == '1') {\n" +
                "                if (rows.length > 0) {\n" +
                "                    var ids = new Array();\n" +
                "                    for (var i = 0; i < rows.length; i++) {\n" +
                "                        ids[i] = rows[i].get('id');\n" +
                "                    }\n" +
                "                } else {\n" +
                "                    $.messager.alert('提示', '请选择要启用的商品库存', \"warning\");\n" +
                "                }\n" +
                "                postParam = {\n" +
                "                    ids: ids.join(','),\n" +
                "                    type: 0,\n" +
                "                    selectCondition: 0\n" +
                "                }\n" +
                "            } else if (type == \"2\") {\n" +
                "                postParam = funParamObj();\n" +
                "                postParam.type = 0;\n" +
                "                postParam.selectCondition = 1;\n" +
                "            }\n" +
                "            $.messager.confirm('确认', '是否确定启用?', function (r) {\n" +
                "                if (r) {\n" +
                "                    $.post('${ctx}/stock/stock/item_location_tenantId/data/delete', postParam, function (msg) {\n" +
                "                        successMsg(msg);\n" +
                "                        dataGrid.getSelectionModel().clearSelections();\n" +
                "                        dataGrid.getStore().reload();\n" +
                "                    });\n" +
                "                }\n" +
                "            });\n" +
                "        }\n" +
                "        <#--function del(type) {-->\n" +
                "        <#--var rows = dataGrid.getSelectionModel().getSelection();-->\n" +
                "        <#--if (rows.length > 0) {-->\n" +
                "        <#--var ids = new Array();-->\n" +
                "        <#--for (var i = 0; i < rows.length; i++) {-->\n" +
                "        <#--ids[i] = rows[i].get('id');-->\n" +
                "        <#--}-->\n" +
                "        <#--$.messager.confirm('确认', '共选择' + rows.length + '个商品库位,是否要' + (type == 1 ? '停用' : '启用') + '?', function (r) {-->\n" +
                "        <#--if (r) {-->\n" +
                "        <#--$.post('${ctx}/stock/stock/item_location_tenantId/data/delete', {-->\n" +
                "        <#--ids: ids.join(','),-->\n" +
                "        <#--type: type-->\n" +
                "        <#--}, function (msg) {-->\n" +
                "        <#--successMsg(msg);-->\n" +
                "        <#--dataGrid.getSelectionModel().clearSelections();-->\n" +
                "        <#--dataGrid.getStore().reload();-->\n" +
                "        <#--});-->\n" +
                "        <#--}-->\n" +
                "        <#--});-->\n" +
                "        <#--} else {-->\n" +
                "        <#--$.messager.alert('提示', '请选择要' + type == 1 ? '停用' : '启用' + '的商品库存', \"warning\");-->\n" +
                "        <#--}-->\n" +
                "        <#--}-->\n" +
                "\n" +
                "        function exportItemLocation() {\n" +
                "            $.messager.confirm('确认', '是否导出商品库位资料?', function (r) {\n" +
                "                if (r) {\n" +
                "                    $.post('${ctx}/stock/stock/item_location_tenantId/data/export', function (msg) {\n" +
                "                        if (msg == \"重复操作\") {\n" +
                "                            $.messager.alert('提示', \"此操作已提交成功，正在执行中，请稍等......【执行进度请到常用页面-任务中心查看】\", \"warning\");\n" +
                "                        } else {\n" +
                "                            successMsg(msg);\n" +
                "                        }\n" +
                "                    });\n" +
                "                }\n" +
                "            });\n" +
                "        }\n" +
                "\n" +
                "        function importDataBySku() {\n" +
                "            $(\"#type\").val(\"sku\");\n" +
                "            $(\"#file\").click();\n" +
                "        }\n" +
                "\n" +
                "        function importDataByBarCode() {\n" +
                "            $(\"#type\").val(\"barCode\");\n" +
                "            $(\"#file\").click();\n" +
                "        }\n" +
                "\n" +
                "        function pullExcel() {\n" +
                "            var file = $(\"#file\");\n" +
                "            var fileSuffix = file.val().substr(file.val().indexOf('.'));\n" +
                "            if (\".xls\" != fileSuffix && \".xlsx\" != fileSuffix) {\n" +
                "                successMsg(\"请选择正确的excel文件[支持.xls/.xlsx格式]！\");\n" +
                "                return false;\n" +
                "            }\n" +
                "            $(\"#importForm\").ajaxSubmit({\n" +
                "                type: 'post',\n" +
                "                dataType: 'text',\n" +
                "                url: \"${ctx}/stock/stock/item_location_tenantId/data/ImportItemLocation\",\n" +
                "                success: function (msg) {\n" +
                "                    if (msg == \"重复操作\") {\n" +
                "                        $.messager.alert('提示', \"此操作已提交成功，正在执行中，请稍等......【执行进度请到常用页面-任务中心查看】\", \"warning\");\n" +
                "                    } else {\n" +
                "                        successMsg(msg);\n" +
                "                    }\n" +
                "                }\n" +
                "            });\n" +
                "        }\n" +
                "\n" +
                "        function itemSkuTemplateDownload() {\n" +
                "            window.location.href = \"${ctx}/stock/stock/item_location_tenantId/downLoadTemplate?type=itemSku\";\n" +
                "        }\n" +
                "\n" +
                "        function barCodeTemplateDownload() {\n" +
                "            window.location.href = \"${ctx}/stock/stock/item_location_tenantId/downLoadTemplate?type=barCode\";\n" +
                "        }\n" +
                "\n" +
                "        function onSelectRow(record) {\n" +
                "            $(\"#locationId\").combobox('clear');\n" +
                "            $('#locationId').combobox({\n" +
                "                valueField: 'id',\n" +
                "                width: 165,\n" +
                "                textField: 'name',\n" +
                "                required: true,\n" +
                "                editable: false,\n" +
                "                url: '${ctx}/info/warehouse_location/data/all?warehouseId=' + record.id\n" +
                "            });\n" +
                "        }\n" +
                "\n" +
                "        function cancelWhere() {\n" +
                "            $(\"#itemName\").find(\"span\").html(\"\");\n" +
                "            $(\"#frmSearch\").form('clear');\n" +
                "            checkboxInit($(\"#chkDisable\"));\n" +
                "            $.cookie('item_location_search_param_header', null);\n" +
                "        }\n" +
                "\n" +
                "        function search() {\n" +
                "            var data = funParamObj();\n" +
                "            if ($(\"#chkDisable\").data(\"checked\") == 0) {\n" +
                "                data.del = 0;\n" +
                "            } else if ($(\"#chkDisable\").data(\"checked\") == 1) {\n" +
                "                data.del = 1;\n" +
                "            } else {\n" +
                "                data.del = null;\n" +
                "            }\n" +
                "            var store = dataGrid.getStore();\n" +
                "            Ext.apply(store.proxy.extraParams, data);\n" +
                "            dataGrid.getStore().loadPage(1);\n" +
                "            dataGrid.getSelectionModel().clearSelections();\n" +
                "            clearTotal();\n" +
                "        }\n" +
                "\n" +
                "        $(document).keydown(function (e) {\n" +
                "            if (e.keyCode == 13) {\n" +
                "                search();\n" +
                "                return false;\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        function clearTotal() {\n" +
                "            var pageItem = dataGrid.child('pagingtoolbar').getComponent('pageTextBox');\n" +
                "            var pageCount = dataGrid.child('pagingtoolbar').getComponent('pageText').id;\n" +
                "            Ext.get(pageItem.id).dom.innerHTML = '<i><i>';\n" +
                "            Ext.get(pageItem.id).insertHtml('afterBegin', 0);\n" +
                "            Ext.get(pageCount).dom.innerHTML = 0;\n" +
                "        }\n" +
                "\n" +
                "        function labelCancel(value, p, index) {\n" +
                "            return value ? \"<input type='checkbox' disabled checked='checked' />\" : \"<input disabled type='checkbox' />\";\n" +
                "        }\n" +
                "\n" +
                "        function itemBarcode() {\n" +
                "            var event = arguments.callee.caller.arguments[0] || window.event;//消除浏览器差异\n" +
                "            if (event.keyCode == 13) {\n" +
                "                search();\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"breadcrumb\">库存中心 > 库存信息 > 商品库位对照</div>\n" +
                "<form id=\"frmSearch\" class=\"form-inline form-default-col cf\" method=\"post\">\n" +
                "    <div class=\"form-item fi-block\">\n" +
                "        <div class=\"form-field\">\n" +
                "            <input class=\"btn btn-primary\" type=\"button\" onclick=\"search()\"\n" +
                "                   value=\"搜索\"/>\n" +
                "            <input class=\"btn\" type=\"button\" onclick=\"cancelWhere()\" value=\"重置条件\"/>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- 基础搜索项 -->\n" +
                "    <div class=\"cf\" style=\"width:1350px;\">\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">商品代码</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input class=\"text\" type=\"text\" id=\"itemCode\"\n" +
                "                       name=\"itemCode\" data-options=\"required:false\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">商品名称</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input class=\"text\" type=\"text\" id=\"itemName\"\n" +
                "                       name=\"itemName\" data-options=\"required:false\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">规格代码</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input type=\"text\" id=\"itemSkuCode\" class=\"text\" name=\"itemSkuCode\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">规格名称</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input class=\"text\" type=\"text\" id=\"itemSkuName\" name=\"itemSkuName\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">商品条码：</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input class=\"easyui-validatebox text\" onkeydown=\"itemBarcode()\" type=\"text\" id=\"barcode\"\n" +
                "                       name=\"barcode\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">仓库名称</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input class=\"easyui-combobox combo-w\" type=\"text\" name=\"warehouseName\" id=\"warehouseName\"\n" +
                "                       data-options=\"valueField:'id',textField:'name',required:false,editable :false,url:'/info/warehouse/data/all',onSelect:onSelectRow\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <!-- <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">库位名称</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input type=\"text\" class=\"easyui-validatebox text\" id=\"locationName\"\n" +
                "                       name=\"locationName\" data-options=\"required:false,editable :false\"/>\n" +
                "            </div>\n" +
                "        </div-->\n" +
                "        <div class=\"form-item\" style=\"width:100px;\">\n" +
                "            <div class=\"form-field\">\n" +
                "                <label class=\"checkbox\"><input type=\"checkbox\" id=\"chkDisable\"/>停用</label>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</form>\n" +
                "<div id=\"toolbar\" class=\"toolbar\">\n" +
                "<@shiro.hasPermission name=\"create:/stock/stock/item_location_tenantId\">\n" +
                "    <a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" onclick=\"add();\" plain=\"true\">新增</a>\n" +
                "</@shiro.hasPermission>\n" +
                "<@shiro.lacksPermission name=\"create:/stock/stock/item_location_tenantId\">\n" +
                "    <a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" onclick=\"add();\" plain=\"true\"\n" +
                "       disabled=\"true\">新增</a>\n" +
                "</@shiro.lacksPermission>\n" +
                "<@shiro.hasPermission name=\"update:/stock/stock/item_location_tenantId\">\n" +
                "    <a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-edit\" onclick=\"edit();\" plain=\"true\">修改</a>\n" +
                "</@shiro.hasPermission>\n" +
                "<@shiro.lacksPermission name=\"update:/stock/stock/item_location_tenantId\">\n" +
                "    <a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-edit\" onclick=\"edit();\" disabled=\"true\"\n" +
                "       plain=\"true\">修改</a>\n" +
                "</@shiro.lacksPermission>\n" +
                "<@shiro.hasPermission name=\"delete:/stock/stock/item_location_tenantId\">\n" +
                "    <a href=\"javascript:void(0);\" class=\"easyui-menubutton\" data-options=\"menu:'#mmStop',iconCls:'icon-stop'\"\n" +
                "       plain=\"true\">批量停用</a>\n" +
                "</@shiro.hasPermission>\n" +
                "<@shiro.lacksPermission name=\"delete:/stock/stock/item_location_tenantId\">\n" +
                "    <a href=\"javascript:void(0);\" class=\"easyui-menubutton\" data-options=\"menu:'#mmStop',iconCls:'icon-stop'\" disabled=\"true\" plain=\"true\">批量停用</a>\n" +
                "</@shiro.lacksPermission>\n" +
                "<@shiro.hasPermission name=\"enable:/stock/stock/item_location_tenantId\">\n" +
                "    <a href=\"javascript:void(0);\" class=\"easyui-menubutton\" data-options=\"menu:'#mmStop',iconCls:'icon-enable'\" plain=\"true\">批量启用</a>\n" +
                "</@shiro.hasPermission>\n" +
                "<@shiro.lacksPermission name=\"enable:/stock/stock/item_location_tenantId\">\n" +
                "    <a href=\"javascript:void(0);\" class=\"easyui-menubutton\" data-options=\"menu:'#mmStop',iconCls:'icon-enable'\" disabled=\"true\" plain=\"true\">批量启用</a>\n" +
                "</@shiro.lacksPermission>\n" +
                "<@shiro.hasPermission name=\"template:/stock/stock/item_location_tenantId\">\n" +
                "    <a href=\"#\" class=\"easyui-menubutton\" data-options=\"menu:'#templateDownload',iconCls:'icon-download'\" plain=\"true\">商品库位导入模版下载</a>\n" +
                "</@shiro.hasPermission>\n" +
                "<@shiro.lacksPermission name=\"template:/stock/stock/item_location_tenantId\">\n" +
                "    <a href=\"#\" class=\"easyui-menubutton\" data-options=\"menu:'#templateDownload',iconCls:'icon-download'\"\n" +
                "       disabled=\"true\" plain=\"true\">商品库位导入模版下载</a>\n" +
                "</@shiro.lacksPermission>\n" +
                "<@shiro.hasPermission name=\"export:/stock/stock/item_location_tenantId\">\n" +
                "    <a href=\"javascript:void(0);\" id=\"btnExportItem\" class=\"easyui-linkbutton\" iconCls=\"icon-export\"\n" +
                "       onclick=\"exportItemLocation()\" plain=\"true\">商品库位导出CSV</a>\n" +
                "</@shiro.hasPermission>\n" +
                "<@shiro.lacksPermission name=\"export:/stock/stock/item_location_tenantId\">\n" +
                "    <a href=\"javascript:void(0);\" id=\"btnExportItem\" class=\"easyui-linkbutton\" iconCls=\"icon-export\" disabled=\"true\"\n" +
                "       onclick=\"exportItemLocation()\" plain=\"true\">商品库位导出CSV</a>\n" +
                "</@shiro.lacksPermission>\n" +
                "<@shiro.hasPermission name=\"import:/stock/stock/item_location_tenantId\">\n" +
                "    <a href=\"#\" class=\"easyui-menubutton\" data-options=\"menu:'#importData',iconCls:'icon-import'\">商品库位导入</a>\n" +
                "</@shiro.hasPermission>\n" +
                "<@shiro.lacksPermission name=\"import:/stock/stock/item_location_tenantId\">\n" +
                "    <a href=\"#\" class=\"easyui-menubutton\" disabled=\"true\"\n" +
                "       data-options=\"menu:'#importData',iconCls:'icon-import'\">商品库位导入</a>\n" +
                "</@shiro.lacksPermission>\n" +
                "</div>\n" +
                "\n" +
                "<form id=\"importForm\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                "    <input type=\"text\" style=\"display: none\" id=\"type\" name=\"type\">\n" +
                "    <input type=\"file\" style=\"display: none\" id=\"file\" name=\"file\" onchange=\"javascript:pullExcel()\"/>\n" +
                "</form>\n" +
                "\n" +
                "\n" +
                "<div id=\"importData\" style=\"width:100px;\">\n" +
                "    <div><a href=\"#\" onclick=\"importDataByBarCode()\">条码导入商品库位</a></div>\n" +
                "    <div><a href=\"#\" onclick=\"importDataBySku()\">规格导入商品库位</a></div>\n" +
                "</div>\n" +
                "<div id=\"templateDownload\" style=\"width:100px;\">\n" +
                "    <div><a href=\"#\" onclick=\"barCodeTemplateDownload()\">条码导入商品库位模板</a></div>\n" +
                "    <div><a href=\"#\" onclick=\"itemSkuTemplateDownload()\">规格导入商品库位模板</a></div>\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"mmStop\" style=\"width:100px;\">\n" +
                "    <div onclick=\"javascript:stop('1')\">选中项</div>\n" +
                "    <div class=\"menu-sep\"></div>\n" +
                "    <div onclick=\"javascript:stop('2')\">筛选项</div>\n" +
                "</div>\n" +
                "<div id=\"mmEnable\" style=\"width:100px;\">\n" +
                "    <div onclick=\"javascript:enable('1')\">选中项</div>\n" +
                "    <div class=\"menu-sep\"></div>\n" +
                "    <div onclick=\"javascript:enable('2')\">筛选项</div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";

        System.out.println("Start...");
        //String patterKey = "(<(!|#)--(.|[\r\n])*?-->)|(//.*)|/\\*.*\\*/$";
        //String patterKey = "(<(!|#)--(.|[\r\n])*?-->)";
        //String patterKey = "(/\\*.*)";
        //String patterKey = "(<(!|#)--(.|[\r\n])*?-->)|(//.*)|(/\\*.*)";
        //Pattern pattern2 = Pattern.compile(patterKey);
        //Matcher matcher2 = pattern2.matcher(fileStr);
        //String replayStr = fileStr;
        //while (matcher2.find()) {
        //    System.out.println(matcher2.group());
        //    //replayStr = matcher2.replaceAll("");
        //}
        String replayStr = fileStr.replaceAll("(?<!:)\\/\\/.*|\\/\\*(\\s|.)*?\\*\\/|<(!|#)--(.|[\r\n])*?-->", "");
        //System.out.println(replayStr);
        if (replayStr.length() > 0) {
            search(replayStr);
        }
        System.out.println("End.");
    }

    @Test
    public void test11() {
        String fileStr = "<#assign ctx=\"${rc.contextPath}\">\n" +
                "<#assign shiro=JspTaglibs[\"/WEB-INF/shiro.tld\"]>\n" +
                "<#assign guanyi=JspTaglibs[\"http://it.gagu.com/web\"]>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>采购建议单策略</title>\n" +
                "<script type=\"text/javascript\">\n" +
                "/*var ajaxPid;\n" +
                "var errorListById;\n" +
                "var errorStrData=[];\n" +
                "var flag;\n" +
                "var flagFail;\n" +
                "var onchangeSupplier=false;\n" +
                "$(document).ready(function () {\n" +
                "    var purchaseId = '${purchaseId!}';\n" +
                "    if (purchaseId != '') {\n" +
                "        var type = ${type! \"''\"};\n" +
                "        $.post(\"${ctx}/stock/purchase/purchase_order_header/data/purchaseInfo\", {purchaseId:purchaseId}, function (data) {\n" +
                "            $(\"#frmItemAdd\").form('load', data);\n" +
                "            $(\"#dgSku\").datagrid({data:data.purchaseOrderDetailInfos});\n" +
                "            if (type == \"copy\") {\n" +
                "                $(\"#frmItemAdd\").attr('action', '${ctx}/stock/purchase/purchase_order_header/create');\n" +
                "            } else {\n" +
                "                $(\"#frmItemAdd\").attr('action', '${ctx}/stock/purchase/purchase_order_header/update');\n" +
                "            }\n" +
                "        });\n" +
                "    }\n" +
                "});*/\n" +
                "\n" +
                "    $(function () {\n" +
                "        var id = '${id!}';\n" +
                "        $(\"#hidId\").val('${token!}');\n" +
                "        if (id != '') {\n" +
                "            $.post(\"${ctx}/stock/purchase/purchase_suggest_order_strategy/data/strategy\", {id: id}, function (data) {\n" +
                "                if(data.stockQtyTypeIsGreaterZero==1) {\n" +
                "                    $(\"#checkStockQtyTypeIsGreaterZero\").attr(\"checked\")==true;\n" +
                "                }else {\n" +
                "                    $(\"#checkStockQtyTypeIsGreaterZero\").attr(\"checked\")==false;\n" +
                "                }\n" +
                "                console.log(data);\n" +
                "                $(\"#frmEdit\").form('load', data);\n" +
                "//                $(\"#stockQtyTypeWarehouseId\").attr(\"readonly\", true);\n" +
                "                $(\"#frmEdit\").attr('action', '${ctx}/stock/purchase/purchase_suggest_order_strategy/update');\n" +
                "            });\n" +
                "        }\n" +
                "        //根据采购类型改变“策略类型设置”\n" +
                "        $('#type').combobox({\n" +
                "            onChange: function (newValue, oldValue) {\n" +
                "                if(newValue == 0) {\n" +
                "                    $('#purchasePanel').show().siblings().hide();\n" +
                "                } else if(newValue == 1) {\n" +
                "                    $('#sellPanel').show().siblings().hide();\n" +
                "                }\n" +
                "            }\n" +
                "        })\n" +
                "\n" +
                "        /* 按现库存采购的商品库存状态 */\n" +
                "        $(\"#stockQtyTypeStockStateIds\").combogrid({\n" +
                "            idField: 'id',\n" +
                "            textField: 'name',\n" +
                "            panelWidth: 220,\n" +
                "            multiple: true,\n" +
                "            editable: false,\n" +
                "            url: '${ctx}/info/item/item_stock_state/strategy/data/all',\n" +
                "            columns: [\n" +
                "                [\n" +
                "                    {field: 'ck', checkbox: true},\n" +
                "                    {field: 'code', title: '代码', width: 80},\n" +
                "                    {field: 'name', title: '名称', width: 100}\n" +
                "                ]\n" +
                "            ]\n" +
                "        });\n" +
                "        /* 按销售量采购的商品库存状态 */\n" +
                "        $(\"#role1\").combogrid({\n" +
                "            idField: 'id',\n" +
                "            textField: 'name',\n" +
                "            panelWidth: 220,\n" +
                "            multiple: true,\n" +
                "            editable: false,\n" +
                "            url: '${ctx}/info/item/item_stock_state/data/all',\n" +
                "            columns: [\n" +
                "                [\n" +
                "                    {field: 'ck', checkbox: true},\n" +
                "                    {field: 'code', title: '代码', width: 80},\n" +
                "                    {field: 'name', title: '名称', width: 100}\n" +
                "                ]\n" +
                "            ]\n" +
                "        });\n" +
                "    });\n" +
                "\n" +
                "    /* 保存表单 */\n" +
                "    function save() {\n" +
                "//        var flag1 = checkForm(\"frmEdit\");\n" +
                "//        var flag2 = $(\"#frmEdit\").form('validate');\n" +
                "//        if ((flag1 == false) || (flag2 == false)) {\n" +
                "//            return false;\n" +
                "//        }\n" +
                "//        if (!isEmail($(\"#email\").val())) {\n" +
                "//            successMsg(\"邮箱格式不正确\");\n" +
                "//            return false;\n" +
                "//        }\n" +
                "//        console.log($(\"#checkStockQtyTypeIsGreaterZero\").attr(\"checked\"));\n" +
                "        if($(\"#checkStockQtyTypeIsGreaterZero\").attr(\"checked\")==\"checked\") {\n" +
                "            $(\"#stockQtyTypeIsGreaterZero\").val(1);\n" +
                "        }else {\n" +
                "            $(\"#stockQtyTypeIsGreaterZero\").val(0);\n" +
                "        }\n" +
                "\n" +
                "        if ($(\"#frmEdit\").form('validate')) {\n" +
                "            var data = $(\"#frmEdit\").serializeObject();\n" +
                "            if((data.stockQtyTypeStockStateIds instanceof Array)==false) {\n" +
                "                data.stockQtyTypeStockStateIds = new Array(data.stockQtyTypeStockStateIds);\n" +
                "            }\n" +
                "            $.ajax({\n" +
                "                type:\"POST\",\n" +
                "                url:$(\"#frmEdit\").attr('action'),\n" +
                "                data:JSON.stringify(data),\n" +
                "                dataType:'text',\n" +
                "                processData:false,\n" +
                "                contentType:'application/json',\n" +
                "                success:function (msg) {\n" +
                "                    if(\"请不要重复提交！\"==msg){\n" +
                "                        successMsg(msg);\n" +
                "                        resetTagValue(getCurrentFormId(\"purchaseSuggestStrategyAddSubmit\"),[\"purchaseSuggestStrategyAddSubmit\"]);\n" +
                "                    }else{\n" +
                "                        var result = eval(\"(\"+msg+\")\");\n" +
                "                        successMsg(result.msg);\n" +
                "                        if(\"保存成功\" == result.msg){\n" +
                "                            window.location.href = \"${ctx}/stock/purchase/purchase_suggest_order_strategy?page=${pageNumber!}&rows=${pageSize!}\";\n" +
                "                        }else{\n" +
                "                            $(\"#hidId\").val(result.token);\n" +
                "                            resetTagValue(getCurrentFormId(\"purchaseSuggestStrategyAddSubmit\"),[\"purchaseSuggestStrategyAddSubmit\"]);\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "            });\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    function cancel() {\n" +
                "        window.location.href = \"${ctx}/stock/purchase/purchase_suggest_order_strategy?page=${pageNumber!}&rows=${pageSize!}\";\n" +
                "    }\n" +
                "\n" +
                "</script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"breadcrumb\">采购管理 > 采购管理 > 采购建议单策略 > 新增/编辑</div>\n" +
                "\n" +
                "<form class=\"form-inline form-default-col\" id=\"frmEdit\" method=\"post\"action=\"${ctx}/stock/purchase/purchase_suggest_order_strategy/create\" >\n" +
                "    <div class=\"form-item fi-block\">\n" +
                "        <div class=\"form-field\">\n" +
                "            <input class=\"btn btn-primary\" type=\"button\" onclick=\"save()\" value=\"保存\"/>\n" +
                "            <input class=\"btn\" type=\"button\" onclick=\"cancel()\" value=\"取消\"/>\n" +
                "            <input type=\"hidden\" id=\"id\" name=\"id\"/>\n" +
                "            <input type=\"hidden\" id=\"stockQtyTypeIsGreaterZero\" name=\"stockQtyTypeIsGreaterZero\"/>\n" +
                "            <input type=\"hidden\" id=\"hidId\" name=\"token\"/>\n" +
                "            <@guanyi.noRepeatSubmit formName=\"purchaseSuggestStrategyAddSubmit\" />\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"form-item form-col-4\">\n" +
                "        <div class=\"form-lab\">策略名称</div>\n" +
                "        <div class=\"form-field\">\n" +
                "            <span class=\"must\">*</span>\n" +
                "            <input class=\"easyui-validatebox text input-large\" type=\"text\" id=\"name\" name=\"name\"\n" +
                "                   data-options=\"required:true\" onkeydown=\"if(event.keyCode==13){return false;}\"/>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"form-item\">\n" +
                "        <div class=\"form-lab\">策略类型</div>\n" +
                "        <div class=\"form-field\">\n" +
                "            <span class=\"must\">*</span>\n" +
                "            <select name=\"type\" id=\"type\" class=\"easyui-combobox combo-w\">\n" +
                "                <option value=\"0\">按现库存采购</option> <!-- 注意：js是用value值来判断的，更改value值时请顺便修改js -->\n" +
                "                <#--<option value=\"1\">按销售采购</option> <!-- 看完这段注释后，请删除这两个注释，^_^ &ndash;&gt;-->\n" +
                "            </select>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"form-item\">\n" +
                "        <div class=\"form-lab\">执行方式</div>\n" +
                "        <div class=\"form-field\">\n" +
                "            <span class=\"must\">*</span>\n" +
                "            <select id='executeType' name=\"executeType\" class=\"easyui-combobox combo-w\">\n" +
                "                <option value=\"0\">手动执行</option>\n" +
                "                <#--<option value=\"1\">自动执行</option>-->\n" +
                "            </select>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"form-item\">\n" +
                "        <div class=\"form-lab\">间隔天数</div>\n" +
                "        <div class=\"form-field\">\n" +
                "            <input class=\"text\" type=\"text\" id='intervalDays' name=\"intervalDays\" />\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"form-item fi-block\">\n" +
                "        <div class=\"form-lab\">备注</div>\n" +
                "        <div class=\"form-field\">\n" +
                "            <input class=\"text input-zlarge\" type=\"text\" id='note' name=\"note\" />\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"easyui-tabs form-lab6\">\n" +
                "        <div title=\"策略类型设置\" style=\"padding: 10px;\">\n" +
                "            <#-- 按现库存采购 -->\n" +
                "            <div id=\"purchasePanel\" class=\"purchase-panel\">\n" +
                "                <p>根据商品现有数量及安全库存自动计算</p>\n" +
                "                <fieldset class=\"config-box\">\n" +
                "                    <legend class=\"config-lab\">满足采购条件设置</legend>\n" +
                "                    <p><label class=\"checkbox\"><input type=\"checkbox\" disabled id=\"checkStockQtyTypeIsGreaterZero\" name=\"checkStockQtyTypeIsGreaterZero\" checked='checked' />采购数量大于0</label></p>\n" +
                "                </fieldset>\n" +
                "                <fieldset class=\"config-box\">\n" +
                "                    <legend class=\"config-lab\">商品属性设置</legend>\n" +
                "                    <div class=\"form-item fi-block\">\n" +
                "                        <div class=\"form-lab\">商品库存状态</div>\n" +
                "                        <div class=\"form-field\">\n" +
                "                            <span class=\"must\">*</span>\n" +
                "                            <input class=\"easyui-combogrid\" id=\"stockQtyTypeStockStateIds\" name=\"stockQtyTypeStockStateIds\"\n" +
                "                                   data-options=\"required:true\" onkeydown=\"if(event.keyCode==13){return false;}\" />\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </fieldset>\n" +
                "                <fieldset class=\"config-box\">\n" +
                "                    <legend class=\"config-lab\">采购数量设置</legend>\n" +
                "                    <div class=\"form-item fi-block\">\n" +
                "                        <div class=\"form-lab\">仓库名称</div>\n" +
                "                        <div class=\"form-field\">\n" +
                "                            <span class=\"must\">*</span>\n" +
                "                            <input class=\"easyui-combobox combo-w\" id=\"stockQtyTypeWarehouseId\" name=\"stockQtyTypeWarehouseId\"\n" +
                "                                   data-options=\"required:true, valueField:'id',textField:'name',editable:false,\n" +
                "                                   url:'${ctx}/info/warehouse/data/all'\" />\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <p>\n" +
                "                        <label class=\"checkbox\"><input type=\"radio\" id=\"stockQtyTypePurchaseCountType\" name=\"stockQtyTypePurchaseCountType\" value=\"0\" checked />按未发货数量采购</label>\n" +
                "                        <span>采购数量 = 0 - （可销售数量 + 在途数量）</span>\n" +
                "                    </p>\n" +
                "                    <p>\n" +
                "                        <label class=\"checkbox\"><input type=\"radio\" id=\"stockQtyTypePurchaseCountType\"  name=\"stockQtyTypePurchaseCountType\" value=\"1\" />按安全数量采购</label>\n" +
                "                        <span>采购数量 = 安全库存 - （可销售数量 + 在途数量）</span>\n" +
                "                    </p>\n" +
                "                </fieldset>\n" +
                "            </div> <!-- end #purchasePanel -->\n" +
                "\n" +
                "            <#-- 按销售数采购 -->\n" +
                "            <div id=\"sellPanel\" class=\"purchase-sel-panel\">\n" +
                "                <p>1、根据商品的属性和日销售量，自动计算采购数量</p>\n" +
                "                <p>2、采购数量 = 预计销售天数 * 日销量 - （可销售数 + 在途数）</p>\n" +
                "                <p>3、日销量：指定时间内的日销售数量</p>\n" +
                "                <p>4、可销售数：存货数量 - 未发货数量</p>\n" +
                "                <p>在途数：已采购为入库数量</p>\n" +
                "                <fieldset class=\"config-box\">\n" +
                "                    <legend class=\"config-lab\">满足采购条件设置</legend>\n" +
                "                    <p><label class=\"checkbox\"><input type=\"checkbox\" name=\"\" disabled checked='checked' />采购数量大于0</label></p>\n" +
                "                </fieldset>\n" +
                "                <fieldset class=\"config-box\">\n" +
                "                    <legend class=\"config-lab\">商品属性设置</legend>\n" +
                "                    <div class=\"form-item\">\n" +
                "                        <div class=\"form-lab\">商品库存状态</div>\n" +
                "                        <div class=\"form-field\">\n" +
                "                            <input id=\"role1\" class=\"easyui-combogrid\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </fieldset>\n" +
                "                <fieldset class=\"config-box\">\n" +
                "                    <legend class=\"config-lab\">采购数量设置</legend>\n" +
                "                    <div class=\"form-item\">\n" +
                "                        <div class=\"form-lab\">仓库名称</div>\n" +
                "                        <div class=\"form-field\">\n" +
                "                            <select name=\"\" id=\"\" class=\"easyui-combobox combo-w\">\n" +
                "                                <option value=\"\"></option>\n" +
                "                            </select>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <p>\n" +
                "                        <label class=\"checkbox\"><input type=\"checkbox\" name=\"purchaseCount\"/>按未发货数量采购</label>\n" +
                "                        <span>采购数量 = 0 - （可销售数量 + 在途数量）</span>\n" +
                "                    </p>\n" +
                "                    <p>\n" +
                "                        <label class=\"checkbox\"><input type=\"checkbox\" name=\"purchaseCount\"/>按安全数量采购</label>\n" +
                "                        <span>采购数量 = 安全库存 - （可销售数量 + 在途数量）</span>\n" +
                "                    </p>\n" +
                "                </fieldset>\n" +
                "            </div> <!-- end #sellPanel -->\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";
        System.out.println("Start...");
        //String patterKey = "(<(!|#)--(.|[\r\n])*?-->)|(//.*)|/\\*.*\\*/$";
        //String patterKey = "(<(!|#)--(.|[\r\n])*?-->)";
        //String patterKey = "(/\\*.*)";
        //String patterKey = "(<(!|#)--(.|[\r\n])*?-->)|(//.*)|(/\\*.*)";
        //Pattern pattern2 = Pattern.compile(patterKey);
        //Matcher matcher2 = pattern2.matcher(fileStr);
        //String replayStr = fileStr;
        //while (matcher2.find()) {
        //    System.out.println(matcher2.group());
        //    //replayStr = matcher2.replaceAll("");
        //}
        String replayStr = fileStr.replaceAll("(?<!:)\\/\\/.*|\\/\\*(\\s|.)*?\\*\\/|<(!|#)--(.|[\r\n])*?-->|(\\(.*==.*\\))", "");
        //String replayStr = fileStr.replaceAll("(\\(.*==.*\\))", "");
        //System.out.println(replayStr);
        if (replayStr.length() > 0) {
            search(replayStr);
        }
        System.out.println("End.");
    }

    @Test
    public void test12() {
        String fileStr = "<#assign ctx=\"${rc.contextPath}\">\n" +
                "<#assign shiro=JspTaglibs[\"/WEB-INF/shiro.tld\"]>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>商品库存信息</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"breadcrumb\">库存中心 > 库存信息 > 商品库存信息</div>\n" +
                "<div class=\"hidden\">\n" +
                "    <form id=\"frmItemSearch\" class=\"reset-box form-inline form-default-col cf\" method=\"post\">\n" +
                "        <div class=\"form-item fi-block\">\n" +
                "            <div class=\"form-field\">\n" +
                "                <input id=\"searchBtn\" class=\"btn btn-primary\" type=\"button\" value=\"搜索\"/>\n" +
                "                <input id=\"resetBtn\" class=\"btn\" type=\"reset\" value=\"重置条件\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">商品代码</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input type=\"text\" id=\"code\" class=\"text\" name=\"code\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">规格代码</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input type=\"text\" id=\"skuCode\" class=\"text\" name=\"skuCode\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">规格名称</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input class=\"text\" type=\"text\" id=\"skuName\" name=\"skuName\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">商品条码</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input type=\"text\" id=\"barCode\" class=\"text\" name=\"barCode\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">商品简称</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input type=\"text\" id=\"sName\" class=\"text\" name=\"sName\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">商品名称</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input type=\"text\" id=\"name\" class=\"text\" name=\"name\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">仓库名称</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input type=\"text\" id=\"warehouseId\" class=\"text\" name=\"warehouseId\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">商品类别</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <div id=\"categoryId\" class=\"ext-combotree\"></div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\"></div>\n" +
                "        <div class=\"form-item\"></div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">供应商</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input type=\"text\" id=\"supplierId\" class=\"text\" name=\"supplierId\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-field qty-type\">\n" +
                "                <select name=\"qtyType\">\n" +
                "                    <option value=\"1\">库存数量</option>\n" +
                "                    <option value=\"2\">可销售数</option>\n" +
                "                    <option value=\"3\">可配数量</option>\n" +
                "                    <option value=\"4\">在途数量</option>\n" +
                "                    <option value=\"5\">安全库存</option>\n" +
                "                </select>\n" +
                "\n" +
                "                <div class=\"input-combo\">\n" +
                "                    <input type=\"text\" class=\"text input-small\" name=\"qtyMin\"/>\n" +
                "                    <span class=\"combo-split\">-</span>\n" +
                "                    <input type=\"text\" class=\"text input-small\" name=\"qtyMax\"/>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div id=\"chkStatus\" class=\"form-field\">\n" +
                "                <label class=\"checkbox\"><input type=\"checkbox\" sign=\"disable\" data-type='0'/>停用</label>\n" +
                "                <label class=\"checkbox\"><input type=\"checkbox\" sign=\"aew\"/>库存预警</label>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </form>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"hidden\">\n" +
                "    <!-- 主表格 - 按钮工具栏 -->\n" +
                "    <div id=\"tbStockToolBar\" class=\"tbar\">\n" +
                "    <@shiro.hasPermission name=\"enable:/stock/message/stock_tenant\">\n" +
                "        <a href=\"javascript:;\" id=\"batchEnableBtn\" class=\"gy-l-btn\">\n" +
                "            <i class=\"new-icon-enable\"></i><span class=\"gy-l-btn-text\">批量启用</span>\n" +
                "        </a>\n" +
                "    </@shiro.hasPermission>\n" +
                "    <@shiro.lacksPermission name=\"enable:/stock/message/stock_tenant\">\n" +
                "        <a href=\"javascript:;\" class=\"gy-l-btn disabled\">\n" +
                "            <i class=\"new-icon-enable\"></i><span class=\"gy-l-btn-text\">批量启用</span>\n" +
                "        </a>\n" +
                "    </@shiro.lacksPermission>\n" +
                "\n" +
                "    <@shiro.hasPermission name=\"delete:/stock/message/stock_tenant\">\n" +
                "        <a href=\"javascript:;\" id=\"batchStopBtn\" class=\"gy-l-btn\">\n" +
                "            <i class=\"new-icon-stop\"></i><span class=\"gy-l-btn-text\">批量停用</span>\n" +
                "        </a>\n" +
                "    </@shiro.hasPermission>\n" +
                "    <@shiro.lacksPermission name=\"delete:/stock/message/stock_tenant\">\n" +
                "        <a href=\"javascript:;\" class=\"gy-l-btn disabled\">\n" +
                "            <i class=\"new-icon-stop\"></i><span class=\"gy-l-btn-text\">批量停用</span>\n" +
                "        </a>\n" +
                "    </@shiro.lacksPermission>\n" +
                "\n" +
                "    <@shiro.hasPermission name=\"calc:/stock/message/stock_tenant\">\n" +
                "        <a href=\"javascript:;\" id=\"calStockBtn\" class=\"gy-l-btn\">\n" +
                "            <i class=\"new-icon-init\"></i><span class=\"gy-l-btn-text\">重新计算安全库存</span>\n" +
                "        </a>\n" +
                "    </@shiro.hasPermission>\n" +
                "    <@shiro.lacksPermission name=\"calc:/stock/message/stock_tenant\">\n" +
                "        <a href=\"javascript:;\" class=\"gy-l-btn disabled\">\n" +
                "            <i class=\"new-icon-init\"></i><span class=\"gy-l-btn-text\">重新计算安全库存</span>\n" +
                "        </a>\n" +
                "    </@shiro.lacksPermission>\n" +
                "\n" +
                "    <@shiro.hasPermission name=\"export:/stock/message/stock_tenant\">\n" +
                "        <a href=\"javascript:;\" id=\"exportItemBtn\" class=\"gy-l-btn\">\n" +
                "            <i class=\"new-icon-export\"></i><span class=\"gy-l-btn-text\">商品库存导出CSV</span>\n" +
                "        </a>\n" +
                "    </@shiro.hasPermission>\n" +
                "    <@shiro.lacksPermission name=\"export:/stock/message/stock_tenant\">\n" +
                "        <a href=\"javascript:;\" class=\"gy-l-btn disabled\">\n" +
                "            <i class=\"new-icon-export\"></i><span class=\"gy-l-btn-text\">商品库存导出CSV</span>\n" +
                "        </a>\n" +
                "    </@shiro.lacksPermission>\n" +
                "\n" +
                "    <@shiro.hasPermission name=\"update:/stock/message/stock_tenant\">\n" +
                "        <a href=\"javascript:;\" id=\"updateSafeQty\" class=\"gy-l-btn\">\n" +
                "            <i class=\"new-icon-settings\"></i><span class=\"gy-l-btn-text\">设置安全库存</span>\n" +
                "        </a>\n" +
                "        <a href=\"javascript:;\" id=\"\" class=\"gy-l-btn gy-split-btn\" data-menu=\"mmSettings\">\n" +
                "            <i class=\"new-icon-import\"></i><span class=\"gy-l-btn-text\">安全库存导入</span>\n" +
                "            <span class=\"gy-l-btn-line\"><b class=\"caret caret-b\"></b></span>\n" +
                "        </a>\n" +
                "    </@shiro.hasPermission>\n" +
                "    <@shiro.lacksPermission name=\"update:/stock/message/stock_tenant\">\n" +
                "        <a href=\"javascript:;\" class=\"gy-l-btn disabled\">\n" +
                "            <i class=\"new-icon-settings\"></i><span class=\"gy-l-btn-text\">设置安全库存</span>\n" +
                "        </a>\n" +
                "        <a href=\"javascript:;\" class=\"gy-l-btn gy-split-btn disabled\">\n" +
                "            <i class=\"new-icon-import\"></i><span class=\"gy-l-btn-text\">安全库存导入</span>\n" +
                "            <span class=\"gy-l-btn-line\"><b class=\"caret caret-b\"></b></span>\n" +
                "        </a>\n" +
                "    </@shiro.lacksPermission>\n" +
                "\n" +
                "    <@shiro.hasPermission name=\"template:/stock/message/stock_tenant\">\n" +
                "        <a href=\"javascript:;\" id=\"tplDownloadBtn\" class=\"gy-l-btn gy-split-btn\" data-menu=\"mmDownload\">\n" +
                "            <i class=\"new-icon-download\"></i><span class=\"gy-l-btn-text\">安全库存导入模版下载</span>\n" +
                "            <span class=\"gy-l-btn-line\"><b class=\"caret caret-b\"></b></span>\n" +
                "        </a>\n" +
                "    </@shiro.hasPermission>\n" +
                "    <@shiro.lacksPermission name=\"template:/stock/message/stock_tenant\">\n" +
                "        <a href=\"javascript:;\" class=\"gy-l-btn disabled\">\n" +
                "            <i class=\"new-icon-download\"></i><span class=\"gy-l-btn-text\">安全库存导入模版下载</span>\n" +
                "        </a>\n" +
                "    </@shiro.lacksPermission>\n" +
                "        <a id=\"resetLayoutBtn\" class=\"gy-l-btn\" href=\"javascript:;\">\n" +
                "            <i class=\"new-icon-layout\"></i><span class=\"gy-l-btn-text\">恢复布局</span>\n" +
                "        </a>\n" +
                "\n" +
                "        <!-- 库存数量汇总 -->\n" +
                "        <div class=\"counter-bar cf\">\n" +
                "            <div class=\"counter-lab\">库存汇总</div>\n" +
                "            <div class=\"counter-item\">\n" +
                "                <span>库存总数：</span>\n" +
                "                <span id=\"qtyCount\" class=\"color3\">0</span>\n" +
                "                <span id=\"refreshStockBtn\" class=\"icon-refresh-1\"></span>\n" +
                "            </div>\n" +
                "        <#--<div class=\"counter-item\">\n" +
                "            <span>成本总金额：</span>\n" +
                "            <span id=\"costPriceCount\">0</span>\n" +
                "        </div>\n" +
                "        <div class=\"counter-item\">\n" +
                "            <span>销售总金额：</span>\n" +
                "            <span id=\"salesPriceCount\">0</span>\n" +
                "        </div>-->\n" +
                "        </div>\n" +
                "    </div> <!-- end #tbStockToolBar -->\n" +
                "\n" +
                "    <!-- 设置安全库存 - 弹出框表单 -->\n" +
                "    <form id=\"setSaveStockForm\" class=\"form-vertical reset-box\" method=\"post\">\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">请输入安全库存数：</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input type=\"text\" id=\"saveStockNum\" class=\"text\"/>\n" +
                "\n" +
                "                <p id=\"saveStockNumTips\" class=\"form-tips must\"></p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </form>\n" +
                "    <!-- 重新计算安全库存 - 弹出框表单 -->\n" +
                "    <form id=\"frmStockCalc\" class=\"form-horizontal\" action=\"${ctx}/stock/message/stock_tenant/stockCalc\" method=\"post\">\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">选择仓库</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <span class=\"must\">*</span>\n" +
                "                <input type=\"text\" class=\"text\" id=\"winWarehouseId\"/>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">日销量</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <select name=\"day\">\n" +
                "                    <option value=\"3\">最近3天日销量</option>\n" +
                "                    <option value=\"7\">最近7天日销量</option>\n" +
                "                    <option value=\"15\">最近15天日销量</option>\n" +
                "                </select>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"form-item\">\n" +
                "            <div class=\"form-lab\">微调比例</div>\n" +
                "            <div class=\"form-field\">\n" +
                "                <input name=\"ratio\" type=\"text\" class=\"text\" value=\"100\"> %\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </form>\n" +
                "</div>\n" +
                "\n" +
                "<!-- 安全库存导出 - 下拉菜单 -->\n" +
                "<div id=\"mmSettings\" class=\"gy-menu hidden\">\n" +
                "    <div class=\"gy-menu-item\" id=\"importDataItem\">\n" +
                "        <span class=\"menu-text\">商品导入安全库存</span>\n" +
                "    </div>\n" +
                "    <div class=\"gy-menu-item\" id=\"importDataBarCode\">\n" +
                "        <span class=\"menu-text\">条码导入安全库存</span>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<!-- 安全库存导入模板下载 - 下拉菜单 -->\n" +
                "<div id=\"mmDownload\" class=\"gy-menu hidden\">\n" +
                "    <div class=\"gy-menu-item\" id=\"itemTemplateDownload\">\n" +
                "        <span class=\"menu-text\">商品导入安全模板下载</span>\n" +
                "    </div>\n" +
                "    <div class=\"gy-menu-item\" id=\"barCodeTemplateDownload\">\n" +
                "        <span class=\"menu-text\">条码导入安全模板下载</span>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<!-- 安全库存导入表单 -->\n" +
                "<form id=\"importForm\" class=\"hidden\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                "    <input type=\"file\" id=\"file\" name=\"file\"/>\n" +
                "</form>\n" +
                "\n" +
                "<script>\n" +
                "    var GLB = {\n" +
                "        pageNumber: '${pageNumber!}',\n" +
                "        pageSize: '${pageSize!}',\n" +
                "        userId: '${user.id}',\n" +
                "        unique: '<@shiro.hasPermission name=\"systemallstartuniquecode:view\">1</@shiro.hasPermission>',\n" +
                "        batch: '<@shiro.hasPermission name=\"SystemBatchManage:view\">1</@shiro.hasPermission>',\n" +
                "        postSort: ''\n" +
                "    };\n" +
                "    var mainColumns = [\n" +
                "        {xtype: \"rownumberer\", resizable: true},\n" +
                "        {dataIndex: 'del', header: '停用', width: 55, align: 'center'},\n" +
                "    <@shiro.hasPermission name=\"systemallstartuniquecode:view\">\n" +
                "        {dataIndex: 'unique', header: '唯一码', width: 55, align: 'center'},\n" +
                "    </@shiro.hasPermission>\n" +
                "        {dataIndex: 'picUrl', header: '图片', width: 90, align: 'center'},\n" +
                "        {dataIndex: 'skuPicUrl', header: '规格图片', width: 90, align: 'center'},\n" +
                "    <@shiro.hasPermission name=\"systemallstartuniquecode:view\">\n" +
                "        {\n" +
                "            dataIndex: 'id', header: '唯一码信息', width: 80,\n" +
                "            renderer: function (v, record, idx) {\n" +
                "                return \"<a href='javascript:void(0);'>唯一码信息</a>\";\n" +
                "            }\n" +
                "        },\n" +
                "    </@shiro.hasPermission>\n" +
                "    <@shiro.hasPermission name=\"SystemBatchManage:view\">\n" +
                "        {dataIndex: 'batchManagement', header: '批次', width: 60},\n" +
                "        {\n" +
                "            dataIndex: 'id', header: '批次信息', width: 80,\n" +
                "            renderer: function (v, record, idx) {\n" +
                "                return \"<a href='javascript:void(0);'>批次信息</a>\";\n" +
                "            }\n" +
                "        },\n" +
                "    </@shiro.hasPermission>\n" +
                "        {\n" +
                "            dataIndex: 'code', header: '商品代码', width: 120, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'itemName', header: '商品名称', width: 160, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'categoryName', header: '商品类别', width: 160, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'skuCode', header: '商品规格代码', width: 120, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'itemSkuName', header: '商品规格名称', width: 120, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'itemBarcode', header: '商品条码', width: 120, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'itemSimpleName', header: '商品简称', width: 120, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'supplierName', header: '供应商', width: 100, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'warehouseName', header: '仓库', width: 120, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'itemUnitName', header: '单位', width: 120, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'locName', header: '库位', width: 90, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'qty', header: '库存数量', width: 70, align: 'center',\n" +
                "            summaryType: 'sum', summaryRenderer: totalCount, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'saleQtyThree',\n" +
                "            header: '近3日销量',\n" +
                "            width: 120,\n" +
                "            summaryType: 'sum',\n" +
                "            summaryRenderer: totalCount,\n" +
                "            align: 'center',\n" +
                "            listeners: {\n" +
                "                'headerclick': function (ct, column, e, t, eOpts) {\n" +
                "                    flagKeep(ct.grid, ct, column);\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'saleQtySeven',\n" +
                "            header: '近7日销量',\n" +
                "            width: 120,\n" +
                "            summaryType: 'sum',\n" +
                "            summaryRenderer: totalCount,\n" +
                "            align: 'center',\n" +
                "            listeners: {\n" +
                "                'headerclick': function (ct, column, e, t, eOpts) {\n" +
                "                    flagKeep(ct.grid, ct, column);\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'saleQtyFifteen',\n" +
                "            header: '近15日销量',\n" +
                "            width: 120,\n" +
                "            summaryType: 'sum',\n" +
                "            summaryRenderer: totalCount,\n" +
                "            align: 'center',\n" +
                "            listeners: {\n" +
                "                'headerclick': function (ct, column, e, t, eOpts) {\n" +
                "                    flagKeep(ct.grid, ct, column);\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'salableQty', header: '可销售数', width: 70,\n" +
                "            summaryType: 'sum', summaryRenderer: totalCount, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'roadQty', header: '在途数', width: 70,\n" +
                "            summaryType: 'sum', summaryRenderer: totalCount, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'unpaidQty', header: '未付款数', width: 70,\n" +
                "            summaryType: 'sum', summaryRenderer: totalCount, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'safeQty', header: '安全库存', width: 70,\n" +
                "            summaryType: 'sum', summaryRenderer: totalCount, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'pickQty', header: '可配数', width: 70,\n" +
                "            summaryType: 'sum', summaryRenderer: totalCount, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'effectiveDate', header: '有效日期', width: 120, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'shelfLife', header: '保质期', width: 100, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'productionDate', header: '生产日期', width: 120, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "    <@shiro.hasPermission name=\"costprice:view\">\n" +
                "        {\n" +
                "            dataIndex: 'costPrice', header: '成本价', width: 70, xtype: 'numbercolumn',\n" +
                "            format: '0.0000', summaryType: 'sum', summaryRenderer: totalCountFloat, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "    </@shiro.hasPermission>\n" +
                "    <@shiro.hasPermission name=\"actualprice:view\">\n" +
                "        {\n" +
                "            dataIndex: 'salesPrice', header: '销售价格', width: 70, xtype: 'numbercolumn',\n" +
                "            format: '0.0000', summaryType: 'sum', summaryRenderer: totalCountFloat, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "    </@shiro.hasPermission>\n" +
                "    <@shiro.hasPermission name=\"costprice:view\">\n" +
                "        {\n" +
                "            dataIndex: 'costPriceCount', header: '成本总金额', width: 100, xtype: 'numbercolumn',\n" +
                "            format: '0.0000', summaryType: 'sum', summaryRenderer: totalCountFloat, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "    </@shiro.hasPermission>\n" +
                "    <@shiro.hasPermission name=\"actualprice:view\">\n" +
                "        {\n" +
                "            dataIndex: 'salesPriceCount', header: '销售总金额', width: 100, xtype: 'numbercolumn',\n" +
                "            format: '0.0000', summaryType: 'sum', summaryRenderer: totalCountFloat, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "    </@shiro.hasPermission>\n" +
                "        {\n" +
                "            dataIndex: 'itemNote', header: '商品备注', width: 120, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'itemSkuNote', header: '规格备注', width: 120, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'stockStatusName', header: '库存状态', width: 120, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'supplierOuterId', header: '供应商货号', width: 120, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "        {\n" +
                "            dataIndex: 'unit', header: '单位', width: 70, listeners: {\n" +
                "            'headerClick': function (ct, column) {\n" +
                "                flagKeep(ct.grid, ct, column);\n" +
                "            }\n" +
                "        }\n" +
                "        },\n" +
                "    ];\n" +
                "\n" +
                "    function totalCount(value, summaryData, dataIndex) {\n" +
                "        return value;\n" +
                "    }\n" +
                "    //当页排序行号更新\n" +
                "    function flagKeep(dataGrid, ct, column) {\n" +
                "        /*搜索按钮恢复*/\n" +
                "        var store = dataGrid.getStore();\n" +
                "        var data = store.data.items;\n" +
                "        var dataSort = ct.items.items;\n" +
                "\n" +
                "        for (var i = 0; i < dataSort.length; i++) {\n" +
                "            var cln = dataSort[i];\n" +
                "            var clnIdx = cln.dataIndex;\n" +
                "            var currentIdx = column.dataIndex;\n" +
                "            if (currentIdx != clnIdx) {\n" +
                "                clnIdx = currentIdx;\n" +
                "                if (cln.hasCls('x-column-header-sort-DESC')) {\n" +
                "                    cln.removeCls(\"x-column-header-sort-DESC\");\n" +
                "                }\n" +
                "\n" +
                "                if (cln.hasCls('x-column-header-sort-ASC')) {\n" +
                "                    cln.removeCls(\"x-column-header-sort-ASC\");\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        //重新载入（解决静态排序导致行号从第一页开始）\n" +
                "        store.loadData(data);\n" +
                "        ///*订单标记载入*/\n" +
                "        var currentPage = store.currentPage;\n" +
                "        var pageSize = store.pageSize;\n" +
                "        for (var i = data.length; i--;) {\n" +
                "            var record = data[i];\n" +
                "            //记录该行的序号（解决静态排序导致行号从第一页开始）\n" +
                "            record.index = (currentPage - 1) * pageSize + (record.index);\n" +
                "        }\n" +
                "        //重新载入（解决静态排序导致行号从第一页开始）\n" +
                "        store.loadData(data);\n" +
                "\n" +
                "    }\n" +
                "    function totalCountFloat(value, summaryData, dataIndex) {\n" +
                "        return value.toFixed(4);\n" +
                "    }\n" +
                "\n" +
                "    if (top.window.location.hostname == \"localhost\" && !(/testmin/.test(top.window.location.href))) {\n" +
                "        var dev = new Date().getTime();\n" +
                "        seajs.config({\n" +
                "            alias: {//别名配置\n" +
                "                'ext': _SEACONFIG.ext,\n" +
                "                'ext-lang': _SEACONFIG.extlang,\n" +
                "                'jquery': _SEACONFIG.jquery,\n" +
                "                'comboGrid': '/js/comm/comboGrid.js?' + dev,\n" +
                "                'extFunc': '/js/comm/extFunc.js?' + dev,\n" +
                "                'autoSelect': '/js/comm/plugin/autoSelect.js?' + dev,\n" +
                "                'calendar': '/js/comm/plugin/calendar.js?' + dev,\n" +
                "                'validateForm': '/js/comm/plugin/validateForm.js?' + dev,\n" +
                "                'gyUi': '/js/comm/plugin/gyUi.js?' + dev,\n" +
                "                'uniqueDetailWindow': '/js/comm/commwin/uniqueDetailWindow.js?' + dev,\n" +
                "                'batchManagementDetailWindow': '/js/comm/commwin/batchManagementDetailWindow.js?' + dev,\n" +
                "                'stockTenantController': '/js/stock/message/stockTenantController.js?' + dev,\n" +
                "                'stockTenantModel': '/js/stock/message/stockTenantModel.js?' + dev,\n" +
                "                'stockTenant': '/js/stock/message/stockTenant.js?' + dev,\n" +
                "                'ajaxUpload': '/js/comm/plugin/ajaxfileupload.js?' + dev\n" +
                "            },\n" +
                "            base: '${ctx}'\n" +
                "        })\n" +
                "    } else {\n" +
                "        seajs.config({\n" +
                "            alias: {//别名配置\n" +
                "                'ext': _SEACONFIG.ext,\n" +
                "                'ext-lang': _SEACONFIG.extlang,\n" +
                "                'jquery': _SEACONFIG.jquery,\n" +
                "                'extFunc': _SEACONFIG.extFunc,\n" +
                "                'gyUi': _SEACONFIG.gyUi,\n" +
                "                'uniqueDetailWindow': _CDNPATH +'/static/js/dist/comm/uniqueDetailWindow.d268885e.js',\n" +
                "                'batchManagementDetailWindow':_CDNPATH + '/static/js/dist/comm/batchManagementDetailWindow.17911a58.js',\n" +
                "                'stockTenant': _CDNPATH + '/static/js/dist/stockTenant.f1cb03c8.js',\n" +
                "                'ajaxUpload': _CDNPATH + '/static/js/dist/comm/ajaxfileupload.d0d67f08.js'\n" +
                "            },\n" +
                "            base: _CDNPATH\n" +
                "        })\n" +
                "    }\n" +
                "    seajs.use('stockTenant');\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";
        System.out.println("Start...");
        //String patterKey = "(<(!|#)--(.|[\r\n])*?-->)|(//.*)|/\\*.*\\*/$";
        //String patterKey = "(<(!|#)--(.|[\r\n])*?-->)";
        //String patterKey = "(/\\*.*)";
        //String patterKey = "(<(!|#)--(.|[\r\n])*?-->)|(//.*)|(/\\*.*)";
        //Pattern pattern2 = Pattern.compile(patterKey);
        //Matcher matcher2 = pattern2.matcher(fileStr);
        //String replayStr = fileStr;
        //while (matcher2.find()) {
        //    System.out.println(matcher2.group());
        //    //replayStr = matcher2.replaceAll("");
        //}
        String replayStr = fileStr.replaceAll("(?<!:)\\/\\/.*|\\/\\*(\\s|.)*?\\*\\/|<(!|#)--(.|[\r\n])*?-->|(\\(.*==.*\\))", "");
        //String replayStr = fileStr.replaceAll("(\\(.*==.*\\))", "");
        //System.out.println(replayStr);
        if (replayStr.length() > 0) {
            search(replayStr);
        }
        System.out.println("End.");
    }

    private void search(String fileStr) {
        //String key0 = "[^\\x00-\\xff].*[^\\x00-\\xff]+";
        //Pattern pattern0 = Pattern.compile(key0);
        //Matcher matcher0 = pattern0.matcher(fileStr);
        //while (matcher0.find()) {
        //    System.out.println(matcher0.group());
        //}

        //String key = "[\\(\\[]?[a-zA-Z]{0,10}+[\\u4e00-\\u9fa5].*[\\u4e00-\\u9fa5]+[a-zA-Z]{0,10}+[\\)\\]]?";
        String key = "[\\(\\[]?[\\d]?[a-zA-Z]{0,10}+[^\\x00-\\xff].*[^\\x00-\\xff]+[a-zA-Z]{0,10}+[\\)\\]]?";
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(fileStr);
        while (matcher.find()) {
            String matchStr2 = matcher.group();
            String keywordString2 = "[\"<>']*[\\x00-\\xff]*[\"<>']";
            matchStr2 = matchStr2.replaceAll(keywordString2, "@@@");
            //Pattern con2 = Pattern.compile(keywordString2);
            //Matcher matcher2 = con2.matcher(matchStr2);
            //if (matcher2.find()) {
            //    //String keywordString3 = "[\\u4e00-\\u9fa5]+";
            //    String keywordString3 = "[^\\x00-\\xff]+";
            //    Pattern con3 = Pattern.compile(keywordString3);
            //    Matcher matcher3 = con3.matcher(matchStr2);
            //    while (matcher3.find()) {
            //        System.out.println(matcher3.group());
            //    }
            //} else {
            //    System.out.println(matchStr2);
            //}
            String[] list = matchStr2.split("@@@");
            for (String s : list) {
                //System.out.println(s.replaceAll("\\s", ""));
                System.out.println(s.trim());
                try {
                    getPinYin(s.trim());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            //System.out.println(matchStr2);
        }
    }

    private String getPinYin(String text) throws Exception {
        String res;
        //String key = "[^\\u4e00-\\u9fa5]+";
        //String tmpText = text.replaceAll(key, "v");
        String tmpText = text;
        int leng = tmpText.length();
        if (leng > 10) {
            res = PinyinHelper.getShortPinyin(tmpText);
        } else {
            //WITH_TONE_NUMBER: 加上音标识别 WITHOUT_TONE:不识别
            res = PinyinHelper.convertToPinyinString(tmpText, "", leng < 4 ? PinyinFormat.WITH_TONE_NUMBER : PinyinFormat.WITHOUT_TONE);
        }
        if (res.length() > 25) {
            return res.substring(0, 25);
        }
        res = res.replaceAll("[^a-zA-Z\\d]", "v");
        if (res.length() > 25) {
            return res.substring(0, 25);
        }
        System.out.println(res);
        return res.toLowerCase();
    }

    @Test
    public void testRep() {
        List<String> strList = new ArrayList<>();
        strList.add("亲爱的，物流状态显示您的宝贝已签收，{物流公司}：{物流单号}。有什么问题及时联系客服。");
        strList.add("波次策略新增/编辑 - 管易C-ERP电子商务管理软件V3");
        strList.add("合并订单时，按照仓库匹配规则重新匹配；&#10; 2.如有手工指定的仓库订单，合并后默认匹配手工指定的仓库；");
        strList.add("自动生成生产日期(当前日期)");
        strList.add("请选择正确的excel文件[支持.xls/.xlsx格式]！");
        strList.forEach(str -> {
            String param2 = str.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]").replaceAll("#", "\\\\#");
            System.out.println(param2);
        });
    }
}
