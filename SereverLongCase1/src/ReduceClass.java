import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class ReduceClass extends Reducer<Text,Text,Text,LongWritable> {
	
	LongWritable result=new LongWritable();
	public void reduce(Text key,Iterable<Text>value,Context context) throws IOException, InterruptedException
	{
	  long count=0;
	  long max=0;
	  for(Text val:value)
	  {  
		  count++;
		   }
	  if(count>max)
	  {
		  max=count;
		  result.set(max);
		    
	  }
	  
	  context.write(key,result);
	}

}
