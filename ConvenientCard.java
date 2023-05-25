public class ConvenientCard implements Payment{ //the tien loi implement Payment
	// code here
	
    private String type;
	private IDCard theDinhDanh;
	private double soDuTK;
	
	public String getType() {
		return this.type;
	}
	
	public void deposit(double amount){ //tien nap
		soDuTK += amount;
	}

	@Override
	public double checkBalance(){ //tra ve so du 
		return  this.soDuTK;
	}
	@Override
	public boolean pay(double amount){ // ham kiem tra co thanh toan thanh cong hay ko 
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

	public ConvenientCard(IDCard theDinhDanh) throws CannotCreateCard{ //ham xac dinh loai the dua tren so tuoi
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
	}

	@Override
	public String toString(){ // ham in theo format
		return theDinhDanh.toString() + ", " + type + ", " +  soDuTK;
	}
}