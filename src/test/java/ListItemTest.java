import org.junit.jupiter.api.Assertions;

public class ListItemTest {
    public static void main(String[] args) {
        ListItem testSubject = new ListItem("Description", "deadline", "by 2/12/2019 1800");
        Assertions.assertEquals("[D]" + "[✗] "+ "Description" + "(by 02/12/19 1800)",testSubject.toString());
        return;
    }
}
