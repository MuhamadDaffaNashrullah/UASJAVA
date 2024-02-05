
package org.valo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.valo.database.Database;


public class LoginViewController implements Initializable{
     @FXML
    private ImageView imgLogo;

    @FXML
    private Button loginbtn;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Alert alert;
    private String username;
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image img = new Image("/org/valo/images/jett.gif");
        imgLogo.setImage(img);
    }
     @FXML
    private void login(ActionEvent event) { 
        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect Username/Password");
            alert.showAndWait();
        } else {
            
            String selctData = "SELECT username, password FROM admin WHERE username = ? and password = ?";
            
            connect = Database.connectdb();
            
            try {
                
                prepare = connect.prepareStatement(selctData);
                prepare.setString(1, txtUsername.getText());
                prepare.setString(2, txtPassword.getText());
                
                result = prepare.executeQuery();

                if (result.next()) {
                    
                    data.username = txtUsername.getText();

                    username = txtUsername.getText();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();
                    
                    Parent root = FXMLLoader.load(getClass().getResource("/org/valo/view/MainView.fxml"));
                    
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    
                    loginbtn.getScene().getWindow().hide();
                    
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password");
                    alert.showAndWait();
                } 
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    }
   
     
        
}


        
        
 
    
 