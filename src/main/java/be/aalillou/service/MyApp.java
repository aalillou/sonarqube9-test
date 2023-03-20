package be.aalillou.service;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

public class MyApp {
    public int multiply(int firstNumber, int secondNumber) {
        return firstNumber * secondNumber;
    }

    public int subtract(int firstNumber, int secondNumber) {
        return firstNumber - secondNumber;
    }

    public List<String> streamConcat (){
        List<Integer> intList = new ArrayList<>();
        Collections.addAll(intList, 1, 2);
        List<String> list1 = intList.stream().map(Object::toString).toList();
        List<String> list2 = Stream.of("A", "B", "C").toList();

        return Stream.concat(list1.stream(), list2.stream()).toList();
    }
}
