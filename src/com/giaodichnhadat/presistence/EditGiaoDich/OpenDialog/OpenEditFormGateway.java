package com.giaodichnhadat.presistence.EditGiaoDich.OpenDialog;

import java.util.List;

public interface OpenEditFormGateway {
    List<ParentTypeDTO> getAll();
    List<ChildrenTypeDTO> getAll(String parentId);
}
