
public class Main0 {
	private static int count = 0;//threadsafe
	private static Object lock = new Object();
	
	//30k ins/s
	
//	private static void sincCount(){ //<-- 1º passa, 2º bloqueia
//		//posso fazer algo
//		synchronized (lock){
//			count++;//threadsafe
//		}
//		//posso algo
//	}
	
	private static void count(){
		Thread t = new Thread(){
			public void run() {
				count++;
			};
		};
		t.start();
	}

	public static void main(String[] args) {
		long t0 = System.nanoTime();
		for (int i = 0; i < 1000; i++){
			count();
		}
		long t1 = System.nanoTime();
		System.out.println(String.format("count: %d, time: %dns", count, (t1-t0)));
		//count: 10000, time: 894427ns - direto, sem threads
		//count: 9999,  time: 661488062ns - com thread
		//count: 10000, time: 585102277ns - com threads sincronizadas
		//count: 10000, time: 588912689ns -
		//count: 20000, time: 590167599ns - 
		//count: 19998, time: 608820751ns (significa que houve repetição)
	}
	
	
}
