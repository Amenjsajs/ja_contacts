package contacts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Organization extends AbstractRecord {
    private String address;
    protected Organization(String name, Contact contact, String address) {
        super(name, contact);
        this.address = address;
        fieldList = new ArrayList<>() {{
            add("name");
            add("address");
            add("number");
        }};
    }

    @Override
    public List<String> getFieldList() {
        return fieldList;
    }

    @Override
    protected String getFieldValue(String field) {
        switch (field) {
            case "name" ->{
                return getName();
            }
            case "address" -> {
                return getAddress();
            }
            case "number" -> {
                return getContact().getNumber();
            }
            default -> {
                return "";
            }
        }
    }

    @Override
    public void setField(String field, String value) {
        switch (field) {
            case "name"->setName(value);
            case "address" -> setAddress(value);
            case "number" -> contact.setNumber(value);
            default -> System.out.println("field not supported");
        }
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String getFullName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Organization name: " + name + "\n" +
                "Address: " + (address.isEmpty() ? "[no data]" : address) + "\n" +
                "Number: " + contact + "\n" +
                "Time created: " + formatDate(createdAt) + "\n" +
                "Time last edit: " + formatDate(updatedAt) + "\n";
    }
}
