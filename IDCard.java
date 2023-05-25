public class IDCard { //The dinh danh
      // code here
      protected int soDinhDanh;
      protected String hoTen;
      protected String sex;
      protected String dayOfBirth;
      protected String address;
      protected int phoneNumber;

      public IDCard(int soDinhDanh,String hoTen,String sex,String dayOfBirth,String address,int phoneNumber){ //khoi tao day constructor du 
            this.soDinhDanh = soDinhDanh;
            this.hoTen = hoTen;
            this.sex = sex;
            this.dayOfBirth = dayOfBirth;
            this.address = address;
            this.phoneNumber = phoneNumber;
      }
      public String getDayOfBirth(){ // lay ngay thang nam
            return dayOfBirth;
      }
      
      public String toString(){ //in theo format
            return soDinhDanh + ", " + hoTen + ", " + sex + ", " + dayOfBirth + ", " + address + ", " + phoneNumber;
      }
}
