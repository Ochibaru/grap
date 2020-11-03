package com.grap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;


public @Data class NutritionDTO {

    @JsonProperty("ingredients")
    private List<Ingredients> ingredients;
    @JsonProperty("totalDaily")
    private TotalDaily totalDaily;
    @JsonProperty("totalNutrients")
    private TotalNutrients totalNutrients;
    @JsonProperty("cautions")
    private List<String> cautions;
    @JsonProperty("healthLabels")
    private List<String> healthLabels;
    @JsonProperty("dietLabels")
    private List<String> dietLabels;
    @JsonProperty("totalWeight")
    private double totalWeight;
    @JsonProperty("calories")
    private int calories;
    @JsonProperty("yield")
    private int yield;
    @JsonProperty("uri")
    private String uri;

    public static class Ingredients {
        @JsonProperty("parsed")
        private List<Parsed> parsed;
        @JsonProperty("text")
        private String text;
    }

    public static class Parsed {
        @JsonProperty("nutrients")
        private Nutrients nutrients;
        @JsonProperty("retainedWeight")
        private double retainedWeight;
        @JsonProperty("weight")
        private double weight;
        @JsonProperty("foodId")
        private String foodId;
        @JsonProperty("food")
        private String food;
        @JsonProperty("foodMatch")
        private String foodMatch;
        @JsonProperty("measure")
        private String measure;
        @JsonProperty("quantity")
        private int quantity;
    }

    public static class Nutrients {
        @JsonProperty("FAPU")
        private FAPU FAPU;
        @JsonProperty("FAMS")
        private FAMS FAMS;
        @JsonProperty("FASAT")
        private FASAT FASAT;
        @JsonProperty("CHOLE")
        private CHOLE CHOLE;
        @JsonProperty("VITB12")
        private VITB12 VITB12;
        @JsonProperty("FOL")
        private FOL FOL;
        @JsonProperty("VITB6A")
        private VITB6A VITB6A;
        @JsonProperty("NIA")
        private NIA NIA;
        @JsonProperty("RIBF")
        private RIBF RIBF;
        @JsonProperty("THIA")
        private THIA THIA;
        @JsonProperty("VITC")
        private VITC VITC;
        @JsonProperty("VITD")
        private VITD VITD;
        @JsonProperty("ZN")
        private ZN ZN;
        @JsonProperty("NA")
        private NA NA;
        @JsonProperty("K")
        private K K;
        @JsonProperty("P")
        private P P;
        @JsonProperty("MG")
        private MG MG;
        @JsonProperty("FE")
        private FE FE;
        @JsonProperty("CA")
        private CA CA;
        @JsonProperty("ENERC_KCAL")
        private ENERC_KCAL ENERC_KCAL;
        @JsonProperty("FAT")
        private FAT FAT;
        @JsonProperty("PROCNT")
        private PROCNT PROCNT;
    }

    public static class FAPU {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class FAMS {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class FASAT {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class CHOLE {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class VITB12 {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class FOL {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class VITB6A {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class NIA {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class RIBF {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class THIA {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class VITC {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class VITD {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class ZN {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class NA {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class K {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class P {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class MG {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class FE {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class CA {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class ENERC_KCAL {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class FAT {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class PROCNT {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class TotalDaily {
        @JsonProperty("VITK1")
        private VITK1 VITK1;
        @JsonProperty("TOCPHA")
        private TOCPHA TOCPHA;
        @JsonProperty("VITD")
        private VITD VITD;
        @JsonProperty("VITB12")
        private VITB12 VITB12;
        @JsonProperty("FOL")
        private FOL FOL;
        @JsonProperty("VITB6A")
        private VITB6A VITB6A;
        @JsonProperty("NIA")
        private NIA NIA;
        @JsonProperty("RIBF")
        private RIBF RIBF;
        @JsonProperty("THIA")
        private THIA THIA;
        @JsonProperty("VITC")
        private VITC VITC;
        @JsonProperty("VITA_RAE")
        private VITA_RAE VITA_RAE;
        @JsonProperty("P")
        private P P;
        @JsonProperty("ZN")
        private ZN ZN;
        @JsonProperty("FE")
        private FE FE;
        @JsonProperty("K")
        private K K;
        @JsonProperty("MG")
        private MG MG;
        @JsonProperty("CA")
        private CA CA;
        @JsonProperty("NA")
        private NA NA;
        @JsonProperty("CHOLE")
        private CHOLE CHOLE;
        @JsonProperty("PROCNT")
        private PROCNT PROCNT;
        @JsonProperty("FIBTG")
        private FIBTG FIBTG;
        @JsonProperty("CHOCDF")
        private CHOCDF CHOCDF;
        @JsonProperty("FASAT")
        private FASAT FASAT;
        @JsonProperty("FAT")
        private FAT FAT;
        @JsonProperty("ENERC_KCAL")
        private ENERC_KCAL ENERC_KCAL;
    }

    public static class VITK1 {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class TOCPHA {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class VITA_RAE {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class FIBTG {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class CHOCDF {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class TotalNutrients {
        @JsonProperty("VITK1")
        private VITK1 VITK1;
        @JsonProperty("TOCPHA")
        private TOCPHA TOCPHA;
        @JsonProperty("VITD")
        private VITD VITD;
        @JsonProperty("VITB12")
        private VITB12 VITB12;
        @JsonProperty("FOL")
        private FOL FOL;
        @JsonProperty("VITB6A")
        private VITB6A VITB6A;
        @JsonProperty("NIA")
        private NIA NIA;
        @JsonProperty("RIBF")
        private RIBF RIBF;
        @JsonProperty("THIA")
        private THIA THIA;
        @JsonProperty("VITC")
        private VITC VITC;
        @JsonProperty("VITA_RAE")
        private VITA_RAE VITA_RAE;
        @JsonProperty("P")
        private P P;
        @JsonProperty("ZN")
        private ZN ZN;
        @JsonProperty("FE")
        private FE FE;
        @JsonProperty("K")
        private K K;
        @JsonProperty("MG")
        private MG MG;
        @JsonProperty("CA")
        private CA CA;
        @JsonProperty("NA")
        private NA NA;
        @JsonProperty("CHOLE")
        private CHOLE CHOLE;
        @JsonProperty("PROCNT")
        private PROCNT PROCNT;
        @JsonProperty("SUGAR")
        private SUGAR SUGAR;
        @JsonProperty("FIBTG")
        private FIBTG FIBTG;
        @JsonProperty("CHOCDF")
        private CHOCDF CHOCDF;
        @JsonProperty("FAPU")
        private FAPU FAPU;
        @JsonProperty("FAMS")
        private FAMS FAMS;
        @JsonProperty("FATRN")
        private FATRN FATRN;
        @JsonProperty("FASAT")
        private FASAT FASAT;
        @JsonProperty("FAT")
        private FAT FAT;
        @JsonProperty("ENERC_KCAL")
        private ENERC_KCAL ENERC_KCAL;
    }

    public static class SUGAR {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

    public static class FATRN {
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("quantity")
        private double quantity;
        @JsonProperty("label")
        private String label;
    }

}
