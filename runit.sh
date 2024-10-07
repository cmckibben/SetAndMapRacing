#!/bin/bash
./update.sh
for dir in 03-sets-maps-searching-submissions/*
do
  echo $dir
	touch ./src/main/java/org/example/My.java
	rm src/main/java/org/example/My*
	[ -f "$dir/src/main/java/org/example/MySet.java" ] || continue
	[ -f "$dir/src/main/java/org/example/MyMap.java" ] || continue
	cp $dir/src/main/java/org/example/My* src/main/java/org/example/
	gradle -q clean
	gradle -q build
	gradle test
	java -cp src/java -cp ./build/classes/java/main/ org.example.Main
done

