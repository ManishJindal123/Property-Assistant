/**
 * Sample Skeleton for 'searchPropertyView.fxml' Controller Class
 */

package searchProperty;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import registrationStatus.registrationStatusBean;

public class searchPropertyViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboPropertyType"
    private ComboBox<String> comboPropertyType; // Value injected by FXMLLoader

    @FXML // fx:id="comboCity"
    private ComboBox<String> comboCity; // Value injected by FXMLLoader

    @FXML // fx:id="comboArea"
    private ComboBox<String> comboArea; // Value injected by FXMLLoader

    @FXML // fx:id="txtMinSize"
    private TextField txtMinSize; // Value injected by FXMLLoader

    @FXML // fx:id="txtMaxSize"
    private TextField txtMaxSize; // Value injected by FXMLLoader

    @FXML // fx:id="txtMinBudget"
    private TextField txtMinBudget; // Value injected by FXMLLoader

    @FXML // fx:id="txtMaxBudget"
    private TextField txtMaxBudget; // Value injected by FXMLLoader

    @FXML // fx:id="tblView"
    private TableView<searchPropertyBean> tblView; // Value injected by FXMLLoader
    
    Connection con;
    PreparedStatement pst;
    ObservableList<searchPropertyBean> list;

    @FXML
    void doComboArea(ActionEvent event) {
    	comboArea.getSelectionModel().getSelectedItem();

    }

    @FXML
    void doComboCity(ActionEvent event) {
    	comboCity.getSelectionModel().getSelectedItem();

    }

    @FXML
    void doComboPropertyType(ActionEvent event) {
    	comboPropertyType.getSelectionModel().getSelectedItem();

    }

    @FXML
    void doSearch(ActionEvent event) {
    	
        {
        	list=FXCollections.observableArrayList();
        	try {
    			pst=con.prepareStatement("select * from properties where ptype=? and city=? and location=? and size>=? and amount>=?");
    			pst.setString(1,comboPropertyType.getSelectionModel().getSelectedItem());
    			pst.setString(2,comboCity.getSelectionModel().getSelectedItem());
    			pst.setString(3,comboArea.getSelectionModel().getSelectedItem());
    			pst.setString(4,txtMaxSize.getText());
    			pst.setString(5,txtMaxBudget.getText());
    			ResultSet records=pst.executeQuery();
    			while(records.next())
    			{
    				int contact=records.getInt("contactNo");
    				String ptype=records.getString("ptype");
    				float size=records.getFloat("size");
    				float widht=records.getFloat("widht");
    				float depth=records.getFloat("depth");
    				float lside=records.getFloat("lside");
    				float rside=records.getFloat("rside");
    				String loc=records.getString("location");
    				String city=records.getString("city");
    				float amt=records.getFloat("amount");
    			    String direction=records.getString("direction");
    				LocalDate doa=records.getDate("doa").toLocalDate();
    				searchPropertyBean array=new searchPropertyBean(contact, ptype, size, widht, depth, lside, rside, loc, city, amt, direction, doa);
    			list.addAll(array);
    			}
    			tblView.setItems(list);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
        }

    }
    void fillPropertyType()
    {
    	ArrayList<String> Propertytype=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("Select  distinct ptype from properties");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String ctype=records.getString("ptype");
				Propertytype.add(ctype);
			}
			comboPropertyType.getItems().addAll(Propertytype);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    void fillCity()
    {
    	ArrayList<String> City=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("Select distinct city from properties");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String areas=records.getString("city");
				City.add(areas);
			}
			comboCity.getItems().addAll(City);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    void fillArea()
    {
    	ArrayList<String> area=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("Select distinct location from properties");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String are=records.getString("location");
				area.add(are);
			}
			comboArea.getItems().addAll(area);
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    void addcoloumn()
    {
    	    TableColumn<searchPropertyBean,Integer> contactcol=new TableColumn<searchPropertyBean, Integer>("Contact");
    	    contactcol.setCellValueFactory(new PropertyValueFactory<searchPropertyBean, Integer>("contactNo"));//field name in bean
    	    contactcol.setMinWidth(50);
 		
 		TableColumn<searchPropertyBean,String> ptypecol=new TableColumn<searchPropertyBean, String>("Property-type");
 		ptypecol.setCellValueFactory(new PropertyValueFactory<searchPropertyBean, String>("ptype"));//field name in bean
 		ptypecol.setMinWidth(150);
 		
 		TableColumn<searchPropertyBean,Float> sizecol=new TableColumn<searchPropertyBean,Float>("Size");
 		sizecol.setCellValueFactory(new PropertyValueFactory<searchPropertyBean,Float>("size"));//field name in bean
 		sizecol.setMinWidth(30);
 		
 		TableColumn<searchPropertyBean,Float> widthcol=new TableColumn<searchPropertyBean,Float>("Widht");
 		widthcol.setCellValueFactory(new PropertyValueFactory<searchPropertyBean,Float>("widht"));//field name in bean
 		widthcol.setMinWidth(30);
 		
 		TableColumn<searchPropertyBean,Float> depthcol=new TableColumn<searchPropertyBean, Float>("Depth");
 		depthcol.setCellValueFactory(new PropertyValueFactory<searchPropertyBean, Float>("depth"));//field name in bean
 		depthcol.setMinWidth(30);
 		
 		TableColumn<searchPropertyBean,Float> lsidecol=new TableColumn<searchPropertyBean,Float>("L-side");
 		lsidecol.setCellValueFactory(new PropertyValueFactory<searchPropertyBean, Float>("lside"));//field name in bean
 		lsidecol.setMinWidth(30);
 		
 		TableColumn<searchPropertyBean,Float> rsidecol=new TableColumn<searchPropertyBean, Float>("R-side");
 		rsidecol.setCellValueFactory(new PropertyValueFactory<searchPropertyBean, Float>("rside"));//field name in bean
 		rsidecol.setMinWidth(30);
 		
 		TableColumn<searchPropertyBean,String> locationcol=new TableColumn<searchPropertyBean, String>("Location");
 		locationcol.setCellValueFactory(new PropertyValueFactory<searchPropertyBean, String>("location"));//field name in bean
 		locationcol.setMinWidth(300);
 		
 		TableColumn<searchPropertyBean,String> citycol=new TableColumn<searchPropertyBean, String>("City");
 		citycol.setCellValueFactory(new PropertyValueFactory<searchPropertyBean, String>("city"));//field name in bean
 		citycol.setMinWidth(50);
 		
 		TableColumn<searchPropertyBean,Float> amountcol=new TableColumn<searchPropertyBean, Float>("Amount");
 		amountcol.setCellValueFactory(new PropertyValueFactory<searchPropertyBean, Float>("amount"));//field name in bean
 		amountcol.setMinWidth(50);
 		
 		TableColumn<searchPropertyBean,String> directioncol=new TableColumn<searchPropertyBean, String>("Direction");
 		directioncol.setCellValueFactory(new PropertyValueFactory<searchPropertyBean, String>("direction"));//field name in bean
 		directioncol.setMinWidth(30);
 		
 		TableColumn<searchPropertyBean,LocalDate> doacol=new TableColumn<searchPropertyBean,LocalDate>("Date of Registration");
 		doacol.setCellValueFactory(new PropertyValueFactory<searchPropertyBean,LocalDate>("doa"));//field name in bean
 	    doacol.setMinWidth(130);
 		
 		tblView.getColumns().addAll(contactcol,ptypecol,sizecol,widthcol,depthcol,lsidecol,rsidecol,locationcol,citycol,amountcol,directioncol,doacol);
    } 

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=DataBaseConnector.getConnection();
    	
    	fillPropertyType();
    	fillCity();
    	fillArea();
    	addcoloumn();
    	
    	

    }
}
