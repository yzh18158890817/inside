package gxlu.flow.module.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "api_r_api_category")
public class ApiInfoCategory{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Transient
	private String apiCategoryname;
	
	@Column(name = "apiid")
	private int apiid;
	
	@Column(name = "apicategoryid")
	private int apicategoryid;
	
	@Column(name = "isenabled")
	private int isenabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getApiid() {
		return apiid;
	}
	
	

	public String getApiCategoryname() {
		return apiCategoryname;
	}

	public void setApiCategoryname(String apiCategoryname) {
		this.apiCategoryname = apiCategoryname;
	}

	public void setApiid(int apiid) {
		this.apiid = apiid;
	}

	public int getApicategoryid() {
		return apicategoryid;
	}

	public void setApicategoryid(int apicategoryid) {
		this.apicategoryid = apicategoryid;
	}

	public int getIsenabled() {
		return isenabled;
	}

	public void setIsenabled(int isenabled) {
		this.isenabled = isenabled;
	}
	
	
}
