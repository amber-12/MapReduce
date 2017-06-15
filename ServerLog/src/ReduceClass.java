
import java.util.*;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class ReduceClass extends Reducer<Text,IntWritable,Text,IntWritable>
{
	public static final NavigableMap<Integer,String> map=new TreeMap<>();
public void reduce(Text inkey,Iterable<IntWritable> invals,Context context) throws InterruptedException, IOException
{
	int count=0;
	for(IntWritable data:invals)
	{
		count++;
	}
	 map.put(count,inkey.toString());
				//context.write(new Text(inkey),new IntWritable(count));
    }
protected void cleanup(Context context) throws IOException,InterruptedException
{ 
			Set<Integer> ob=map.keySet();
			int count=0;
	for(Integer a:ob)
	{
		if(count==(ob.size()-1))
		{
	context.write(new Text(map.get(a)),new IntWritable(a));
		}
		count++;
	}
}
}