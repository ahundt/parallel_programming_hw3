
export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar

hadoop jar hadoop/share/hadoop/tools/lib/hadoop-streaming-2.7.3.jar -mapper ./fof.mapper.pl -reducer fof.reducer.pl -input simple.input -output simple.output


hadoop com.sun.tools.javac.Main FoF.java
jar cf FoF.jar FoF*.class
hadoop jar ./FoF.jar FoF simple.input simple.output