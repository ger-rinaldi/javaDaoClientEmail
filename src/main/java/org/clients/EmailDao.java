package org.clients;

import java.util.List;
import java.util.Optional;

public class EmailDao extends AbstractDaoCrud<Email> {
    public EmailDao() {
        super(Email.class);
    }

    public List<Email> getEmails(){
        return this.getAll();
    }

    public Optional<Email> getById(int id){
        return this.getById("id", Integer.toString(id));
    }

}
