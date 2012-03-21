package contiesta.production.backend.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;

public class ClassPathResourceUtil {

	public static String getResourceAsString(String resourcePath) throws IOException
	{
		ClassPathResource cpr = new ClassPathResource(resourcePath);
		String out = "";
		String line = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(cpr.getInputStream()));
		while((line = reader.readLine())!= null)
		{
			out = out.concat(line).concat("\n");
		}
		return out;
	}
	
}
