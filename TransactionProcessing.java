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
        //code here
        // try{
        //     File f = new File(path);
        //     Scanner myReader = new Scanner(f);
        //     while(myReader.hasNextLine()){
        //         String data = myReader.nextLine();
        //         System.out.println(data);
        //     }
        //     myReader.close();
        // } catch (FileNotFoundException e){
        //     System.out.println("error");
        //     e.printStackTrace();
        // } 
        // return true; 

        paymentObjects = new ArrayList<>();
        try(BufferReader br = new BufferReader(new FileReader(path))){
            String line;
            while((line = br.readLine()) != null){
                Payment payment = null;
                String[] data = line.split(",");
                if(data.length ==2){
                    int soDinhDanh = Integer.parseInt(data[0]);
                    double ls = Double.parseDouble(data[1]);
                    payment = new BankAccount(soDinhDanh,ls);
                }
                else if(data.length ==1 && line.length() ==7){
                    int sdt = Integer.parseInt(data[0]);
                    payment = new EWallet(sdt);
                }
                else if ( data.length == 1 && line.length()==6){
                    int id = Integer.parseInt(data[0]);
                    ArrayList<IDCard> idcards = idcm.getIDcards();
                    IDCard timThay = null;
                    for(IDCard theDinhDanh : idcards){
                        if(idcard.getSoDinhDanh() == id){
                            timThay = idCard;
                        }
                    }
                    if(timThay != null){
                        try{
                            payment = new ConvenientCard(timThay);
                        } catch (CannotCreateCard e){
                            e.printStackTrace();
                        }
                    }
                }
                paymentObjects.add(payment);
            }
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
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
