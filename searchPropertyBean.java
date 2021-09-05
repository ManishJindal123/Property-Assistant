package searchProperty;

import java.time.LocalDate;

public class searchPropertyBean {
	
	int contactNo;
	String ptype;
    float size;
    float  widht;
    float  depth;
    float  lside;
    float  rside;
	String location;
	String city;
	float amount;
	String direction;
	LocalDate doa;
	public int getContactNo() {
		return contactNo;
	}
	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public float getSize() {
		return size;
	}
	public void setSize(float size) {
		this.size = size;
	}
	public float getWidht() {
		return widht;
	}
	public void setWidht(float widht) {
		this.widht = widht;
	}
	public float getDepth() {
		return depth;
	}
	public void setDepth(float depth) {
		this.depth = depth;
	}
	public float getLside() {
		return lside;
	}
	public void setLside(float lside) {
		this.lside = lside;
	}
	public float getRside() {
		return rside;
	}
	public void setRside(float rside) {
		this.rside = rside;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public LocalDate getDoa() {
		return doa;
	}
	public void setDoa(LocalDate doa) {
		this.doa = doa;
	}
	public searchPropertyBean(int contactNo, String ptype, float size, float widht, float depth, float lside,
			float rside, String location, String city, float amount, String direction, LocalDate doa) {
		super();
		this.contactNo = contactNo;
		this.ptype = ptype;
		this.size = size;
		this.widht = widht;
		this.depth = depth;
		this.lside = lside;
		this.rside = rside;
		this.location = location;
		this.city = city;
		this.amount = amount;
		this.direction = direction;
		this.doa = doa;
	}
	
	
}
