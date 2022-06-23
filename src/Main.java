import testconcurrenthashmap.GetConcurrentHashMap;
import testconcurrenthashmap.PutConcurrentHashMap;
import testsynchronizedmap.GetSynchronizedMap;
import testsynchronizedmap.PutSynchronizedMap;

import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException{
        int[] arr = new int[10_000_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        ExecutorService concurrentHashMapPool = Executors.newFixedThreadPool(4);

        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();

        Callable<String> putConcurrentHashMap = new PutConcurrentHashMap(concurrentHashMap, arr);
        Collection<Callable<String>> listPutConcurrentHashMap = new ArrayList<>();
        listPutConcurrentHashMap.add(putConcurrentHashMap);
        listPutConcurrentHashMap.add(putConcurrentHashMap);
        listPutConcurrentHashMap.add(putConcurrentHashMap);
        listPutConcurrentHashMap.add(putConcurrentHashMap);
        String result = concurrentHashMapPool.invokeAny(listPutConcurrentHashMap);
        System.out.println(result);

        Callable<String> getConcurrentHashMap = new GetConcurrentHashMap(concurrentHashMap, arr);
        Collection<Callable<String>> listGetConcurrentHashMap = new ArrayList<>();
        listGetConcurrentHashMap.add(getConcurrentHashMap);
        listGetConcurrentHashMap.add(getConcurrentHashMap);
        listGetConcurrentHashMap.add(getConcurrentHashMap);
        listGetConcurrentHashMap.add(getConcurrentHashMap);
        String result1 = concurrentHashMapPool.invokeAny(listGetConcurrentHashMap);
        System.out.println(result1);


        Map<Integer, Integer> hashMap = Collections.synchronizedMap(new HashMap<Integer, Integer>());

        Callable<String> putSynchronizedMap = new PutSynchronizedMap(hashMap, arr);
        Collection<Callable<String>> listPutSynchronizedMap= new ArrayList<>();
        listPutSynchronizedMap.add(putSynchronizedMap);
        listPutSynchronizedMap.add(putSynchronizedMap);
        listPutSynchronizedMap.add(putSynchronizedMap);
        listPutSynchronizedMap.add(putSynchronizedMap);
        String result3 = concurrentHashMapPool.invokeAny(listPutSynchronizedMap);
        System.out.println(result3);

        Callable<String> getSynchronizedMap = new GetSynchronizedMap(hashMap, arr);
        Collection<Callable<String>> listGetSynchronizedMap= new ArrayList<>();
        listGetSynchronizedMap.add(getSynchronizedMap);
        listGetSynchronizedMap.add(getSynchronizedMap);
        listGetSynchronizedMap.add(getSynchronizedMap);
        listGetSynchronizedMap.add(getSynchronizedMap);
        String result4 = concurrentHashMapPool.invokeAny(listGetSynchronizedMap);
        System.out.println(result4);

        concurrentHashMapPool.shutdown();
    }
}
