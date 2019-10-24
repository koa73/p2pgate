#!/bin/sh

cd /usr/local/p2p-gate

SCRIPT="${0##*/}"
SERVICE="${SCRIPT%.*}"

set -o allexport
source ./etc/config
LOG_FILE=$SERVICE
set +o allexport

BINDIR=$ROOTDIR/bin
OPTIONS="-Xmx400M"
PIDFILE=./run/$SERVICE.pid



echo $LOG_PATH

case "$1" in
    start)
        if [ -f $PIDFILE ]
        then
            echo "$PIDFILE exists, process is already running or crashed"
        else
            echo -ne "Starting \E[34;40m\033[1m$SERVICE\033[0m service ."
            java $OPTIONS -jar $BINDIR/$SERVICE.jar $CONFIG 1 > $LOG_PATH/$SERVICE.log & echo $! >$PIDFILE

            while ! grep -q -m 1 "Started\|failed" $LOG_PATH/$SERVICE.log; do
              sleep 1
              echo -ne "."
            done

            if grep -q "failed" $LOG_PATH/$SERVICE.log
            then
               echo -ne '\E[31;40m'"\033[1m [Error]\033[0m"
               rm $PIDFILE
            else
               echo -ne '\E[32;40m'"\033[1m [oK]\033[0m"
            fi
            echo
        fi
        ;;
    stop)
        if [ ! -f $PIDFILE ]
        then
            echo "$PIDFILE does not exist, process is not running"
        else
            PID=$(cat $PIDFILE)
            echo "Stopping ..."
            echo ${PID}
            kill -6 ${PID} && rm  $PIDFILE
            while [ -x /proc/${PID} ]
            do
                echo "Waiting for $SERVICE to shutdown ..."
                sleep 1
            done
            echo "$SERVICE stopped"
        fi
        ;;
    status)
        if [ -f $PIDFILE ]
        then
            PID=$(cat $PIDFILE)
            if [! -x /proc/${PID} ]
               then
                  echo 'Service "'$SERVICE'" is not running'
               else
                  echo 'Service "'$SERVICE'" is running ('$PID')'
               fi
        else
            echo 'Service "'$SERVICE'" is not running'
        fi
        ;;
    restart)
        $0 stop
        $0 start
        ;;
    *)
        echo -e '\E[37;40m'"\033[1mPlease use start, stop, restart or status as first argument\033[0m"
        ;;
esac

