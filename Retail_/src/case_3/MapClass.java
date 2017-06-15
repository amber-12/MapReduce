package case_3;


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
			String[] parts = record.split(";");
			String catrgory=parts[4];
			String sale=parts[8];
			String ageGroup=parts[2];
		
			context.write(new Text(catrgory),new Text(ageGroup+","+sale));
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			}
			
	
		}
	