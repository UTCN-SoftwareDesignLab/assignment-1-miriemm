package controller;

import model.Account;
import model.Client;
import model.validation.Notification;
import service.client.ClientService;
import view.EmployeeView;

import service.account.AccountService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


public class EmployeeController {

    private final EmployeeView employeeView;
    private final ClientService clientService;
    private final AccountService accountService;

    public EmployeeController(EmployeeView employeeView, ClientService clientService, AccountService accountService) {
        this.employeeView = employeeView;
        this.clientService = clientService;
        this.accountService = accountService;
        employeeView.setSearchButtonListener(new SearchClientButtonListener());
        employeeView.setSubmitButtonListener(new AddClientButtonListener());
        employeeView.setUpdateButtonListener(new UpdateClientButtonListener());
        //employeeView.setSearchButtonListener(new SearchAccountButtonListener());
        employeeView.setSearchAccountModeButtonListener(new SearchAccountButtonListener());
        employeeView.setUpdateAccountListener(new UpdateAccountButtonListener());
        employeeView.setDeleteAccountButtonListener(new DeleteAccountButtonListener());
        employeeView.setSubmitAccountButtonListener(new SubmitAccountButtonListener());
        employeeView.setAddAccountModeButtonListener(new CreateAccountButtonListener());
        employeeView.setSubmitTransferButtonListener(new TranferButtonListener());
        employeeView.setSubmitBillsButtonListener(new BillsButtonListener());

    }

    // client

    private class SearchClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String clientName = employeeView.getSearchClientName();
            Notification<Client> existingClient = clientService.findByName(clientName);

