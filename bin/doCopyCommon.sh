#!/bin/bash
##
## example:
##

DIR=$1

#if [[ $TEMP -eq "" ]]; then
#    mkdir -p $TEMP
#fi

cd ${DIR}
for file in $(ls)
do
#   sed -n "/\<$file\>/p" /home/hcj/Work/data/managefilei18n/temp/info-message.outv2.properties >> /home/hcj/Work/data/managefilei18n/temp/infov2/${file}.properties
    cp ${file} ${file}.bak
    sed -e 's/ifcommon/common/g' /home/hcj/Work/data/managefilei18n/temp/common-info-hcj.properties/info-common-message.outv2.properties >> ${file}.bak
#    sed -i 's/@#@/=/g' ${file}
done

[[ $? -ne 0 ]] && exit 2
