package cn.milo.util;

import java.io.IOException;
import java.util.Properties;




/******************************************************
 ****** @ClassName   : PropertyFactory.java            
 ****** @illustration: illustration                                 
 ****** @author      : 商明涛 ^ ^                     
 ****** @date        : 2017-1-10 下午12:09:39      
 ****** @version     : v5.0.x                      
 *******************************************************/
public class PropertyFactory {
	static Properties pops = new Properties();
	
	static {
		try {
			pops.load(PropertyFactory.class.getClassLoader().getResourceAsStream("system.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private PropertyFactory() {
	}; 

	public static String getProperty(String key) {
		return pops.getProperty(key);
	}
}
