import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddressBookCSVFile {


    public void writeDataInCSVFile(List<AddressBook> addressBook)
            throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        Path csvFilePath = Path.of("C:\\Users\\tanya\\Downloads\\AddressBook.csv");
        try (Writer writer = Files.newBufferedWriter(csvFilePath)) {
            StatefulBeanToCsv<AddressBook> beanToCsv = new StatefulBeanToCsvBuilder<AddressBook>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
            beanToCsv.write(addressBook);
        }
    }
    public int readData(){
        int count = 0;
        try {
            Reader reader = Files
                    .newBufferedReader(Paths.get("C:\\Users\\tanya\\Downloads\\AddressBook.csv"));

            CSVReader csvReader = new CSVReader(reader);
            String records[];
            while ((records = csvReader.readNext()) != null) {
                for (String record : records) {
                    System.out.println(record);

                }
                count += 1;
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  count;
    }

}