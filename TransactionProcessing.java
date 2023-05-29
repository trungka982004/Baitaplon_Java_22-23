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
            try {
                Scanner paymentObjectsScanner = new Scanner(file);
                while (paymentObjectsScanner.hasNextLine()) {
                    String line = paymentObjectsScanner.nextLine();
                    String[] parts = line.split(",");
                    
                    if (parts.length == 2) {
                        int soTK = Integer.parseInt(parts[0]);
                        double rate = Double.parseDouble(parts[1]);
                        Payment pm = new BankAccount(soTK, rate);
                        paymentObjects.add(pm);
                    }
                    else if (parts.length == 1) {
                        if (parts[0].length() == 7) {
                            int phoneNumber = Integer.parseInt( parts[0]);
                            Payment pm = new EWallet(phoneNumber);
                            paymentObjects.add(pm);
                        }
                        else if (parts[0].length() == 6) {
                            try {
                                int soDinhDanh = Integer.parseInt(parts[0]);
                                ArrayList<IDCard> idCards = idcm.getIDCards();
                                IDCard card = null;
                                for (IDCard idCard : idCards) {
                                    if (idCard.getSoDinhDanh() == soDinhDanh){
                                        try{
                                            Payment pm = new ConvenientCard(idCard);
                                            paymentObjects.add(pm);
                                        } catch (CannotCreateCard e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    
                                } 
                            
                            } catch (NumberFormatException e){
                                e.printStackTrace();
                            } 
                    paymentObjectsScanner.close();
                    return true;
                } 
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            return true;
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
