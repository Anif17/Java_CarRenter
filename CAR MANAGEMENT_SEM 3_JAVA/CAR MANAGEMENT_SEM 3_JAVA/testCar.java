import java.io.*;
import java.util.*;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import java.time.LocalDate;

public class testCar
{
    public static void main(String [] args)
    {
        try
        {
            Scanner sc = new Scanner(System.in);
            DecimalFormat df = new DecimalFormat("0.00");
            BufferedReader in = null;
            in = new BufferedReader(new FileReader("customerList.txt"));

            LinkedList localCar = new LinkedList();
            LinkedList importCar = new LinkedList();
            Queue customer = new Queue();

            String customerName;
            String IDTransaction;
            String plateNumber;
            String modelCar;
            double carPrice;
            char typeCar;
            char typePayment;
            int year = 0;

            String nameTypeCar,nameTypePayment;

            String input = "";
            while ((input = in.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(input,";");

                customerName = st.nextToken();
                IDTransaction = st.nextToken();
                plateNumber = st.nextToken();
                modelCar = st.nextToken();
                carPrice = Double.parseDouble(st.nextToken());
                typeCar = st.nextToken().charAt(0);
                typePayment = st.nextToken().charAt(0);
                year = Integer.parseInt(st.nextToken());

                Car car = new Car(customerName,IDTransaction,plateNumber,modelCar,carPrice,typeCar,typePayment,year);
                customer.enqueue(car);
            }

            Car obj;
            Queue tempStore = new Queue();

            double totalPrice = 0.00;
            double price = 0.00;
            double priceWithCharge = 0.00;
            double charge = 0.00;
            int totalInstalment = 0;

            LocalDate current_date = LocalDate.now();
            int current_Year = current_date.getYear();  

            while (!customer.isEmpty())
            {
                obj = (Car)customer.dequeue();
                if (obj.getTypePayment() == 'I')
                {
                    totalInstalment++;
                    price = obj.getCarPrice();
                    int yearInstallment = current_Year - year;
                    charge = obj.calExtraCharge(price, yearInstallment);
                    priceWithCharge = price + charge;
                    obj.setPrice(priceWithCharge);
                }

                price = obj.getCarPrice();
                totalPrice += price;
                tempStore.enqueue(obj);
            }

            while (!tempStore.isEmpty())
            {
                customer.enqueue(tempStore.dequeue());
            }

            //find average of total price of all customer
            double average = totalPrice / 20;

            System.out.println("===============================================================================================================================================");
            System.out.println("\nINFORMATION ABOUT SYSTEM DATA : ");
            System.out.println("\n->\tTotal Sum of all amount of price car that has been sold : RM " + df.format(totalPrice));
            System.out.println("\n->\tTotal Customer who choose Instalment type for pay is : " + totalInstalment + " Customer");
            System.out.println("\n->\tTotal Average of all amount of price car that has been sold : RM " + df.format(average) + "\n");
            System.out.println("===============================================================================================================================================");

            // store element into two different linked which are linked for import car or local car from queue customer
            while (!customer.isEmpty())
            {
                obj = (Car)customer.dequeue();
                if(obj.getTypeCar() == 'L')
                {
                    localCar.insertAtBack(obj);
                }
                else if(obj.getTypeCar() == 'I')
                {
                    importCar.insertAtBack(obj);
                }
                tempStore.enqueue(obj);
            }

            while (!tempStore.isEmpty())
            {
                customer.enqueue(tempStore.dequeue());
            }

            //display data in both LinkedList which is Local and import
            System.out.println("\n\nDisplay all data in Local Car  ;");
            obj = (Car)localCar.getFirst();
            while(obj != null)
            {
                table(localCar);
                obj = (Car)localCar.getNext();
            }

            System.out.println("\nDisplay all data in Import Car  ;");
            obj = (Car)importCar.getFirst();
            while(obj != null)
            {
                table(importCar);
                obj = (Car)importCar.getNext();
            }

            //find highest price in local car 
            obj = (Car)localCar.getFirst();
            Car highestLocal = (Car)localCar.getFirst();

            while(obj != null)
            {
                if(obj.getCarPrice() > highestLocal.getCarPrice())
                    highestLocal = obj;
                obj = (Car)localCar.getNext();
            }

            System.out.println("\nSorting customer name in Local Car with ascending order");
            localCar.bubbleSort();
            Car localObj = (Car)localCar.getFirst();
            while (localObj != null)
            {
                table(localCar);
                localObj = (Car)localCar.getNext();
            }

            System.out.println("\nSorting customer name in Import Car with ascending order");
            importCar.bubbleSort();
            Car importObj = (Car)importCar.getFirst();
            while (importObj != null)
            {
                table(importCar);
                importObj = (Car)importCar.getNext();
            }

            System.out.println(" ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");  
            System.out.println("---Highest price in LinkedList Local Car---");
            System.out.println(highestLocal.toString());

            //find highest price in import car
            obj = (Car)importCar.getFirst();
            Car highestImport = (Car)importCar.getFirst();

            while(obj != null)
            {
                if(obj.getCarPrice() > highestImport.getCarPrice())
                    highestImport = obj;
                obj = (Car)importCar.getNext();
            }

            System.out.println();
            System.out.println("---Highest price in LinkedList Import Car---");
            System.out.println(highestImport.toString());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");  

            String userTransID;
            System.out.print("\nEnter the ID Transaction to get the details : ");
            userTransID = sc.next();
            System.out.println("\nSearching for " + userTransID + " in customer list ...");
            boolean found = false;
            Car carFound = null;
            String[] display = new String[6];
            String text = "";
            while (!customer.isEmpty())
            {
                obj = (Car)customer.dequeue();
                if (obj.getIDTransaction().equalsIgnoreCase(userTransID))
                {
                    System.out.println("***********************************************************************************************************************************************");
                    System.out.println(obj.toString());
                    System.out.println("\n***********************************************************************************************************************************************");
                    found = true;
                    carFound = obj;
                }
            }

            if(found)
            {
                if(carFound.getTypeCar() == 'I')
                    nameTypeCar = "IMPORT";
                else
                    nameTypeCar = "LOCAL";

                if(carFound.getTypePayment() == 'F')
                    nameTypePayment = "    FULL   ";
                else
                    nameTypePayment = "INSTALLMENT";
                display[0] = "Data About Customer From ID " + userTransID +  " : ";
                display[1] = " ";
                display[2] = "Customer Name                       " +  "Id Transaction         "  + "Plat Number          " + "Model Car         " + "Car Price (RM)       " + "Type Car        " + "Type Payment          " + "Year          "; 
                display[3] = "==============================================================================================================";
                display[4] = carFound.getCustomerName() + carFound.getIDTransaction() + "                      " + carFound.getPlateNumber() + "               " + carFound.getModelCar() + "              " + df.format(carFound.getCarPrice()) + "              " + nameTypeCar + "         " + nameTypePayment + "        " + carFound.getYear(); 
            }
            else
            {
                System.out.println("***********************************************************************************************************************************************");
                System.out.println(userTransID + " is not in the customer list.");
                System.out.println("***********************************************************************************************************************************************");
                text = userTransID + " is not in the customer list.";
                display[0] = text;

            }

            JFrame frame = new JFrame ("Display Panel");
            frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add (new DisplayPanel(display));
            frame.pack();
            frame.setVisible (true);
        }
        catch (FileNotFoundException fnfe){System.err.println(fnfe.getMessage());}
        catch (EOFException eof){System.out.println(eof.getMessage());}
        catch (NumberFormatException nfe){System.out.println(nfe.getMessage());}
        catch (Exception e){System.out.println(e.getMessage());}
    }

    public static void table(LinkedList localCar)
    {
        DecimalFormat df = new DecimalFormat("RM 0.00");
        List<Car> car= new ArrayList<>();  
        String typeCar,typePayment;

        Car obj = (Car)localCar.getFirst();
        while(obj != null)
        {
            car.add(obj); 
            obj = (Car)localCar.getNext();
        }

        //prints the list objects in tabular format  
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");  
        System.out.printf("%10s %35s %15s %16s %15s %13s %16s %10s", "CUSTOMER NAME", "ID TRANSACTION", "PLAT NUMBER", "MODEL CAR", "CAR PRICE", "TYPE CAR", "TYPE PAYMENT", "YEAR");  
        System.out.println();  
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");  
        //iterates over the list   
        for(Car carObj: car)  
        {  
            if(carObj.getTypeCar() == 'I')
                typeCar = "IMPORT";
            else
                typeCar = "LOCAL";

            if(carObj.getTypePayment() == 'F')
                typePayment = "    FULL   ";
            else
                typePayment = "INSTALLMENT";

            System.out.format("%7s %10s %17s %19s %15s %10s %17s %11s",carObj.getCustomerName(), carObj.getIDTransaction(), carObj.getPlateNumber(), carObj.getModelCar(), df.format(carObj.getCarPrice()), typeCar, typePayment, carObj.getYear());  
            System.out.println();  
        }  
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");  
    }  
}  