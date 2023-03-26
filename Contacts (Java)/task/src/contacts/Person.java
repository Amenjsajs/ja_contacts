package contacts;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Person extends AbstractRecord{
    private String surname;
    private LocalDate birthAt;
    private GENDER gender;

    public Person(String name, String surname, LocalDate birthAt, Contact contact, GENDER gender) {
        super(name, contact);
        this.surname = surname;
        this.birthAt = birthAt;
        this.gender = gender;
        fieldList = new ArrayList<>(){{
            add("name");
            add("surname");
            add("birth");
            add("number");
            add("gender");
        }};
    }

    @Override
    public String getFullName() {
        return name + " " + surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthAt() {
        return birthAt;
    }

    public void setBirthAt(LocalDate birthAt) {
        this.birthAt = birthAt;
    }

    public GENDER getGender() {
        return gender;
    }

    public void setGender(GENDER gender) {
        this.gender = gender;
    }

    public void setField(String field, String value) {
        switch (field) {
            case "name" -> setName(value);
            case "surname" -> setSurname(value);
            case "number" -> contact.setNumber(value);
            case "birth" -> {
                try {
                    setBirthAt(LocalDate.parse(value));
                } catch (DateTimeParseException e) {
                    birthAt = null;
                }
            }
            case "gender" -> gender = GENDER.getValueOf(value);
            default -> System.out.println("field not supported");
        }
        updatedAt = LocalDateTime.now();
    }

    @Override
    public List<String> getFieldList() {
        return fieldList;
    }

    @Override
    protected String getFieldValue(String field) {
        switch (field) {
            case "name" -> {
                return getName();
            }
            case "surname" -> {
                return getSurname();
            }
            case "number" -> {
                return getContact().getNumber();
            }
            default -> {
                return "";
            }
        }
    }

    public enum GENDER {
        MALE("M"), FEMALE("F");

        GENDER(String c) {
            this.firstGenderLetter = c;
        }

        public static GENDER getValueOf(String c) {
            for (GENDER gender : values()) {
                if (gender.firstGenderLetter.equals(c)) {
                    return gender;
                }
            }
            return null;
        }

        public String getFirstGenderLetter() {
            return firstGenderLetter;
        }

        private final String firstGenderLetter;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Birth date: " + (birthAt == null ? "[no data]" : birthAt) + "\n" +
                "Gender: " + (gender == null ? "[no data]" : gender.firstGenderLetter) + "\n" +
                "Number: " + contact + "\n" +
                "Time created: " + formatDate(createdAt) + "\n" +
                "Time last edit: " + formatDate(updatedAt) + "\n";
    }
}
