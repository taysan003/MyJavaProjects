package collections;

/**
 * Created by Andrei on 26-Jun-16.
 */
public class cash {
    public static void main(String[] args) {

        calculateWithCache(key) {
            curTime = getCurrentTime();
            // Если значение уже было в кэше вернем его
            if (key in hashTable) {
                // Сначала обновим время последнего запроса к key
                timeQueue.set(key, curTime);
                return hashTable[key];
            }
            // Иначе вычислим результат
            result = calculate(key);

            // Если в кэше уже N элементов, то вытесним самый старый
            if (hashTable.length == N) {
                minKey = timeQueue.extractMinValue();
                hashTable.remove(minKey);
            }

            // Добавим в таблицу, и в очередь
            hashTable.add(key, result);
            timeQueue.add(key, curTime);

            return result;
        }
    }
}
