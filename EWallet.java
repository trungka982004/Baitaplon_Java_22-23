public class EWallet implements Payment, Transfer {
	// code here
	private int phoneNumber;
	private double soDuTK;

	public EWallet(int phoneNumber){
		this.phoneNumber = phoneNumber;
		this.soDuTK=0;
	}

	@Override
	public double checkBalance(){
		return  this.soDuTK;
	}
	public boolean pay(double amount){
		double soTienThanhToan = amount;
		if( soTienThanhToan<= soDuTK ){
			soDuTK -= soTienThanhToan;
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
		
		
		if(soTienChuyen<=soDuTK){
			if(to instanceof EWallet){
				EWallet ew =(EWallet) to;
				soDuTK -= soTienChuyen;
				ew.topUp(amount);
				return true;
			}
			if(to instanceof BankAccount){
				BankAccount ba = (BankAccount) to;
				soDuTK-=soTienChuyen;
				ba.topUp(amount);
				return true;
			}
			
		}
		return false;
	}


	public double topUp(double amount){
		return soDuTK +=amount;
	}
	public String toString(){
        return phoneNumber + "," + soDuTK;
    }
}
