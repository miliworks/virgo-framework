package com.miliworks.virgo.util;

import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class StringUtils
{
	
	/**
	 * MessagePack.address的值可能为sms://....等形式，这个方法提供解析功能，只取出号码部分
	 * 
	 * @param messagePackAddress
	 * @return
	 */
	public static String getPhoneNumber(String messagePackAddress) {
		if (messagePackAddress == null) return null;
		else if ((messagePackAddress.startsWith("sms://+86")) || (messagePackAddress.startsWith("mms://+86")))
		{
			messagePackAddress = messagePackAddress.substring(9);
		}
		else if ((messagePackAddress.startsWith("sms://")) || (messagePackAddress.startsWith("mms://")))
		{
			messagePackAddress = messagePackAddress.substring(6);
		}
		int i;
		if ((i = messagePackAddress.indexOf(':')) != -1)
		{
			messagePackAddress = messagePackAddress.substring(0, i);
		}
		messagePackAddress = getFormatPhoneNumber(messagePackAddress);
		return messagePackAddress;
	}
	
	/**
	 * 之前只是去除+86
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public static String getFormatPhoneNumber(String phoneNumber) {
		if (phoneNumber == null) return null;
		else if (phoneNumber.startsWith("+86"))
		{
			phoneNumber = phoneNumber.substring(3);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0, l = phoneNumber.length(); i < l; i++)
		{
			char ch = phoneNumber.charAt(i);
			if (ch >= '0' && ch <= '9')
			{
				sb.append(ch);
			}
		}
		phoneNumber = sb.toString();
		return phoneNumber;
	}
	
	/**
	 * 去掉减号、空格、+86前缀
	 * 
	 * @param phone
	 * @return
	 */
	public static String getPhoneNumberWithClearMinussign(String phone) {
		if (phone == null) return null;
		phone = getFormatPhoneNumber(phone);
		phone = phone.replaceAll("-", "");
		phone = phone.replaceAll(" ", "");
		return phone;
	}
	
	public static String ShortUrl(long mmsid) {
		//对传入网址进行MD5加密
		String hex = getMD5Str(String.valueOf(mmsid));
		
		//把加密字符按照8位一组16进制与0x3FFFFFFF进行位与运算
		
		long hexint = 0x3FFFFFFF & Long.parseLong(hex.substring(0, 8), 16);
		
		StringBuffer outChars = new StringBuffer();
		
		for (int j = 0; j < 6; j++)
		{
			//把得到的值与0x0000003D进行位与运算，取得字符数组chars索引
			int index = (int) (0x0000003D & hexint);
			//把取得的字符相加
			outChars.append(chars[index]);
			//每次循环按位右移5位
			hexint = hexint >> 5;
		}
		return outChars.toString();
	}
	
	public static byte[] MD5(byte[] sourceBytes) {
		java.security.MessageDigest md = null;
		try
		{
			md = java.security.MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e)
		{
			//#debug
			e.printStackTrace();
		}
		if (md == null) return null;
		md.update(sourceBytes);
		return md.digest(); // MD5 的计算结果是一个 128 位的长整数，
	}
	
	/**
	 * MD5加密
	 */
	public static String getMD5Str(String sourceStr) {
		byte[] source = sourceStr.getBytes();
		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
		        '0',
		        '1',
		        '2',
		        '3',
		        '4',
		        '5',
		        '6',
		        '7',
		        '8',
		        '9',
		        'a',
		        'b',
		        'c',
		        'd',
		        'e',
		        'f'
		};
		java.security.MessageDigest md = null;
		try
		{
			md = java.security.MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e)
		{
			//#debug
			e.printStackTrace();
		}
		if (md == null) return null;
		md.update(source);
		byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
		// 用字节表示就是 16 个字节
		char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
		// 所以表示成 16 进制需要 32 个字符
		int k = 0; // 表示转换结果中对应的字符位置
		for (int i = 0; i < 16; i++)
		{ // 从第一个字节开始，对 MD5 的每一个字节
		  // 转换成 16 进制字符的转换
			byte byte0 = tmp[i]; // 取第 i 个字节
			str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
			// >>> 为逻辑右移，将符号位一起右移
			str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
		}
		s = new String(str); // 换后的结果转换为字符串
		return s;
	}
	
	//要使用生成URL的字符
	private final static char[] chars = new char[] {
	        'a',
	        'b',
	        'c',
	        'd',
	        'e',
	        'f',
	        'g',
	        'h',
	        'i',
	        'j',
	        'k',
	        'l',
	        'm',
	        'n',
	        'o',
	        'p',
	        'q',
	        'r',
	        's',
	        't',
	        'u',
	        'v',
	        'w',
	        'x',
	        'y',
	        'z',
	        '0',
	        '1',
	        '2',
	        '3',
	        '4',
	        '5',
	        '6',
	        '7',
	        '8',
	        '9',
	        'A',
	        'B',
	        'C',
	        'D',
	        'E',
	        'F',
	        'G',
	        'H',
	        'I',
	        'J',
	        'K',
	        'L',
	        'M',
	        'N',
	        'O',
	        'P',
	        'Q',
	        'R',
	        'S',
	        'T',
	        'U',
	        'V',
	        'W',
	        'X',
	        'Y',
	        'Z'
	                                  };
	
	public static String getRealImagePath(String imageFilePath, boolean noBigImg) {
		if (imageFilePath == null) return null;
		return noBigImg ? imageFilePath : (imageFilePath).concat("$GX");
	}
	
	public static String null2String(String string) {
		return string == null ? "" : string;
	}
	
	public static String null2String(Object string) {
		return string == null ? "" : string.toString();
	}
	
	public static String bytes2String(byte[] buffer,int offset,int length) {
		StringBuilder sb = new StringBuilder();
		
		for( int i = offset; i < offset+length; i++ )
		{
			byte b = buffer[i];
			
			sb.append(String.format("%02X", b));
			sb.append(" ");
		}
		
		return sb.toString();
	}
	
	public static String getRandomString(int length) {
		if (length < 0) length = 100;
		if (length > 100) length = 10;
		Random ran = new Random();
		char[] textChars = new char[length];
		for (int i = 0; i < length; i++)
		{
			textChars[i] = chars[ran.nextInt(chars.length)];
		}
		return new String(textChars);
	}
}
