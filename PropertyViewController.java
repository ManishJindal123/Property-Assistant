/**
 * Sample Skeleton for 'PropertyView.fxml' Controller Class
 */

package availProperty;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class PropertyViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPropertyID"
    private TextField txtPropertyID; // Value injected by FXMLLoader

    @FXML // fx:id="txtContact"
    private TextField txtContact; // Value injected by FXMLLoader

    @FXML // fx:id="comboType"
    private ComboBox<String> comboType; // Value injected by FXMLLoader

    @FXML // fx:id="txtSize"
    private TextField txtSize; // Value injected by FXMLLoader

    @FXML // fx:id="txtWidht"
    private TextField txtWidht; // Value injected by FXMLLoader

    @FXML // fx:id="txtRSide"
    private TextField txtRSide; // Value injected by FXMLLoader

    @FXML // fx:id="txtLSide"
    private TextField txtLSide; // Value injected by FXMLLoader

    @FXML // fx:id="txtDepth"
    private TextField txtDepth; // Value injected by FXMLLoader

    @FXML // fx:id="txtLocation"
    private TextField txtLocation; // Value injected by FXMLLoader

    @FXML // fx:id="txtCity"
    private TextField txtCity; // Value injected by FXMLLoader

    @FXML // fx:id="txtAmount"
    private TextField txtAmount; // Value injected by FXMLLoader

    @FXML // fx:id="comboDirection"
    private ComboBox<String> comboDirection; // Value injected by FXMLLoader

    @FXML // fx:id="txtOtherDetail"
    private TextField txtOtherDetail; // Value injected by FXMLLoader
    
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void doDelete(ActionEvent event) {

    	try{
        	pst=con.prepareStatement("delete from properties where pID=?" );
        	pst.setInt(1, Integer.parseInt(txtPropertyID.getText()));
        	
        	int count=pst.executeUpdate();
        	if(count==0)
        		System.out.println("Invalid Id");
        	else
        		System.out.println(count+" Records Deleted");
        	
        	}
        	catch(Exception exp){
        		exp.printStackTrace();
        	}
    	
    }

    @FXML
    void doFetch(ActionEvent event) {
    	
    	try{
    		pst=con.prepareStatement("select * from properties where pID=?");
    		pst.setInt(1, Integer.parseInt(txtPropertyID.getText()));
    	ResultSet table=pst.executeQuery();
    	while(table.next())
    	{
    		
    		int contact=table.getInt("contactNo");
    		String type=table.getString("ptype");
    		float size=table.getFloat("size");
    		float widht=table.getFloat("widht");
    		float depth=table.getFloat("depth");
    		float lside=table.getFloat("lside");
    		float rside=table.getFloat("rside");
    		String location=table.getString("location");
    		String city=table.getString("city");
    		float amount=table.getFloat("amount");
    		String direction=table.getString("direction"); //error
    		String description=table.getString("description");
    		
    		
    		
    		txtContact.setText(String.valueOf(contact));
    		comboType.getSelectionModel().select(type);
    		txtSize.setText(String.valueOf(size));
    		txtWidht.setText(String.valueOf(widht));
    		txtDepth.setText(String.valueOf(depth));
    		txtLSide.setText(String.valueOf(lside));
    		txtRSide.setText(String.valueOf(rside));
    		txtLocation.setText(location);
    		txtCity.setText(city);
    		txtAmount.setText(String.valueOf(amount));
    		comboDirection.getSelectionModel().select(direction);
    		txtOtherDetail.setText(description);
    		
    		
    		
    		
    		
    		
    		
    				
    				
    				
    		  		
    		
    	}
    	}
    	catch(Exception exp)
    	{ 
    		exp.printStackTrace();
    	}

    }

    @FXML
    void doSave(ActionEvent event) {
    	
    	try {
			pst=con.prepareStatement("insert into properties(contactNo,ptype,size,widht,depth,lside,rside,location,city,amount,direction,description,doa) values(?,?,?,?,?,?,?,?,?,?,?,?,current_date())");
			
			//pst.setInt(1, Integer.parseInt(txtPropertyID.getText()));
			pst.setString(1, txtContact.getText());
			pst.setString(2,comboType.getEditor().getText());
			pst.setFloat(3,Float.parseFloat(txtSize.getText()));
			pst.setFloat(4,Float.parseFloat(txtWidht.getText()));
			pst.setFloat(5,Float.parseFloat(txtDepth.getText()));
			pst.setFloat(6,Float.parseFloat(txtLSide.getText()));
			pst.setFloat(7,Float.parseFloat(txtRSide.getText()));
			pst.setString(8,txtLocation.getText());
			pst.setString(9,txtCity.getText());
			pst.setFloat(10,Float.parseFloat(txtAmount.getText()));
			pst.setString(11,comboDirection.getEditor().getText());
			pst.setString(12,txtOtherDetail.getText());
			
			
			
			
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

    	String Directions[]={"Select","North", "North East", "East", "South East", "South", "South West", "West","North West"};
        comboDirection.getItems().addAll(Arrays.asList(Directions));
        
        
        String Type[]= {"Select","House","Shop","Plot","Showroom","Residential","Commercial","Semi-Commercial"};
        comboType.getItems().addAll(Arrays.asList(Type));

    }
}
