public class BankAccount implements Payment, Transfer{
    // code here
    private int soTK;
    private double rate;
    private double soDuTK;

    public BankAccount(int soTK, double rate){
        this.soTK =soTK;
        this.rate = rate;
        this.soDuTK = 50;
    }
	public int getSoTK(){
		return soTK;
	}
    @Override
    public double checkBalance(){
		return  this.soDuTK;
	}
	public boolean pay(double amount){
		double soTienThanhToan = amount;
        double soDuTK = 50;
		if( (amount + 50)<= soDuTK ){
			soDuTK -=amount;
			return true;
		}
		return false;
	}


    public boolean transfer(double amount, Transfer to){
		double soTienChuyen = amount + Transfer.transferFee*amount;
		double soTienNhan = amount;
		if(soTienChuyen<=(this.soDuTK-50)){
			
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
        return soTK + "," + rate + "," + soDuTK;

    }
}
