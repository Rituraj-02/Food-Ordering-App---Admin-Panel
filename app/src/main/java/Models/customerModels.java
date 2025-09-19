package Models;

public class customerModels {
    String customerName,moneyStatus;

    public customerModels(String customerName, String moneyStatus) {
        this.customerName = customerName;
        this.moneyStatus = moneyStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMoneyStatus() {
        return moneyStatus;
    }

    public void setMoneyStatus(String moneyStatus) {
        this.moneyStatus = moneyStatus;
    }
}
