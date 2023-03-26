package contacts;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class AbstractRecord implements Serializable {
    @Serial
    private static final long serialVersionUID = -5961632080641874587L;
    protected String name;
    protected final LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected Contact contact;
    protected List<String> fieldList;

    protected AbstractRecord(String name, Contact contact){
        this.contact = contact;
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    protected abstract void setField(String field, String value);
    protected abstract List<String> getFieldList();

    protected abstract String getFieldValue(String field);

    public abstract String getFullName();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String formatDate(LocalDateTime date){
        return date.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "T" + date.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
