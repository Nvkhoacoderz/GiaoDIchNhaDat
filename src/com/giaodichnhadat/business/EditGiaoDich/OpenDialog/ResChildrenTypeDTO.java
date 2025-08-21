package com.giaodichnhadat.business.EditGiaoDich.OpenDialog;

public class ResChildrenTypeDTO {
    public int id;
    public String name;
    public String description;
    public int parentId;

    public ResChildrenTypeDTO(int id, String name, String description, int parentId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentId = parentId;
    }

    public ResChildrenTypeDTO() {
    }
}
