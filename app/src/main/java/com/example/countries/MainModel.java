package com.example.countries;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainModel {
    private String countryName;
    private JSONArray capital;
    private String region;
    private String subRegion;
    private JSONObject languages;
    private JSONArray borders;
    private String population;
    private String flagUrl;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public JSONArray getCapital() {
        return capital;
    }

    public void setCapital(JSONArray capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public JSONObject getLanguages() {
        return languages;
    }

    public void setLanguages(JSONObject languages) {
        this.languages = languages;
    }

    public JSONArray getBorders() {
        return borders;
    }

    public void setBorders(JSONArray borders) {
        this.borders = borders;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    @Override
    public String toString() {
        return "MainModel{" +
                "countryName='" + countryName + '\'' +
                ", capital='" + capital + '\'' +
                ", region='" + region + '\'' +
                ", subRegion='" + subRegion + '\'' +
                ", languages='" + languages + '\'' +
                ", borders='" + borders + '\'' +
                ", population='" + population + '\'' +
                ", flagUrl='" + flagUrl + '\'' +
                '}';
    }
}
