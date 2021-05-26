import java.util.Scanner;
public class AddressBookMain {
    ContactDirectory contactDirectory = new ContactDirectory();
    public void addressBookMain(){
        Scanner sc = new Scanner(System.in);
        addressBookOperations();
        System.out.println("Do u wqant to proceed , say Yes/No");
        String ch = sc.next();
        while(ch.equals("Yes")) {
            addressBookOperations();
            System.out.println("Do u wqant to proceed , say Yes/No");
            ch = sc.next();
        }
    }
    public void addressBookOperations(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 : Add contact");
        System.out.println("Enter 2 : edit contact ");
        System.out.println("Enter 3 : Delete Contact");
        System.out.println("Enter 4 : Show Contact");
        int choose = sc.nextInt();
        switch(choose) {
            case 1 :
                contactDirectory.addContact();
                break;

            case 2:
                contactDirectory.editContact();
                break;
            case 3:
                contactDirectory.deleteContact();
                break;
            case 4:
                contactDirectory.show();
                break;

            default:
                System.out.println("Invalid Choice");
                break;

        }

    }
}