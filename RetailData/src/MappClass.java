import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class MappClass extends Mapper<LongWritable,Text,Text,IntWritable>
	{	
		IntWritable outvalue=new IntWritable();
		Text outkey=new Text();
		public void map(LongWritable key,Text value,Context context)
		{
			try
			{
				String [] str=value.toString().split(";");
				String cust_id=str[1];
				int sale=Integer.parseInt(str[8]);
				outkey.set(cust_id);
				outvalue.set(sale);
				context.write(outkey,outvalue);
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			
			
		}
	}