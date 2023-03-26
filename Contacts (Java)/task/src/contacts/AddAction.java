package contacts;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddAction extends AbstractAction {
    public AddAction() {
        super("add");
    }

    @Override
    protected void run() {
        add();
    }

    private void add() {
        String name;
        Contact contact;

        System.out.print("Enter the type (person, organization): ");
        String type = input.nextLine();

        AbstractRecord record;
        if (!(type.equals("person") || type.equals("organization"))) {
            System.out.println("Record type not found!\n");
        } else {
            if (type.equals("organization")) {
                System.out.print("Enter the organization name: ");
                name = input.nextLine();

                System.out.print("Enter the address: ");
                String address = input.nextLine();

                System.out.print("Enter the number: ");
                contact = new Contact(input.nextLine());

                record = new Organization(name, contact, address);
            } else {
                System.out.print("Enter the name: ");
                name = input.nextLine();

                System.out.print("Enter the surname: ");
                String surname = input.nextLine();

                System.out.print("Enter the birth date: ");
                LocalDate birthAt = null;
                try {
                    birthAt = LocalDate.parse(input.nextLine());
                } catch (DateTimeParseException ignored) {
                    System.out.println("Bad birth date!");
                }

                System.out.print("Enter the gender (M, F): ");
                Person.GENDER gender;
                gender = Person.GENDER.getValueOf(input.nextLine());
                if (gender == null) {
                    System.out.println("Bad gender!");
                }

                System.out.print("Enter the number: ");
                contact = new Contact(input.nextLine());

                record = new Person(name, surname, birthAt, contact, gender);
            }

            contacts.add(record);
            if (save() == -1) {
                contacts.remove(record);
            } else {
                System.out.println("The record added.\n");
            }
        }
    }
}
