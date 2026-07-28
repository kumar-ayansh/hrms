package com.example.hrms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="response")
public class Response {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int userid;
	
	@Column(length=100)
	private String name;
	@Column(length=100)
	private String emailaddress;
	@Column(length=20)
	private String contactno;
	@Column(length=50)
	private String responsetype;
	@Column(length=100)
	private String subject;
	@Column(length=500)
	private String responsetex;
	@Column(length=100)
	private String posteddate;
	@Column(length=50)
	private String status = "Pending";
	@Column(length=1000)
	private String adminreply;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getResponsetype() {
		return responsetype;
	}
	public void setResponsetype(String responsetype) {
		this.responsetype = responsetype;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getResponsetex() {
		return responsetex;
	}
	public void setResponsetex(String responsetex) {
		this.responsetex = responsetex;
	}
	public String getResponsetext() {
		return responsetex;
	}
	public void setResponsetext(String responsetext) {
		this.responsetex = responsetext;
	}
	public String getPosteddate() {
		return posteddate;
	}
	public void setPosteddate(String posteddate) {
		this.posteddate = posteddate;
	}
	public String getStatus() {
		return status != null ? status : "Pending";
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAdminreply() {
		return adminreply;
	}
	public void setAdminreply(String adminreply) {
		this.adminreply = adminreply;
	}
}
