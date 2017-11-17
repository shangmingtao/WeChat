package cn.milo.util;

import org.apache.log4j.Logger;


/******************************************************
 ****** @ClassName   : StringUtil.java            
 ****** @illustration: illustration                                 
 ****** @author      : 商明涛 ^ ^                     
 ****** @date        : 2017-1-10 下午12:09:46      
 ****** @version     : v5.0.x                      
 *******************************************************/
public class StringUtil {
	
	static Logger log = Logger.getLogger(StringUtil.class);
	
	/***          
	 *** @illustration: String 类型校验                 
	 *** @Description : Description                  
	 *** @author      : 商明涛 ^ ^                     
	 *** @date        : 2017-1-2 下午5:07:50      
	 *** @version     : v5.0.x                  
	 ***/
	public static boolean isEmpty(String str) {
		if (str != null && !str.equals(""))
			return false;
		return true;
	}
}
