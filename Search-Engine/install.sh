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
fi
#Compiling Files
echo "Compiling Source..."
javac -d bin -sourcepath src  src/com/iiit/parser/Main.java
javac -d bin -sourcepath src  src/com/iiit/parser/SearchQueryHandler.java
javac -d bin -sourcepath src  src/com/iiit/parser/CreateSecondaryIndex.java
javac -d bin -sourcepath src  src/com/iiit/parser/SecondaryIndexMain.java
echo "All Source Compiled Successfully"
#Run Indexing as Part of Installation
java -cp bin com.iiit.parser.Main $1
echo "-----------------CREATING SECONDARY INDEX ON INVERTED INDEX--------------------------"
java -cp bin com.iiit.parser.CreateSecondaryIndex 	