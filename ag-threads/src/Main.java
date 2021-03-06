
public class Main {
  int x = 0;

  public static void main(String[] args) throws InterruptedException {
    //
    Thread t1 = new Thread(){
      public void run() {
        synchronized (this) {
            System.out.println("iniciando operação");
            notifyAll();
            System.out.println("operação completa");
        }
      };
    };
    
    Thread t2 = new Thread(){
      public void run() {
        //
        System.out.println("iniciando operação em t2");
        //
        synchronized (t1) {
          try {
            t1.join();//aguardando
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          //
        }
        //
        System.out.println("operacao em t2 completa");
        //
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      };
    };
    //
    Thread t3 = new Thread(){
      public void run() {
        //
        System.out.println("iniciando operação em t3");
        //
        synchronized (t1) {
          try {
            //t1.wait();//aguardando
            t1.join();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          //
        }
        //
        
        System.out.println("operacao em t3 completa");
      };
    };
    //
    t2.start();
    t3.start();
    //
    Thread.sleep(1000);
    t1.start();//vai e notifica o primeiro bloqueio
    Thread.sleep(10000);
  }
  
  
}
