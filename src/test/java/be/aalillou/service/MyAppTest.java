package be.aalillou.service;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class MyAppTest {
    private MyApp myApp;

    @Before
    public void setUp() {
        myApp = new MyApp();
    }

    @Test
    public void multipliesNumbers() {
        int result = myApp.multiply(2, 4);

        assertThat(result, equalTo(8));
    }

    @Test
    public void subtractNumbers() {
        int result = myApp.subtract(4, 2);

        assertThat(result, equalTo(2));
    }

    @Test
    public void doStreamTest() {
        List<String> list = myApp.streamConcat();
        String first = list.stream().findFirst().get();
        assertThat(first, equalTo("1"));
    }
}
