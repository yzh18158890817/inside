package gxlu.flow.module.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "api_detail")
public class ApiDetail extends EntityCommonField{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@OneToOne
	@JoinColumn(name="apiid")
	private ApiInfo apiid;
	
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "interfacedescription")
	private String interfaceDescription;
	
	@Column(name = "chargingcategory")
	private String chargingcategory;
	
	@Column(name = "unitprice")
	private double unitPrice;
	
	@Column(name="abbreviation")
	private String abbreviation;
	
	@Column(name = "unitpricedescription")
	private String unitpricedescription;
	
	@Column(name = "pictureurl")
	private String pictureurl;
	
	@Column(name = "introduction")
	private String introduction;
	
	@Column(name = "example")
	private String example;
	
	@Column(name = "status")
	private int status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	

	public ApiInfo getApiid() {
		return apiid;
	}

	public void setApiid(ApiInfo apiid) {
		this.apiid = apiid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInterfaceDescription() {
		return interfaceDescription;
	}

	public void setInterfaceDescription(String interfaceDescription) {
		this.interfaceDescription = interfaceDescription;
	}

	
	

	public String getChargingcategory() {
		return chargingcategory;
	}

	public void setChargingcategory(String chargingcategory) {
		this.chargingcategory = chargingcategory;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	
}
