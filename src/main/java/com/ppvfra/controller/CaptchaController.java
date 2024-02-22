package com.ppvfra.controller;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ppvfra.common.EncryptionUtil;



@Controller
public class CaptchaController {

	private static final int SALT_LENGTH = 10;
	String salt = null;
	char[] arr  = new char[6]; 
	private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaController.class);
	
	
	@GetMapping("/bin/captcha")
	public void getCaptcha(HttpServletRequest request, HttpServletResponse response){
		
		int width = 150;
		int height = 50;

		String uuid = UUID.randomUUID().toString();
		String randomString= uuid.substring(0, 7);

		for (int i = 0; i < arr.length; i++) {
			arr[i] = randomString.charAt(i);
		}
		
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D g2d = bufferedImage.createGraphics();

		Font font = new Font("Georgia", Font.BOLD, 18);
		g2d.setFont(font);

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);

		GradientPaint gp = new GradientPaint(0, 0, Color.lightGray, 0, height / 2,
				Color.lightGray, true);

		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);

		g2d.setColor(new Color(0, 0, 0));

		Random r = new Random();

		String captcha = String.copyValueOf(arr);
		
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < arr.length; i++) {
			x += 10 + (Math.abs(r.nextInt()) % 15);
			y = 20 + Math.abs(r.nextInt()) % 20;
			g2d.drawChars(arr, i, 1, x, y);
		}
		
		g2d.dispose();
		response.setContentType("image/png");
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		OutputStream outputStream = Base64.getEncoder().wrap(arrayOutputStream);
		try {
			ImageIO.write(bufferedImage, "png", outputStream);
		try {
			String randomness = StringUtils.remove(UUID.randomUUID().toString(), "-");
			this.salt = RandomStringUtils.random(SALT_LENGTH, 0, randomness.length(), false, false,
			        randomness.toCharArray(), SecureRandom.getInstance("SHA1PRNG"));
		} catch (NoSuchAlgorithmException e) {
			 this.salt = RandomStringUtils.randomAlphanumeric(SALT_LENGTH);
			e.printStackTrace();
		}
		captcha = EncryptionUtil.encryptWithSHA(this.salt + captcha);
		
		String base64Image = arrayOutputStream.toString("UTF-8");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("salt", salt);
		jsonObject.put("captcha",captcha);
		jsonObject.put("image", base64Image);
		response.setContentType("application/json");
		response.getWriter().write(jsonObject.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/bin/validateCaptcha")
	public void validateCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String code =  request.getParameter("code");
	 	String encCaptcha  = request.getParameter("enc");
	 	String salt = request.getParameter("salt");
	 	
	 	String encryptedVal  = decryptCaptcha(salt,code);
	 	JSONObject jsonObject = new JSONObject();
	 	 if (encryptedVal != null && encCaptcha != null) {

	 	    if (encryptedVal.equals(encCaptcha)) {
	 	    	try {
					jsonObject.put("valid", true);
				} catch (JSONException e) {
					LOGGER.error("Error while preparing JSON: ", e);
				}
	 	    } else {
	 	    	try {
					jsonObject.put("valid", false);
				} catch (JSONException e) {
					LOGGER.error("Error while preparing JSON: ", e);
				}
	 	    }
	 	   response.setContentType("application/json");
	 	   response.getWriter().write(jsonObject.toString());
	 	  }
	}
	
	private String decryptCaptcha(String salt, String code) {
		
		try {
			String encryptedVal = EncryptionUtil.encryptWithSHA(salt + code);
			return encryptedVal;
		} catch (Exception e) {
			LOGGER.error("Error while processing resquest: ", e);
			
		}

		return null;

	}
	
}
