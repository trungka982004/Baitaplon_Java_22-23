public class EWallet implements Payment, Transfer {
	// code here
	private int phoneNumber;
	private double balance;

	public EWallet(int phoneNumber){
		this.phoneNumber = phoneNumber;
		this.balance=0;
	}
	public int getPhoneNumber(){
		return phoneNumber;
	}
	@Override
	public double checkBalance(){
		return  this.balance;
	}
	public boolean pay(double amount){
		double pament = amount;
		if( pament<= balance ){
			balance -= pament;
			return true;
		}

			return false;

	}
	
	public boolean transfer(double amount, Transfer to){
		double moneyTransfer = amount + 0.05*amount;
		double receiving = amount;
		
		
		if(moneyTransfer<=balance){
			if(to instanceof EWallet){
				EWallet ew =(EWallet) to;
				balance -= moneyTransfer;
				ew.topUp(amount);
				return true;
			}
			if(to instanceof BankAccount){
				BankAccount ba = (BankAccount) to;
				balance-=moneyTransfer;
				ba.topUp(amount);
				return true;
			}
			
		}
		return false;
	}


	public double topUp(double amount){
		return balance += amount;
	}
	public double topDown(double amount){
		return balance -= amount;
	}
	public String toString(){
        return phoneNumber + "," + balance;
    }
}
