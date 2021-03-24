package model;

import java.math.BigInteger;

public class Client {

    private Long id;
    private String name;
    private String identityCardNr;
    private String personalNumericCode;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityCardNr() {
        return identityCardNr;
    }

    public void setIdentityCardNr(String identityCardNr) {
        this.identityCardNr = identityCardNr;
    }

    public String getPersonalNumericCode() {
        return personalNumericCode;
    }

    public void setPersonalNumericCode(String personalNumericCode) {
        this.personalNumericCode = personalNumericCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
