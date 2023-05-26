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
        paymentObjects = new ArrayList<>();
        File file = new File(path);
        try{
            Scanner paymentObjects = new Scanner(file);
            while(paymentObjects.hasNextLine()){
                String line = paymentObjects.nextLine();
                 System.out.println(line);
            }
            paymentObjects.close();

            String[] parts = paymentObject.split(",");
            if(parts.length == 2 ){
                Payment pm = new BankAccount(soTK, rate);
                String paymentObject = paymentObjects.add(pm);
            }
            if(parts.length == 1 ){
                if((parts.length).length() == 7){
                    Payment ew = new EWallet(phoneNumber);
                    String paymentObject = paymentObjects.add(pm);
                }
                if((parts.length).length() == 6){
                    Payment ew = new ConvenientCard(theDinhDanh);
                    String paymentObject = paymentObjects.add(pm);
                }
            }
            
            

        } catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
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
