import java.util.Scanner;
import java.util.ArrayList;
public class ContactDirectory{
    String firstName,lastName,address,city,state,email,m;
    int zip;
    long phoneNo;
    Scanner scanner = new Scanner(System.in);
    ArrayList <AddressBook> contacts = new ArrayList <AddressBook>();


    public void addContact() {

        System.out.println("Enter the first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter the last name :");
        String lastName = scanner.nextLine();

        System.out.println("Enter the address :");
        String address = scanner.nextLine();

        System.out.println("Enter the city :");
        String city = scanner.nextLine();

        System.out.println("Enter the state :");
        String state = scanner.nextLine();

        System.out.println("Enter the email :");
        String email = scanner.nextLine();

        System.out.println("Enter the mobile number :");
        long phoneNo = scanner.nextLong();

        System.out.println("Enter the zip :");
        int zip = scanner.nextInt();

        contacts.add(new AddressBook(firstName, lastName, address, city,
                state, phoneNo, email, zip));
    }
    public void editContact() {

        if (contacts.isEmpty()) {
            System.out.println("AddressBook is empty");
        }
        else {
            System.out.println("Enter First Name");
            String Name = scanner.next();
            for(int i = 0 ; i<contacts.size();i++) {
                m = contacts.get(i).getFirstName();

                if(m.equalsIgnoreCase(Name)) {
                    System.out.println("Enter updated first name:");
                    String fname = scanner.next();
                    System.out.println("Enter updated last name :");
                    String lname = scanner.next();
                    System.out.println("Enter updated address :");
                    String addr = scanner.nextLine();
                    System.out.println("Enter updated city :");
                    String cit = scanner.next();
                    System.out.println("Enter updated state:");
                    String stat = scanner.next();
                    System.out.println("Enter updated email:");
                    String ema = scanner.next();
                    System.out.println("Enter updated pin code :");
                    int zp = scanner.nextInt();
                    System.out.println("Enter updated Phone Number :");
                    long phone = scanner.nextLong();

                    contacts.get(i).setFirstName(fname);
                    contacts.get(i).setLastName(lname);
                    contacts.get(i).setAddress(addr);
                    contacts.get(i).setCity(cit);
                    contacts.get(i).setState(stat);
                    contacts.get(i).setEmail(ema);
                    contacts.get(i).setPhoneNo(phone);
                    contacts.get(i).setZip(zp);
                    System.out.println("contact updated");
                }
                else {
                    System.out.println("Name not found");
                }
            }
        }
    }
    public void show() {
        if (contacts.isEmpty()) {
            System.out.println("AddressBook is empty");
        }
        else {
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println("First Name is : "+contacts.get(i).getFirstName());
                System.out.println("Last Name is: "+contacts.get(i).getLastName());
                System.out.println("Address is: "+contacts.get(i).getAddress());
                System.out.println("City is: "+contacts.get(i).getCity());
                System.out.println("State is: "+contacts.get(i).getState());
                System.out.println("Email is: "+contacts.get(i).getEmail());
                System.out.println("Phone Number is: "+contacts.get(i).getPhoneNo());
                System.out.println("Pin code is: "+contacts.get(i).getZip());
            }
        }
    }
}
