package gsu;

import java.util.Scanner;

/**
 * Created by Andrei on 12-Jul-16.
 */
public class Purchase {
    private String commodity;
    private int price;
    private int number;

    public Purchase(String commodity, int price, int number)
    {
        this.commodity = commodity;
        this.price = price;
        this.number = number;
    }
    public Purchase(String[] initString)
    {
        this(initString[0].trim(),Integer.parseInt(initString[1]),Integer.parseInt(initString[2]));
    }

    public Purchase()
    {
        this("",0,0);
    }

    public Purchase(Scanner scanner)
    {
        this.commodity = scanner.next().trim();
        this.price = scanner.nextInt();
        this.number = scanner.nextInt();
    }

    public String getCommodity()
    {
        return commodity;
    }

    public void setCommodity(String commodity)
    {
        this.commodity = commodity;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public int getCost()
    {
        return price*number;
    }

    @Override
    public String toString()
    {
        return fieldToString()+ ";"+getCost();
    }

    @Override
    public boolean equals(Object o)
    {

        if(o==null){return false;}
        if(!(o instanceof Purchase)){return false;}
        if(o==this){return true;}


        Purchase purchase =(Purchase)o;
        return this.commodity.equals(purchase.getCommodity()) && this.price == purchase.getPrice();

    }

    @Override
    public int hashCode()
    {
        int result = commodity.hashCode();
        result = 31 * result + price;
        return result;
    }

    protected String fieldToString()
    {
        return commodity+";"+number+";"+price;
    }
}
