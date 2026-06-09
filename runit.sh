#!/bin/bash
#./update.sh
rm log.txt

CSV=data.csv
rm $CSV
echo "name,set,map,all" > $CSV
for dir in 03-sets-maps-searching-submissions/*
do
  echo $dir >> log.txt
	cd $dir
	git pull
	cd ../..
	touch ./src/main/java/org/example/My.java
	rm src/main/java/org/example/My*
	[ -f "$dir/src/main/java/org/example/MySet.java" ] || continue
	[ -f "$dir/src/main/java/org/example/MyMap.java" ] || continue
	cp $dir/src/main/java/org/example/My* src/main/java/org/example/
	./gradlew -q clean
	./gradlew -q build
	#./gradlew test
	java -cp src/java -cp ./build/classes/java/main/ org.example.Main $dir $CSV >> log.txt
done

