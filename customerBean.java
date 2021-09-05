package customerView;

import java.time.LocalDate;

public class customerBean {

	String cname;
	String address;
	String city;
	String mobile;
	String utype; 
	String idproof;
	String proofNumber;
	LocalDate dor;
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getIdproof() {
		return idproof;
	}
	public void setIdproof(String idproof) {
		this.idproof = idproof;
	}
	public String getProofNumber() {
		return proofNumber;
	}
	public void setProofNumber(String proofNumber) {
		this.proofNumber = proofNumber;
	}
	public LocalDate getDor() {
		return dor;
	}
	public void setDor(LocalDate dor) {
		this.dor = dor;
	}
	public customerBean(String cname, String address, String city, String mobile, String utype, String idproof,
			String proofNumber, LocalDate dor) {
		super();
		this.cname = cname;
		this.address = address;
		this.city = city;
		this.mobile = mobile;
		this.utype = utype;
		this.idproof = idproof;
		this.proofNumber = proofNumber;
		this.dor = dor;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	
	
}
