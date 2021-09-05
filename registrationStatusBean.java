package registrationStatus;

import java.time.LocalDate;

public class registrationStatusBean {
	
	String buyerID;
	String sellerID;
	String pid;
	String totalAmount;
	String advAmount;
	String balence;
	LocalDate doreg;
	public String getBuyerID() {
		return buyerID;
	}
	public void setBuyerID(String buyerID) {
		this.buyerID = buyerID;
	}
	public String getSellerID() {
		return sellerID;
	}
	public void setSellerID(String sellerID) {
		this.sellerID = sellerID;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getAdvAmount() {
		return advAmount;
	}
	public void setAdvAmount(String advAmount) {
		this.advAmount = advAmount;
	}
	public String getBalence() {
		return balence;
	}
	public void setBalence(String balence) {
		this.balence = balence;
	}
	public LocalDate getDoreg() {
		return doreg;
	}
	public void setDoreg(LocalDate doreg) {
		this.doreg = doreg;
	}
	public registrationStatusBean(String buyerID, String sellerID, String pid, String totalAmount, String advAmount,
			String balence, LocalDate doreg) {
		super();
		this.buyerID = buyerID;
		this.sellerID = sellerID;
		this.pid = pid;
		this.totalAmount = totalAmount;
		this.advAmount = advAmount;
		this.balence = balence;
		this.doreg = doreg;
	}
	
	
	
}
