import java.io.IOException;
import java.util.StringTokenizer;
import java.util.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

// Former WordCount class source: https://hadoop.apache.org/docs/r2.7.3/hadoop-mapreduce-client/hadoop-mapreduce-client-core/MapReduceTutorial.html#Example:_WordCount_v1.0 
public class FoF {

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, Text>{

    private final static Text one = new Text("1");
    private Text word = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      ArrayList<Text> list = new ArrayList<Text>();
      ArrayList<Text> slist = new ArrayList<Text>();
      while (itr.hasMoreTokens()) {
          list.add(new Text(itr.nextToken()));
      }

      if(list.size() >= 3)
      {
        for(int i = 0; i < list.size(); i++)
        {
            for(int j = i+1; j < list.size(); j++)
            {
                for(int k = j+1; k < list.size(); k++)
                {
                    slist.clear();
                    slist.add(list.get(j));
                    slist.add(list.get(k));
                    Collections.sort(slist);
                    Text keyout = new Text(list.get(i) + " " + slist.get(0) + " " + slist.get(1));
                    word.set(keyout);
                    Text oneOut = new Text("1");
                    context.write(word, oneOut);
                }
            }
        }
      }
    }
  }

  public static class IntSumReducer
       extends Reducer<Text,Text,Text,Text> {
    private Text result = new Text();

    public void reduce(Text key, Iterable<Text> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      context.write(key, key);
      for (Text val : values) {
        sum += 1;
        if(sum == 2)
        {
            // TODO(ahundt) don't actually want to write the sum out! 
            // TODO(ahundt) Store current_word or current_count?
            Text empty = new Text("");
            result.set(key);
            context.write(key, empty);
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "FoF");
    job.setJarByClass(FoF.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
