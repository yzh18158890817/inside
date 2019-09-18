package gxlu.flow.module.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "api_requestlog")
public class ApiRequestlog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "apiurl")
	private String apiurl;
	
	@Column(name = "membershipid")
	private int membershipid;
	
	@Column(name = "accesstoken")
	private String accesstoken;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "requesttime")
	private Date requesttime;
	
	@Column(name = "requestparameter")
	private String requestparameter;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "responsetime")
	private String responsetime;
	
	@Column(name = "responsedata")
	private String responsedata;
	
	@Column(name = "cost")
	private String cost;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApiurl() {
		return apiurl;
	}

	public void setApiurl(String apiurl) {
		this.apiurl = apiurl;
	}

	public int getMembershipid() {
		return membershipid;
	}

	public void setMembershipid(int membershipid) {
		this.membershipid = membershipid;
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	public Date getRequesttime() {
		return requesttime;
	}

	public void setRequesttime(Date requesttime) {
		this.requesttime = requesttime;
	}

	public String getRequestparameter() {
		return requestparameter;
	}

	public void setRequestparameter(String requestparameter) {
		this.requestparameter = requestparameter;
	}

	public String getResponsetime() {
		return responsetime;
	}

	public void setResponsetime(String responsetime) {
		this.responsetime = responsetime;
	}

	public String getResponsedata() {
		return responsedata;
	}

	public void setResponsedata(String responsedata) {
		this.responsedata = responsedata;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	
	
}
