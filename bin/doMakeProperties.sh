#!/usr/bin/env bash
##
## 合并多个文件转换为native2ascii

NEWFILE="/home/hcj/Work/data/managefilei18n/temp/common-info-hcj.properties/info-properties"

if [[ ! -f "$NEWFILE" ]]; then
    touch $NEWFILE
fi


cd /home/hcj/Work/data/managefilei18n/temp/common-info-hcj.properties/

echo "# ========================================info common====================================" >> $NEWFILE
cat info-common-message.outv2.properties > $NEWFILE
echo "# ==========================It is a dignity in the dividing line=============================" >> $NEWFILE

cd /home/hcj/Work/data/managefilei18n/temp/infov2/

for file in `ls *.properties`
do
echo "# ================================="${file/.properties/}"====================================" >> $NEWFILE
cat $file >> $NEWFILE
echo "# ==========================It is a dignity in the dividing line=============================" >> $NEWFILE
echo >> $NEWFILE
echo >> $NEWFILE
done

sed -i "s/@#@/=/g" $NEWFILE

if [[ $? -eq 0 ]]; then
    native2ascii $NEWFILE >> /home/hcj/Work/data/ecerp-saas/Sources/ecerp/ecerp-web/src/main/resources/messages.properties
    if [[ $? -eq 0 ]]; then
        echo "native2ascii success"
    else
        echo "native2ascii fail"
    fi
else
    echo "sed replay @#@ to = fail"
fi
