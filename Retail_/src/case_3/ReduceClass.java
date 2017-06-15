package case_3;

import java.io.IOException;
import java.util.TreeMap;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class ReduceClass extends Reducer<Text,Text,NullWritable, Text>
	 {
		
		   private TreeMap<Long, Text> tm = new TreeMap<Long, Text>();
		   
		    public void reduce(Text key, Iterable<Text> values,Context context) throws IOException, InterruptedException
		    {
		      long sum = 0;
				String myvalue=null;
				String mysum=null;
				String age=null;
		        
		         for (Text val : values)
		         {       	
		        	 String str[]=val.toString().split(",");
		        	sum += Long.parseLong(str[1]);
		           age=str[0];
		         }
		         myvalue=key.toString();
		         
		         mysum=String.format("%d",sum);
		         myvalue=myvalue+","+mysum+","+age;
		         
		         tm.put(new Long(sum),new Text(myvalue));
		         if (tm.size() > 5)
		         {
						tm.remove(tm.firstKey());
				}
		    }
	      
		    protected void cleanup(Context context) throws IOException, InterruptedException 
	    	{
	    	
		    	for (Text t : tm.descendingMap().values())
		    	{
		    		context.write(NullWritable.get(), t);
	    	
            
		    	}
	    	} 
			
		}