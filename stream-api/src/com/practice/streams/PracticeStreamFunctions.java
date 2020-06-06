package com.practice.streams;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PracticeStreamFunctions {

    public static void main(String[] args) {
        // 1. filter with starting letter 'c', convert to uppercase, sort and print
        System.out.println("FILTER, MANIPULATE AND PRINT");
        List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");
        myList.stream().filter( x -> x.startsWith("c"))
                       .map( x -> x.toUpperCase())
                       .sorted()
                       .forEach(System.out :: println);

        // 2. take average
        System.out.println("AVERAGE OF NUMBERS");
        Arrays.stream(new int[] {1, 2, 3})
                .average()
                .ifPresent(System.out::println);

        System.out.println("MAP IMPLEMENTATION");
        Stream.of("a1","a2","a3","a4")
              .map(s -> s.substring(1))
              .mapToInt(s -> Integer.parseInt(s))
              .max().ifPresent(System.out::println);

        // 3. filter example
        List<String> lines = Arrays.asList("spring", "node", "mkyong");
        lines.stream().filter( s -> !s.equals("mkyong"))
                .forEach(System.out :: println);

        // 4. filter Person name example
        List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );
        persons.stream()
                .filter( p -> "jack".equals(p.getName()))
                .forEach(System.out :: println);

        // 5. collectors - word count
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");
        Map<String,Long> result = items.stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(result);

        Map<String, Long> finalMap = new LinkedHashMap<>();
        result.entrySet().stream()
                         .sorted(Map.Entry.comparingByKey())
                         .forEach(e -> finalMap.put(e.getKey(),e.getValue()));

        System.out.println("Sorted Map :"+finalMap);

        //6.  sum by int
        //3 apple, 2 banana, others 1
        List<Item> itemList = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );

        // 7. group by quantity - sum
        Map<String, Integer> sumMap = itemList.stream().collect(Collectors.groupingBy(Item :: getName, Collectors.summingInt(Item :: getQty)));

        // 8. group by counting
        Map<String, Long> countMap = itemList.stream().collect(Collectors.groupingBy(Item :: getName, Collectors.counting()));
        System.out.println("sumMap :" +sumMap);
        System.out.println("countMap :" +countMap);

        // 9. group by - price
        Map<BigDecimal, List<Item>> priceMap = itemList.stream().collect(Collectors.groupingBy(Item :: getPrice));
        System.out.println("priceMap :" +priceMap);
        // 10. group by price with item name remove duplicates
        Map<BigDecimal, Set<String>> resultMap =
                itemList.stream().collect(
                        Collectors.groupingBy(Item::getPrice,
                                Collectors.mapping(Item::getName, Collectors.toSet())
                        )
                );
        System.out.println("resultMap :" +resultMap);

        // 11. flat map examples
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
        Stream<String []> temp = Arrays.stream(data);
        Stream<String> stream = temp.flatMap(x -> Arrays.stream(x)).filter(x -> "a".equals(x));

        stream.forEach(System.out::println);

        // 12. student class - flatMap

        Student obj1 = new Student();
        obj1.setName("mkyong");
        obj1.addBook("Java 8 in Action");
        obj1.addBook("Spring Boot in Action");
        obj1.addBook("Effective Java (2nd Edition)");

        Student obj2 = new Student();
        obj2.setName("zilap");
        obj2.addBook("Learning Python, 5th Edition");
        obj2.addBook("Effective Java (2nd Edition)");

        List<Student> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);

        List<String> bookNames = list.stream()
              .map( x -> x.getBook())
              .flatMap(x -> x.stream())
              .distinct()
              .collect(Collectors.toList());
        System.out.println("bookNames :" +bookNames);

        // 13. read content from file
        String fileName = "C://Users//Administrator//Desktop//microservices-notes//circuit-breaker-hystrix-examples.txt";
        //read file into stream, try-with-resources
        try (Stream<String> streamTemp = Files.lines(Paths.get(fileName))) {
            streamTemp.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
