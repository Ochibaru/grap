package com.grap.dao;

public interface IBMI_DAO {

    double fetchBMI_DAO(double weight, double height, String type, String type2) throws Exception;

    double fetchAll(String mesurenmentBMI) throws Exception;
}
