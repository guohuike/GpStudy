package com.ghk.study.spring.run;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Encoder {

	public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

	public static final String String_key="2016pete";

	public static void main(String[] args) throws Exception{
		/*String pwd =encode("白哥哥超市");
		System.out.println("=====加密之后："+pwd+"=====");*/
		//String npwd=decode("393734FE8F4EE4E125376B5AFCBEFEB1");
		//System.out.println("=====解密之后："+npwd+"=====");
		//Encoder encoder = JSONObject.parseObject("{}", Encoder.class);
		//System.out.println(encoder.toString());
		//JSONObject jsonObject2 = JSON.parseObject("{\"key\":32}");
		//Encoder jsonObject = JSON.parseObject("{\"key\":32}",Encoder.class);
		//System.out.println("dsgsdfgsdfg");
		List<LocalDateTime> timeList = new ArrayList<>();
		timeList.add(LocalDateTime.now());
		timeList.add(LocalDateTime.of(2020,12,03,12,03));
		timeList.sort((time1,time2)->{
			int i = time1.compareTo(time2);
			if(i == 0){
				return 0;
			}else{
				return -i;
			}
		});
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (LocalDateTime date : timeList) {
			//System.out.println(simpleDateFormat.format(date));
			System.out.println(date);
		}
	}

    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws InvalidAlgorithmParameterException
     * @throws Exception
     */
    public static String encode(String data) {
    	if(data == null)
    		return null;
    	try{
			// 生成一个可信任的随机数源
	    	DESKeySpec dks = new DESKeySpec(String_key.getBytes("utf-8"));
	    	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        //key的长度不能够小于8位字节
	        Key secretKey = keyFactory.generateSecret(dks);

	        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
	        IvParameterSpec iv = new IvParameterSpec(String_key.getBytes());
	        AlgorithmParameterSpec paramSpec = iv;
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey,paramSpec);
			byte[] bytes = cipher.doFinal(data.getBytes());
			return byte2hex(bytes);
    	}catch(Exception e){
    		e.printStackTrace();
    		return data;
    	}
    }

    /**
     * DES算法，解密
     * @param data 待解密字符串
     * @return 解密后的字节数组
     * @throws Exception 异常
     */
    public static String decode(String data) throws Exception{
    	if(data == null)
    		return null;
		DESKeySpec dks = new DESKeySpec(String_key.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		//key的长度不能够小于8位字节
		Key secretKey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
		IvParameterSpec iv = new IvParameterSpec(String_key.getBytes("utf-8"));
		AlgorithmParameterSpec paramSpec = iv;
		cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
		return new String(cipher.doFinal(hex2byte(data.getBytes())));
    }

	/**
	 * 二行制转字符串
	 * @param b
	 * @return
	 */
    private static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp;
		for (int n = 0; b!=null && n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
				hs.append('0');
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

    private static byte[] hex2byte(byte[] b) {
        if((b.length%2)!=0)
            throw new IllegalArgumentException();
		byte[] b2 = new byte[b.length/2];
		for (int n = 0; n < b.length; n+=2) {
		    String item = new String(b,n,2);
		    b2[n/2] = (byte)Integer.parseInt(item,16);
		}
        return b2;
    }

}
