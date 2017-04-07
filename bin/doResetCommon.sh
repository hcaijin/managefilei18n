#!/usr/bin/env bash
##
## 替换在注释里的KEY
##
## 先查询一下
## find . -name "*.ftl" | xargs sed -n '/<[!#]\{1\}--.*\(<@spring.message "cerp.common.info.sou1suo3"\/>\).*-->$/p'
## 匹配多个|
## find .. -name "*.ftl" | xargs sed -n '/<[!#]\{1\}--.*\(<@spring.message "cerp.common.info.sou1suo3"\/>\).*-->$\|if.*<@spring.message "cerp.common.info.bao3cun2"\/>.*)/p'
##
## 替换
## find . -name "*.ftl" | xargs sed -n '/<[!#]\{1\}--.*\(<@spring.message "cerp.common.info.sou1suo3"\/>\).*-->$/p'
##
## 脚本执行
## find $DIR -name "*.ftl" | xargs sed -i "s#\(<[!#]\{1\}--.*\)\($KEY\)\(.*-->$\)#\1$NAME\3#g"
##
## 例子：
##  ./doResetCommon.sh '<@spring.message "cerp.common.info.sou1suo3"/>' 搜索 /home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/info/express/
##

KEY=$1
NAME=$2
DIR=$3

#echo "find $DIR -name \"*.ftl\" | xargs sed -i \"s#\(<[!#]\{1\}--.*\)\($KEY\)\(.*-->$\)#\1$NAME\3#g\""
#echo "find $DIR -name \"*.ftl\" | xargs sed -i \"s#(//.*\)\($KEY\)\(.*\)#\1$NAME\3#g\""
#exit 2

find $DIR -name "*.ftl" | xargs sed -i "s#\(<[!#]\{1\}--.*\)\($KEY\)\(.*-->$\)#\1$NAME\3#g"
find $DIR -name "*.ftl" | xargs sed -i "s#\(//.*\)\($KEY\)\(.*\)#\1$NAME\3#g"

[[ $? -ne 0 ]] && exit 2
