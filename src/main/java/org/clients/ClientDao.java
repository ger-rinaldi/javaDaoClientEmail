package org.clients;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class ClientDao extends AbstractDaoCrud<Client>{
    public ClientDao() {
        super(Client.class);
    }

    public List<Client> listClients(){
        return this.getAll();
    }


     public Optional<Client> getClientByDni(String dni){
        return super.getById("dni", dni);
     }

}
