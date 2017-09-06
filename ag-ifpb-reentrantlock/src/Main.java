import java.util.concurrent.locks.ReentrantLock;

public class Main {
	private static ReentrantLock locker = new ReentrantLock(true);
	
	private static void f(){
		locker.lock();
		//do some thing #0
		g();
		//do some thing #2
		locker.unlock();
	}
	
	private static void g(){
		locker.lock();
		//do some thing #1
		locker.unlock();
	}
	
	public static void main(String[] args) {
		f();
		System.out.println("Hello!!");
	}

}

