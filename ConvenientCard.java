//the tien loi implement Payment
public class ConvenientCard implements Payment{ 
	// code here
	
    private String type;
	private IDCard theDinhDanh;
	private double soDuTK;
	
	public String getType() {
		return this.type;
	}
	
	public void topUp(double amount){ //tien nap
		soDuTK += amount;
	}

	@Override
	//ham lay so du 
	public double checkBalance(){ 
		return  this.soDuTK;
	}
	@Override
	//ham check da thanh toan thanh cong hay khong
	public boolean pay(double amount){
		double soTienThanhToan = 0.0;
		if(type.equals("Student")){
			soTienThanhToan = amount;
			if( soTienThanhToan<= soDuTK ){
				soDuTK -=soTienThanhToan;
				return true;
			}
		}
		else if(type.equals("Adult")) {
			soTienThanhToan = amount + 0.01*amount;
			if( soTienThanhToan <= soDuTK ){
				soDuTK -=soTienThanhToan;
				return true;
			}
		}
		return false;
	}

	public ConvenientCard(IDCard theDinhDanh) throws CannotCreateCard{
		String date = theDinhDanh.getDayOfBirth();
		String year = date.substring(6, 10);
		int age = 2023 - Integer.parseInt(year);
		if(age < 12){
			throw new CannotCreateCard("Not enough age");
		} else if (age >=12 && age<=18){
			this.type = "Student";
		} else {
			this.type = "Adult";
		}


		this.theDinhDanh = theDinhDanh;
		this.soDuTK = 100;
	}

	@Override
	//in theo format
	public String toString(){ 
		return theDinhDanh + "," + type  + "," + soDuTK;
	}
}
