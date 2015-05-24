package com.jeecms.common.util;


public class Msg {
	private String id;
	private String name;
	private String title;
	private boolean success;
	private String url;
	private String status;
	private int hashCode = Integer.MIN_VALUE;
	
	public Msg() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Msg)) return false;
		else {
			Msg Msg = (Msg) obj;
			if (null == this.getId() || null == Msg.getId()) return false;
			else return (this.getId().equals(Msg.getId()));
		}
	}
	
	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}
}
