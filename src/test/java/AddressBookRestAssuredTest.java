import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
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

    @Test
    public void givenContactDataInJsonServer_WhenRetrieved_ShouldMatchContactCount() {
        AddressBook[] arrayOfContacts = getAddressBook();
        AddressBookService serviceObject = new AddressBookService(Arrays.asList(arrayOfContacts));
        long entries = serviceObject.sizeOfContactList();
        Assertions.assertEquals(3, entries);
    }
}