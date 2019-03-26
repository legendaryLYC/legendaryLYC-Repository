package com.design.background.entity;

public class ProjectFiles {
    private Long id;

    private Long projectId;

    private String filePath;

    private String filename;

    public ProjectFiles() {
    	super();
    }
    
    public ProjectFiles(Long  projectId,String filePath ,String filename){
    	this.projectId = projectId;
    	this.filePath = filePath;
    	this.filename = filename;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }
}