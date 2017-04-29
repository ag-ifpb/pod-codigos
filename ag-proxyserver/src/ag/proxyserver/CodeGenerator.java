package ag.proxyserver;

public class CodeGenerator {
	private static char[] hashs = new char[]{'A', 'B', 'C', 'F', 'X', 'Z', '0', '3', '8', '9'};//10
	
	private static int[] indexs(){
		//
		int[] ix = new int[6];
		//
		for (int i = 0; i < ix.length; i++) {
			ix[i] = (int) (Math.random()*10);
		}
		//
		return ix;
	}
	
	public static String code(){
		//
		int sum = 0;
		StringBuffer sb = new StringBuffer();
		for(int i : indexs()){
			sb.append(hashs[i]);
			sum += i;
		}
		//
		int digit = 10 + sum;
		//
		return String.format("CAM %s-%d", sb.toString(), digit);
		//CAM @@@@@@-##
	}
	
	public static void main(String[] args) {
		System.out.println(code());
	}
		
}
