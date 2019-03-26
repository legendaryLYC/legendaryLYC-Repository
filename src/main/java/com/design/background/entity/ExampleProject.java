package com.design.background.entity;

import java.util.Date;
public class ExampleProject {
    private Long id; // 案例id

    private Long projectId; // 项目id

    private String designerName; // 设计师姓名

    private String projecterName; // 项目方姓名

    private String projectName; // 项目名

    private String coverImage; // 封面

    private Date createTime; // 发布时间

    private Long userId; // 发布人id

    private String content; // 富文本内容

    private String username; // 发布人姓名

    public ExampleProject() {
    }

    public ExampleProject(Long id, Long projectId, String designerName, String projecterName, String projectName, Date createTime, Long userId, String content,String username) {
        this.id = id;
        this.projectId = projectId;
        this.designerName = designerName;
        this.projecterName = projecterName;
        this.projectName = projectName;
        this.createTime = createTime;
        this.userId = userId;
        this.content = content;
        this.username = username;
    }

    public ExampleProject(Long id, Long projectId, String designerName, String projecterName, String projectName, Date createTime, Long userId, String content, String username, String coverImage) {
        this.id = id;
        this.projectId = projectId;
        this.designerName = designerName;
        this.projecterName = projecterName;
        this.projectName = projectName;
        this.coverImage = coverImage;
        this.createTime = createTime;
        this.userId = userId;
        this.content = content;
        this.username = username;
    }

    public ExampleProject(Long id, Long projectId, String designerName, String projecterName, String projectName, Date createTime, Long userId, String content) {
        this.id = id;
        this.projectId = projectId;
        this.designerName = designerName;
        this.projecterName = projecterName;
        this.projectName = projectName;
        this.createTime = createTime;
        this.userId = userId;
        this.content = content;
    }

    public ExampleProject(Long projectId, String designerName, String projecterName, String projectName, Date createTime, Long userId, String content) {
        this.projectId = projectId;
        this.designerName = designerName;
        this.projecterName = projecterName;
        this.projectName = projectName;
        this.createTime = createTime;
        this.userId = userId;
        this.content = content;
    }

    public ExampleProject(Long projectId, String designerName, String projecterName, String projectName, Date createTime, Long userId, String content,String username) {
        this.projectId = projectId;
        this.designerName = designerName;
        this.projecterName = projecterName;
        this.projectName = projectName;
        this.createTime = createTime;
        this.userId = userId;
        this.content = content;
        this.username = username;
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

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName == null ? null : designerName.trim();
    }

    public String getProjecterName() {
        return projecterName;
    }

    public void setProjecterName(String projecterName) {
        this.projecterName = projecterName == null ? null : projecterName.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public ExampleProject setCoverImage(String coverImage) {
        this.coverImage = coverImage;
        return this;
    }
}