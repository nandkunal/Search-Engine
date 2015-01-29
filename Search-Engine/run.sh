#!/bin/sh
n=$#
if [ $n -ne 1 ]
then
echo "Error!please provide sample data path"
exit 1
fi
if [ -e "$1" ]
then
if [ ! -d "bin" ]
then
mkdir bin
else
rm -rf bin
mkdir bin
fi
#Compiling Files
echo "Compiling Source..."
javac -d bin -sourcepath src  src/com/iiit/parser/Main.java
echo "All Source Compiled Successfully"
java -cp bin com.iiit.parser.Main $1	
else
echo "Invalid File Path. File does not exists"	
fi