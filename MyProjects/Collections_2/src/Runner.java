import gsu.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Andrei on 12-Jul-16.
 */
public class Runner {
    final static String FIRST_DAY="first day is ";
    final static String LAST_DAY="last day is ";
    final static String SEPARATOR="--------------";
    final static String NOT_FOUND="not found";
    final static String TOTAL_COST=" total cost= ";
    final static String DELETE_NUM_ENTRY =" Delete entry ";



    public static void main(String[] args)
    {
        final String FILE_NAME="src/in.csv";
        final Purchase BREAD_1550=new Purchase("bread",1550,1);
        final Purchase BREAD_1700=new Purchase("bread",1700,1);

        Map<Purchase, WeekDay> firstDayPurchase=new HashMap<Purchase,WeekDay>();
        Map<Purchase,WeekDay> lastDayPurchase=new HashMap<Purchase,WeekDay>();
        Map<WeekDay, PurchaseList> weekDayKey=new TreeMap<WeekDay,PurchaseList>();


        Scanner scanner=null;
        try
        {
            scanner=new Scanner(new FileReader(FILE_NAME));
            while (scanner.hasNext())
            {
                Purchase purchase= PurchaseFactory.getPurchase(scanner);
                WeekDay weekDay= WeekDay.valueOf(scanner.nextLine());

                if(!weekDayKey.containsKey(weekDay))
                {
                    weekDayKey.put(weekDay,new PurchaseList(purchase));
                }
                else
                {
                    weekDayKey.get(weekDay).addPurchase(purchase);
                }

                if(!firstDayPurchase.containsKey(purchase))
                {
                    firstDayPurchase.put(purchase,weekDay);
                }



                lastDayPurchase.put(purchase,weekDay);

            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(scanner!=null)
            {
                scanner.close();
            }
        }


        printMap(lastDayPurchase);
        System.out.println(SEPARATOR);

        printMap(firstDayPurchase);
        System.out.println(SEPARATOR);

        printMap(weekDayKey);
        System.out.println(SEPARATOR);

        System.out.println(BREAD_1550);
        System.out.println(FIRST_DAY
                +(firstDayPurchase.get(BREAD_1550)==null?NOT_FOUND:firstDayPurchase.get(BREAD_1550))
                +LAST_DAY
                + (lastDayPurchase.get(BREAD_1550)==null?NOT_FOUND:lastDayPurchase.get(BREAD_1550)));
        System.out.println(SEPARATOR);
        System.out.println(BREAD_1700);
        System.out.println(FIRST_DAY+
                (firstDayPurchase.get(BREAD_1700)==null?NOT_FOUND:firstDayPurchase.get(BREAD_1700)));

        PurchaseChecker checkMeat =new PurchaseChecker()
        {
            @Override
            public boolean check(Map.Entry<Purchase, WeekDay> mapEntry)
            {
                return mapEntry.getKey().getCommodity().equals("meat");
            }
        };

        System.out.println(SEPARATOR);
        removeFromMap(firstDayPurchase, checkMeat);
        printMap(firstDayPurchase);

        PurchaseChecker checkFriday=new PurchaseChecker()
        {
            @Override
            public boolean check(Map.Entry<Purchase, WeekDay> mapEntry)
            {
                return mapEntry.getValue()==(WeekDay.FRIDAY);
            }
        };

        System.out.println(SEPARATOR);
        removeFromMap(lastDayPurchase,checkFriday);
        printMap(lastDayPurchase);

        System.out.println(SEPARATOR);

        for (Map.Entry<WeekDay,PurchaseList> entry:weekDayKey.entrySet())
        {
            System.out.println(entry.getKey()+ TOTAL_COST+entry.getValue().getTotalCost());
        }


    }

    private static void removeFromMap(Map<Purchase, WeekDay> purchases, PurchaseChecker check)
    {
        Iterator<Map.Entry<Purchase,WeekDay>> iterator=purchases.entrySet().iterator();
        int count=0;
        while (iterator.hasNext())
        {
            if(check.check(iterator.next()))
            {
                iterator.remove();
                count++;
            }
        }
        System.out.println(DELETE_NUM_ENTRY+count);

    }

    private static void printMap(Map<?,?> purchases)
    {
        for (Map.Entry<?,?> temp:purchases.entrySet())
        {
            System.out.println(temp.getKey()+" = "+temp.getValue());
        }
    }
}
