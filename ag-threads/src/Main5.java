
public class Main5 {

	public static void main(String[] args) {
		//System.out.println(Runtime.getRuntime().availableProcessors());
		//
		final Thread t0 = new Thread(){
			public void run() {
				System.out.println("Notificando... sqn " + getName());
				synchronized (this) {
					notify();
				}
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		};
		final Thread t1 = new Thread(){
			public void run() {
				System.out.println("Comecei: " + getName());
				synchronized (t0) {
					try {
						t0.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				};
				System.out.println("Fui notificado: " + getName());
			};
		};
		final Thread t2 = new Thread(){
			public void run() {
				System.out.println("Comecei: " + getName());
				synchronized (t0) {
					try {
						t0.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				};
				System.out.println("Fui notificado: " + getName());
			};
		};
		//
		t1.start();
		t2.start();
		t0.start();
	}
}
