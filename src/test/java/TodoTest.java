package java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void ToDoTest(){
        assertEquals("[T][-] borrow book", Todo("borrow book"));
    }
}