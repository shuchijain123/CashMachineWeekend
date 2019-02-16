package rocks.zipcode.atm.bank;

import javafx.scene.control.*;
import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.Map;


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Example 1", "example1@gmail.com", 500
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Example 2", "example2@gmail.com", 200
        )));
        accounts.put(1234, new PremiumAccount(new AccountData(
                1234, "Milo", "milo2@gmail.com", 123
        )));
        accounts.put(5000, new PremiumAccount(new AccountData(
                5000, "Shuchi", "mylearn2@gmail.com", 300
        )));
        accounts.put(4444, new PremiumAccount(new AccountData(
                4444, "Beauty", "zip42@gmail.com", 444
        )));
        accounts.put(1234, new PremiumAccount(new AccountData(
                1234, "Blue", "bluegroup@gmail.com", 500
        )));
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + id + "\nTry account 1000 or 2000");
        }
    }

    public ActionResult<AccountData> deposit(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, int amount) {

        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            Boolean resizable=true;
            Alert alert = new Alert (Alert.AlertType.ERROR,"Error" ,new ButtonType("OK"), new ButtonType("EXIT"));

            alert.setTitle("Overdraft! Account");
            alert.setHeaderText("Withdraw failed: ");
            alert.isResizable();
            alert.setContentText("Withdraw Failed: " + amount + ". Account has: " + account.getBalance());
            alert.showAndWait();




            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }
}
