#!/bin/bash

# $1 - remote project name
# $2 - remote project repository
# $3 - `course` directory

git remote add -f $1 $2
git merge -s ours --allow-unrelated-histories --no-commit $1/master
git read-tree --prefix=$3/$1 -u $1/master
git commit -m "Merge $1 as itmo/$3 subdirectory"
git pull -s subtree $1 master

