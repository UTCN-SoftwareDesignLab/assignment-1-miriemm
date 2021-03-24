package model.builder;

import model.Client;

import java.math.BigInteger;

public class ClientBuilder {

    private Client client;


    public ClientBuilder() {
        client = new Client();
    }

    public ClientBuilder setId(Long id){
        client.setId(id);
        return this;
    }

    public ClientBuilder setName(String name) {
        client.setName(name);
        return this;
    }

    public ClientBuilder setIdentityCardNr(String identityCardNr) {
        client.setIdentityCardNr(identityCardNr);
        return this;
    }

    public ClientBuilder setPersonalNumericCode(String personalNumericCode) {
        client.setPersonalNumericCode(personalNumericCode);
        return this;
    }

    public ClientBuilder setAddress(String address){
        client.setAddress(address);
        return this;
    }

    public Client build() {return client;}
}