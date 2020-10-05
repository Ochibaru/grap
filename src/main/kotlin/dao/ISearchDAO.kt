package com.grap.dao

public interface ISearchDAO {
    List<SearchDTO> fetch(String searchTerm) throws Exception;
}