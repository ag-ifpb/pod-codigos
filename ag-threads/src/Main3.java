import java.math.BigInteger;

public class Main3 {
	//SET V, 0
	private static Object lock = new Object();
	private static int v = 0;//exclusividade de acesso
	
	java.util.concurrent.locks.ReentrantLock l;

	public static void main(String[] args) throws InterruptedException {
		//
		int i = 0;
		while(i < 1000){
			//
			Thread t1 = new Thread(){
				public void run() {
					synchronized (lock) {
						v = v + 3;//enq inst
						v = v + 5;
					}
				};
			};
			
			Thread t2 = new Thread(){
				public void run() {
					synchronized (lock) {
						v = v + 7;//exec1
						v = v + 11;
					}
				};
			};
			//
			t1.start();
			t2.start();
			//
			synchronized (lock){//<-- 8
				System.out.println("Valor de v: " + v);//<-- 0, 2, 3, 4, 5, 6, 7 e 8
				System.out.println("Valor de v: " + v);//<-- 0, 2, 3, 4, 5, 6, 7 e 8
				System.out.println("-------");
				v = 0;
			}
			//
			i++;
		}
		
	}
}
