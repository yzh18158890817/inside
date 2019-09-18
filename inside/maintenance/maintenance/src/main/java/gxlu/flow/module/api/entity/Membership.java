package gxlu.flow.module.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class Membership{
	private int id;
	private String telephone;
	private String membershipName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMembershipName() {
		return membershipName;
	}
	public void setMembershipName(String membershipName) {
		this.membershipName = membershipName;
	}
	
	
	
}
