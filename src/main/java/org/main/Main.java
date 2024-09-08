
package org.main;

import java.util.*;

import org.clients.Client;
import org.clients.ClientDao;
import org.clients.Email;
import org.clients.EmailDao;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start");
        EmailDao emailDao = new EmailDao();
        ClientDao clientDao = new ClientDao();
        List<Client> clients = getFakeUsers();

        testUserDao(clientDao, clients);
        testEmailDao(emailDao, clientDao, clients);
        System.out.println("End");

    }

    private static List<Client> getFakeUsers() {
        List<Client> clients = new ArrayList<>();

        clients.add(new Client(11111111L, "Denis", "Villeneuve"));
        clients.add(new Client(33333333L, "Wes", "Anderson"));
        clients.add(new Client(22222222L, "Quentin", "Tarantino"));
        clients.add(new Client(55555555L, "Andrei", "Tarkovsky"));
        clients.add(new Client(44444444L, "Akira", "Kurosawa"));

        return clients;
    }

    private static void testUserDao(ClientDao clientDao, List<Client> clients) {

        System.out.print("New clients list:\n");
        for (Client client : clients) {
            System.out.printf("- %s\n", client.getName());
        }

        clientDao.saveAll(clients);

        List<Client> clientList = clientDao.getAll();

        System.out.println("Inserted Clients List:\n");
        for (Client client : clientList) {
            System.out.printf("%s | %s | %s%n\n", client.getDni(),
                    client.getName(), client.getSurname());
        }

        Client client1 = clients.get(1);
        client1.setSurname("Odertsurneim");
        clientDao.update(client1);

        Client client2 = clients.get(2);
        clientDao.delete(client2);

        Optional<Client> clientByDni = clientDao.getClientByDni("44444444");

        String wrongDni = "44414444";

        Optional<Client> clientByDniOptional = clientDao.getClientByDni(wrongDni);

        clientByDni.ifPresent(client -> System.out.printf("Correct client %s",
                client.getName()));

        if (clientByDniOptional.isEmpty()) {
            System.out.printf("No client with \"%s\" ID", wrongDni);
        }
    }

    private static void testEmailDao(EmailDao emailDao, ClientDao clientDao, List<Client> fakeUsers) {

        List<Email> emails = new ArrayList<>();

        clientDao.saveAll(fakeUsers);

        emails.add(new Email(fakeUsers.get(0), "First mail of List"));
        emails.add(new Email(fakeUsers.get(1), "Second mail of List"));
        emails.add(new Email(fakeUsers.get(2), "3rd mail of List"));
        emails.add(new Email(fakeUsers.get(1), "Fourth mail of List"));

        emailDao.save(new Email(fakeUsers.get(3), "Inserted on it's own"));
        emailDao.saveAll(emails);

        emailDao.delete(emails.get(0));

        Email changed1 = emails.get(2);
        changed1.setContent("Altered mail!");
        emailDao.update(changed1);
    }
}
