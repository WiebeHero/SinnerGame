package me.WiebeHero.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {

	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream in = loader.getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while((line = br.readLine()) != null) {
				builder.append(line + "\n");
			}
			br.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
		return builder.toString();
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		}
		catch(NumberFormatException ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	public static float parseFloat(String number) {
		try {
			return Float.parseFloat(number);
		}
		catch(NumberFormatException ex) {
			ex.printStackTrace();
			return 0.00F;
		}
	}
}
