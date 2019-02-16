package rocks.zipcode.atm;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.control.*;


/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField AccountId = new TextField();
    private TextField  Name = new TextField();
    private TextField Email = new TextField();
    private TextField Balance = new TextField();
    private TextField Amount = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

   /* private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();

        Button btnSubmit = new Button("Set Account ID");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            cashMachine.exit();

            areaInfo.setText(cashMachine.toString());
        });

        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        vbox.getChildren().addAll(field, flowpane, areaInfo);
        return vbox;
    }

*/



    private Parent createContent1() {

        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();
        Button btnDeposit = new Button("Deposit");
        Button btnWithdraw = new Button("Withdraw");
        Button btnSubmit = new Button("Login with your Account ID");
        btnSubmit.setOnAction(e -> {
            try {
            System.out.println("accountid1"+AccountId.getText()+"suffix");
            if (AccountId.getText().equalsIgnoreCase("")){

                Boolean resizable=true;
                Alert alert = new Alert (Alert.AlertType.ERROR,"Error" ,new ButtonType("OK"), new ButtonType("EXIT"));

                alert.setTitle("Login Failed");
                alert.setHeaderText("Login Failed :");
                alert.isResizable();
                alert.setContentText("Please enter your Account Id");
                alert.showAndWait();



            }

                int id = Integer.parseInt(AccountId.getText());
                cashMachine.login(id);




            if(cashMachine.getAccountData()!=null) {
                btnDeposit.setDisable(false);
                btnWithdraw.setDisable(false);
            }
            else{
                btnDeposit.setDisable(true);
                btnWithdraw.setDisable(true);
                Name.setText("");
                Email.setText("");
                Balance.setText("");

            }

            areaInfo.setText(cashMachine.toString());
            System.out.println("accountid"+AccountId.getText()+"suffix");


            Name.setText(cashMachine.getAccountData().getName());
            Email.setText(cashMachine.getAccountData().getEmail());
            Balance.setText(String.valueOf(cashMachine.getAccountData().getBalance()));
            //cashMachine.exit();
            } catch (Exception e1) {
                //e1.printStackTrace();
            }

        });


        btnDeposit.setDisable(true);
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(Amount.getText());

                cashMachine.deposit(amount);
            Balance.setText(String.valueOf(cashMachine.getAccountData().getBalance()));

                areaInfo.setText(cashMachine.toString());




        });


        btnWithdraw.setDisable(true);
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(Amount.getText());
            cashMachine.withdraw(amount);


            areaInfo.setText(cashMachine.toString());
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            cashMachine.exit();
            System.exit(0);

            //areaInfo.setText(cashMachine.toString());
        });

        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);


        HBox hbx1= new HBox();
        Text nametext = new Text();
        Text emailtext = new Text();

        nametext.setText("Name : ");
        emailtext.setText("Email : ");
        hbx1.setMargin(nametext, new Insets(20, 20, 20, 20));
        hbx1.setMargin(emailtext, new Insets(20, 20, 20, 20));
        hbx1.setMargin(Name, new Insets(20, 20, 20, 20));
        hbx1.setMargin(Email, new Insets(20, 20, 20, 20));
        ;
        hbx1.getChildren().addAll(nametext,Name, emailtext,Email);



        HBox hbx2 = new HBox();

        Text amounttext = new Text();
        Text balancetext = new Text();
        amounttext.setText("Amount : ");
        balancetext.setText("Balance : ");

        hbx2.setMargin(amounttext, new Insets(20, 20, 20, 20));
        hbx2.setMargin(balancetext, new Insets(20, 20, 20, 20));
        hbx2.setMargin(Amount, new Insets(20, 20, 20, 0));
        hbx2.setMargin(Balance, new Insets(20, 20, 20, 0));
        hbx2.getChildren().addAll(amounttext,Amount,balancetext,Balance);


        Text Idtext = new Text();
        Idtext.setText("Enter your account Id : ");
        vbox.getChildren().addAll(Idtext,AccountId, hbx1,hbx2,flowpane, areaInfo);
        return vbox;
    }



    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent1()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
