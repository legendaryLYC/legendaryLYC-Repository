package com.design.background.entity;

public class NotificationTemplate {
    private Long id;
    
    private Long type;

    private String notifyType;

    private String template;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType == null ? null : notifyType.trim();
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "NotificationTemplate [id=" + id + ", type=" + type + ", notifyType=" + notifyType + ", template="
				+ template + "]";
	}
	
}