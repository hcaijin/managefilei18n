#!/bin/bash
##
## example:
##  ./doSpringI18n.sh cerp.web.tc.delivery.huiyuanmingcheng 会员名称 /home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/webapp/WEB-INF/views/tc/delivery/
##
## find . -iregex "./[a-z]+\.ftl" | xargs sed -i 's/会员名称/<@spring.message \"cerp.web.tc.delivery.huiyuanmingcheng\"\/>/g'
##

KEY=$1
NAME=$2
DIR=$3

#echo $KEY
#echo $NAME
#echo $DIR

cd ${DIR}
#echo "find . -iregex \"./[a-z]+\.ftl\" >> ./dospringi18n-find.log"
#find . -iregex "./[a-z]+\.ftl" >> ./doSpringI18n-find.log
find . -iregex "./[a-z]+\.ftl" | xargs sed -i "s#\<$NAME\>#<@spring\.message \"$KEY\"/>#g"
[[ $? -ne 0 ]] && exit 2
