/**
 * Sample Skeleton for 'loginForm.fxml' Controller Class
 */

package login;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import availProperty.DataBaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginFormController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtusername"
    private TextField txtusername; // Value injected by FXMLLoader

    @FXML // fx:id="txtpass"
    private PasswordField txtpass; // Value injected by FXMLLoader

    @FXML // fx:id="btnSignIN"
    private Button btnSignIN; // Value injected by FXMLLoader

    @FXML // fx:id="btnSignUP"
    private Button btnSignUP; // Value injected by FXMLLoader

    
    Connection con;
    PreparedStatement pst;
    
    
    void ShowWarning(String msg)
    {
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("Warning Message");
    	alert.setHeaderText("ThankYou For Using Our Services");
    	alert.setContentText(msg);
    	alert.showAndWait();
    }
    @FXML
    void doSignIN(ActionEvent event) throws SQLException {
    	
    	String user = txtusername.getText();
        String pass = txtpass.getText();
        
        pst = con.prepareStatement("select * from signup");
        ResultSet record = pst.executeQuery();
        
        if(record.next())
        {
     	   String checkuser = record.getString("name");
     	   String checkpass = record.getString("pass");
     	   
     	   if(user.equals(checkuser) && pass.equals(checkpass))
     	   {
     		   try{
     			  FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/dashboard/dashboardView.fxml"));
     	        	Parent root=(Parent)fxmlloader.load();
     	        	
     	        	Stage stage=new Stage();
     	        	stage.setScene(new Scene(root));
     	        	stage.show();

     	            Scene scene1 = (Scene)btnSignIN.getScene();
     	            scene1.getWindow().hide();

     			}
     			catch(Exception e)
     			{
     				e.printStackTrace();
     			}
     	   }
     	   else 
     	   {
     		  Alert alert = new Alert(AlertType.WARNING);
   	    	alert.setTitle("Warning Message");
   	    	alert.setHeaderText("ThankYou For Using Our Services");
   	    	alert.setContentText("Invalid Username or Password");
   	    	alert.showAndWait();
     	   }
        }

    }

    @FXML
    void doSignUP(ActionEvent event) {
    	try{
			  FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/signup/signupForm.fxml"));
	        	Parent root=(Parent)fxmlloader.load();
	        	
	        	Stage stage=new Stage();
	        	stage.setScene(new Scene(root));
	        	stage.show();
	        	 Scene scene1 = (Scene)btnSignUP.getScene();
	            scene1.getWindow().hide();

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
  
}

    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=DataBaseConnector.getConnection();


    }
}
