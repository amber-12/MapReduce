import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class MapClass extends Mapper<LongWritable,Text,Text,Text> {
	public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException
	{
		String[] str=value.toString().split(",");
		int year=Integer.parseInt(str[2]);
		
		if(year>=1945 && year <=1959 )
		{
			Text outkey=new Text("1");
			String str1=str[1];
			context.write(new Text(outkey), new Text(str1));
	
		}
		
		
		
			}
	

}
