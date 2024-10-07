#!/bin/bash
for dir in 03-sets-maps-searching-submissions/*
do
  echo $dir
  cd $dir
  git pull
  cd ../..
done
