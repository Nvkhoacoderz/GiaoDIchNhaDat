package com.giaodichnhadat.presentation.EditGiaoDich.OpenDialog;

import com.giaodichnhadat.business.EditGiaoDich.OpenDialog.OpenAndEditFormUseCase;
import com.giaodichnhadat.business.EditGiaoDich.OpenDialog.ResChildrenTypeDTO;
import com.giaodichnhadat.business.EditGiaoDich.OpenDialog.ResParentTypeDTO;

import java.util.ArrayList;
import java.util.List;

public class OpenAndEditFormController {
    private OpenAndEditFormUseCase useCase;
    private OpenEditFormModel model;

    public OpenAndEditFormController(OpenAndEditFormUseCase useCase, OpenEditFormModel model) {
        this.useCase = useCase;
        this.model = model;
    }

    public void execute() {
        List<ResParentTypeDTO> majorDTOs = useCase.execute();

        model.parentTypeItems = this.convertToParentitem(majorDTOs);
        model.notifySubscriber();
    }

    public void executes(String parentTypeId) {
        List<ResChildrenTypeDTO> majorDTOs = useCase.executes(parentTypeId);

        model.childrenTypeItems = this.convertToChildrenitem(majorDTOs);
        model.notifySubscriber();
    }

    private List<ParentTypeItem> convertToParentitem
            (List<ResParentTypeDTO>  majorDTOs){
        List<ParentTypeItem> list = new ArrayList<ParentTypeItem>();

        for (ResParentTypeDTO resMajorDTO : majorDTOs) {
            ParentTypeItem item = new ParentTypeItem();
            item.id = String.valueOf(resMajorDTO.id);
            item.name = resMajorDTO.name;
            list.add(item);
        }

        return list;
    }

    private List<ChildrenTypeItem> convertToChildrenitem
            (List<ResChildrenTypeDTO>  majorDTOs){
        List<ChildrenTypeItem> list = new ArrayList<ChildrenTypeItem>();

        for (ResChildrenTypeDTO resMajorDTO : majorDTOs) {
            ChildrenTypeItem item = new ChildrenTypeItem();
            item.id = String.valueOf(resMajorDTO.id);
            item.name = resMajorDTO.name;
            item.parentId = String.valueOf(resMajorDTO.parentId);
            list.add(item);
        }

        return list;
    }
}
