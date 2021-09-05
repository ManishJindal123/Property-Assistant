/**
 * Sample Skeleton for 'CustomerRegisterView.fxml' Controller Class
 */

package customer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CustomerRegisterViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="lblPath"
    private Label lblPath; // Value injected by FXMLLoader


    @FXML // fx:id="txtName"
    private TextField txtName; // Value injected by FXMLLoader

    @FXML // fx:id="txtAddress"
    private TextField txtAddress; // Value injected by FXMLLoader

    @FXML // fx:id="txtCity"
    private TextField txtCity; // Value injected by FXMLLoader

    @FXML // fx:id="txtContact"
    private TextField txtContact; // Value injected by FXMLLoader

    @FXML // fx:id="imagePrev"
    private ImageView imagePrev; // Value injected by FXMLLoader

    @FXML // fx:id="radBuyer"
    private RadioButton radBuyer; // Value injected by FXMLLoader

    @FXML // fx:id="select"
    private ToggleGroup select; // Value injected by FXMLLoader

    @FXML // fx:id="radSeller"
    private RadioButton radSeller; // Value injected by FXMLLoader

    @FXML // fx:id="radBoth"
    private RadioButton radBoth; // Value injected by FXMLLoader

    @FXML // fx:id="comboProof"
    private ComboBox<String> comboProof; // Value injected by FXMLLoader

    @FXML // fx:id="txtProofNumber"
    private TextField txtProofNumber; // Value injected by FXMLLoader
    
    
    @FXML // fx:id="btnNew"
    private Button btnNew; // Value injected by FXMLLoader
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void doBrowse(ActionEvent event) {
    	
    	FileChooser chooser=new FileChooser();
    	
    	
    	
    	chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("*.*", "*.*")
                
            );
    	File file=chooser.showOpenDialog(null);
    	String FilePath = file.getAbsolutePath();
    	lblPath.setText(FilePath);//set pic path on lable
    	
    	try {
    		imagePrev.setImage(new Image(new FileInputStream(file)));
    	} 
    	catch (FileNotFoundException e) 
    	{	
    		e.printStackTrace();
    		}
    }

    

    @FXML
    void doCancelRegister(ActionEvent event) {
    	System.exit(0);

    }

    @FXML
    void doFetch(ActionEvent event) {

    	try{
    		pst=con.prepareStatement("select * from customer_register  where mobile=?");
    		pst.setInt(1, Integer.parseInt(txtContact.getText()));
    	ResultSet table=pst.executeQuery();
    	while(table.next())
    	{
    		
    		int contact=table.getInt("mobile");
    		String name=table.getString("cname");
    		String address=table.getString("address");
    		String city=table.getString("city");
    		String path=table.getString("picPath");
    		String utype=table.getString("utype");   //ERRORss
    		String idproof=table.getString("idproof");
    		String proofNumber=table.getString("proofNumber");
    		
    		
    		
    		if(utype.equals("Both"))
    			
    			radBoth.setSelected(true);
    		
    		else if(utype.equals("Buyer"))
    			radBuyer.setSelected(true);
    		else
    			radSeller.setSelected(true);
    		
    		

			comboProof.getSelectionModel().select(idproof);
    		
			
			File file1=new File(path);
			try {
				imagePrev.setImage(new Image(new FileInputStream(file1)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    		txtAddress.setText(address);
    		txtName.setText(name);
    		txtCity.setText(city);
    		txtProofNumber.setText(proofNumber);
    		lblPath.setText(path);
    		
    	}
    	}
    	catch(Exception exp)
    	{ 
    		exp.printStackTrace();
    	}

    	
    }

    @FXML
    void doModify(ActionEvent event) {
    	
    	try {
			pst=con.prepareStatement("update customer_register set address=?, city=? where mobile=?");
			
			pst.setString(1, txtAddress.getText());
			pst.setString(2,txtCity.getText());
			pst.setInt(3,Integer.parseInt(txtContact.getText()));
			int count=pst.executeUpdate();
			if(count==0)
				System.out.println("Invalid contact");
			else
				System.out.println(count+" Records Updated");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void doNew(ActionEvent event) {
    	try{
			  FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/customer/CustomerRegisterView.fxml"));
	        	Parent root=(Parent)fxmlloader.load();
	        	
	        	Stage stage=new Stage();
	        	stage.setScene(new Scene(root));
	        	stage.show();

	        	Scene scene1 = (Scene)btnNew.getScene();
	            scene1.getWindow().hide();
	            
	            

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		 


    }

    @FXML
    void doRegister(ActionEvent event) {
    	
    	try {
			pst=con.prepareStatement("insert into customer_register values(?,?,?,?,?,?,?,?,current_date())");
			pst.setString(1, txtName.getText());
			pst.setString(2, txtAddress.getText());
			pst.setString(3, txtCity.getText());
			pst.setString(4, txtContact.getText());
			pst.setString(5,lblPath.getText());
			if(radBoth.isSelected())
			{
			pst.setString(6,radBoth.getText());
			}
			else
				if(radBuyer.isSelected())
				{
					pst.setString(6,radBuyer.getText());
				}
				else
					pst.setString(6,radSeller.getText());
			pst.setString(7,comboProof.getEditor().getText());
			pst.setString(8,txtProofNumber.getText());
			pst.executeUpdate();
			System.out.println("Saved ");
			
		} catch (SQLException e)
    	{
			
			e.printStackTrace();
		}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=DataBaseConnector.getConnection();
    	
    	
    	comboProof.getItems().add("Select");
        comboProof.getItems().add("Aadhar Card");
        comboProof.getItems().add("Pan Card");
        comboProof.getItems().add("Driving Licence");
        comboProof.getItems().add("Voter Card");
        comboProof.getItems().add("Passport");

    }
}

    	
        


