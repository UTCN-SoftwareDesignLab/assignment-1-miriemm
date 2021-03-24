package repository.client;

import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;


public interface ClientRepository {

    List<Client> findAll();

    Notification<Client> findByName(String name);

    Notification<Client> findById(Long id) ;

    boolean save(Client client);

    boolean update(Client client);

    void removeAll();

}
