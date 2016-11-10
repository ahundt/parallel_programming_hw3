
export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar
export PATH=$PATH:/home/parallel/hadoop/bin/
export JAVA_HOME=/usr/lib/jvm/java-7-oracle/

#hadoop jar hadoop/share/hadoop/tools/lib/hadoop-streaming-2.7.3.jar -mapper ./fof.mapper.pl -reducer fof.reducer.pl -input simple.input -output simple.output


hadoop com.sun.tools.javac.Main FoF.java
jar cf FoF.jar FoF*.class
hadoop jar ./FoF.jar FoF simple.input simple.output


# hadoop fs -mkdir -p /user/parallel
# parallel@parallel-pr3|~/parallel_programming_hw3 on master
# hadoop fs -copyFromLocal simple.output /user/parallel/simple.input

## Run Hadooop:
#  git pull && hadoop com.sun.tools.javac.Main FoF.java && jar cf FoF.jar FoF*.class && hadoop jar ./FoF.jar FoF simple.input simple.output

# hadoop fs -put /user/parallel/simple.output
# hadoop fs -rm -r /user/parallel/simple.output

# mkdir
# hadoop fs -mkdir -p /user/parallel/simple.output



# other command line commands:

# cat simple.input/* | python fof.mapper.py  | sort | python fof.reducer.py


# hadoop/sbin/start-all.sh


# hadoop fs -put simple.input /

# hadoop fs -rm -r /simple.output


# hadoop jar /home/parallel/hadoop/share/hadoop/tools/lib/hadoop-streaming-2.7.3.jar -mapper 'python fof.mapper.py' -reducer 'python fof.reducer.py' -input /simple.input -output simple.output


# hadoop fs -get simple.output ~/parallel


# hadoop fs -cat /user/parallel/simple.output/part-r-00000



# ———————————————————




# hadoop fs -copyFromLocal simple.output /