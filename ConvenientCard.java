//the tien loi implement Payment
public class ConvenientCard implements Payment{ 
	// code here
	
    private String type;
	private IDCard idCard;
	private double balance;
	
	public ConvenientCard(IDCard idCard) throws CannotCreateCard{
		String date = idCard.getDayOfBirth();
		String year = date.substring(6, 10);
		int age = 2023 - Integer.parseInt(year);
		if(age < 12){
			throw new CannotCreateCard("Not enough age");
		} else if (age >=12 && age<=18){
			this.type = "Student";
		} else {
			this.type = "Adult";
		}


		this.idCard = idCard;
		this.balance = 100;
	}

	public String getType() {
		return this.type;
	}
	
	public void topUp(double amount){ //tien nap
		balance += amount;
	}
	public IDCard getIDCard(){
		return idCard;
	}

	public int getIDNumber(){
		return idCard.IDnumber;
	}
	@Override
	public double checkBalance(){ 
		return  this.balance;
	}
	@Override
	public boolean pay(double amount){
		double pament = 0.0;
		if(type.equals("Student")){
			pament = amount;
			if( pament<= balance ){
				balance -=pament;
				return true;
			}
		}
		else if(type.equals("Adult")) {
			pament = amount + 0.01*amount;
			if( pament <= balance ){
				balance -=pament;
				return true;
			}
		}
		return false;
	}

	public double topDown(double amount){
		return balance -= amount;
	}

	@Override
	//in theo format
	public String toString(){ 
		return idCard + "," + type  + "," + balance;
	}
}
