import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AddressServiceTest {
    @Test
    public void given3ContactDetailsWhenWrittenToFile() {
        AddressBook[] arrayOfEmps = {
                new AddressBook("Tanya", "Kansal", "Chandi Mandir", "Hapur", "UP", "9874568547" , "xyz@gmail.com", 245101)
        };
        AddressBookIOService addressBookService;
        addressBookService = new AddressBookIOService();
        addressBookService.writeData(Arrays.asList(arrayOfEmps));
        addressBookService.readData();
        long entries = addressBookService.countDataEntries();
        Assertions.assertEquals(1,entries);

    }
}