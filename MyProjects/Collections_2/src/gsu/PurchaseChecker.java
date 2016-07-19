package gsu;

import java.util.Map;

/**
 * Created by Andrei on 12-Jul-16.
 */
public abstract class PurchaseChecker
{
    public abstract boolean check(Map.Entry<Purchase, WeekDay> mapEntry);
}