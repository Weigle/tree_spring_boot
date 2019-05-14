package com.example.DTO;

public class NodeDTO {

    private Long id;

    private String code;

    private String description;

    private String detail;

    private Long parentid;

    private Boolean hasChildren;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentId) {
        this.parentid = parentId;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }


}
