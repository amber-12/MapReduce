package case1;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class Driver {

	public static class MapClass extends Mapper <LongWritable,Text,DoubleWritable,Text>
	{
		Text result=new Text();
		DoubleWritable result1=new DoubleWritable();
		
		public void map(LongWritable key,Text value,Context context)
		{
			try
			{
			String [] str=value.toString().split(",");
			double amt=Long.parseLong(str[3]);
			String str1=str[0];
			if(amt>160)
			{
				result1.set(amt);
				result.set(str1);
				context.write(result1,result);
			}
			
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	public static class ReduceClass extends Reducer<DoubleWritable,Text,DoubleWritable,Text>
	{   Text result1=new Text();
		//DoubleWritable result=new DoubleWritable();
		public void reduce(DoubleWritable key,Iterable<Text> value,Context context) throws IOException, InterruptedException
		{
			//double sum=0.00;
			for(Text val:value)
			{
				result1.set(val);
			}
			context.write(key,result1);
		}
		
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		   
		 Job job = new Job(conf);
		    job.setJarByClass(Driver.class);
		    job.setMapperClass(MapClass.class);
		    //job.setCombinerClass(ReduceClass.class);
		    job.setReducerClass(ReduceClass.class);
		    //job.setNumReduceTasks(0);
		    job.setOutputKeyClass(Text.class);
		    job.setOutputValueClass(LongWritable.class);
		    FileInputFormat.addInputPath(job, new Path(args[0]));
		    FileOutputFormat.setOutputPath(job, new Path(args[1]));
		    System.exit(job.waitForCompletion(true) ? 0 : 1);


	}

}
