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
                    Payment pm = null;

                    if(parts.length == 2){
                        int soDinhDanh = Integer.parseInt(parts[0]);
                        double rate = Double.parseDouble(parts[1]);
                        pm = new BankAccount(soDinhDanh, rate);
                    }
                    else if (parts.length == 1 && parts[0].length() == 7 ){
                        int phoneNumber = Integer.parseInt(parts[0]);
                        pm = new EWallet(phoneNumber);
                    }
                    else if(parts.length == 1 && parts[0].length() == 6) {
                        int id = Integer.parseInt(parts[0]);
                        ArrayList<IDCard> idCards = idcm.getIDCards();
                        IDCard card = null;
                        for(IDCard IDCard : idCards){
                            if(IDCard.getSoDinhDanh() == id) {
                                card = IDCard;
                            }
                        }
                        if(card != null) {
                            try{
                                pm = new ConvenientCard(card);
                            } catch (CannotCreateCard e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    paymentObjects.add(pm);
                }
                return true;
            } catch(IOException e) {
                e.printStackTrace();
                return false;
            }    
    }       
    // Requirement 4
    public ArrayList<ConvenientCard> getAdultConvenientCards() {
        //code here
        ArrayList<ConvenientCard> adultsCards = new ArrayList<>();

        
        for(Payment pm : paymentObjects) {
            if(pm instanceof ConvenientCard) {
                ConvenientCard card = (ConvenientCard) pm;
                if(card.getType().equals("Adult")) {
                    adultsCards.add(card);
                }
            }
        }
        return adultsCards;
    }

    // Requirement 5
    public ArrayList<IDCard> getCustomersHaveBoth() {
        //code here
        ArrayList<IDCard> customersHaveBoth = new ArrayList<>();
        ArrayList<IDCard> idCards = idcm.getIDCards();
        for(IDCard card : idcm.getIDCards()){
            // int demTK =0;
            boolean hasBankAccount = false;
            boolean hasConvenientCard = false;
            boolean hasEWallet = false;
            for(Payment pm : paymentObjects) {
                if(pm instanceof BankAccount){
                    BankAccount ba = (BankAccount) pm;
                    if(ba.getSoTK()==card.getSoDinhDanh()){
                        hasBankAccount=true;
                    }
                }
                else if(pm instanceof EWallet){
                    EWallet ew = (EWallet) pm;
                    if(ew.getPhoneNumber() == card.getPhoneNumber()){
                        hasEWallet =true;
                    }
                }
                else if(pm instanceof ConvenientCard) {
                    try{
                        ConvenientCard cc1 = (ConvenientCard) pm;
                        ConvenientCard newCCard = new ConvenientCard(card);
                        String stringIdCard = newCCard.toString();
                        String cc1String = cc1.toString();

                        if(stringIdCard.equals(cc1String)){
                            hasConvenientCard=true;
                        } 
                    } catch (CannotCreateCard e){
                        e.printStackTrace();
                    }
                }
            }
            if(hasBankAccount && hasEWallet && hasConvenientCard){
                customersHaveBoth.add(card);
            }
        }
        return customersHaveBoth;
    }

    // Requirement 6
    public void processTopUp(String path) {
        //code here

        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                String typeCard = parts[0];
                int soTK = Integer.parseInt(parts[1]);
                double amount = Double.parseDouble(parts[2]);
                
                if(typeCard.equals("EW")){
                    for(Payment pm : paymentObjects) {
                        if(pm instanceof EWallet) {
                            EWallet e = (EWallet) pm;
                            if(e.getPhoneNumber() == soTK){
                                e.topUp(amount);
                            }
                        }
                    }
                }
                else if(typeCard.equals("BA")){
                    for(Payment pm : paymentObjects ) {
                        if(pm instanceof BankAccount) {
                            BankAccount b = (BankAccount) pm;
                            if(b.getSoTK() == soTK) {
                                b.topUp(amount);
                            } 
                        }
                    }
                }
                else if(typeCard.equals("CC")){
                    for(Payment pm : paymentObjects) {
                        if(pm instanceof ConvenientCard) {
                            ConvenientCard c = (ConvenientCard) pm;
                            IDCard IDCard = c.getIDCard();
                            if(IDCard.getSoDinhDanh() == soTK){
                                c.topUp(amount);
                            }
                        }
                    }
                }
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Requirement 7
    public ArrayList<Bill> getUnsuccessfulTransactions(String path) {
        //code here
        ArrayList<Bill> payUnSuccessful = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                int billID = Integer.parseInt(parts[0]);
                double total = Double.parseDouble(parts[1]);
                String payFor = parts[2];
                String typeCard = parts[3];
                int inForAccount = Integer.parseInt(parts[4]);
                int count=0;
                double amount = total;
                if(typeCard.equals("EW")){
                    for(Payment pm : paymentObjects){
                        if(pm instanceof EWallet){
                            EWallet ew = (EWallet) pm;
                            
                            if(ew.getPhoneNumber() == inForAccount){
                                if(!ew.pay(amount)){
                                    
                                    count++;
                                }
                                
                            }
                        }
                    }
                }
                else if(typeCard.equals("BA")){
                    for(Payment pm : paymentObjects){
                        if(pm instanceof BankAccount){
                            BankAccount ba = (BankAccount) pm;
                            
                            if(ba.getSoTK() == inForAccount){
                                if(!ba.pay(amount)){
                                    
                                    count++;
                                }
                            }
                        }
                    }
                }
                else if(typeCard.equals("CC")){
                    for(Payment pm : paymentObjects){
                        if(pm instanceof ConvenientCard) {
                            ConvenientCard cc = (ConvenientCard) pm;
                            IDCard idCards = cc.getIDCard();
                            
                            if(idCards.getSoDinhDanh() == inForAccount ){

                                if(!cc.pay(amount)){
                                    count++;
                                }
                            }

                        }
                    }
                }

                if(count!=3){
                    Bill bill = new Bill( billID,  total,  payFor);
                    payUnSuccessful.add(bill);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return payUnSuccessful;
    }

    // Requirement 8
    public ArrayList<BankAccount> getLargestPaymentByBA(String path) {
        //code here
        ArrayList<BankAccount> largestPaymentByBA = new ArrayList<>();
        double maxPay = 0;
        try(BufferedReader reader  = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");                    
                int billID = Integer.parseInt(parts[0]);
                double total = Double.parseDouble(parts[1]);
                String payFor = parts[2];
                String typeCard = parts[3];
                int inForAccount = Integer.parseInt(parts[4]);
                
                if(typeCard.equals("BA")){
                    for(Payment pm : paymentObjects){
                        if(pm instanceof BankAccount){
                            BankAccount ba = (BankAccount) pm;
                            if(inForAccount == ba.getSoTK()){
                                ba.pay(total);
                                if( total > maxPay){
                                    maxPay = total;
                                    largestPaymentByBA.clear();
;                                    largestPaymentByBA.add(ba);
                                }
                            }
                        }
                    }
                }
            }
            
        } catch (IOException e){
            e.printStackTrace();
        }

        return largestPaymentByBA;
    }
    //Requirement 9
    public void processTransactionWithDiscount(String path) {
        //code here
    }
}
