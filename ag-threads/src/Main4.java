
public class Main4 {
	private static int count = 0;
	private static Object lock = new Object();
	
	private static Thread count(){
		Thread t = new Thread(){
			public void run() {
				synchronized (lock) {
					count++;
					System.out.println(getName() + ": " + count);
				}
			};
		};
		t.start();//inicializar
		//t.run();
		return t;
	}

	public static void main(String[] args) throws InterruptedException {
		//marcando tempo inicial
		long t0 = System.nanoTime();
		//iniciando a contagem
		Thread t = null;
		for (int i = 0; i < 5; i++){
			if (t != null) t.join();
			t = count();
		}
		//marcando o tempo final
		long t1 = System.nanoTime();
		//imprimindo o resultado
		System.out.println(String.format("count: %d, time: %dns", count, (t1-t0)));
	}	
	
}
