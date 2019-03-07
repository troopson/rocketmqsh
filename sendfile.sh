#!/bin/bash

if [ $# -ne 4 ];then
   echo "Usage:"
   echo "sendfile address topic tag filename"
   exit 0
fi


cat $4 | while read line1
do
    echo $line1
    java -cp rmsend.jar producer.Sync $1 $2 $3 "${line1}"
done
