#!/usr/bin/env bash

#read -p "please input your name" NAME
#if [ $NAME = root ]
#    then
#        echo "hello ${NAME},welcome!";
#    elif [ $NAME = itcast ]
#        then
#            echo "hello ${NAME}, welcome!"
#        else
#            echo "Get out please"
#fi

#for N in {1..5} ; do
#    echo $N
#done
#
#for (( i = 0; i < 5; i++ )); do
#    echo "welcome $i times"
#done

funWithReturn(){
    echo "add func";
    echo "input first number";
    read -r aNum;
    echo "inout second number";
    read -r bNum;
    return "$((aNum+bNum))"
}
funWithReturn
echo "result is $?"
