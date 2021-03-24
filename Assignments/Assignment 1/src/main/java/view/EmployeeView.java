
package view;


import model.Account;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class EmployeeView extends JFrame {

    private JLabel title;
    private JLabel lMode;
    private Choice choice;
    // name
    private JLabel lSearchClientName;
    private JTextField txSearchClientName;
    private JButton btnSearchClientMode;
    // identity card number
    private JButton btnAdd;

    private JLabel lClientId;
    private JTextField txClientId;
    private JLabel lClientName;
    private JTextField txClientName;
    private JLabel lIdentityCardNr;
    private JTextField txIdentityCardNr;
    private JLabel lCNP;
    private JTextField txCNP;
    private JLabel lAddress;
    private JTextField txAddress;
    private JButton btnSubmit;
    private JButton btnUpdate;

    // account

    private JLabel lAccountId;
    private JTextField txAccountId;
    private JLabel lIdentificationNr;
    private JTextField txIdentificationNr;
    private JLabel lTypeOfAccount;
    private JTextField txTypeOfAccount;
    private JLabel lMoney;
    private JTextField txMoney;
    private JLabel lCreationDate;
    private JTextField txCreationDate;
    // works like view, if the client has an account, it displays it
    private JButton btnSearchAccountMode;
    private JButton btnCreateAccount;
    private JButton btnUpdateAccount;
    private JButton btnDeleteAccount;
    private JButton btnSubmitAccount;

    // transfer
    private JLabel lFrom;
    private JTextField txFrom;
    private JLabel lTo;
    private JTextField txTo;
    private JLabel lSum;
    private JTextField txSum;
    private JButton btnSubmitTranfer;

    // bills
    private JLabel lClientNameBill;
    private JTextField txClientNameBill;
    private JLabel lCurrentBalance;
    private JTextField txCurrentBalance;
    private JLabel lValueOfBill;
    private JTextField txValueOfBill;
    private JButton btnSubmitBill;


    public EmployeeView() throws HeadlessException {
        setTitle("Employee");
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(null);
        setResizable(false);

        // bounds for window
        setBounds(100, 100, 500, 600);

        title.setBounds(150,15,250,50);
        title.setFont(new Font("TimesRoman", Font.PLAIN, 25));


        lMode.setBounds(80,55,50,50);
        choice.add("Client");
        choice.add("Account");
        choice.add("Transfer");
        choice.add("Bills");

        choice.setBounds(160,70,100,100);


        //label for username
        lSearchClientName.setBounds(80, 0, 150, 300);
        // text field for username
        txSearchClientName.setBounds(185, 140, 150, 20);

        btnSearchClientMode.setBounds(50,225,150,30);

        btnAdd.setBounds(230,225,150,30);

        lClientId.setBounds(80, 240,100,100);

        txClientId.setBounds(205,280,150,20);

        lClientName.setBounds(80,280,100,100);

        txClientName.setBounds(205,320,150,20);

        lIdentityCardNr.setBounds(80,310,150,100);

        txIdentityCardNr.setBounds(205,350,150,20);

        lCNP.setBounds(80,340,100,100);

        txCNP.setBounds(205,380,150,20);

        lAddress.setBounds(80,370,100,100);

        txAddress.setBounds(205,410,150,20);

        btnSubmit.setBounds(180,455,100,50);

        btnUpdate.setBounds(180,455,100,50);

        // acount mode

        lAccountId.setBounds(50, 95, 150, 300);

        txAccountId.setBounds(205,235,150,20);


        btnSearchAccountMode.setBounds(80,190,110,30);

        btnCreateAccount.setBounds(200,190,110,30);


        lIdentificationNr.setBounds(50, 230,170,100);

        txIdentificationNr.setBounds(205,270,150,20);

        lTypeOfAccount.setBounds(50,270,170,100);

        txTypeOfAccount.setBounds(205, 310, 150, 20);

        lMoney.setBounds(50,310,180,100);

        txMoney.setBounds(205, 350, 150, 20);

        lCreationDate.setBounds(50,350,170,100);

        txCreationDate.setBounds(205, 390, 150, 20);


        btnUpdateAccount.setBounds(100,435,90,50);

        btnDeleteAccount.setBounds(200,435,90,50);

        btnSubmitAccount.setBounds(150,435,90,50);

        // transfer
        lFrom.setBounds(80, 0, 150, 300);

        txFrom.setBounds(185, 140, 150, 20);

        lTo.setBounds(80, 40, 150, 300);

        txTo.setBounds(185, 180, 150, 20);

        lSum.setBounds(80, 80, 150, 300);

        txSum.setBounds(185, 220, 150, 20);

        btnSubmitTranfer.setBounds(175,280,100,50);


        // bills

        lClientNameBill.setBounds(80, 0, 150, 300);
        txClientNameBill.setBounds(215, 140, 200, 20);
        lCurrentBalance.setBounds(80, 40, 150, 300);
        txCurrentBalance.setBounds(215, 180, 200, 20);
        lValueOfBill.setBounds(80, 80, 150, 300);
        txValueOfBill.setBounds(215, 220, 200, 20);
        btnSubmitBill.setBounds(175,280,100,50);


        add(title);
        add(lMode);
        add(choice);
        add(lSearchClientName);
        add(txSearchClientName);
        add(btnSearchClientMode);
        add(btnAdd);

        add(lClientId);
        add(txClientId);
        add(lClientName);
        add(txClientName);
        add(lIdentityCardNr);
        add(txIdentityCardNr);
        add(lCNP);
        add(txCNP);
        add(lAddress);
        add(txAddress);
        add(btnSubmit);
        add(btnUpdate);


        // account
        add(lAccountId);
        add(txAccountId);
        add(lIdentificationNr);
        add(txIdentificationNr);
        add(lTypeOfAccount);
        add(txTypeOfAccount);
        add(lMoney);
        add(txMoney);
        add(lCreationDate);
        add(txCreationDate);
        add(btnSearchAccountMode);
        add(btnCreateAccount);
        add(btnUpdateAccount);
        add(btnDeleteAccount);
        add(btnSubmitAccount);

        // transfer
        add(lFrom);
        add(txFrom);
        add(lTo);
        add(txTo);
        add(lSum);
        add(txSum);
        add(btnSubmitTranfer);

        // bills

        add(lClientNameBill);
        add(txClientNameBill);
        add(lCurrentBalance);
        add(txCurrentBalance);
        add(lValueOfBill);
        add(txValueOfBill);
        add(btnSubmitBill);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {

        title = new JLabel("EMPLOYEE");
        lMode = new JLabel("MODE:");
        choice = new Choice();

        //client mode
        lSearchClientName = new JLabel("CLIENT NAME:");
        txSearchClientName = new JTextField(15);
        btnSearchClientMode = new JButton("SEARCH");
        btnAdd = new JButton("ADD USER");

        lClientId = new JLabel("ID: ");
        txClientId = new JTextField();
        lClientName = new JLabel("CLIENT NAME: ");
        txClientName = new JTextField();
        lIdentityCardNr = new JLabel("IDENTITY CARD NR:");
        txIdentityCardNr = new JTextField();
        lCNP = new JLabel("CNP:");
        txCNP = new JTextField();
        lAddress = new JLabel("ADDRESS:");
        txAddress = new JTextField();
        btnSubmit = new JButton("SUBMIT");
        btnUpdate = new JButton("UPDATE");

        // account
        lAccountId = new JLabel("ACCOUNT ID:");
        txAccountId = new JTextField();
        lIdentificationNr = new JLabel("IDENTIFICATION NR:");
        txIdentificationNr = new JTextField();
        lTypeOfAccount = new JLabel("ACCOUNT TYPE:");
        txTypeOfAccount = new JTextField();
        lMoney = new JLabel("MONEY AMOUNT:");
        txMoney = new JTextField();
        lCreationDate = new JLabel("CREATION DATE:");
        txCreationDate = new JTextField();
        btnSearchAccountMode = new JButton("SEARCH");
        btnCreateAccount = new JButton("CREATE");
        btnUpdateAccount = new JButton("UPDATE");
        btnDeleteAccount = new JButton("DELETE");
        btnSubmitAccount = new JButton("SUBMIT");

        // transfer
        lFrom = new JLabel("FROM:");
        txFrom = new JTextField();
        lTo =  new JLabel("TO:");
        txTo = new JTextField();
        lSum = new JLabel("SUM:");
        txSum = new JTextField();
        btnSubmitTranfer = new JButton("SUBMIT");

        // bills
        lClientNameBill = new JLabel("CLIENT NAME:");
        txClientNameBill = new JTextField();
        lCurrentBalance =  new JLabel("CURRENT BALANCE:");
        txCurrentBalance = new JTextField();
        lValueOfBill = new JLabel("BILL VALUE:");
        txValueOfBill = new JTextField();
        btnSubmitBill = new JButton("SUBMIT");


        choice.addItemListener(new EmployeeModeActionListener());

        hideAllElements();
        showClientMode();
        btnAdd.addActionListener(new ShowClientButtonListener());
        txClientId.setEditable(false);

        txAccountId.setEditable(false);
        btnSearchAccountMode.addActionListener(new ShowAccountButtonListener());
        btnUpdateAccount.addActionListener(new UpdateAccountButtonListener());
        btnDeleteAccount.addActionListener(new DeleteAccountButtonListener());

        btnSubmitTranfer.addActionListener(new SubmitTranferButtonListener());
        btnSubmitBill.addActionListener(new SubmitBillButtonListener());

        txCreationDate.setEditable(false);


    }

    public String getUsername() { return txSearchClientName.getText();}

    private void showClientMode() {
        lSearchClientName.setVisible(true);
        txSearchClientName.setVisible(true);
        btnSearchClientMode.setVisible(true);
        btnAdd.setVisible(true);

    }

    public void showClientInfo() {
        lClientId.setVisible(true);
        txClientId.setVisible(true);
        lClientName.setVisible(true);
        txClientName.setVisible(true);
        lIdentityCardNr.setVisible(true);
        txIdentityCardNr.setVisible(true);
        lCNP.setVisible(true);
        txCNP.setVisible(true);
        lAddress.setVisible(true);
        txAddress.setVisible(true);
        btnSubmit.setVisible(false);
        btnUpdate.setVisible(true);

        txClientId.setText("");
        txClientName.setText("");
        txIdentityCardNr.setText("");
        txCNP.setText("");
        txAddress.setText("");
    }

    private void showAccountMode() {
        lSearchClientName.setVisible(true);
        txSearchClientName.setVisible(true);
        btnSearchAccountMode.setVisible(true);
        btnCreateAccount.setVisible(true);

    }

    public void showAccountInfo() {
        lAccountId.setVisible(true);
        txAccountId.setVisible(true);
        lIdentificationNr.setVisible(true);
        txIdentificationNr.setVisible(true);
        lTypeOfAccount.setVisible(true);
        txTypeOfAccount.setVisible(true);
        lMoney.setVisible(true);
        txMoney.setVisible(true);
        lCreationDate.setVisible(true);
        txCreationDate.setVisible(true);
        btnUpdateAccount.setVisible(true);
        btnDeleteAccount.setVisible(true);
        btnSubmitAccount.setVisible(true);
    }

    public void editAccountInfo(Boolean isEditable) {
        txIdentificationNr.setEditable(isEditable);
        txTypeOfAccount.setEditable(isEditable);

        btnSubmitAccount.setVisible(isEditable);

        btnUpdateAccount.setVisible(!isEditable);
        btnDeleteAccount.setVisible(!isEditable);

        txAccountId.setText("");
        txIdentificationNr.setText("");
        txTypeOfAccount.setText("");
        txMoney.setText("");
        txCreationDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

    }

    public void showTranferMode() {
        lFrom.setVisible(true);
        txFrom.setVisible(true);
        lTo.setVisible(true);
        txTo.setVisible(true);
        lSum.setVisible(true);
        txSum.setVisible(true);
        btnSubmitTranfer.setVisible(true);
    }

    public void showBillsMode() {
        lClientNameBill.setVisible(true);
        txClientNameBill.setVisible(true);
        lCurrentBalance.setVisible(true);
        txCurrentBalance.setVisible(true);
        lValueOfBill.setVisible(true);
        txValueOfBill.setVisible(true);
        btnSubmitBill.setVisible(true);
        txCurrentBalance.setEditable(false);
    }


    public void hideClientInfo() {
        lClientId.setVisible(false);
        txClientId.setVisible(false);
        lClientName.setVisible(false);
        txClientName.setVisible(false);

        // account
        lIdentityCardNr.setVisible(false);
        txIdentityCardNr.setVisible(false);
        lCNP.setVisible(false);
        txCNP.setVisible(false);
        lAddress.setVisible(false);
        txAddress.setVisible(false);
        btnSubmit.setVisible(false);
        btnUpdate.setVisible(false);
        btnSubmitAccount.setVisible(false);

        btnSearchAccountMode.setVisible(false);
        btnCreateAccount.setVisible(false);

    }

    public void hideAccountInfo() {
        lAccountId.setVisible(false);
        txAccountId.setVisible(false);
        lIdentificationNr.setVisible(false);
        txIdentificationNr.setVisible(false);
        lTypeOfAccount.setVisible(false);
        txTypeOfAccount.setVisible(false);
        lMoney.setVisible(false);
        txMoney.setVisible(false);
        lCreationDate.setVisible(false);
        txCreationDate.setVisible(false);
        btnUpdateAccount.setVisible(false);
        btnDeleteAccount.setVisible(false);
        btnSubmitAccount.setVisible(false);
    }

    private void hideTransferMode() {
        lFrom.setVisible(false);
        txFrom.setVisible(false);
        lTo.setVisible(false);
        txTo.setVisible(false);
        lSum.setVisible(false);
        txSum.setVisible(false);
        btnSubmitTranfer.setVisible(false);
    }

    private void hideBillsMode() {
        lClientNameBill.setVisible(false);
        txClientNameBill.setVisible(false);
        lCurrentBalance.setVisible(false);
        txCurrentBalance.setVisible(false);
        lValueOfBill.setVisible(false);
        txValueOfBill.setVisible(false);
        btnSubmitBill.setVisible(false);
    }



    public void setClientInfo(Client client) {
        txClientId.setText(client.getId().toString());
        txClientName.setText(client.getName());
        txIdentityCardNr.setText(client.getIdentityCardNr().toString());
        txCNP.setText(client.getIdentityCardNr().toString());
        txAddress.setText(client.getAddress());
    }

    public void setAccountInfo(Account account) {
        txAccountId.setText(account.getId().toString());
        txIdentificationNr.setText(account.getIdentificationNumber().toString());
        txTypeOfAccount.setText(account.getType());
        txMoney.setText(account.getAmountOfMoney().toString());
        txCreationDate.setText(account.getDateOfCreation().toString());
    }

    public void setBillsBalance(Account account) {
        txCurrentBalance.setText(account.getAmountOfMoney().toString());
    }

    private void hideAllElements() {
        // client mode
        lSearchClientName.setVisible(false);
        txSearchClientName.setVisible(false);
        btnSearchClientMode.setVisible(false);
        btnAdd.setVisible(false);
        hideClientInfo();
        hideAccountInfo();
        hideTransferMode();
        hideBillsMode();

    }


    private class EmployeeModeActionListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            String mode = choice.getItem(choice.getSelectedIndex());
            switch(mode) {
                case "Client":
                    hideAllElements();
                    showClientMode();
                    break;


                case "Account":
                    hideAllElements();
                    showAccountMode();

                    break;

                case "Transfer":
                    hideAllElements();
                    showTranferMode();

                    break;

                case "Bills":
                    hideAllElements();
                    showBillsMode();

                    break;

            }

        }
    }

    // client

    public String getSearchClientName() {
        return txSearchClientName.getText();
    }

    public Long getClientId() { return Long.parseLong(txClientId.getText());}

    public String getClientName() { return txClientName.getText();}


    public String getIdentityCardNr() { return txIdentityCardNr.getText();}

    public String getCNP() { return txCNP.getText();}

    public String getAddress() { return  txAddress.getText();}


    // account

    public Long getAccountId() {return  Long.parseLong(txAccountId.getText());}


    public Integer getIdentificationNr() { return Integer.parseInt(txIdentificationNr.getText()); }

    public String getTypeOfAccount() {  return txTypeOfAccount.getText(); }

    public Integer getMoney() { return Integer.parseInt(txMoney.getText());}


    public Date getCurrentDate() {
        java.util.Date date=new java.util.Date();
        return date;

    }

    // transfer

    public String getTxFrom() { return txFrom.getText();}

    public String getTxTo() { return txTo.getText();}

    public Integer getTxSum() { return  Integer.parseInt(txSum.getText());}


    public void setSearchButtonListener(ActionListener searchButtonListener) {
        btnSearchClientMode.addActionListener(searchButtonListener);
    }

    // bills

    public String getClientNameBill() { return txClientNameBill.getText(); }

    public Integer getBillValue() { return Integer.parseInt(txValueOfBill.getText()); }



    private class ShowClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            showClientInfo();
            btnSubmit.setVisible(true);
            btnUpdate.setVisible(false);
        }
    }

    private class ShowAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            btnSubmitAccount.setVisible(false);

        }
    }



    private class UpdateAccountButtonListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showAccountMode();
            hideAccountInfo();
            txAccountId.setText("");
            txIdentificationNr.setText("");
            txTypeOfAccount.setText("");
            txMoney.setText("");
            txCreationDate.setText("");
        }
    }

    private class DeleteAccountButtonListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showAccountMode();
            hideAccountInfo();
            txAccountId.setText(" ");
            txIdentificationNr.setText(" ");
            txTypeOfAccount.setText(" ");
            txMoney.setText(" ");
            txCreationDate.setText(" ");


        }
    }

    private class SubmitTranferButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            txFrom.setText("");
            txTo.setText("");
            txSum.setText("");
        }
    }

    private class SubmitBillButtonListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            txClientNameBill.setText("");
            txCurrentBalance.setText("");
            txValueOfBill.setText("");
        }
    }

    public void setSubmitButtonListener(ActionListener submitButtonListener) {
        btnSubmit.addActionListener(submitButtonListener);
    }

    public void setUpdateButtonListener(ActionListener updateButtonListener) {
        btnUpdate.addActionListener(updateButtonListener);
    }


    // account

    public void setSearchAccountModeButtonListener(ActionListener searchAccountModeButtonListener) {
        btnSearchAccountMode.addActionListener(searchAccountModeButtonListener);

    }

    public void setAddAccountModeButtonListener(ActionListener addAccountModeButtonListener) {
        btnCreateAccount.addActionListener(addAccountModeButtonListener);
    }

    public void setUpdateAccountListener(ActionListener updateAccountListener){
        btnUpdateAccount.addActionListener(updateAccountListener);
    }

    public  void setDeleteAccountButtonListener(ActionListener deletedAccountListener) {
        btnDeleteAccount.addActionListener(deletedAccountListener);
    }

    public void setSubmitAccountButtonListener(ActionListener submitAccountButtonListener) {
        btnSubmitAccount.addActionListener(submitAccountButtonListener);

    }

    // transfer

    public void setSubmitTransferButtonListener(ActionListener submitTransferButtonListener) {
        btnSubmitTranfer.addActionListener(submitTransferButtonListener);
    }

    // bills

    public void setSubmitBillsButtonListener(ActionListener submitBillsButtonListener) {
        btnSubmitBill.addActionListener(submitBillsButtonListener);
    }


    public void setVisible() {
        this.setVisible(true);
    }

}
