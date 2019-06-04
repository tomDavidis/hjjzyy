#获取当前目录的上一级目录
SERVER=$(cd `dirname $0`;cd ..;pwd)

cd $SERVER
JARNAME=`ls -t|grep ".jar"|head -1|awk '{print $NF}'`
JARNAME=${JARNAME%.*}

if [ -z "$JAVA_PARAMS" ] ; then
        JAVA_PARAMS="-Xmx384m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$SERVER -Xloggc:$SERVER/gc_log_file.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Djava.security.egd=file:/dev/./urandom -XX:AutoBoxCacheMax=20000 -XX:+PrintCommandLineFlags"
fi

case "$1" in

  start)
    nohup $JAVA_HOME/bin/java $JAVA_PARAMS $DIAMOND_OPTS -jar $JARNAME.jar -Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=63323,server=y,suspend=n >/dev/null 2>&1 &
    echo $! > $SERVER/$JARNAME.pid
    ;;

  stop)
    kill `cat $SERVER/$JARNAME.pid`
    rm -rf $SERVER/$JARNAME.pid
    ;;

  restart)
    $0 stop
    sleep 1
    $0 start
    ;;

  *)
    echo "Usage: run.sh {start|stop|restart}"
esac 
exit 0  