package speed;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class SpeedOffence {
	public static class MapClass extends Mapper<LongWritable,Text,Text,IntWritable>
	{
		 public void map(LongWritable key, Text value, Context context)
		 {
			 try
				{
					String[] str =value.toString().split(",");
					int speed =Integer.parseInt(str[1]);
					context.write(new Text(str[0]),new IntWritable(speed));
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
		 }
	}
	public static class ReduceClass extends Reducer<Text,IntWritable,Text,Text> 
	{
	  int offence_per=0;
	  
	  public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException, InterruptedException
	  {
		  int offence_count=0;
		  int total=0;
		  for(IntWritable val: values)
		  {
			  if(val.get()>65)
			  {
			  offence_count++;
			  }
			  total++;
		  }
	  
	  
	  
	  offence_per=(offence_count*100/total);
	  String percentagevalue=String.format("%d",offence_per);
	  String valwithsign=percentagevalue+"%";
	  context.write(key,new Text(valwithsign));
	  }	  
	
	
	
	
	
	}
	
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
    
		 Configuration conf = new Configuration();
		    conf.set("mapreduce.output.textoutputformat.separator",",");
		    Job job = new Job (conf);
		    job.setJarByClass(SpeedOffence.class);
		    job.setMapperClass(MapClass.class);
		    //job.setCombinerClass(ReduceClass.class);
		    job.setReducerClass(ReduceClass.class);
		    job.setOutputKeyClass(Text.class);
		    job.setOutputValueClass(IntWritable.class);
		    job.setInputFormatClass(TextInputFormat.class);
		    job.setOutputFormatClass(TextOutputFormat.class);
		    FileInputFormat.addInputPath(job, new Path(args[0]));
		    FileOutputFormat.setOutputPath(job, new Path(args[1]));
		    System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
