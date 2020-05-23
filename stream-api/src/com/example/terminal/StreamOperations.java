package com.example.terminal;

import java.util.*;
import java.util.stream.Collectors;

public class StreamOperations {

    public static void anyMatchAndAllMatchAndNoneMatch(){
        List<String> stringList = new ArrayList<String>();
        stringList.add("One flew over the cuckoo's nest");
        stringList.add("To kill a muckingbird");
        stringList.add("Gone with the wind");
        System.out.println("ANY MATCH :" +stringList.stream().anyMatch((value) -> { return value.startsWith("One"); }));
        System.out.println("ALL MATCH :" +stringList.stream().allMatch((value) -> { return value.startsWith("One"); }));
        System.out.println("NONE MATCH :" +stringList.stream().noneMatch((value) -> { return value.startsWith("A"); }));
        System.out.println("-------------------------------------------------------------------");
    }

    public static void collectAndCountAndFindAnyAndFindFirstAndForEach(){
        List<String> stringList = new ArrayList<String>();

        stringList.add("One flew over the cuckoo's nest");
        stringList.add("To kill a muckingbird");
        stringList.add("Gone with the wind");
        System.out.println("COLLECT :" +stringList.stream()
                .map(value -> value.toUpperCase())
                .collect(Collectors.toList()));
        System.out.println("COUNT :" +stringList.stream()
                .map(value -> value.toUpperCase()).count());
        System.out.println("FIND ANY :" +stringList.stream().findAny().get());
        System.out.println("FIND FIRST :" +stringList.stream().findFirst().get());
        stringList.stream().forEach( element -> { System.out.println(element); });
        System.out.println("-------------------------------------------------------------------");
    }


    public static void minAndMax(){
        List<String> stringList = new ArrayList<String>();
        stringList.add("abc");
        stringList.add("def");
        Optional<String> min = stringList.stream().min((val1, val2) -> {
            return val1.compareTo(val2);
        });
        System.out.println("MIN :" +min.get());
        Optional<String> max = stringList.stream().max((val1, val2) -> {
            return val1.compareTo(val2);
        });
        System.out.println("MAX :" +max.get());
        System.out.println("-------------------------------------------------------------------");
    }

    public static void reduceAndToArrayAndFilterAndMapAndFlatMapAndDistinctAndLimitAndPeek(){
        List<String> stringList = new ArrayList<String>();
        stringList.add("One flew over the cuckoo's nest");
        stringList.add("To kill a muckingbird");
        stringList.add("Gone with the wind");
        stringList.add("Gone with the wind");
        Optional<String> reduced = stringList.stream().reduce((value, combinedValue) -> {
            return combinedValue + " + " + value;
        });
        System.out.println("REDUCE :" +reduced.get());
        System.out.println("TO ARRAY :" +stringList.toArray());
        stringList.stream().filter( str -> str.length() > 20).forEach(System.out :: println);
        stringList.stream().map( str -> str.toLowerCase()).forEach(System.out :: println);
        stringList.stream().limit(2).forEach(System.out :: println);
        stringList.stream().peek(value -> System.out.println(value));
        stringList.stream().flatMap((value) -> {
            String[] split = value.split(" ");
            return Arrays.asList(split).stream();
        })
                .forEach((value) -> System.out.println(value));
        stringList.stream().distinct().collect(Collectors.toList()).forEach(System.out :: println);
        System.out.println("-------------------------------------------------------------------");
    }

    public static void groupBy(){
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("b");
        list.add("cc");
        list.add("dddd");

        Map<Integer,List<String>> map = list.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(map);

        Map<Integer,List<String>> customMap = list.stream().collect(Collectors.groupingBy(String::length, TreeMap :: new, Collectors.toList()));
        System.out.println(customMap);

        Map<Integer,Long> countMap = list.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(countMap);

        Map<Integer,String> combineMap = list.stream().collect(Collectors.groupingBy(String::length, Collectors.joining(",","#","#")));
        System.out.println(combineMap);
        System.out.println("-------------------------------------------------------------------");
    }

}
