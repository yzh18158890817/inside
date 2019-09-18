package gxlu.flow.module.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "api_serviceerrorcode")
public class ApiServiceerrorcode extends EntityCommonField{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="apiid")
	private int apiid;
	
	@Column(name = "errorcode")
	private String errorcode;
	
	@Column(name = "remark")
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getApiid() {
		return apiid;
	}

	public void setApiid(int apiid) {
		this.apiid = apiid;
	}
	
}
