
export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar

hadoop com.sun.tools.javac.Main FoF.java
jar cf FoF.jar FoF*.class
hadoop jar ./FoF.jar FoF simple.input simple.output