package com.grap.dao;

import com.grap.dto.IngredientsDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IIngredientsDAO {
    List<IngredientsDTO> fetch() throws Exception;

}
