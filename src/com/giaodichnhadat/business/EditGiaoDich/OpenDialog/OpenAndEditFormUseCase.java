package com.giaodichnhadat.business.EditGiaoDich.OpenDialog;

import com.giaodichnhadat.presistence.EditGiaoDich.OpenDialog.ChildrenTypeDTO;
import com.giaodichnhadat.presistence.EditGiaoDich.OpenDialog.OpenEditFormGateway;
import com.giaodichnhadat.presistence.EditGiaoDich.OpenDialog.ParentTypeDTO;

import java.util.ArrayList;
import java.util.List;

public class OpenAndEditFormUseCase {
    private OpenEditFormGateway openEditFormGateway;

    public OpenAndEditFormUseCase(OpenEditFormGateway openEditFormGateway) {
        this.openEditFormGateway = openEditFormGateway;
    }

    public List<ResParentTypeDTO> execute() {
        List<ParentTypeDTO> listDTO = null;
        listDTO = openEditFormGateway.getAll();
        return convertToResParentTypeDTO(listDTO);
    }

    public List<ResChildrenTypeDTO> executes(String parentId) {
        List<ChildrenTypeDTO> listDTO = null;
        listDTO = openEditFormGateway.getAll(parentId);
        return convertToResChildrenTypeDTO(listDTO);
    }

    private List<ResParentTypeDTO> convertToResParentTypeDTO(List<ParentTypeDTO> listDTO) {
        // TODO Auto-generated method stub
        List<ResParentTypeDTO> list = new ArrayList<ResParentTypeDTO>();
        for (ParentTypeDTO majorDTO : listDTO) {
            ResParentTypeDTO resDTO = new ResParentTypeDTO();
            resDTO.id = majorDTO.id;
            resDTO.name = majorDTO.name;
            resDTO.description = majorDTO.description;

            list.add(resDTO);

        }

        return list;
    }

    private List<ResChildrenTypeDTO> convertToResChildrenTypeDTO(List<ChildrenTypeDTO> listDTO) {
        // TODO Auto-generated method stub
        List<ResChildrenTypeDTO> list = new ArrayList<ResChildrenTypeDTO>();
        for (ChildrenTypeDTO majorDTO : listDTO) {
            ResChildrenTypeDTO resDTO = new ResChildrenTypeDTO();
            resDTO.id = majorDTO.id;
            resDTO.name = majorDTO.name;
            resDTO.description = majorDTO.description;
            resDTO.parentId = majorDTO.parentId;

            list.add(resDTO);

        }

        return list;
    }
}
