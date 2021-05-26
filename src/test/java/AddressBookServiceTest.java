import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

class AddressServiceTest {
    @Test
    public void givenContactDetailsWhenWrittenToFile() {
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
    @Test
    public void CSV_Test() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        AddressBook[] arrayOfEmps = {
                new AddressBook("Tanya", "Kansal", "Chandi Mandir", "Hapur", "UP", "9874568547" , "xyz@gmail.com", 245101),
                new AddressBook("Prashant", "Kumar", "Kannauj", "Kanpur", "UP", "98754788777" , "xyabxt@gmail.com", 245524)
        };
        AddressBookCSVFile addressBookCsvReader = new AddressBookCSVFile();
        addressBookCsvReader.writeDataInCSVFile(Arrays.asList(arrayOfEmps));
        int count = addressBookCsvReader.readData();
        Assertions.assertEquals(3,count);
    }
}