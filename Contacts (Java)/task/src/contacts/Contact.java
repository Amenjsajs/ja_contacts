package contacts;

import java.io.Serial;
import java.io.Serializable;
import java.util.regex.Pattern;

public class Contact implements Serializable {

    @Serial
    private static final long serialVersionUID = -2502448053531954136L;
    private String number;
    private static Pattern pattern;
    private static final String NUMBER_FORMAT_REGEX = "\\+?((\\([a-zA-Z0-9]+\\)([\\s|-][a-zA-Z0-9]{2,})*)|([a-zA-Z0-9]+([\\s|-]\\([a-zA-Z0-9]{2,}\\))*))([\\s|-][a-zA-Z0-9]{2,})*";

    public Contact(String number) {
        pattern = Pattern.compile(NUMBER_FORMAT_REGEX);
        setNumber(number);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (isValidNumberFormat(number)) {
            this.number = number;
        } else {
            this.number = "";
            System.out.println("Wrong number format!");
        }
    }

    private static boolean isValidNumberFormat(String number) {
        return pattern.matcher(number).matches();
    }

    public boolean hasNumber() {
        return !number.isEmpty();
    }

    @Override
    public String toString() {
        return number.isEmpty() ? "[no number]" : number;
    }
}
