package com.grap.dao;

public interface IBMI_DAO {

    double fetchBMI_DAO(String calculateBMI) throws Exception;

    double fetchAll(String mesurenmentBMI) throws Exception;
}
