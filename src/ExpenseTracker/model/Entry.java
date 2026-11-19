package ExpenseTracker.model;
import java.time.*;

public class Entry {
    private String name;
    private LocalDate date;
    private double price;
    public Entry (String name, double price, LocalDate date){
        setDate(date);
        setName(name);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        boolean hasLetter = false;
        if (name != null && !name.isBlank()){
           for (int i = 0; i < name.length(); i++){
               if (!Character.isDigit(name.charAt(i))){
                   hasLetter = true;
               }
           }
           if(hasLetter){
               this.name = name;
           }
           if (!hasLetter) {
                throw new IllegalArgumentException("Name entry cannot contain only numbers");
        }

        }
        if (name == null||name.isBlank()){
            throw new IllegalArgumentException("Name entry cannot be null/blank");
        }


    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date != null)
           this.date = date;
        else{
            throw new DateTimeException("Incorrect date format");
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0 && price < 5000){
            this.price = price;
        }
       else {
           throw new IllegalArgumentException("Price cannot be less then 0 / Price cannot be greater then 5000 ");
        }
    }
    public String toString (){
        return ("[" + date + "] " + name +": " + price);
    }

    public String toCsv (){
        return (date + ", " + name + ", " + price);
    }

    public static Entry fromCsv (String line){
        if(line != null && !line.isEmpty()) {
            String[] entry = line.split(", ");
            if (entry.length == 3){
                LocalDate date = LocalDate.parse(entry[0]);
                String product = entry[1];
                double price = Double.parseDouble(entry[2]);
                return new Entry(product,price,date);
            }
        }
        return null;
    }
}
