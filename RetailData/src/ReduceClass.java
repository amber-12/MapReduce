import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class ReduceClass extends Reducer<Text,IntWritable,Text,IntWritable>
	   {
		   public Text result=new Text();
		    int max=0;
		    public void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
		      int sum = 0;
				
		         for (IntWritable val : values)
		         {       	
		        	sum += val.get();      
		         }
		         if(sum>max)
			       {
			    	   max=sum;
			    	   result.set(key);
			       }
		         
		    }
		    
		    protected void cleanup(Context context) throws IOException, InterruptedException 
	    	{
	    	
	    	context.write(result, new IntWritable(max));
	    	
               
            }
	   }