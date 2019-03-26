package com.design.background.form;

import java.util.Date;
/**
 * 通告后台数据库夺标查询生成临时form类
 *  时间:2019/2/14
 * 最后修改时间:2019/2/14
 * 注意事项:无
* @author 周天
*
*/
public class MessageForm {

	private Long id;
	
	private Long type;

    private Long acceptUser;
    
	private String notifyType;

    private Long sender;

    private String title;

    private Date creatTime;

    private Integer isall;

    private String message;

    private String template;
    
    private String templateText;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAcceptUser() {
		return acceptUser;
	}

	public void setAcceptUser(Long acceptUser) {
		this.acceptUser = acceptUser;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public Long getSender() {
		return sender;
	}

	public void setSender(Long sender) {
		this.sender = sender;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Integer getIsall() {
		return isall;
	}

	public void setIsall(Integer isall) {
		this.isall = isall;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getTemplateText() {
		return templateText;
	}

	public void setTemplateText(String templateText) {
		this.templateText = templateText;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}


}
