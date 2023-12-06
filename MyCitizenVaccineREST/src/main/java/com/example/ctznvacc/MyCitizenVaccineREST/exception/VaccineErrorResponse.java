package com.example.ctznvacc.MyCitizenVaccineREST.exception;

public class VaccineErrorResponse {
	private int status;
	private String message;
	private long timestamp;
	
	public VaccineErrorResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public VaccineErrorResponse(int status, String message, long timestamp) {
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "VaccineErrorResponse [status=" + status + ", message=" + message + ", timestamp=" + timestamp + "]";
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
