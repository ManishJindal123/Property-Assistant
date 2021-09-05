/**
 * Sample Skeleton for 'CustomerDemandView.fxml' Controller Class
 */

package CustomerDemand;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import availProperty.DataBaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class CustomerDemandViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboID"
    private ComboBox<String> comboID; // Value injected by FXMLLoader

    @FXML // fx:id="txtContact"
    private TextField txtContact; // Value injected by FXMLLoader

    @FXML // fx:id="comboType"
    private ComboBox<String> comboType; // Value injected by FXMLLoader

    @FXML // fx:id="txtBudget"
    private TextField txtBudget; // Value injected by FXMLLoader

    @FXML // fx:id="txtLocality"
    private TextField txtLocality; // Value injected by FXMLLoader

    @FXML // fx:id="txtsizeMin"
    private TextField txtsizeMin; // Value injected by FXMLLoader

    @FXML // fx:id="txtsizeMax"
    private TextField txtsizeMax; // Value injected by FXMLLoader

    @FXML // fx:id="txtOtherDemands"
    private TextArea txtOtherDemands; // Value injected by FXMLLoader

    @FXML // fx:id="txtRecordID"
    private TextField txtRecordID; // Value injected by FXMLLoader
    
    Connection con;
    PreparedStatement pst;


    @FXML
    void doDelete(ActionEvent event) {
    	try{
        	pst=con.prepareStatement("delete from properties where rid=?" );
        	pst.setString(1,comboID.getSelectionModel().getSelectedItem());
        	
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
    void doSave(ActionEvent event) {
    	try {
			
    		pst=con.prepareStatement("insert into demands(contact,ptype,budget,locality,minsize,maxsize,otherinfo) values(?,?,?,?,?,?,?)");
			pst.setString(1, txtContact.getText());
			pst.setString(2,comboType.getEditor().getText());
			pst.setInt(3, Integer.parseInt(txtBudget.getText()));
			pst.setString(4,txtLocality.getText());
			pst.setInt(5, Integer.parseInt(txtsizeMin.getText()));
			pst.setInt(6, Integer.parseInt(txtsizeMax.getText()));
			pst.setString(7,txtOtherDemands.getText());
			
				

			pst.executeUpdate();
			System.out.println("Saved ");
			
		pst=con.prepareStatement("select  rid from demands");
			
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String rid=records.getString("rid");
				txtRecordID.setText(rid);
			}
			
			
		}
    	catch (SQLException e)
    	{
			
			e.printStackTrace();
		}
    	
    	

    }
    
    
    void doFill()
    {
    	ArrayList<String> rec=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("select distinct rid from demands");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String crec=records.getString("rid");
				rec.add(crec);
			}
			comboID.getItems().addAll(rec);
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
    @FXML
    void doModify(ActionEvent event) {
    	
    	try {
			pst=con.prepareStatement("update demands set contact=?,ptype=?,budget=?,locality=?,minsize=?,maxsize=?,otherinfo=? where rid=?");
			
			pst.setString(1,txtContact.getText());
			pst.setString(2,comboType.getEditor().getText());
			pst.setString(3,txtBudget.getText());
			pst.setString(4,txtLocality.getText());
			pst.setString(5,txtsizeMin.getText());
			pst.setString(6,txtsizeMax.getText());
			pst.setString(7,txtOtherDemands.getText());
			pst.setString(8,txtRecordID.getText());
			int count=pst.executeUpdate();
			if(count==0)
        		System.out.println("Invalid Id");
        	else
        		System.out.println(count+" modified ");
        	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    

    @FXML
    void dofetch(ActionEvent event) {
    	try {
			pst=con.prepareStatement("select * from demands where rid=?");
			pst.setString(1,comboID.getSelectionModel().getSelectedItem());
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String contact=records.getString("contact");
				String ptype=records.getString("ptype");
				String budget=records.getString("budget");
				String place=records.getString("locality");
				String min=records.getString("minsize");
				String max=records.getString("maxsize");
				String info=records.getString("otherinfo");
				String record=records.getString("rid");
				txtContact.setText(contact);
				comboType.getEditor().setText(ptype);
				txtBudget.setText(budget);
				txtLocality.setText(place);
				txtsizeMin.setText(min);
				txtsizeMax.setText(max);
				txtOtherDemands.setText(info);
				txtRecordID.setText(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	
    	
    	con=DataBaseConnector.getConnection();
    	String Type[]= {"Select","House","Shop","Plot","Showroom","Residential","Commercial","Semi-Commercial"};
        comboType.getItems().addAll(Arrays.asList(Type));
        
        doFill();

    }
}
