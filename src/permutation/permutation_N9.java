package permutation;

import java.util.ArrayList;

public class permutation_N9 {

	public static void main(String[] args) {
		int[] tag = {1,2,3,4,5,6,7,8,9};
		int[] ans = operator(3, tag);
		for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}	
	/**
	 * num中挑n个排列
	 * @param n
	 * @param num
	 * @return
	 */
	public static int[] operator(int n, int... num ) {
		return split(n, pick(n, num));
	}
	
	/* 数组num中的元素位数为n，单个元素各个位全排列后得到的新数组 
	 * @param n
	 * @param num
	 * @return
	 */
	
	public static int[] split(int n, int... num ) {
		int N = factorial(n)*num.length;
		int[] result = new int[N];
		int[] site = new int[n];
		ArrayList<Integer> buffer = new ArrayList<Integer>();
		for (int i = 0; i < num.length; i++) {
			int temp = num[i];
			for (int j = 0; j < site.length; j++) {
				site[j] = temp%10;
				temp = temp/10;
			}
			
			for (int j = 0; j < factorial(n); j++) {
				buffer.add(permutation(site)[j]);
			}
		}
		
		for (int i = 0; i < result.length; i++) {
			result[i]=buffer.get(i);
		}
		return result;
	}
	
	/* n 的阶乘
	 * @param n
	 * @return
	 */
	public static int factorial(int n) {
		if (n == 1||n==0) {
			return 1;
		} else {
			return n*factorial(n-1);
		}
	}
	
	/* 除去数组num中的数x，返回新的数组。
	 * @param x
	 * @param num
	 * @return
	 */
	
	public static int[] f(int x, int... num) {
		int[] result = new int[num.length-1];
		int index = -1;
		int come=0;
		
		if (num.length ==1) {
			result= num;
		} else {
			for (int i = 0; i < num.length; i++) {
				if(num[i]==x){
					index = i;
				}
			}
			
			for (int i = 0; i < index; i++) {
				result[i]=num[i];
			}
			for (int i = index; i < result.length; i++) {
				result[i] = num[i+1];
			}
		}
				return result;		
	}
	
	/*数组num中元素的的全排列
	 * @param num->array
	 * @return
	 */
	public static int[] permutation(int... num) {
		
		int[] result = new int[ factorial(num.length)];
		
		if ( factorial(num.length)==1) {
			result[0] =num[0];
			
		} else {
			for (int i = 0; i < num.length; i++) {
				for (int j = 0; j < factorial(num.length-1); j++) {
					int k = (i)*factorial(num.length-1)+j;
					result[k] = num[i]*(int)(Math.pow(10, num.length -1))+
							permutation(f(num[i], num))[j];
				}
				
			}
	}
				return result;
		
}
	
	/**
	 * 数组num中挑选n个出来组合
	 * @param n
	 * @param num
	 * @return
	 */
	public static int[] pick(int n, int... num) {
		int N = factorial(num.length)/(factorial(n)*factorial(num.length-n));
		int[] result = new int[N];
		if (n==1) {
			result= num;
		} else {

			 result = new int[N];
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < num.length; i++) {
				for (int j = 0; j < pick(n-1, num).length; j++) {
					int[] site = new int[n-1];
					Boolean isBig = true;
					int temp = pick(n-1, num)[j];
					for (int k = 0; k < site.length; k++) {
						site[k] = temp%10;
						temp = temp /10;
						if(num[i]>site[k]){
							isBig = isBig && true;
						}else{
							isBig = isBig && false;
						}
					}
					
					if(isBig){
						list.add(num[i]*(int)(Math.pow(10, n-1))+pick(n-1, num)[j]);
					}
				}
			}
			
			for (int i = 0; i < result.length; i++) {
				result[i] = list.get(i);
			}
			
		}
		
		return result;
	
	}}
