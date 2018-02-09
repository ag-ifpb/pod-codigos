package br.edu.ifpb.atm.manager;

public class ATM {
	private int billet10dollars = 0;
	private int billet20dollars = 0;
	private int billet50dollars = 0;
	private int startingBalance = 0;
	
	public ATM() {
		billet10dollars = (int) (Math.random()*100);
		billet20dollars = (int) (Math.random()*100);
		billet50dollars = (int) (Math.random()*100);
		startingBalance = balance();
	}
	
	public int balance(){
		return
			billet10dollars * 10 +
			billet20dollars * 20 +
			billet50dollars * 50;
	}
	
	public int getStartingBalance() {
		return startingBalance;
	}
	
	public int getBillet10dollars() {
		return billet10dollars;
	}
	
	public int getBillet20dollars() {
		return billet20dollars;
	}
	
	public int getBillet50dollars() {
		return billet50dollars;
	}
	
	public boolean billet10dollarsWithdraw(){
		boolean withdrawing = billet10dollars >= 1;
		if (withdrawing) billet10dollars--;
		return withdrawing;
	}
	
	public boolean billet20dollarsWithdraw(){
		boolean withdrawing = billet20dollars >= 1;
		if (withdrawing) billet20dollars--;
		return withdrawing;
	}
	
	public boolean billet50dollarsWithdraw(){
		boolean withdrawing = billet50dollars >= 1;
		if (withdrawing) billet50dollars--;
		return withdrawing;
	}

}
