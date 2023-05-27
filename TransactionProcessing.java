import java.util.*;
import java.io.*;

public class TransactionProcessing {
    private ArrayList<Payment> paymentObjects;
    private IDCardManagement idcm;
    // Phuong thuc khoi tao de doc danh sach the dinh danh va cac tai khoan thanh toan
    public TransactionProcessing(String idCardPath, String paymentPath) {
        idcm = new IDCardManagement(idCardPath);
        readPaymentObject(paymentPath);
    
    }
    //tra ve cac tai khoan thanh toan
    public ArrayList<Payment> getPaymentObject() {
        return this.paymentObjects;
    }

    // Requirement 3
    public boolean readPaymentObject(String path) {
        ArrayList<Payment> paymentObjectsList = new ArrayList<>();
        File file = new File(path);
        try{
            Scanner paymentObjects = new Scanner(file);
            while(paymentObjects.hasNextLine()){
                String line = paymentObjects.nextLine();
                System.out.println(line);
                String[] parts = paymentObjects.split(",");
            if(parts.length == 2 ){
                Payment pm = new BankAccount(soTK, rate);

            }
            if(parts.length == 1 ){
                if((parts.length).length() == 7){
                    Payment pm = new EWallet(phoneNumber);

                }
                if((parts.length).length() == 6){
                    int id = Interger.parseInt(parts[0]);
                    ArrayList<IDCard> idcard = idcm.getIDCards();
                    try{
                        for(IDCard idcard :idCards){
                            if( id == idcm.getSoDinhDanh() ){
                                IDCard theDinhDanh = idcard;
                            }
                        }
                        Payment ew = new ConvenientCard(theDinhDanh);

                    } catch (CannotCreateCard e){
                        e.printStackTrace();
                    }
                    
                }
            }
        } catch (FileNotFoundException e){
                e.printStackTrace();
                return false;
            }
        }
        return  true;
    }
    // Requirement 4
    public ArrayList<ConvenientCard> getAdultConvenientCards() {
        //code here
        return null;
    }

    // Requirement 5
    public ArrayList<IDCard> getCustomersHaveBoth() {
        //code here
        return null;
    }

    // Requirement 6
    public void processTopUp(String path) {
        //code here
    }

    // Requirement 7
    public ArrayList<Bill> getUnsuccessfulTransactions(String path) {
        //code here
        return null;
    }

    // Requirement 8
    public ArrayList<BankAccount> getLargestPaymentByBA(String path) {
        //code here
        return null;
    }

    //Requirement 9
    public void processTransactionWithDiscount(String path) {
        //code here
    }
}
