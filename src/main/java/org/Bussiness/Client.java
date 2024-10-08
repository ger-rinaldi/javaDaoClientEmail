package org.Bussiness;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @Column
    private Long dni;
    @Column
    private String name;
    @Column
    private String surname;

    public Client() {
        super();
    }

    public Client(Long dni, String name, String surname) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Client{" + "dni=" + dni + ", name='" + name + '\'' + ", surname='" + surname + '\'' + '}';
    }
}
