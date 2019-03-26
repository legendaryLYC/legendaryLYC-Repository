package com.design.background.model;

/**
 * @Author： 李紫林
 * @Date： 2019/2/18
 * @Description： 用来将组建分类的code和字符传给前台
 */
public class ProjectTypeModel {
    private int code; // 字典码
    private String typeString; // 字典的值

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public ProjectTypeModel(int code, String typeString) {
        this.code = code;
        this.typeString = typeString;
    }

    public ProjectTypeModel() {
    }
}
