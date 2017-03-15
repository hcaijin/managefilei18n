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
}
