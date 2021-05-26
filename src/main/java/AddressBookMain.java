
import java.util.Scanner;
public class AddressBookMain {
    public static void main(String args[]) {
        AddressBook addressBook = new AddressBook();
        Scanner sc = new Scanner(System.in);
        String m;
        System.out.println("Enter your first name:");
        String firstName = sc.nextLine();
        addressBook.setFirstName(firstName);
        System.out.println("Enter the last name :");
        String lastName = sc.nextLine();
        addressBook.setLastName(lastName);
        System.out.println("Enter the address :");
        String addressBookdress = sc.nextLine();
        addressBook.setAddress(addressBookdress);
        System.out.println("Enter the city :");
        String city = sc.nextLine();
        addressBook.setCity(city);
        System.out.println("Enter the state :");
        String state = sc.nextLine();
        addressBook.setState(state);
        System.out.println("Enter the email :");
        String email = sc.nextLine();
        addressBook.setEmail(email);
        System.out.println("Enter the mobile number :");
        long phoneNo = sc.nextLong();
        addressBook.setPhoneNo(phoneNo);
        System.out.println("Enter the zip :");
        int zip = sc.nextInt();
        addressBook.setZip(zip);
        m = "First Name :- "+addressBook.getFirstName()+
                "\nLast name : - "+addressBook.getLastName()+
                "\nAddress :- "+addressBook.getAddress()+
                "\nCity :- "+addressBook.getCity()+
                "\nState :- "+addressBook.getCity()+
                "\nEmail:- "+addressBook.getEmail()+
                "\nMobileNo. :- "+addressBook.getPhoneNo()+
                "\nZip :- "+addressBook.getZip();

        System.out.println("contact added\n"+m);
    }


}