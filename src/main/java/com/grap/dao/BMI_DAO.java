package com.grap.dao;

import com.grap.dto.BMI_DTO;



public class BMI_DAO implements IBMI_DAO{
    double bmiCalculation = 0;
    String heightMeasurement = "";
    String weightMeasurement = "";

    @Override
    public double fetchBMI_DAO(double weight, double height, String type, String type2) throws Exception {

        BMI_DTO bmi_dto = new BMI_DTO();

        if (heightMeasurement.equals("ft") && weightMeasurement.equals("lb")) {
            bmiCalculation = (weight / ((height * 12) * (height * 12))) * 703;
        }

       else if (heightMeasurement.equals("cm") && weightMeasurement.equals("kg"))    {
             bmiCalculation = (weight / ((height/100) * (height/100)));
        }

        return bmiCalculation;
    }

    @Override
    public double fetchAll(String measurementBMI) throws Exception {

        if (bmiCalculation >=40.0)    {
            return Double.parseDouble("Morbidly Obese");
        }

        else if (bmiCalculation > 35.0 && bmiCalculation < 39.9) {
            return Double.parseDouble(("Severely Obese"));
        }

        else if (bmiCalculation > 30.0 && bmiCalculation <34.9) {
            return Double.parseDouble(("Moderately Obese"));
        }

        else if (bmiCalculation > 25.0 && bmiCalculation <29.9) {
            return Double.parseDouble(("Overweight"));
        }

        else if (bmiCalculation > 18.5 && bmiCalculation <24.9) {
            return Double.parseDouble(("Normal BMI Range"));
        }

        else if (bmiCalculation > 16.0 && bmiCalculation < 18.4) {
            return Double.parseDouble(("Underweight"));
        }

        else   {
            return Double.parseDouble((" Severely Underweight"));
        }
    }
}
