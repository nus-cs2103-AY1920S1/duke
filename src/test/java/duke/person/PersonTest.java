package duke.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {
    @Test
    public void NameConstructorTest() {
        Person person = new Person("myname");
        String name = person.getName();
        String title = person.getTitle();
        String contact = person.getContact();
        assertEquals("myname", name);
        assertEquals("", title);
        assertEquals("N.A.", contact);
    }

    @Test
    public void NameTitleContactConstructorTest() {
        Person person = new Person("myname","mytitle", "mycontact");
        String name = person.getName();
        String title = person.getTitle();
        String contact = person.getContact();
        assertEquals("myname", name);
        assertEquals("mytitle", title);
        assertEquals("mycontact", contact);
    }

}
