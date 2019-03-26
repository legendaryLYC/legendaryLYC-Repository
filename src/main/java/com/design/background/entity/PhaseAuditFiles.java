package com.design.background.entity;

public class PhaseAuditFiles {
    private Long id;

    private String filePath;

    private String fileName;

    private Long userId;

    private Long phaseAuditId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPhaseAuditId() {
        return phaseAuditId;
    }

    public void setPhaseAuditId(Long phaseAuditId) {
        this.phaseAuditId = phaseAuditId;
    }

    public PhaseAuditFiles(String filePath, String fileName, Long userId, Long phaseAuditId) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.userId = userId;
        this.phaseAuditId = phaseAuditId;
    }

    public PhaseAuditFiles() {
    }
}