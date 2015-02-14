#!/bin/sh
IFS=$'\n'
skip_first_line=false
for line in $(cat $2)
do
	
	if [ $skip_first_line == true ]
		then 
#java -cp bin com.iiit.parser.SearchQueryHandler "$1" "$line"
java -cp bin com.iiit.parser.SecondaryIndexMain "$1" "$line"
fi
skip_first_line=true
done


