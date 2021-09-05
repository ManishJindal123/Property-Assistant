/**
 * Sample Skeleton for 'signupForm.fxml' Controller Class
 */

package signup;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class signupFormController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtusername"
    private TextField txtusername; // Value injected by FXMLLoader

    @FXML // fx:id="dateDob"
    private DatePicker dateDob; // Value injected by FXMLLoader

    @FXML // fx:id="txtPhone"
    private TextField txtPhone; // Value injected by FXMLLoader

    @FXML // fx:id="btnSignUP"
    private Button btnSignUP; // Value injected by FXMLLoader

    @FXML // fx:id="txtpass"
    private PasswordField txtpass; // Value injected by FXMLLoader

    @FXML // fx:id="txtpass1"
    private PasswordField txtpass1; // Value injected by FXMLLoader
    
    Connection con;
    PreparedStatement pst;
    @FXML
    void doSignUP(ActionEvent event) {
    	
    	String user = txtusername.getText();
        String pass = txtpass.getText();
        String pass1 = txtpass1.getText();
        if(user.isEmpty() && pass.isEmpty())
        {
  	        btnSignUP.setText("Invalid");
        }
        else
        {
        try {
			pst = con.prepareStatement("insert into signup values(?,?,?,?)");
			pst.setString(1,txtusername.getText());
			if(user.isEmpty()==true)
			{
				Alert alert3 = new Alert(AlertType.WARNING);
    	    	alert3.setTitle("Warning Message");
    	    	alert3.setContentText("Please fill Username");
    	    	alert3.showAndWait();
    	    	
			}
			pst.setString(2,txtpass.getText());
            if(pass.equals(pass1)==true)
	    	{
	    		Alert alert2 = new Alert(AlertType.WARNING);
    	    	alert2.setTitle("Warning Message");
    	    	alert2.setContentText("Password Confirmed");
    	    	alert2.showAndWait();
    	    	
    	    	pst.setDate(3,Date.valueOf(dateDob.getValue()));
    	    	pst.setString(4, txtPhone.getText());
               
    	    	pst.executeUpdate();
    	    	try{
         			  FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/Login1/Login1_Form.fxml"));
         	        	Parent root=(Parent)fxmlloader.load();
         	        	
         	        	Stage  stage=new Stage();
         	        	stage.setScene(new Scene(root));
         	        	stage.show();

         	            Scene scene1 = (Scene)btnSignUP.getScene();
         	            scene1.getWindow().hide();

         			}
         			catch(Exception e)
         			{
         				e.printStackTrace();
         			}
    	    	Alert alert = new Alert(AlertType.WARNING);
    	    	alert.setTitle("Warning Message");
    	    	alert.setHeaderText("ThankYou For Using Our Services");
    	    	alert.setContentText("Signed Up Successfully");
    	    	alert.showAndWait();
    	    	
	    	}
	    	else
	    	{
	    		Alert alert1 = new Alert(AlertType.WARNING);
    	    	alert1.setTitle("Warning Message");
    	    	alert1.setContentText("Incorrect Password");
    	    	alert1.showAndWait();
	    	}

        }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	
    	
    	con=DataBaseConnector.getConnection();


    }
}
