package com.grap.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchDAO implements ISearchDAO{

    @Autowired
    private NetworkDAO networkDAO;

    @Override
    public void fetch(String searchTerm) throws Exception {

    }
}
