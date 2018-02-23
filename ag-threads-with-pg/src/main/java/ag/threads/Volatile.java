package ag.threads;

public class Volatile {
	//private volatile boolean suspend = false;
	private boolean suspend = false;
	private Object lock = new Object();
	
	public boolean getSuspend(){
		synchronized (lock) {
			return suspend;
		}
	}
	
	public void markAsSuspend() {
		synchronized (lock) {
			this.suspend = true;
		}
	}
	
	public void markAsUnsuspend(Object obj) {
		synchronized (lock) {
			//primeiro notifica para
			//que todos possam continuar
			synchronized (obj) {
				obj.notifyAll();
			}
			//depois altera o valor
			this.suspend = false;
			//o contrário pode fazer com que 
			//alguém que IRIA receber o valor de TRUE fique falso
		}
	}
}
