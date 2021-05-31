import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AddressBookServiceTest {
    AddressBookService addressBookService = new AddressBookService();
    List<AddressBook> addressBookList;

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
    @Test
    public void Json_Test() throws IOException {
        AddressBook[] arrayOfEmps = {
                new AddressBook("Tanya", "Kansal", "Chandi Mandir", "Hapur", "UP", "9874568547" , "xyz@gmail.com", 245101),
                new AddressBook("Prashant", "Kumar", "Kannauj", "Kanpur", "UP", "98754788777" , "xyabxt@gmail.com", 245524)
        };
        AddJsonFile addJsonFile = new AddJsonFile();
        addJsonFile.writeDataInJSONFile(Arrays.asList(arrayOfEmps));
        addJsonFile.readDataFromJSONFile();
        int m = addJsonFile.count();
        Assertions.assertEquals(2,m);
    }

    @Test
    public void givenAddressBook_WhenRetrived_ShouldReturnAddressBookSize() throws AddressBookException {
        addressBookList = addressBookService.readAddressBookData();
        Assertions.assertEquals(13, addressBookList.size());
    }
    @Test
    public void givenNewAddress_WhenUpdated_ShouldSyncWithDatabase() throws AddressBookException {
        AddressBookService addressBookService = new AddressBookService();
        List<AddressBook>addressBooks = addressBookService.readAddressBookData();
        addressBookService.updateAddress("Sandip","Ghaziabad");
        System.out.println(addressBooks);
        boolean result = addressBookService.checkAddressBookInSyncWithDB("Sandip");
        Assertions.assertTrue(result);
    }
    @Test
    public void givenDateRangeForRecord_WhenRetrieved_ShouldReturnProperData() throws AddressBookException {
        AddressBookService addressBookService = new AddressBookService();
        List<AddressBook> recordDataInGivenDateRange = addressBookService.getRecordAddedInDateRange("2020-01-01","2015-05-20");
        Assertions.assertEquals(3,recordDataInGivenDateRange.size());
    }
    @Test
    public void givenNameofCityOrState_WhenRetrieved_ShouldReturnProperData() throws AddressBookException {
        AddressBookService addressBookService = new AddressBookService();
        List<AddressBook> addressBooks = addressBookService.getRecordsAddedByCityOrStateName("Ghaziabad","UP");
        System.out.println(addressBooks);
        Assertions.assertEquals(5,addressBooks.size());
    }
    @Test
    public void givenNewContact_WhenAdded_ShouldSyncWithDB() throws AddressBookException {
        AddressBookService addressBookService = new AddressBookService();
        addressBookService.addNewContact("Radhika","Kansal","chandi mandir","Hapur","UP","7458964401","abz@gmail.com");
        Assertions.assertTrue(addressBookService.checkAddressBookInSyncWithDB("Tanya"));

    }
    @Test
    public void givenMultipleContact_WhenAdded_ShouldSyncWithDB() throws AddressBookException {
        AddressBook[] addressBooks= {
                new AddressBook("Palak", "Singhal", "abc", "Hapur",
                        "UP", "789456244", "Pss@gmail.com"),
                new AddressBook("Paras", "Singhal", "abcd", "Hapur",
                        "UP", "785625444", "Parass@gmail.com")

        };
        List<AddressBook> addressBookList = Arrays.asList(addressBooks);
        addressBookService.addMultipleContactsToRecord(addressBookList);
        Assertions.assertTrue(addressBookService.checkAddressBookInSyncWithDB("Palak"));
        Assertions.assertTrue(addressBookService.checkAddressBookInSyncWithDB("Paras"));

    }
}
