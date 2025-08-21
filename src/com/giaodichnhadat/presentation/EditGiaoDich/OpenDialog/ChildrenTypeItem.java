package com.giaodichnhadat.presentation.EditGiaoDich.OpenDialog;

public class ChildrenTypeItem {
    public String id;
    public String name;
    public String description;
    public String parentId;

    public ChildrenTypeItem(String id, String name, String description, String parentId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentId = parentId;
    }

    public ChildrenTypeItem() {
    }
}
