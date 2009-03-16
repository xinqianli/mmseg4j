package com.chenlb.mmseg4j;

/**
 * 
 * 
 * @author chenlb 2009-3-16 上午11:39:42
 */
public class Chunk {

	char[][] words = new char[3][];
	int[] degrees = {-1, -1, -1};
	
	private int count = -1;
	
	/** Word Length */
	private int len = -1;
	/** Largest Average Word Length */
	private double avgLen = -1;
	/** Variance of Word Lengths 就是 标准差的平方 */
	private double variance = -1;
	/** Sum of Degree of Morphemic Freedom of One-Character */
	private int sumDegree = -1;
	
	/** Word Length */
	public int getLen() {
		if(len < 0) {
			len = 0;
			count = 0;
			for(char[] word : words) {
				if(word != null) {
					len += word.length;
					count++;
				}
			}
		}
		return len;
	}
	
	/** 有多少个词，最多3个。*/
	public int getCount() {
		if(count < 0) {
			count = 0;
			for(char[] word : words) {
				if(word != null) {
					count++;
				}
			}
		}
		return count;
	}
	
	/** Largest Average Word Length */
	public double getAvgLen() {
		if(avgLen < 0) {
			avgLen = (double)getLen()/getCount();
		}
		return avgLen;
	}
	
	/** Variance of Word Lengths 就是 标准差的平方 */
	public double getVariance() {
		if(variance < 0) {
			double sum = 0;
			for(char[] word : words) {
				if(word != null) {
					sum += Math.pow(word.length-getAvgLen(), 2);
				}
			}
			variance = sum/getCount();
		}
		return variance;
	}
	
	/** Sum of Degree of Morphemic Freedom of One-Character */
	public int getSumDegree() {
		if(sumDegree < 0) {
			int sum = 0;
			for(int degree : degrees) {
				if(degree > -1) {
					sum += degree;
				}
			}
			sumDegree = sum;
		}
		return sumDegree;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(char[] word : words) {
			if(word != null) {
				sb.append(word).append('_');
			}
		}
		return sb.toString();
	}
	
	public String toFactorString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("len=").append(getLen()).append(", ");
		sb.append("avgLen=").append(getAvgLen()).append(", ");
		sb.append("variance=").append(getVariance()).append(", ");
		sb.append("sum100log=").append(getSumDegree()).append("]");
		return sb.toString();
	}
}