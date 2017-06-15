import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class MapClass extends Mapper<LongWritable,Text,Text,Text> {
	public void map(LongWritable key,Text value, Context context)
	{
		Text result=new Text();
		try
		{
			
			String[] str=value.toString().split(" ");
		    if(str[3].contains("ERROR"))
		    {
		    String str1=str[2];
		    Text outkey=new Text("1");
		    result.set(str1);
		    context.write(result,outkey);
		    }
		
		
		}
		catch(Exception e)
		{
			
		}
	}

}
