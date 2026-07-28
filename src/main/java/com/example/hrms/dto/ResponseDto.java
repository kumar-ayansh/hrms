package com.example.hrms.dto;

public class ResponseDto {
 private int userid;
 private String name;
 private String emailaddress;
 private String contactno;
 private String responsetype;
 private String subject;
 private String responsetext;
 private String posteddate;
 private String status;
 private String adminreply;

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
 public String getResponsetext() {
	return responsetext;
 }
 public void setResponsetext(String responsetext) {
	this.responsetext = responsetext;
 }
 public String getPosteddate() {
	return posteddate;
 }
 public void setPosteddate(String posteddate) {
	this.posteddate = posteddate;
 }
 public String getStatus() {
	return status;
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
