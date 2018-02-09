package test.comm;

import java.lang.Thread.UncaughtExceptionHandler;

public abstract class AbstractRunnable implements Runnable {
	
	protected abstract void runWithException() throws Exception;
	
	public void run(){
		try {
			runWithException();
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static void main(AbstractRunnable r){
		Thread t = new Thread(r);
		t.start();
		t.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				String msg = String.format("In thread %s occured an error: ", 
						t.getName(), e.getMessage());
				System.out.println(msg);
			}
		});
	}
}
