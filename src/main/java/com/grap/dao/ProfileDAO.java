package com.grap.dao;
import com.grap.dto.NutritionDTO;
import com.grap.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileDAO implements IProfileDAO{

    @Autowired
    private ProfileDAO profileDAO;


    @Override
    public void fetchProfileDAO(String searchProfile) throws Exception {
        List<ProfileDTO> profileDTO = new ArrayList<>();

        return getProfileDTO(profileDTO, searchProfile);

    }

    private List<ProfileDTO> getProfileDTO(List<ProfileDTO> profileDTO, String searchProfile) {

        return ProfileDTO;
    }
}
