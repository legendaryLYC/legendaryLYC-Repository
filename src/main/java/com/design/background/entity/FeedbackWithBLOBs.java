package com.design.background.entity;

import java.util.Date;

public class FeedbackWithBLOBs extends Feedback {
    private String content;

    private String reply;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public FeedbackWithBLOBs(Long id, Long feedbackUserId, String title, Date submitTime, Date processTime, Long processorId, String content, String reply) {
        super(id, feedbackUserId, title, submitTime, processTime, processorId);
        this.content = content;
        this.reply = reply;
    }

    public FeedbackWithBLOBs() {
    }
}