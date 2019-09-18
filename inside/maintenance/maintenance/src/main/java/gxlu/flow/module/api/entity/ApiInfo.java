package gxlu.flow.module.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "api_info")
public class ApiInfo implements Serializable{
	@Id
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	//返回格式
	@Column(name="format")
	private int format;
	
	//请求代码示例
	@Column(name="requestdata")
	private String requestdata;
	
	//请求示例
	@Column(name="request")
	private String request;
	
	@Column(name = "uri")
	private String uri;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "protocol")
	private int protocol;
	
	@Column(name = "method")
	private int method;
	
	@Column(name = "responsedata")
	private String responsedata;
	
	@Column(name="abbreviation")
	private String abbreviation;
	
	@Column(name="sequence")
	private int sequence;
	
	public String getAbbreviation() {
		return abbreviation;
	}
	
	

	public int getSequence() {
		return sequence;
	}



	public void setSequence(int sequence) {
		this.sequence = sequence;
	}



	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getProtocol() {
		return protocol;
	}

	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public String getResponsedata() {
		return responsedata;
	}

	public void setResponsedata(String responsedata) {
		this.responsedata = responsedata;
	}
	
	@Column(name = "CREATOR")
	private String creator;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATTIME")
	private Date creatTime;

	@Column(name = "MODIFIER")
	private String modifier;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "MODIFYTIME")
	private Date modifyTime;

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatTime() {
		return this.creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRequestdata() {
		return requestdata;
	}

	public void setRequestdata(String requestdata) {
		this.requestdata = requestdata;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getFormat() {
		return format;
	}

	public void setFormat(int format) {
		this.format = format;
	}

	
	
}
