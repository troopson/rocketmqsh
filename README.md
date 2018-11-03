# rocketmqsh
用来执行rockert mq发送和监听的shell脚本程序


1. 先安装java虚拟机
2. gradle build编译出rmsend.jar包
3. 将jar包拷贝到send.sh, consumer.sh目录下
4. 发送： sh send.sh 10.11.12.13:9876 "topic" "tag" "this is the content"
5. 监听： sh consumer.sh "consumerid" 10.11.12.13:9876 "topic" "tag"
