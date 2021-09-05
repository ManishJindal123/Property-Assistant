/**
 * Sample Skeleton for 'galleryPic.fxml' Controller Class
 */

package galleryPic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class galleryPicController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboPropertyID"
    private ComboBox<String> comboPropertyID; // Value injected by FXMLLoader
    @FXML // fx:id="img4"
    private ImageView img4; // Value injected by FXMLLoader

    @FXML // fx:id="img3"
    private ImageView img3; // Value injected by FXMLLoader

    @FXML // fx:id="img2"
    private ImageView img2; // Value injected by FXMLLoader

    @FXML // fx:id="img1"
    private ImageView img1; // Value injected by FXMLLoader
    
    @FXML
    private Button btnFech;
    
    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;
    
    String  FilePath1;
    String  FilePath2;
    String  FilePath3;
    String  FilePath4;
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void doFetch(ActionEvent event) {
    	
    	try {
			pst=con.prepareStatement("Select * from gallery where pID=?");
			pst.setString(1,comboPropertyID.getSelectionModel().getSelectedItem());
			ResultSet records=pst.executeQuery();
			if(records.next())
			{
				//int pid=records.getInt("pID");
				String pic1=records.getString("path1");
				String pic2=records.getString("path2");
				String pic3=records.getString("path3");
				String pic4=records.getString("path4");
				
				
				File file1=new File(pic1);
				try {
					img1.setImage(new Image(new FileInputStream(file1)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				File file2=new File(pic2);
				try {
					img2.setImage(new Image(new FileInputStream(file2)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				File file3=new File(pic3);
				try {
					img3.setImage(new Image(new FileInputStream(file3)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				File file4=new File(pic4);
				try {
					img4.setImage(new Image(new FileInputStream(file4)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
			btnFech.setText("Fetched");
		}
    	
    	
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void doPic1(ActionEvent event) {

FileChooser chooser=new FileChooser();
    	
    	
    	
    	chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("*.*", "*.*")
                
            );
    	File file=chooser.showOpenDialog(null);
    	 FilePath1 = file.getAbsolutePath();
    	//lblPath.setText(FilePath);//set pic path on lable
    	
    	try {
    		img1.setImage(new Image(new FileInputStream(file)));
    	} 
    	catch (FileNotFoundException e) 
    	{	
    		e.printStackTrace();
    		}
    }

    @FXML
    void doPic2(ActionEvent event) {

FileChooser chooser=new FileChooser();
    	
    	
    	
    	chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("*.*", "*.*")
                
            );
    	File file=chooser.showOpenDialog(null);
    	 FilePath2 = file.getAbsolutePath();
    	//lblPath.setText(FilePath);//set pic path on lable
    	
    	try {
    		img2.setImage(new Image(new FileInputStream(file)));
    	} 
    	catch (FileNotFoundException e) 
    	{	
    		e.printStackTrace();
    		}
    }

    @FXML
    void doPic3(ActionEvent event) {
    	
FileChooser chooser=new FileChooser();
    	
    	
    	
    	chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("*.*", "*.*")
                
            );
    	File file=chooser.showOpenDialog(null);
    	 FilePath3 = file.getAbsolutePath();
    	//lblPath.setText(FilePath);//set pic path on lable
    	
    	try {
    		img3.setImage(new Image(new FileInputStream(file)));
    	} 
    	catch (FileNotFoundException e) 
    	{	
    		e.printStackTrace();
    		}

    }

    @FXML
    void doPic4(ActionEvent event) {

FileChooser chooser=new FileChooser();
    	
    	
    	
    	chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("*.*", "*.*")
                
            );
    	File file=chooser.showOpenDialog(null);
    	 FilePath4 = file.getAbsolutePath();
    	//lblPath.setText(FilePath);//set pic path on lable
    	
    	try {
    		img4.setImage(new Image(new FileInputStream(file)));
    	} 
    	catch (FileNotFoundException e) 
    	{	
    		e.printStackTrace();
    		}
    }

    @FXML
    void doSave(ActionEvent event) {
    	
    	try {
			pst=con.prepareStatement("insert into gallery values(?,?,?,?,?)");
			pst.setInt(1,Integer.parseInt(comboPropertyID.getSelectionModel().getSelectedItem()));
			pst.setString(2,String.valueOf(FilePath1));
			pst.setString(3,String.valueOf(FilePath2));
			pst.setString(4,String.valueOf(FilePath3));
			pst.setString(5,String.valueOf(FilePath4));	
			
			pst.executeUpdate();
			System.out.println("Saved ");
			btnSave.setText("Saved");
			
		} 
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void doUpdate(ActionEvent event) {
    	try {
			pst=con.prepareStatement("Update gallery set path1=?,path2=?,path3=?,path4=? where pID=?");
			pst.setInt(5,Integer.parseInt(comboPropertyID.getSelectionModel().getSelectedItem()));
			pst.setString(1,String.valueOf(FilePath1));
			pst.setString(2,String.valueOf(FilePath2));
			pst.setString(3,String.valueOf(FilePath3));
			pst.setString(4,String.valueOf(FilePath4));
			
			int count=pst.executeUpdate();
			if(count==0)
				btnUpdate.setText("Invalid");
			else
			btnUpdate.setText("Updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    }
    
    void doFill()
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
    void docomboPropertyID(ActionEvent event) {
    	comboPropertyID.getSelectionModel().getSelectedItem();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=DataBaseConnector.getConnection();
        doFill();
        

    }
}
