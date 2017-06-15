import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MapClass extends Mapper<LongWritable,Text,Text,Text> {
	public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException
	{
		try
		{
			
		
		String[] str=value.toString().split(",");
			if(!str[4].isEmpty())
			{
				Integer seconds=Integer.parseInt(str[4]);
    		    if(seconds > 4500)		
                {
        	     Text outkey=new Text("1");
        	     String str1=str[1];
        	     context.write(new Text(outkey), new Text(str1));
        
               }
                }
		}catch(Exception e)
                {
                	System.out.println(e.getMessage());
                }
			}
	}


