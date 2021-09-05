/**
 * Sample Skeleton for 'dealsMaturedView.fxml' Controller Class
 */

package dealsMatured;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import availProperty.DataBaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class dealsMaturedViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboBuyerID"
    private ComboBox<String> comboBuyerID; // Value injected by FXMLLoader

    @FXML // fx:id="comboSellerID"
    private ComboBox<String> comboSellerID; // Value injected by FXMLLoader

    @FXML // fx:id="txtTotalAmount"
    private TextField txtTotalAmount; // Value injected by FXMLLoader

    @FXML // fx:id="txtAdvanceAmount"
    private TextField txtAdvanceAmount; // Value injected by FXMLLoader

    @FXML // fx:id="txtBalAmount"
    private TextField txtBalAmount; // Value injected by FXMLLoader

    @FXML // fx:id="dateReg"
    private DatePicker dateReg; // Value injected by FXMLLoader

    @FXML // fx:id="doSave"
    private Button doSave; // Value injected by FXMLLoader

    @FXML // fx:id="comboPropertyID"
    private ComboBox<String> comboPropertyID; // Value injected by FXMLLoader
    
    Connection con;
    PreparedStatement pst;
    
    @FXML
    void doBalence(KeyEvent event) {
    	int  totalAmount=Integer.parseInt(txtTotalAmount.getText());
    	int  advAmount=Integer.parseInt(txtAdvanceAmount.getText());
    	int balAmount=totalAmount-advAmount;
    	
    	
    	txtBalAmount.setText(String.valueOf(balAmount));

    }

    void doFillBuyerID()
    {
    	ArrayList<String> rec=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("select  rid from demands");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String crec=records.getString("rid");
				rec.add(crec);
			}
			comboBuyerID.getItems().addAll(rec);
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    void docomboProperty(javafx.event.ActionEvent event) {
    	try {
     		pst=con.prepareStatement("Select  amount from properties where pID=?");
     		pst.setString(1,comboPropertyID.getSelectionModel().getSelectedItem());
     		ResultSet records=pst.executeQuery();
     		while(records.next())
     		{
     			String pamt=records.getString("amount");
     			txtTotalAmount.setText(pamt);
     		}
     	} catch (SQLException e) {
     		// TODO Auto-generated catch block
     		e.printStackTrace();
     	}
    	

    }
    
    @FXML
    void docomboBuyer(ActionEvent event) {
    	comboBuyerID.getSelectionModel().getSelectedItem();

    }

    

    @FXML
    void docomboSeller(ActionEvent event) {

    	comboSellerID.getSelectionModel().getSelectedItem();
    	
    }

    
    void doFillSellerID()
    {
    	
    	ArrayList<String> rec=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("select  pID from properties");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String crec=records.getString("pID");
				rec.add(crec);
			}
			comboSellerID.getItems().addAll(rec);
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    void doFillPropertID()
    {
    	ArrayList<String> rec=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("select  pID from properties");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String crec=records.getString("pID");
				rec.add(crec);
			}
			comboPropertyID.getItems().addAll(rec);
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void doSave(ActionEvent event) {
    	
    	try {
			pst=con.prepareStatement("insert into matured values(?,?,?,?,?,?,?)");
			pst.setInt(1,Integer.parseInt(comboBuyerID.getSelectionModel().getSelectedItem()));
			pst.setInt(2,Integer.parseInt(comboSellerID.getSelectionModel().getSelectedItem()));
			pst.setInt(3,Integer.parseInt(comboPropertyID.getSelectionModel().getSelectedItem()));
			pst.setInt(4, Integer.parseInt(txtTotalAmount.getText()));
			pst.setInt(5, Integer.parseInt(txtAdvanceAmount.getText()));
			pst.setString(6, txtBalAmount.getText());
			pst.setDate(7, Date.valueOf(dateReg.getValue()));
			
			
			
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
    	
    	doFillBuyerID();
    	doFillSellerID();
    	doFillPropertID();

    }
}
