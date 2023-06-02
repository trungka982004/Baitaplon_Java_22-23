public class BankAccount implements Payment, Transfer{
    // code here
    private int accountNumber;
    private double rate;
    private double balance;

    public BankAccount(int accountNumber, double rate){
        this.accountNumber =accountNumber;
        this.rate = rate;
        this.balance = 50.0;
    }
	public int getAccountNumber(){
		return accountNumber;
	}
    @Override
    public double checkBalance(){
		return  this.balance;
	}
	@Override
	public boolean pay(double amount){
		double pament = amount;
		if( (amount + 50)<= balance ){
			balance = balance - amount;
			return true;
		}
		return false;
	}
	@Override
    public boolean transfer(double amount, Transfer to){
		double moneyTransfer = amount + Transfer.transferFee*amount;
		double receiving = amount;
		if(moneyTransfer<=(this.balance-50)){
			
			if(to instanceof BankAccount){
				BankAccount ba =(BankAccount) to;
				balance -= moneyTransfer;
				ba.topUp(amount);
				return true;
			}
			if(to instanceof EWallet){
				EWallet ew =(EWallet) to;
				balance -=moneyTransfer;
				ew.topUp(amount);
				return true;
			}
		}
		return false;
	}

    public double topUp(double amount){
		return balance +=amount;
	}

	public void topDown(doubble amount){
		return balance -= amount;
	}

    public String toString(){
        return accountNumber + "," + rate + "," + balance;

    }
}
