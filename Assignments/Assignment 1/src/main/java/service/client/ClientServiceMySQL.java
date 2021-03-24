package service.client;

import model.Client;
import model.validation.Notification;
import repository.client.ClientRepository;

public class ClientServiceMySQL implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceMySQL(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Notification<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }

    @Override
    public  Notification<Client> findById(Long id) {
        return  clientRepository.findById(id);
    }

    @Override
    public boolean save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public boolean update(Client client) {
        return clientRepository.update(client);
    }

}
