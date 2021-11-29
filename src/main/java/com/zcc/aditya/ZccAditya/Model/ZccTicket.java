package com.zcc.aditya.ZccAditya.Model;

public class ZccTicket {

	private Integer id;

	private String subject;

	private String status;

	private String description;

	private String type;

	private Long requesterId;

	private Long submitterId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}

	public Long getSubmitterId() {
		return submitterId;
	}

	public void setSubmitterId(Long submitterId) {
		this.submitterId = submitterId;
	}

	@Override
	public String toString() {
		return "ZccTicket [id=" + id + ", subject=" + subject + ", status=" + status + ", description=" + description
				+ ", type=" + type + ", requesterId=" + requesterId + ", submitterId=" + submitterId + "]";
	}

}
