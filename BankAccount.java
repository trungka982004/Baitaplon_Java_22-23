public class BankAccount implements Payment, Transfer{
    // code here
    private int soTK;
    private double rate;
    private double soDuTK;

    public BankAccount(int sTK, double rate){
        this.soTK =soTK;
        this.rate = rate;
        soDuTK = 50;
    }
    @Override
    public double checkBalance(){
		return  this.soDuTK;
	}
	public boolean pay(double amount){
		double soTienThanhToan = amount;
        double soDuTK = 50;
		if( (soTienThanhToan + 50)<= checkBalance() ){
			soTienThanhToan -=checkBalance();
			return true;
		}
		else{
			soTienThanhToan = amount;
			return false;
		}
	}


    public boolean transfer(double amount, Transfer to){
		double soTienChuyen = amount + 0.05*amount;
		double soTienNhan = amount;
		if(soTienChuyen<=(soDuTK-50)){
			
			if(to instanceof BankAccount){
				BankAccount ba =(BankAccount) to;
				soDuTK -= soTienChuyen;
				ba.topUp(amount);
				return true;
			}
			if(to instanceof EWallet){
				EWallet ew =(EWallet) to;
				soDuTK -=soTienChuyen;
				ew.topUp(amount);
				return true;
			}
		}
		return false;
	}

    public double topUp(double amount){
		return soDuTK +=amount;
	}
    public String toString(){
        return soTK + ", " + rate + ", " +  checkBalance();

    }
}
