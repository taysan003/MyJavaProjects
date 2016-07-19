package gsu;

import java.util.Scanner;

/**
 * Created by Andrei on 12-Jul-16.
 */
public class PurchaseFactory {
    final static String SEPARATOR=";";
    final static String SIMPLY="SIMPLY";
    final static String PRICE_PURCHASE="PRICE_PURCHASE";
    private enum PurchaseType
    {
        SIMPLY
                {
                    @Override
                    Purchase getPurchase(String[] initString )
                    {
                        return new Purchase(initString);
                    }
                },
        PRICE_PURCHASE
                {
                    @Override
                    Purchase getPurchase(String[] initString)
                    {
                        return new PricePurchase(initString);
                    }
                };

        abstract Purchase getPurchase(String[] initString);

    }

    public static Purchase getPurchase(Scanner scanner) throws IllegalArgumentException
    {
        String typePurchase;
        String readRow=scanner.nextLine();
        String[] readRows=readRow.split(SEPARATOR);
        switch (readRows.length)
        {
            case 3:
            {
                typePurchase=SIMPLY;
                break;
            }
            case 4:
            {
                typePurchase=PRICE_PURCHASE;
                break;
            }
            default:
            {
                throw new IllegalArgumentException("Bad row in file");
            }
        }
        PurchaseType purchaseType= PurchaseType.valueOf(typePurchase);
        return purchaseType.getPurchase(readRows);
    }
}
