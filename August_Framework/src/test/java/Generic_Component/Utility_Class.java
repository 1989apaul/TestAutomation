package Generic_Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utility_Class {
	
	public String reading_properties(String Skey) throws IOException
	{
		
		FileInputStream fis= new FileInputStream("August.properties");
		Properties prop= new Properties();
		
		prop.load(fis);
		
		return prop.getProperty(Skey);
		
		
		
	}

}
