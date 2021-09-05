/**
 * Sample Skeleton for 'customerView.fxml' Controller Class
 */

package customerView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.ResourceBundle;


import customer.DataBaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class customerViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboCustomer"
    private ComboBox<String> comboCustomer; // Value injected by FXMLLoader

    @FXML // fx:id="comboCity"
    private ComboBox<String> comboCity; // Value injected by FXMLLoader

    @FXML // fx:id="tblview"
    private TableView<customerBean> tblview; // Value injected by FXMLLoader
    
    Connection con;
    PreparedStatement pst;
    ObservableList<customerBean> list;

    @FXML
    void doCity(ActionEvent event) {
    	list = FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("Select * from customer_register where city=?");
			pst.setString(1,comboCity.getSelectionModel().getSelectedItem());
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String cname=records.getString("cname");
				String caddress=records.getString("address");
				String ccity=records.getString("city");
				String cmob=records.getString("mobile");
				String cuType=records.getString("uType");
				String cid=records.getString("idproof");
				String cproof=records.getString("proofNumber");
				LocalDate cdor=records.getDate("dor").toLocalDate();
				customerBean array =new customerBean(cname,caddress,ccity,cmob,cuType,cid,cproof,cdor);
				list.add(array);
			}
			tblview.setItems(list);
			
			
		} 
    	catch (SQLException e)
    	{
    	}
    }

    
    
    
    
			
			
		

    @FXML
    void doComboCity(ActionEvent event) {
    	comboCity.getSelectionModel().getSelectedItem();
    }

    @FXML
    void doComboCustomer(ActionEvent event) {
    	 comboCustomer.getSelectionModel().getSelectedItem();
    }

    

    @FXML
    void doCustomer(ActionEvent event) {
    	list=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("Select * from customer_register where uType=?");
			pst.setString(1,comboCustomer.getSelectionModel().getSelectedItem());
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String cname=records.getString("cname");
				String caddress=records.getString("address");
				String ccity=records.getString("city");
				String cmob=records.getString("mobile");
				String cuType=records.getString("uType");
				String cid=records.getString("idproof");
				String cproof=records.getString("proofNumber");
				LocalDate cdor=records.getDate("dor").toLocalDate();
				customerBean array =new customerBean(cname,caddress,ccity,cmob,cuType,cid,cproof,cdor);
				list.add(array);
			}
			tblview.setItems(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    
    
    
    

    @FXML
    void doShowAll(ActionEvent event) {
    	doShowAll();
    

    }
    
    void doShowAll()
    {
    	list=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("Select * from customer_register");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String cname=records.getString("cname");
				String caddress=records.getString("address");
				String ccity=records.getString("city");
				String cmob=records.getString("mobile");
				String cuType=records.getString("uType");
				String cid=records.getString("idproof");
				String cproof=records.getString("proofNumber");
				LocalDate cdor=records.getDate("dor").toLocalDate();
				customerBean array =new customerBean(cname,caddress,ccity,cmob,cuType,cid,cproof,cdor);
				list.add(array);
			}
			tblview.setItems(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    
    
    void fillCity()
    {
    	ArrayList<String> carea=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("Select distinct city from customer_register");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String areas=records.getString("city");
				carea.add(areas);
			}
			comboCity.getItems().addAll(carea);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    void fillcustomer()
    {
    	ArrayList<String> ccustomer=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("Select  distinct uType from customer_register");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String ctype=records.getString("uType");
				ccustomer.add(ctype);
			}
			comboCustomer.getItems().addAll(ccustomer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @SuppressWarnings("unchecked")
	void addcoloumn()
    {
    	TableColumn<customerBean,String> namecol=new TableColumn<customerBean, String>("Name");
		namecol.setCellValueFactory(new PropertyValueFactory<customerBean, String>("cname"));//field name in bean
		namecol.setMinWidth(50);
		
		TableColumn<customerBean,String> addresscol=new TableColumn<customerBean, String>("Address");
		addresscol.setCellValueFactory(new PropertyValueFactory<customerBean, String>("address"));//field name in bean
		addresscol.setMinWidth(100);
		
		TableColumn<customerBean,String> citycol=new TableColumn<customerBean,String>("City");
		citycol.setCellValueFactory(new PropertyValueFactory<customerBean,String>("city"));//field name in bean
		citycol.setMinWidth(50);
		
		TableColumn<customerBean,String> mobilecol=new TableColumn<customerBean,String>("Mobile No.");
		mobilecol.setCellValueFactory(new PropertyValueFactory<customerBean,String>("mobile"));//field name in bean
		mobilecol.setMinWidth(80);
		
		TableColumn<customerBean,String> customercol=new TableColumn<customerBean, String>("Customer Type");
		customercol.setCellValueFactory(new PropertyValueFactory<customerBean, String>("utype"));//field name in bean
		customercol.setMinWidth(100);
		
		TableColumn<customerBean,String> idproofcol=new TableColumn<customerBean, String>("ID Proof");
		idproofcol.setCellValueFactory(new PropertyValueFactory<customerBean, String>("idproof"));//field name in bean
		idproofcol.setMinWidth(80);
		
		TableColumn<customerBean,String> proofcol=new TableColumn<customerBean, String>("Proof No.");
		proofcol.setCellValueFactory(new PropertyValueFactory<customerBean, String>("proofNumber"));//field name in bean
		proofcol.setMinWidth(100);
		
		TableColumn<customerBean,LocalDate> dorcol=new TableColumn<customerBean,LocalDate>("Date of Registration");
		dorcol.setCellValueFactory(new PropertyValueFactory<customerBean,LocalDate>("dor"));//field name in bean
	    dorcol.setMinWidth(50);
		
		tblview.getColumns().addAll(namecol,addresscol,citycol,mobilecol,customercol,idproofcol,proofcol,dorcol);
    }
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=DataBaseConnector.getConnection();
    	
    	fillcustomer();
        fillCity();
        addcoloumn();

    }
}
