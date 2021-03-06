public class Engine {
	private IncomingManager manager;
	private Attendante attendante;
	private Queue queue;
	
	//
	private Thread t0 = null;
	private boolean fim = false;
	
	//
	private void printAll(){
		System.out.println("-------------------------------------------------------");
		System.out.println("Quantidade de pessoas que chegaram: " + manager.count());
		System.out.println("Quantidade de pessoas que foram embora: " + manager.fail());
		System.out.println("Quantidade de pessoas na fila: " + queue.size());
		System.out.println("Quantidade de pessoas atendidas: " + attendante.count());
		System.out.println("-------------------------------------------------------");
	}
	
	
//	//temporizador
//	thread t0 {
//	  set timef = 0
//	  set time0 = 0
//	  loop{
//	    set time1 = currentTime
//	    if (time1 - time0 >= 1){
//	      timef++
//	      time0 = time1
//	      notifyAll()//passou um segundo
//	    }
//	    if (timef == 89)
//	      fim = true
//	    if (timef == 90){
//	      printAll()//imprime todos os valores
//	      break
//	    }
//	  }
//	}
	private void temporizador(){
		Runnable r0 = new Runnable() {
			@Override
			public void run() {
				//
				long ti = System.currentTimeMillis();
				//
				int timef = 0;
				boolean printed = false;
				//
				long time0 = System.currentTimeMillis();
				long time1 = System.currentTimeMillis();
				while(true){
					time1 = System.currentTimeMillis();
					if (time1 - time0 >= 1000){
						timef++;
						time0 = time1;
						synchronized (t0) {
							t0.notifyAll();
						}
						System.out.println("[T0] Time: " + timef + "s");
					}
					if(timef == 89 && printed == false){
						fim = true;
						printed = true;
						System.out.println("[T0] The end!");
					}
					if(timef == 90){
						printAll();
						break;
					}
				}
				//
				long tf = System.currentTimeMillis();
				System.out.println("Tempo total de simulação (ms): " + (tf-ti));
			}
		};
		//
		t0 = new Thread(r0);
		t0.start();
	}
	
//	//gerenciador de entrada
//	thread t1 {
//	  set time = 0
//	  loop (fim == false){
//	    t0.wait()
//	    if (++time == 3s){
//	      t11 { manager.exec() }//pressupoe que o time < 1s
//	      time = 0
//	    }
//	  }
//	}
	private void gerenciadorDeEntrada(){
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				int time = 0;
				while(fim == false){
					//
					synchronized (t0) {
						try {
							t0.wait();
						} 
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					//
					if (++time == 3){
						int qt = manager.exec();
						time = 0;
						System.out.println("[T1] Ocorreu a entrada de " + qt + " pessoa(s).");
					}
					else {
						System.out.println("[T1] Aguardando a entrada de pessoas.");
					}
				}
			}
		};
		//
		Thread t1 = new Thread(r1);
		t1.start();
	}
	
//	//atendimento
//	thread t2 {
//	  loop (fim == false){[loop]
//	    t0.wait()
//	    try {
//	     start attendance
//	    }
//	    catch {
//	      continue [loop]
//	    }
//	    set time = 0
//	    loop {
//	      t0.wait()
//	      if (++time == 2s){
//	        t21{stop attendance}
//	        break
//	      }
//	    }
//	  }
//	}
	private void atendimento(){
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				//
				while(fim == false){
					//
					synchronized (t0) {
						try {
							t0.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					//
					try {
						System.out.println("[T2] Verifacar se há atendimento.");
						System.out.print  ("[T2] ");
						attendante.startService();
					} 
					catch (RuntimeException e){
						System.out.println("Ninguém para atendimento.");
						continue;
					}
					//
					int time = 0;
					while(true){
						//
						synchronized (t0) {
							try {
								t0.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						//
						if (++time == 2){
							System.out.print("[T2] ");
							attendante.stopService();
							break;
						}
					}
				}
			}
		};
		//
		Thread t2 = new Thread(r2);
		t2.start();
	}

	
	public Engine(IncomingManager manager, Attendante attendante, Queue queue) {
		this.manager = manager;
		this.attendante = attendante;
		this.queue = queue;		
	}

	public void exec(){
		temporizador();
		gerenciadorDeEntrada();
		atendimento();
	}
	
}
