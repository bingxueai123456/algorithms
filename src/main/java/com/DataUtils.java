package com;

import java.util.Date;

/**
 * @Auther: eclair
 * @Date: 2020/10/26 21:31
 * @Description:
 */
public class DataUtils {
	public static String byteToHex(byte[] array) {
		StringBuilder sb = new StringBuilder();
		for (byte b : array) {
			String s = Integer.toHexString(b & 0xff);
			if (s.length() == 1) {
				s = "0" + s;
			}
			sb.append(s);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		int dayHour = 12;
		long depostTime = 1626233612000L;
		long startTime = 1626237268000L;

		long sumDepositTimeMinutes = (long) Math.ceil((startTime - depostTime) / 1000.0 / 60);//275.1
		int sumDepositDays = (int) (sumDepositTimeMinutes / (60 * dayHour));//0
		int sumDepositMinutesExceptDays = (int) (sumDepositTimeMinutes % (60 * dayHour)); //275.11
		int sumDepositHourExceptDays = (int) Math.ceil(sumDepositMinutesExceptDays / 60.0); //5
		long expireTime = sumDepositDays * dayHour * 3600 * 1000L;
		System.out.println(sumDepositTimeMinutes);
		System.out.println(sumDepositMinutesExceptDays);
		System.out.println(sumDepositHourExceptDays);
		System.out.println(expireTime);

		System.out.println(new Date(3600L*1000*2 * 2 +depostTime));
		System.out.println(Math.ceil(2/4.0));

	}
}
