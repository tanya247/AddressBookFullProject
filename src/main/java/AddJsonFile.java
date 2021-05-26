import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddJsonFile {
    private  Path jsonFilePath = Path.of("C:\\Users\\tanya\\Desktop\\AddressBoook\\AddressBook.json");


    public  void writeDataInJSONFile(List<AddressBook> addressBook) throws IOException {

        try (Writer writer = Files.newBufferedWriter(jsonFilePath)) {
            Gson gson = new Gson();
            String json = gson.toJson(addressBook);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  List<AddressBook> readDataFromJSONFile() throws IOException {

        try (Reader reader = Files.newBufferedReader(jsonFilePath)) {
            Gson gson = new Gson();
            AddressBook[] contactObject = gson.fromJson(reader, AddressBook[].class);
            List<AddressBook> addressBook = Arrays.asList(contactObject);
            return addressBook;
        }
    }
    public int count() throws IOException {
        try (Reader reader = Files.newBufferedReader(jsonFilePath)) {
            Gson gson = new Gson();
            AddressBook[] contactObject = gson.fromJson(reader, AddressBook[].class);
            List<AddressBook> addressBook = Arrays.asList(contactObject);
            return contactObject.length;
        }
    }
}

