package com.design.command;

/**
 * @Auther: eclair
 * @Date: 2019/9/10 11:39
 * @Description:
 */
public class TestSplitNum {
	public static void main(String[] args) {
		int num = 100;
		showSplitSumForNum(num);
	}

	/**
	 * 和试分解
	 * @param num
	 */
	public static void showSplitSumForNum(int num) {
		if (num <= 0) return;
		int temp = num;
		StringBuilder sb = new StringBuilder();
		//1单独处理
		while (temp > 0) {
			sb.append("1+");
			temp--;
		}
		sb.replace(sb.toString().length() - 1, sb.toString().length(), "").append("=" + num + "\n");

		for (int i = 2; i < num; i++) {
			int tailNum = num % i;
			int loopNum = num / i;
			for (int j = 0; j < loopNum; j++) {
				sb.append(i + "+");
			}
			if (tailNum != 0) {
				sb.append(tailNum + "=" + num).append("\n");
			} else {
				sb.append("=" + num).append("\n");
			}

		}
		System.out.println(sb.toString());
	}
}
