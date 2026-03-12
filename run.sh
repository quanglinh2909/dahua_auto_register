#!/bin/bash
# linux/mac下运行脚本
# @author 47081
ARG_COUNT=$#
#echo $ARG_COUNT
if [ $ARG_COUNT -lt 1 ]; then
  echo "Usage example: ./run.sh com.netsdk.demo.RealPlayByDataType"
  exit 1
fi
current_path=`pwd`
echo $current_path
#源码路径
source_path=src/main/java
#依赖包路径
dependencies_path=libs
res_path=res
#编译输出路径
out_path=target

#检查编译输出路径是否存在，如果存在,删除重新创建
if [ -d $out_path ]; then
  rm -rf $out_path
fi
mkdir $out_path
#复制resources下的jar包到编译目录
cp $dependencies_path/*.jar $out_path
#复制res下xml和properties到编译目录
cp $res_path/*.xml $out_path
cp $res_path/*.properties $out_path
#复制动态库到编译目录
os=$(uname -a)
arch=$(getconf LONG_BIT)
if [[ $os =~ "Darwin" ]]; then
  cp -r $dependencies_path/mac64 $out_path/
  cp=$out_path/mac64:
elif [ $arch == '64' ]; then
  cp -r $dependencies_path/linux64 $out_path/
  cp=$out_path/linux64:
  export_path=$out_path/linux64
  echo ${current_path}/${export_path}
  export LD_LIBRARY_PATH=${current_path}/${export_path}
else
  cp -r $dependencies_path/linux32 $out_path/
  cp=$out_path/linux32:
  export_path=$out_path/linux32
  export LD_LIBRARY_PATH=${current_path}/${export_path}
fi
#获得需要编译的java文件名称，输出到list.txt文件中
find $source_path -name "*.java" >$out_path/list.txt
cp+=$source_path:
for file in $out_path/*.jar; do
  cp+=$file:
done
#javac编译
javac -encoding UTF-8 -d $out_path -cp $cp @$out_path/list.txt
#进入编译文件夹
cd $out_path
cp=.:
# Add lib jars (SQLite JDBC)
for file in ../lib/*.jar; do
  if [ -f "$file" ]; then
    cp+=$file:
  fi
done
for file in ./*.jar; do
  cp+=$file:
done
java -Dfile.encoding=UTF-8 -cp $cp $1
