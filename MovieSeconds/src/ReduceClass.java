import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;


public class ReduceClass extends Reducer<Text,Text,NullWritable,LongWritable>
{
	public LongWritable result =new LongWritable();
public void reduce(Text key,Iterable<Text> value,Context context) throws IOException, InterruptedException
{
	long count=0;
	
	
	
		
	
	for(Text val:value)
	{
	
			count++;
		
			
	}
	result.set(count);
	
context.write(null,result);
}

 }
