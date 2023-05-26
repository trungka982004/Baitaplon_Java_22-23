//Dont modify this file

public class Bill {
    private int billID;
    private double total;
    private String payFor;

    public Bill(int billID, double total, String payFor) {
        this.billID = billID;
        this.total = total;
        this.payFor = payFor;
    }

    public int getBillID() { //lay ma hoa don
        return billID;
    }

    public double getTotal() { // lay tong tien 
        return total;
    }

    public String getPayFor() { // muc tieu chi tra
        return payFor;
    }

    @Override
    public String toString() { // in theo format
        return billID + "," + total + "," + payFor;
    }
}
