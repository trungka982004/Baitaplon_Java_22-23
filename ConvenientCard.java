public class ConvenientCard implements Payment extends IDCard{
	// code here

	public String getType() {
	}
	public ConvenientCard(IDCard theDinhDanh) throws CannotCreateCard{
		if(theDinhDanh ==null){
			throw new CannotCreateCard("Invalid IDCard");
		}
		
		this.theDinhDanh = theDinhDanh;
		this.soDuTK = 100;
	}
	public void deposit(double amount){
		soDuTK += amount;
	}

	@Override
	public double checkBalance(){
		return  this.soDuTK;
	}
	@Override
	public boolean pay(double amount){
		double soTienThanhToan = 0.0;
		if(type.equals("Student")){
			soTienThanhToan = amount;
			if( soTienThanhToan<= soDuTK ){
				soDuTK -=soTienThanhToan;
				return true;
			}
			// else{
			// 	return false;
			// }

		}
		else if(type.equals("Adult")) {
			soTienThanhToan = amount + 0.01*amount;
			if( soTienThanhToan <= soDuTK ){
				soDuTK -=soTienThanhToan;
				return true;
			}
			// else{
			// 	return false;
			// }
		}
		return false;
	}
	@Override
	public String toString(){
		return theDinhDanh.toString() + ", " + type + ", " +  soDuTK;
	}
}
