/**
 * Sample Skeleton for 'registrationStatus.fxml' Controller Class
 */

package registrationStatus;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import availProperty.DataBaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class registrationStatusController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="radPending"
    private RadioButton radPending; // Value injected by FXMLLoader

    @FXML // fx:id="status"
    private ToggleGroup status; // Value injected by FXMLLoader

    @FXML // fx:id="radDone"
    private RadioButton radDone; // Value injected by FXMLLoader

    @FXML // fx:id="radAll"
    private RadioButton radAll; // Value injected by FXMLLoader

    @FXML // fx:id="datePickerRegister"
    private DatePicker datePickerRegister; // Value injected by FXMLLoader

    @FXML // fx:id="tblView"
    private TableView<registrationStatusBean> tblView; // Value injected by FXMLLoader

    
    Connection con;
    PreparedStatement pst;
    ObservableList<registrationStatusBean> list;
    
    @FXML
    void doShow(ActionEvent event) {
    	
    	 if(radAll.isSelected())
 	    {
             showAll();
 	    }
             else
 		if(radDone.isSelected())
 		{
 			showDone();
 		}
 			else
 			{	showPending();
 }

    }
    
    void showAll()
    {
    	list=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("Select * from matured");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String pid=records.getString("pid");
				String buyerID=records.getString("buyerID");
				String sellerID=records.getString("sellerID");
				String totalAmount=records.getString("totalAmount");
				String advAmount=records.getString("advAmount");
				String balence=records.getString("balence");
				LocalDate doreg=records.getDate("doreg").toLocalDate();
				registrationStatusBean array=new registrationStatusBean(pid,buyerID,sellerID,totalAmount,advAmount,balence,doreg);
				list.addAll(array);	
			}
			tblView.setItems(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
    
    void showPending()
	{
		list=FXCollections.observableArrayList();
			try {
				pst=con.prepareStatement("select * from matured where doreg>CURRENT_DATE");
				ResultSet records=pst.executeQuery();
				while(records.next())
				{
					String pid=records.getString("pid");
					String buyerID=records.getString("buyerID");
					String sellerID=records.getString("sellerID");
					String totalAmount=records.getString("totalAmount");
					String advAmount=records.getString("advAmount");
					String balence=records.getString("balence");
					LocalDate doreg=records.getDate("doreg").toLocalDate();
					registrationStatusBean array=new registrationStatusBean(pid,buyerID,sellerID,totalAmount,advAmount,balence,doreg);
					list.addAll(array);	
				}
				tblView.setItems(list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
    
    void showDone()
 	{list=FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("select * from matured where doreg>=CURRENT_DATE");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String pid=records.getString("pid");
				String buyerID=records.getString("buyerID");
				String sellerID=records.getString("sellerID");
				String totalAmount=records.getString("totalAmount");
				String advAmount=records.getString("advAmount");
				String balence=records.getString("balence");
				LocalDate doreg=records.getDate("doreg").toLocalDate();
				registrationStatusBean array=new registrationStatusBean(pid,buyerID,sellerID,totalAmount,advAmount,balence,doreg);
				list.addAll(array);	
			}
			tblView.setItems(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
    
    void addcoloumn()
    {
    	TableColumn<registrationStatusBean,String> pidcol=new TableColumn<registrationStatusBean, String>("P-ID");
		pidcol.setCellValueFactory(new PropertyValueFactory<registrationStatusBean, String>("pid"));//field name in bean
		pidcol.setMinWidth(15);
		
		TableColumn<registrationStatusBean,String> buyercol=new TableColumn<registrationStatusBean, String>("Buyer");
		buyercol.setCellValueFactory(new PropertyValueFactory<registrationStatusBean, String>("buyerID"));//field name in bean
		buyercol.setMinWidth(50);
		
		TableColumn<registrationStatusBean,String> sellercol=new TableColumn<registrationStatusBean,String>("Seller");
		sellercol.setCellValueFactory(new PropertyValueFactory<registrationStatusBean,String>("sellerID"));//field name in bean
		sellercol.setMinWidth(50);
		
		TableColumn<registrationStatusBean,String> tamtcol=new TableColumn<registrationStatusBean,String>("Total Amount");
		tamtcol.setCellValueFactory(new PropertyValueFactory<registrationStatusBean,String>("totalAmount"));//field name in bean
		tamtcol.setMinWidth(100);
		
		TableColumn<registrationStatusBean,String> advcol=new TableColumn<registrationStatusBean, String>("Advance Amount");
		advcol.setCellValueFactory(new PropertyValueFactory<registrationStatusBean, String>("advAmount"));//field name in bean
		advcol.setMinWidth(120);
		
		TableColumn<registrationStatusBean,String> balcol=new TableColumn<registrationStatusBean, String>("Balance Amount");
		balcol.setCellValueFactory(new PropertyValueFactory<registrationStatusBean, String>("balence"));//field name in bean
		balcol.setMinWidth(120);
	
		TableColumn<registrationStatusBean,LocalDate> dorcol=new TableColumn<registrationStatusBean,LocalDate>("Date of Registration");
		dorcol.setCellValueFactory(new PropertyValueFactory<registrationStatusBean,LocalDate>("doreg"));//field name in bean
	    dorcol.setMinWidth(200);
		
		tblView.getColumns().addAll(pidcol,buyercol,sellercol,tamtcol,advcol,balcol,dorcol);
    
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=DataBaseConnector.getConnection();
    	addcoloumn();
       
    }
}
