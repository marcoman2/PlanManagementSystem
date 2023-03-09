/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planmanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author WINDOWS 10
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField si_username;

    @FXML
    private PasswordField si_password;

    @FXML
    private Button si_loginBtn;

    @FXML
    private Hyperlink si_createAccount;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private TextField su_username;

    @FXML
    private PasswordField su_password;

    @FXML
    private Button su_signupBtn;

    @FXML
    private Hyperlink su_alreadyhaveAccount;

    // LETS CREATE OUR DATABASE
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;

    public void loginAccount() {

        String selectData = "SELECT username, password FROM user WHERE username = '"
                + si_username.getText() + "' and password = '" + si_password.getText() + "'";

        connect = database.connectDB();

        try {

            if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(selectData);
                result = prepare.executeQuery();

                if (result.next()) {

                    data.username = si_username.getText();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

                    // TO HIDE THE LOGIN FORM
                    si_loginBtn.getScene().getWindow().hide();

                    // TO SHOW THE MAIN FORM
                    Parent root = FXMLLoader.load(getClass().getResource("planController.fxml"));

                    Stage stage = new Stage();
                    stage.setTitle("Plans Management System");

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password");
                    alert.showAndWait();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void registerAccount() {

        String insertData = "INSERT INTO user (username, password, date) VALUES(?,?,?)";

        connect = database.connectDB();

        try {

            if (su_username.getText().isEmpty() || su_password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                // CHECK THE USERNAME
                String checkUsername = "SELECT username FROM user WHERE username = '"
                        + su_username.getText() + "'";

                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(su_username.getText() + " was already taken");
                    alert.showAndWait();
                } else {

                    if (su_password.getText().length() < 8) {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid Password, alteast 8 characters needed");
                        alert.showAndWait();
                    } else {
                        prepare = connect.prepareStatement(insertData);
                        prepare.setString(1, su_username.getText());
                        prepare.setString(2, su_password.getText());

                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        prepare.setString(3, String.valueOf(sqlDate));

                        prepare.executeUpdate();

                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully create a new account!");
                        alert.showAndWait();

                        su_username.setText("");
                        su_password.setText("");

                        signup_form.setVisible(false);
                        login_form.setVisible(true);
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == si_createAccount) {
            signup_form.setVisible(true);
            login_form.setVisible(false);
        } else if (event.getSource() == su_alreadyhaveAccount) {
            signup_form.setVisible(false);
            login_form.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

// THATS IT FOR THIS VIDEO, THANKS FOR WATCHING : ) 
// PLEASE SUBSCRIBE OUR CHANNEL FOR MORE SYSTEM TUTORIALS  : ) 
// THANK YOU!

// ~MARCOMAN
