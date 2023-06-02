public class IDCard { //The dinh danh
      // code here
      protected int IDnumber;
      protected String name;
      protected String sex;
      protected String dayOfBirth;
      protected String address;
      protected int phoneNumber;

      public IDCard(int IDnumber,String name,String sex,String dayOfBirth,String address,int phoneNumber){ //khoi tao day constructor du 
            this.IDnumber = IDnumber;
            this.name = name;
            this.sex = sex;
            this.dayOfBirth = dayOfBirth;
            this.address = address;
            this.phoneNumber = phoneNumber;
      }
      public String getDayOfBirth(){ // lay ngay thang nam
            return dayOfBirth;
      }
      public int getIDNumber(){
            return IDnumber;
      }
      public int getPhoneNumber(){
            return phoneNumber;
      }
      public String toString(){ //in theo format
            return IDnumber + "," + name + "," + sex + "," + dayOfBirth + "," + address + "," + phoneNumber;
      }
}
