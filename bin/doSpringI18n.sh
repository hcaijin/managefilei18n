#!/bin/bash
##
## example:
## find . -iregex "./[a-z]+\.ftl" | xargs sed -i 's/会员名称/<@spring.message \"cerp.web.tc.delivery.huiyuanmingcheng\"\/>/g'
##

KEY=$1
NAME=$2
DIR=$3

#echo $KEY
#echo $NAME
#echo $DIR

find ${DIR} -iregex "./[a-z]+\.ftl" | xargs sed -i "s/${NAME}/\<\@spring\.message \"${KEY}\"\/\>/g"
[[ $? -ne 0 ]] && exit 200
