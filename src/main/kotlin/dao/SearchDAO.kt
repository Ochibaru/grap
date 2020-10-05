package com.grap.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.ArrayList

@Component
public class SearchDAO implements ISearchDAO {

    @Autowired
    NetworkDAO networkDAO;

    @Override
    public List<SearchDTO> fetch(String searchTerm) throws Exception {
        val searchResults: List<SearchDTO> = java.util.ArrayList<SearchDTO>()

    }

}