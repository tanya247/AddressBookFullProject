

import java.util.ArrayList;
import java.util.List;

public class AddressBookService {
    
    public List<AddressBook> addressBookList;
    private static AddressBookConnection addressBookConnection;

    public AddressBookService(List<AddressBook> addressBookList) {
        this();
        this.addressBookList = addressBookList;
    }


    public void setContactDataList(List<AddressBook> addressBookList) {
        this.addressBookList = new ArrayList<>(addressBookList);
    }

    public AddressBookService() {
        addressBookConnection = AddressBookConnection.getInstance();
    }

    public List<AddressBook> readAddressBookData() throws AddressBookException {

        return this.addressBookList = addressBookConnection.readData();
    }
    public void updateAddress(String firstName, String address) throws AddressBookException{
        int result = new AddressBookConnection().updateDataUsingPreparedStatement(firstName,address);
        if (result == 0)
            return;
        AddressBook addressBook = this.getAddressBookData(firstName);
        if (addressBook != null)
            addressBook.setAddress(address);
    }

    private AddressBook getAddressBookData(String firstName) throws AddressBookException {
        addressBookList = this.readAddressBookData();
        return this.addressBookList.stream()
                .filter(addressBook -> addressBook.getFirstName().equals(firstName))
                .findFirst()
                .orElse(null);
    }

    public boolean checkAddressBookInSyncWithDB(String firstName) throws AddressBookException {
        List<AddressBook> addressBooks = addressBookConnection.getRecordDataByName(firstName);

        return addressBooks.get(0).equals(getAddressBookData(firstName));
    }
    public List<AddressBook> getRecordAddedInDateRange(String date1, String date2) throws AddressBookException {
        List<AddressBook> addressBooks = addressBookConnection.getRecordsAddedInGivenDateRange(date1, date2);
        return addressBooks;
    }
    public List<AddressBook> getRecordsAddedByCityOrStateName(String city, String state) throws AddressBookException {
        List<AddressBook> addressBooks = addressBookConnection.getRecordsByCityOrState(city, state);
        return addressBooks;
    }
    public void addNewContact(String firstName, String lastName,  String address, String city, String state,
                              String phoneNo, String email) throws AddressBookException {
        addressBookList = this.readAddressBookData();
        addressBookList.add(addressBookConnection.addNewContact(firstName, lastName,  address, city, state,  phoneNo,
                email));
    }
    public void addMultipleContactsToRecord(List<AddressBook> addressBooks) throws AddressBookException {
        addressBookConnection.addMultipleContactsToDBUsingThread(addressBooks);
    }

    public long sizeOfContactList() {
        return this.addressBookList.size();
    }
    public void addContactToAddressBook(AddressBook addressBookData) {
        addressBookList.add(addressBookData);
    }
    public AddressBook getAddressBookContent(String firstName) {
        AddressBook addressBookData;
        addressBookData = this.addressBookList.stream().filter(dataItem -> dataItem.getFirstName().equals(firstName))
                .findFirst().orElse(null);
        return addressBookData;
    }

    public void updateContactCity(String firstName, String city) {
        AddressBook addressBookData = this.getAddressBookContent(firstName);
        if (addressBookData != null)
            addressBookData.setCity(city);

    }


}

