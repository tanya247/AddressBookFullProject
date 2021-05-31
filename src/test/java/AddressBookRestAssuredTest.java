import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AddressBookRestAssuredTest {
    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
    }

    public AddressBook[] getAddressBook() {
        Response response = RestAssured.get("/AddressBook");
        System.out.println("AddressBook entries in json server :\n" + response.asString());
        AddressBook[] arrayOfAddressBook = new Gson().fromJson(response.asString(), AddressBook[].class);
        return arrayOfAddressBook;

    }
    private Response addContactToJsonServer(AddressBook addressBookData) {
        String addressBookJson = new Gson().toJson(addressBookData);
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(addressBookJson);
        return request.post("/Addressbook");
    }

    @Test
    public void givenContactDataInJsonServer_WhenRetrieved_ShouldMatchContactCount() {
        AddressBook[] arrayOfContacts = getAddressBook();
        AddressBookService serviceObject = new AddressBookService(Arrays.asList(arrayOfContacts));
        long entries = serviceObject.sizeOfContactList();
        Assertions.assertEquals(3, entries);
    }

    @Test
    public void givenMultipleContact_WhenAdded_ShouldMatch201ResponseAndCount() {
        AddressBook[] arrayOfAddressBook = getAddressBook();
        AddressBookService addressBookService;
        addressBookService = new AddressBookService(Arrays.asList(arrayOfAddressBook));
        AddressBook[] arrayOfPersonPayroll = {
                new AddressBook("Tanya", "Kansal", "Chandi Mandir", "Hapur", "UP", "9874568547", "xyz@gmail.com", 245101),
                new AddressBook("Prashant", "Kumar", "Kannauj", "Kanpur", "UP", "98754788777", "xyabxt@gmail.com", 245524)
        };
        for (AddressBook addressBookData : arrayOfPersonPayroll) {

            Response response = addContactToJsonServer(addressBookData);
            int statusCode = response.getStatusCode();
            Assertions.assertEquals(201, statusCode);

            addressBookData = new Gson().fromJson(response.asString(), AddressBook.class);
            addressBookService.addContactToAddressBook(addressBookData);
        }
        long entries = addressBookService.sizeOfContactList();
        Assertions.assertEquals(5, entries);
    }
    @Test
    public void givenCity_WhenUpdated_ShouldMatch200response() {
        AddressBook[] arrayOfAddressBook = getAddressBook();
        AddressBookService addressBookService;
        addressBookService = new AddressBookService(Arrays.asList(arrayOfAddressBook));
        addressBookService.updateContactCity("Tanya","Ghaziabad");
        AddressBook addressBookData = addressBookService.getAddressBookContent("Prashant");

        String addJson = new Gson().toJson(addressBookData);
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(addJson);
        Response response = request.put("/Addressbook/" + addressBookData.getName());
        int statusCode = response.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }

}