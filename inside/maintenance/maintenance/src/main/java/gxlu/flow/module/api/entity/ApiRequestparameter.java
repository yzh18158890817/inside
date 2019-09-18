package gxlu.flow.module.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "api_requestparameter")
public class ApiRequestparameter extends EntityCommonField{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "apiid")
	private int apiid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "mustfill")
	private String mustfill;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "remark")
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getApiid() {
		return apiid;
	}

	public void setApiid(int apiid) {
		this.apiid = apiid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMustfill() {
		return mustfill;
	}

	public void setMustfill(String mustfill) {
		this.mustfill = mustfill;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
