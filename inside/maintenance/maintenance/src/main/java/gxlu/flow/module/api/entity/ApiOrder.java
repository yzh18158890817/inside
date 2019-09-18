package gxlu.flow.module.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "api_order")
public class ApiOrder extends EntityCommonField {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "apiid")
	private ApiInfo apiinfo;
	
	@Column(name = "apiname")
	private String apiname;
	
	@Column(name = "chargingcategory")
	private int chargingcategory;
	
	@Column(name = "unitprice")
	private double unitprice;
	
	@Column(name = "unitpricedescription")
	private String unitpricedescription;
	
	@Column(name = "pictureurl")
	private String pictureurl;
	
	@Column(name = "membershipid")
	private int membershipid;
	
	@Column(name = "amountmoney")
	private double amountmoney;
	
	
	@Column(name = "totaltimes")
	private int totaltimes;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public ApiInfo getApiinfo() {
		return apiinfo;
	}


	public void setApiinfo(ApiInfo apiinfo) {
		this.apiinfo = apiinfo;
	}


	public String getApiname() {
		return apiname;
	}


	public void setApiname(String apiname) {
		this.apiname = apiname;
	}





	public int getChargingcategory() {
		return chargingcategory;
	}


	public void setChargingcategory(int chargingcategory) {
		this.chargingcategory = chargingcategory;
	}


	public double getUnitprice() {
		return unitprice;
	}


	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}


	public String getUnitpricedescription() {
		return unitpricedescription;
	}


	public void setUnitpricedescription(String unitpricedescription) {
		this.unitpricedescription = unitpricedescription;
	}


	public String getPictureurl() {
		return pictureurl;
	}


	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}


	public int getMembershipid() {
		return membershipid;
	}


	public void setMembershipid(int membershipid) {
		this.membershipid = membershipid;
	}


	public double getAmountmoney() {
		return amountmoney;
	}


	public void setAmountmoney(double amountmoney) {
		this.amountmoney = amountmoney;
	}


	public int getTotaltimes() {
		return totaltimes;
	}


	public void setTotaltimes(int totaltimes) {
		this.totaltimes = totaltimes;
	}
	
	


	
}
