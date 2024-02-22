package com.ppvfra.common;
public class CommonUtil {

	private static int regiNum = 0;

	public static String generateRegistrationNumber(String state) {
		StringBuilder sb = new StringBuilder("PPVFRA"+state);
		sb.append(String.format("%04d", ++regiNum));
		return sb.toString();
	}
	public static String generatePassword() {
		String generatedPass = EncryptionUtil.generateRandomString().substring(0, 7);
		return generatedPass;
	}
	
	public boolean validatefield(String inp)
		{
			boolean splchr_flag = false;
			String[] splChrs = { "<", ">", "script", "alert", "truncate", "delete", "insert", "drop",  "xp_", "<>","!", "`", "input" }; 
			for (int i = 0; i < splChrs.length; i++) 
			{
				if (inp.toUpperCase().indexOf(splChrs[i].toUpperCase()) >= 0)
				{
					splchr_flag = true; 
					break;
				}
			}
			return splchr_flag;
		}
	
}
