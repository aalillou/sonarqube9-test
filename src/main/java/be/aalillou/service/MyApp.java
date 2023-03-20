package be.aalillou.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyApp {
    public int multiply(int firstNumber, int secondNumber) {
        return firstNumber * secondNumber;
    }

    public int subtract(int firstNumber, int secondNumber) {
        return firstNumber - secondNumber;
    }

    public List<String> streamConcat (){
        List<String> list1 = Stream.of("A", "B", "C").toList();
        List<String> list2 = Stream.of("D", "E", "F").collect(Collectors.toList());
        return Stream.concat(list1.stream(), list2.stream()).toList();
    }
}
