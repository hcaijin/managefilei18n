#!/usr/bin/env bash
##
## 批量替换在注释,if里的KEY
##
## find . -name '*.ftl' | xargs sed -i 's/\(\/\*.*\)\(<@spring.message "cerp.common.info.shop.jiesuanzhanghu"\/>\)\(.*\)/\1结算账户\3/g'
##

DIR=$1

#FILE="/home/hcj/Work/data/managefilei18n/temp/common-info-hcj.properties/info-common-message-batch-do-reset.properties"
#FILE="/tmp/info-spring-reset-unicode.log"
#FILE="/tmp/info-spring-sed-space-cut-grep-reverse-uniqu-v2.0.log"
#FILE="/tmp/info-common-ss-uniq-for-unascii.log"
FILE="/tmp/info-spring-sed0327-uniq-for-ascii.log"

for line in `cat $FILE`
do
    #echo $line
    KEY="<@spring.message \""$(echo $line | cut -f1 -d"=")"\"\/>"
    NAME=$(echo $line | cut -f2 -d"=")
    echo $KEY
    echo $NAME
#    echo "find $DIR -name \"*.ftl\" | xargs sed -i \"s#\(<[!#]\{1\}--.*\)\($KEY\)\(.*-->$\)#\1$NAME\3#g\""
#    echo "find $DIR -name \"*.ftl\" | xargs sed -i 's#\(<[!#]\{1\}--.*\)\('$KEY'\)\(.*-->$\)#\1'$NAME'\3#g'"
#    echo "find $DIR -name \"*.ftl\" | xargs sed -i 's#\(<[!#]\{1\}--.*\)\($KEY\)\(.*\)#\1$NAME\3#g'"

#    find $DIR -name "*.ftl" | xargs sed -i 's#\(<[!#]\{1\}--.*\)\('$KEY'\)\(.*\)#\1'$NAME'\3#g'

#    find $DIR -name "*.ftl" | xargs sed -n "/\(<[!#]\{1\}--.*\)\($KEY\)\(.*\)/p"
#    find $DIR -name "*.ftl" | xargs sed -i "s/\(<[!#]\{1\}--.*\)\($KEY\)\(.*\)/\1$NAME\3/g"


#    echo "find $DIR -name \"*.ftl\" | xargs sed -i \"s#\(//.*\)\($KEY\)\(.*\)#\1$NAME\3#g\""
#    find $DIR -name "*.ftl" | xargs sed -n "/\(\/\/.*\)\($KEY\)\(.*\)/p"
#    find $DIR -name "*.ftl" | xargs sed -i "s#\(//.*\)\($KEY\)\(.*\)#\1$NAME\3#g"

#    find $DIR -name "*.ftl" | xargs sed -i "s#\(/\*.*\)\($KEY\)\(.*\)#\1$NAME\3#g"


    find $DIR -name '*.ftl' | xargs sed -i "s#\(/*.*\)\($KEY\)\(.*\)#\1$NAME\3#g"


#    find $DIR -name "*.ftl" | xargs sed -i "s#\(if.*\)\($KEY\)\(.*\)#\1$NAME\3#g"

done

#exit 2

[[ $? -ne 0 ]] && exit 2
