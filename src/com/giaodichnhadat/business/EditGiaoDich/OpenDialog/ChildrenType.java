package com.giaodichnhadat.business.EditGiaoDich.OpenDialog;

public class ChildrenType {
    private int id;
    private String name;
    private String description;
    private int parentId;

    public ChildrenType(int id, String name, String description, int parentId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentId = parentId;
    }

    public ChildrenType() {
    }
}
