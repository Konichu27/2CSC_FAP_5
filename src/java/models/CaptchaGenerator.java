// Java pprogram to automatically generate CAPTCHA and
// verify user
import java.util.*;
import java.io.*;

public class CaptchaGenerator
{
	// Returns true if given two strings are same
	static boolean checkCaptcha(String captcha, String user_captcha)
	{
		return captcha.equals(user_captcha);
	}
	
	// Generates a CAPTCHA of given length
	static String generateCaptcha(int n)
	{
		//to generate random integers in the range [0-61]
		Random rand = new Random(62); 
		
		// Characters to be included
		String chrs = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
		// Generate n characters from above set and
		// add these characters to captcha.
		String captcha = "";
		while (n-- > 0){
			int index = (int)(Math.random()*62);
			captcha+=chrs.charAt(index);
		}
		
		return captcha;
	}
}


