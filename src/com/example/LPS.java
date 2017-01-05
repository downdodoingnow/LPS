package com.example;

/*
 * 将长的字符串化为更小的字符串进行求解
 * 获取最长回文子序列思路：倒序开始遍历存储各个长度的子序列数组，
 * 					当读到大于1的长度时，该位置（i,j）在原字符串中所对应的字符一定相同，
 * 					记录这两个字符，继续向上遍历，直到完全遍历完该数组为止。
 */
public class LPS {
	// 用于最长回文数子序列的长度
	public static int[][] L;
	public static String seq = "12345321";

	public static int max(int x, int y) {
		return (x > y) ? x : y;
	}

	public static int lps(int length, int k, String seq) {
		if (k == 1) {
			return 1;
		}
		int i, j;

		// k表示有几个相同的元素
		lps(length, k - 1, seq);
		for (i = 0; i < length - k + 1; i++) {
			j = i + k - 1;
			if (seq.charAt(i) == seq.charAt(j) && k == 2) {
				// 当k为2时最多只有两个元素
				L[i][j] = 2;
			} else if (seq.charAt(i) == seq.charAt(j)) {
				// 加2是指加上这两个相同的元素
				L[i][j] = L[i + 1][j - 1] + 2;
			} else {
				L[i][j] = max(L[i][j - 1], L[i + 1][j]);
			}
		}
		return L[0][length - 1];
	}

	public static void main(String args[]) {
		int seq_length = seq.length();
		// 用于保存子序列的长度
		L = new int[seq_length][seq_length];
		// 对角上面的元素都是相同的
		for (int i = 0; i < seq_length; i++) {
			L[i][i] = 1;
		}
		int lps_length = lps(seq_length, seq_length, seq);
		// 用于存储最长子序列
		String lps_str = "";

		int length = L.length;
		output(length);

		if (lps_length == 1) {
			lps_str = seq.charAt(0) + "";
		} else {
			lps_str = getLPS(length);
		}
		System.out.println(seq + " 中最长的回文子序列为   " + lps_str + "  长度为：" + ""
				+ lps_length);
	}

	// 获取最长回文最序列
	public static String getLPS(int length) {
		String lps_str = "";
		int length1 = 0;
		int temp = 0;// 用于存储前一个长度值
		// 获取最长子序列
		for (int i = length - 1; i >= 0; i--) {
			for (int j = 0; j < length; j++) {
				length1 = L[i][j];
				if (length1 > 1) {
					// 判断temp是为了能够防止获取3个或者2个公共回文子序列时的位置最佳
					if (temp == 3 && Math.abs(i - j) == 2) {
						lps_str = " ";
						lps_str = seq.charAt(i) + "" + seq.charAt(i + 1)
								+ seq.charAt(j);
					} else if (temp == 2 && Math.abs(i - j) == 1) {
						lps_str = " ";
						lps_str = seq.charAt(i) + lps_str + seq.charAt(j);
					} else {
						if (length1 == 3 && length1 > temp) {
							lps_str = seq.charAt(i) + "" + seq.charAt(i + 1)
									+ seq.charAt(j);
							temp = length1;
							break;
						} else if ((length1 == 2 || length1 > 3)
								&& length1 > temp) {
							lps_str = seq.charAt(i) + lps_str + seq.charAt(j);
							temp = length1;
							break;
						}
					}
				}
			}
		}
		return lps_str;
	}

	public static void output(int length) {
		// 输出长度数组
		System.out.println("最长子序列长度数组：");
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (j != 0 && j % (length - 1) == 0) {
					System.out.println(L[i][j]);
				} else {
					System.out.print(L[i][j] + " ");
				}
			}
		}
	}
}