            if (existingClient.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), existingClient.getFormattedErrors());

            } else {
                employeeView.showClientInfo();
                employeeView.setClientInfo(existingClient.getResult());

            }
        }
    }

    private class AddClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = employeeView.getClientName();
            String identityCardNr = employeeView.getIdentityCardNr();
            String cnp = employeeView.getCNP();
            String address = employeeView.getAddress();

            Client newClient = new Client();
            newClient.setName(name);
            newClient.setIdentityCardNr(identityCardNr);
            newClient.setPersonalNumericCode(cnp);
            newClient.setAddress(address);

            Notification<Client> existingClient = clientService.findByName(name);


            if (existingClient.getFormattedErrors() == "Client not found!") {
                clientService.save(newClient);
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client added succesfully!");
                employeeView.hideClientInfo();

            } else {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client already exists!");
            }
        }
    }

    private class UpdateClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Long id = employeeView.getClientId();
            String name = employeeView.getClientName();
            String identityCardNr = employeeView.getIdentityCardNr();
            String cnp = employeeView.getCNP();
            String address = employeeView.getAddress();


            Client updatedClient = new Client();
            updatedClient.setId(id);
            updatedClient.setName(name);
            updatedClient.setIdentityCardNr(identityCardNr);
            updatedClient.setPersonalNumericCode(cnp);
            updatedClient.setAddress(address);


            if (clientService.update(updatedClient)) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client updated succesfully!");

            } else {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Cannot update unexisting client!");
            }
        }
    }

    private class SearchAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Long clientId = employeeView.getClientIdAccountMode();

            String clientName = employeeView.getSearchClientName();
            Notification<Client> existingClient = clientService.findByName(clientName);

            if (existingClient.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), existingClient.getFormattedErrors());

            } else {
                Long clientId = existingClient.getResult().getId();
                Notification<Account> existingAccount = accountService.findByClientId(clientId);

                if (existingAccount.hasErrors()) {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), existingAccount.getFormattedErrors());
                    employeeView.hideAccountInfo();

                } else {
                    employeeView.showAccountInfo();
                    employeeView.editAccountInfo(false);
                    employeeView.setAccountInfo(existingAccount.getResult());
                }
            }


        }
    }

    private class CreateAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String clientName = employeeView.getSearchClientName();
            Notification<Client> existingClient = clientService.findByName(clientName);
            Date creationDate = employeeView.getCurrentDate();


            if (existingClient.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), existingClient.getFormattedErrors());
            } else {
                Long clientId = existingClient.getResult().getId();
                Notification<Account> existingAccount = accountService.findByClientId(clientId);

                if (existingAccount.getFormattedErrors() == "Account not found!") {
                    employeeView.showAccountInfo();
                    employeeView.editAccountInfo(true);
                } else {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client already has an account!");
                }


            }

        }
    }


    private class SubmitAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String clientName = employeeView.getSearchClientName();
            Notification<Client> existingClient = clientService.findByName(clientName);
            Integer identificationNr = employeeView.getIdentificationNr();
            String typeOfAccount = employeeView.getTypeOfAccount();
            Integer money = employeeView.getMoney();
            Date creationDate = employeeView.getCurrentDate();


            if (existingClient.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), existingClient.getFormattedErrors());

            } else {
                Long clientId = existingClient.getResult().getId();

                Account createdAccount = new Account();
                createdAccount.setClientId(clientId);
                createdAccount.setIdentificationNumber(identificationNr);
                createdAccount.setType(typeOfAccount);
                createdAccount.setAmountOfMoney(money);
                createdAccount.setDateOfCreation(creationDate);

                if (accountService.saveAccount(createdAccount)) {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account created succesfully!");

                } else {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Cannot create account!");
                }
            }


        }
    }

    private class UpdateAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Long id = employeeView.getAccountId();
            Integer money = employeeView.getMoney();

            Account updatedAccount = new Account();
            updatedAccount.setId(id);
            updatedAccount.setAmountOfMoney(money);


            if (accountService.updateAccount(updatedAccount)) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account updated succesfully!");

            } else {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Cannot update unexisting account!");
            }
        }
    }

    private class DeleteAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Long accountId = employeeView.getAccountId();

            if (accountService.deleteAccount(accountId)) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account deleted succesfully!");

            } else {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Cannot delete unexisting account!");
            }
        }
    }

    private class TranferButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String fromClientName = employeeView.getTxFrom();
            String toClientName = employeeView.getTxTo();
            Integer sum = employeeView.getTxSum();

            Notification<Client> existingClient1 = clientService.findByName(fromClientName);
            Notification<Client> existingClient2 = clientService.findByName(toClientName);


            if (existingClient1.hasErrors() && existingClient2.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "One of the clients does not exist!");

            } else {
                Long clientId1 = existingClient1.getResult().getId();
                Long clientId2 = existingClient2.getResult().getId();

                Notification<Account> existingAccount1 = accountService.findByClientId(clientId1);
                Notification<Account> existingAccount2 = accountService.findByClientId(clientId2);


                Account account1 = existingAccount1.getResult();
                Account account2 = existingAccount2.getResult();


                account1.setAmountOfMoney(account1.getAmountOfMoney() - sum);
                account2.setAmountOfMoney(account2.getAmountOfMoney() + sum);

                if (account1.getAmountOfMoney() < sum) {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client does not have enough money to make the transfer!");
                } else {


                    if (accountService.updateAccount(account1) && accountService.updateAccount(account2)) {
                        JOptionPane.showMessageDialog(employeeView.getContentPane(), "Successful transfer of money!");

                    } else {
                        JOptionPane.showMessageDialog(employeeView.getContentPane(), "Cannot transfer money!");
                    }

                }
            }
        }
    }

    private class BillsButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String clientName = employeeView.getClientNameBill();
            Integer billValue = employeeView.getBillValue();

            Notification<Client> existingClient1 = clientService.findByName(clientName);


            if (existingClient1.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client does not exists");

            } else {
                Long clientId1 = existingClient1.getResult().getId();

                Notification<Account> existingAccount1 = accountService.findByClientId(clientId1);


                Account account1 = existingAccount1.getResult();


                account1.setAmountOfMoney(account1.getAmountOfMoney() - billValue);

                if (account1.getAmountOfMoney() < billValue) {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client does not have enough money to pay the bill!");
                } else {
                    employeeView.setBillsBalance(account1);

                    if (accountService.updateAccount(account1)) {
                        JOptionPane.showMessageDialog(employeeView.getContentPane(), "Successful bill payment!");

                    } else {
                        JOptionPane.showMessageDialog(employeeView.getContentPane(), "Cannot pay bill!");
                    }

                }
            }
        }
    }
}

