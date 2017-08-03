package Classes;

import java.io.Serializable;

/**
 * Created by Ofek on 31-Jul-17.
 */
@SuppressWarnings("serial")
public class Contact implements Serializable {
    String name;
    String surname;
    String phone;

    public Contact(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (surname != null ? !surname.equals(contact.surname) : contact.surname != null)
            return false;
        return phone != null ? phone.equals(contact.phone) : contact.phone == null;

    }
}
