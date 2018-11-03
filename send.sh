#!/bin/sh
c=$4
java -cp rmsend.jar producer.Sync $1 $2 $3 "${c}"
