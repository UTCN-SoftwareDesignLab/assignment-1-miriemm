package service.client;

import model.Client;
import model.validation.Notification;

public interface ClientService {

    Notification<Client> findByName(String name);

    Notification<Client> findById(Long id);

    boolean save(Client client);

    boolean update(Client client);
}
