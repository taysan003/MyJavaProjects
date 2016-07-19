package gsu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrei on 12-Jul-16.
 */
public class PurchaseList {
    private List<Purchase> purchases;

    public PurchaseList(Purchase purchase)
    {
        this.purchases = new ArrayList<Purchase>();
        this.purchases.add(purchase);
    }

    public List<Purchase> getPurchases()
    {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases)
    {
        this.purchases = purchases;
    }

    public void addPurchase(Purchase purchase)
    {
        purchases.add(purchase);
    }

    public int getTotalCost()
    {
        int result=0;
        for (Purchase purchase:purchases)
        {
            result+=purchase.getCost();
        }
        return result;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder=new StringBuilder();
        for(Purchase purchase:purchases)
        {
            stringBuilder.append(purchase)
                    .append("\n");
        }


        return stringBuilder.toString();
    }
}
