package case_4;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class MapClass extends Mapper<LongWritable, Text,Text, Text>
	{

		public void map(LongWritable key, Text value, Context context ) 
		{   
			try
			{
			String record = value.toString();
			String[] str = record.split(";");
			String id=str[5];
			String sale=str[8];
			String ageGroup=str[2];
		
			context.write(new Text(id),new Text(ageGroup+","+sale));
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			}
			
	
		}
