package gxlu.flow.module.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "api_r_api_membership")
public class ApiRapiMembership {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//api名称
//	@Transient
//	private String apiname;
	
	//会员对象
	/*
	 * @Transient private Membership membership;
	 */
	
	@OneToOne
	@JoinColumn(name ="apiurl",referencedColumnName ="uri")
	private ApiInfo apiurl;
	
	
	@Column(name = "membershipid")
	private int membershipid;
	
	@Column(name = "applytimes")
	private int applytimes;
	
	@Column(name = "usedtimes")
	private int usedtimes;
	
	@Column(name = "remaindertimes")
	private int remaindertimes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	


	
	

	public ApiInfo getApiurl() {
		return apiurl;
	}

	public void setApiurl(ApiInfo apiurl) {
		this.apiurl = apiurl;
	}

	public int getMembershipid() {
		return membershipid;
	}

	public void setMembershipid(int membershipid) {
		this.membershipid = membershipid;
	}

	public int getApplytimes() {
		return applytimes;
	}

	public void setApplytimes(int applytimes) {
		this.applytimes = applytimes;
	}

	

	public int getUsedtimes() {
		return usedtimes;
	}

	public void setUsedtimes(int usedtimes) {
		this.usedtimes = usedtimes;
	}

	public int getRemaindertimes() {
		return remaindertimes;
	}

	public void setRemaindertimes(int remaindertimes) {
		this.remaindertimes = remaindertimes;
	}

//	public String getApiname() {
//		return apiname;
//	}
//
//	public void setApiname(String apiname) {
//		this.apiname = apiname;
//	}
//
//	public Membership getMembership() {
//		return membership;
//	}
//
//	public void setMembership(Membership membership) {
//		this.membership = membership;
//	}
	
	
}
