app_name="sdk-server.jar"
log_name="sdk-server.log"

start() {
  echo "service start..."
  nohup java -jar -Xms256m -Xmx256m $app_name \
      --weixin.jssdk.appid=xxx \
      --weixin.jssdk.secret=xxx \
      --weixin.jssdk.basic-config-token=xxx \
      --spring.profiles.active=prod > ./$log_name 2>&1 &
}

stop() {
  echo "service stop..."
  pid=`getPid`
  kill $pid
}

restart() {
  stop
  start
}

getPid() {
  pid=`ps -ef|grep $app_name|grep -v grep|awk '{printf $2}'`
  echo $pid
}

status() {
  pid=`getPid`
  if [ "$pid" == "" ]; then
    echo "service is stopped"
  else
    echo "service is starting, pid is $pid"
  fi
}

case "$1" in
  start)
    start
    ;;
  stop)
    stop
    ;;
  restart)
    restart
    ;;
  status)
    status
    ;;
  *)
    echo "start | stop | restart | status"
    ;;
esac