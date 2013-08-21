package com.duoshuo.JWT;
import net.minidev.json.JSONObject;

import com.nimbusds.jose.crypto.MACSigner;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;

public class Example {
	
	static String DUOSHUO_SHORTNAME = "test";
	static String DUOSHUO_SECRET = "3d990d2276917dfac04467df11fff26d";
	
	public static void main(String [] args){
        
        JSONObject userInfo = new JSONObject();
        
        userInfo.put("short_name", DUOSHUO_SHORTNAME);//必须项
        userInfo.put("user_key", "1");//必须项
        userInfo.put("name", "网站用户A");//可选项
        
        Payload payload = new Payload(userInfo);
        
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        header.setContentType("jwt");
        
        
        // Create JWS object
        JWSObject jwsObject = new JWSObject(header, payload);
        
        // Create HMAC signer        
        JWSSigner signer = new MACSigner(DUOSHUO_SECRET.getBytes());
        
        try {
                jwsObject.sign(signer);
                
                
        } catch (JOSEException e) {
        
                System.err.println("Couldn't sign JWS object: " + e.getMessage());
                return;
        }        
        // Serialise JWS object to compact format
        String token = jwsObject.serialize();
        
        System.out.println("Serialised JWS object: " + token);
        //示例输出结果为eyJhbGciOiJIUzI1NiIsImN0eSI6Imp3dCJ9.eyJ1c2VyX2tleSI6IjEiLCJuYW1lIjoi572R56uZ55So5oi3QSIsInNob3J0X25hbWUiOiJ0ZXN0In0.NXKDXwXThzFkyfl_k_-p6mfM5cpOFppvfdIjrjEq14I
	}
}
