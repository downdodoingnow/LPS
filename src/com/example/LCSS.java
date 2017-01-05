package com.example;

/*
 * 数组c是用来记录最长子序列的长度
 * 数组子序列用来记录是那个子序列所求得的值
 */
public class LCSS {
	public final static int MAXLEN = 100;
	public static String LPS = "";

	public static void LCSLength(char[] x, char[] y, int xLength, int yLength,
			int[][] c, int b[][]) {
		int i, j;
		// 将第一列全部初始化0，因为在其中一个字符串为空时，它们之间不存在子序列
		for (i = 0; i <= xLength; i++) {
			c[i][0] = 0;
		}
		// 将第一行初始化为0
		for (j = 1; j <= yLength; j++) {
			c[0][j] = 0;
		}
		for (i = 1; i <= xLength; i++) {
			for (j = 1; j <= yLength; j++) {
				if (x[i - 1] == y[j - 1]) {
					c[i][j] = c[i - 1][j - 1] + 1;
					b[i][j] = 0;
					// xi！=yi时
				} else if (c[i - 1][j] >= c[i][j - 1]) {
					c[i][j] = c[i - 1][j];
					b[i][j] = 1;
				} else {
					c[i][j] = c[i][j - 1];
					b[i][j] = -1;
				}
			}
		}
	}

	public static void PrintLCS(int b[][], char x[], int xLength, int yLength) {
		if (xLength == 0 || yLength == 0)
			return;
		// 如果等于0则表示该位置两个元素相同输出
		if (b[xLength][yLength] == 0) {
			PrintLCS(b, x, xLength - 1, yLength - 1);
			LPS += x[xLength - 1];
			// 等于1表示x需要向后移动一位
		} else if (b[xLength][yLength] == 1)
			PrintLCS(b, x, xLength - 1, yLength);
		else
			PrintLCS(b, x, xLength, yLength - 1);
	}

	public static void main(String[] args) {
		int b[][] = new int[MAXLEN][MAXLEN];
		int c[][] = new int[MAXLEN][MAXLEN];

		String str_x = "qwersada";
		char x[] = str_x.toCharArray();

		String str_y = "weqasda";
		char y[] = str_y.toCharArray();

		int xLength = str_x.length();
		int yLength = str_y.length();

		LCSLength(x, y, xLength, yLength, c, b);
		PrintLCS(b, x, xLength, yLength);
		System.out.println("最长公共序列为：" + LPS);
	}
}
