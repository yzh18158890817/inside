package gxlu.flow.module.api.entity;

import java.util.Date;

import javax.persistence.Entity;


public class EolinkerApi {
	//apiRequestType  0.POST 1.GET 2.PUT  3.DELETE  4.HEAD 5.OPTIONS 6.PATCH
	private int apiRequestType;
	//名称
	private String apiName;
	
    private int updateUserID;
    //分组id
    private int groupID;
    
    private Date apiUpdateTime;
    
    //父级分组id
    private int parentGroupID;
    //创建人名字
    private String userName;
    //分组名称
    private String groupName;
    //uri
    private String apiURI;
    
    private int starred;
    //创建人中文名称
    private String userNickName;
    //id
    private Long apiID;
    
	//apiStatus  0.启动  1.维护  2.弃用
    private int apiStatus;

	public int getApiRequestType() {
		return apiRequestType;
	}

	public void setApiRequestType(int apiRequestType) {
		this.apiRequestType = apiRequestType;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public int getUpdateUserID() {
		return updateUserID;
	}

	public void setUpdateUserID(int updateUserID) {
		this.updateUserID = updateUserID;
	}

	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public Date getApiUpdateTime() {
		return apiUpdateTime;
	}

	public void setApiUpdateTime(Date apiUpdateTime) {
		this.apiUpdateTime = apiUpdateTime;
	}

	public int getParentGroupID() {
		return parentGroupID;
	}

	public void setParentGroupID(int parentGroupID) {
		this.parentGroupID = parentGroupID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getApiURI() {
		return apiURI;
	}

	public void setApiURI(String apiURI) {
		this.apiURI = apiURI;
	}

	public int getStarred() {
		return starred;
	}

	public void setStarred(int starred) {
		this.starred = starred;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public Long getApiID() {
		return apiID;
	}

	public void setApiID(Long apiID) {
		this.apiID = apiID;
	}

	public int getApiStatus() {
		return apiStatus;
	}

	public void setApiStatus(int apiStatus) {
		this.apiStatus = apiStatus;
	}
    
    
    
    
}
