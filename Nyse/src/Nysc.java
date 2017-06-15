import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;




public class Nysc {
	public static class MapClass extends Mapper<LongWritable,Text,LongWritable, NullWritable>
	   {
	      public void map(LongWritable key, Text value, Context context)
	      {	    	  
	         try{
	            String[] str = value.toString().split(",");	 
	            Double high = Double.parseDouble(str[4]);
	            Double low = Double.parseDouble(str[5]);
	             Double variance=(high-low)/low*100;
	            context.write(key,null);
		               
	         }
	         catch(Exception e)
	         {
	            System.out.println(e.getMessage());
	         }
	      }
	   }
	public static class ReduceClass extends Reducer<LongWritable,NullWritable,NullWritable,LongWritable>
	   {
		public void reduce(LongWritable key, Iterable<NullWritable> values,Context context) throws IOException, InterruptedException
		{
			context.write(null,key);
			
		}
	   }
	
 static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
	 Configuration conf = new Configuration();
	    //conf.set("name", "value")
	    
	    Job job = new Job(conf);
	    job.setJarByClass(Nysc.class);
	    job.setMapperClass(MapClass.class);
	   
	    job.setReducerClass(ReduceClass.class);
	   
	    job.setOutputKeyClass(LongWritable.class);
	    job.setOutputValueClass(NullWritable.class);
	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	

	}

}
