import java.text.DecimalFormat;
public class Car
{
    private String customerName;
    private String IDTransaction;
    private String plateNumber;
    private String modelCar;
    private double carPrice;
    private char typeCar;
    private char typePayment;
    private int year;
    
    public Car(String customerName,String IDTransaction,String plateNumber,String modelCar,Double carPrice,char typeCar,char typePayment,int year)
    {
           this.customerName = customerName;
           this.IDTransaction = IDTransaction;
           this.plateNumber = plateNumber;
           this.modelCar = modelCar;
           this.carPrice = carPrice;
           this.typeCar = typeCar;
           this.typePayment = typePayment;
           this.year = year;
    }
    
    public String getCustomerName() {return customerName;}
    public String getIDTransaction() {return IDTransaction;} 
    public String getPlateNumber() {return plateNumber;}
    public String getModelCar() {return modelCar;}
    public double getCarPrice() {return carPrice;}
    public char getTypeCar() {return typeCar;}
    public char getTypePayment() {return typePayment;}
    public int getYear() {return year;}
    
    public void setPrice(double carPrice)
    {
        this.carPrice = carPrice;
    }
    
    public Double calExtraCharge(double carPrice, int year) 
    {
        double extraCharge = 0.00;
        
        if(year<=3)
            extraCharge = carPrice * 5/100;
        else if(year>3)
            extraCharge = carPrice * 10/100;
        
        return extraCharge;
    }

    DecimalFormat df = new DecimalFormat("0.00");

    public String toString()
    {
        return "\nCustomer Name: " + customerName + "\nCustomer ID Transaction: "+ IDTransaction + "\nCustomer Plat Number: " + plateNumber + "\nCustomer Model Car: " + 
        modelCar + "\nCustomer Car Price: RM" + df.format(carPrice) + "\nCustomer Type Car: " + typeCar + "\nCustomer Type Payment: " + typePayment + "\nYear Bought: " + year;
    } 
}