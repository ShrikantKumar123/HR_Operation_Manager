package model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Application {

	private int application_id;
	private String email;
	private String opportunity_id;
	private double application_date;
	private String resume_location;
	private String status;
	private String offer_id;
	private String interviwer;
	private double interview_date;
	private Time interview_time;
	private String result;
	private String reason;
	public int getApplication_id() {
		return application_id;
	}
	public void setApplication_id(int application_id) {
		this.application_id = application_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOpportunity_id() {
		return opportunity_id;
	}
	public void setOpportunity_id(String opportunity_id) {
		this.opportunity_id = opportunity_id;
	}
	public double getApplication_date() {
		return application_date;
	}
	public void setApplication_date(double application_date) {
		this.application_date = application_date;
	}
	public String getResume_location() {
		return resume_location;
	}
	public void setResume_location(String resume_location) {
		this.resume_location = resume_location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOffer_id() {
		return offer_id;
	}
	public void setOffer_id(String offer_id) {
		this.offer_id = offer_id;
	}
	public String getInterviwer() {
		return interviwer;
	}
	public void setInterviwer(String interviwer) {
		this.interviwer = interviwer;
	}
	public double getInterview_date() {
		return interview_date;
	}
	public void setInterview_date(double interview_date2) {
		this.interview_date = interview_date2;
	}
	public Time getInterview_time() {
		return interview_time;
	}
	public void setInterview_time(Time interview_time) {
		this.interview_time = interview_time;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Application(int application_id, String email, String opportunity_id, double application_date,
			String resume_location, String status, String offer_id, String interviwer, double interview_date,
			Time interview_time, String result, String reason) {
		super();
		this.application_id = application_id;
		this.email = email;
		this.opportunity_id = opportunity_id;
		this.application_date = application_date;
		this.resume_location = resume_location;
		this.status = status;
		this.offer_id = offer_id;
		this.interviwer = interviwer;
		this.interview_date = interview_date;
		this.interview_time = interview_time;
		this.result = result;
		this.reason = reason;
	}
	public Application() {
		// TODO Auto-generated constructor stub
	}
	
	

	


}
