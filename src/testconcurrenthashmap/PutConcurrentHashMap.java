package testconcurrenthashmap;

import java.util.Map;
import java.util.concurrent.Callable;

public class PutConcurrentHashMap implements Callable<String> {
    private Map<Integer, Integer> map;
    private int[] arr;

    public PutConcurrentHashMap(Map<Integer, Integer> map, int[] arr) {
        this.map = map;
        this.arr = arr;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        long start = System.currentTimeMillis() ;
        for (int i = 0; i < arr.length; i++){
            map.putIfAbsent(i, arr[i]);
        }
        long resultTime =   System.currentTimeMillis() - start;
        return "Время выполнения записи ConcurrentHashMap: " + resultTime + " "  + map.size();
    }
}
