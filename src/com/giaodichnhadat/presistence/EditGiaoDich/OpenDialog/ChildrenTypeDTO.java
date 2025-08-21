package com.giaodichnhadat.presistence.EditGiaoDich.OpenDialog;

public class ChildrenTypeDTO {
    public int id;
    public String name;
    public String description;
    public int parentId;

    public ChildrenTypeDTO(int id, String name, String description, int parentId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentId = parentId;
    }

    public ChildrenTypeDTO() {
    }
}
