package com.ifreework.entity.system;




public class Attachment{
	
	//columns START
	private java.lang.String attachmentId;
	private java.lang.String attachmentName;
	private java.lang.String attachmentPath;
	private Long attachmentSize;
	private String attachmentFormatSize;
	
	public Long getAttachmentSize() {
		return attachmentSize;
	}
	//columns END
	public java.lang.String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(java.lang.String attachmentId) {
		this.attachmentId = attachmentId;
	}
	public java.lang.String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(java.lang.String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public java.lang.String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(java.lang.String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	public void setAttachmentSize(Long attachmentSize) {
		this.attachmentSize = attachmentSize;
	}
	public String getAttachmentFormatSize() {
		return attachmentFormatSize;
	}
	public void setAttachmentFormatSize(String attachmentFormatSize) {
		this.attachmentFormatSize = attachmentFormatSize;
	}

}


