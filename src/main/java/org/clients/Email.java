package org.clients;

import jakarta.persistence.*;

@Entity
@Table(name ="email")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    @JoinColumn(name ="client_id")
    Client client;
    @Column
    String content;

    public Email() {
    }

    public Email(Client client, String content) {
        this.client = client;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Email{" + "id=" + id + ", client=" + client + ", content='" + content + '\'' + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
