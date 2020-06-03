/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcoscompany.tarea03_marcos;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Marcos
 */
public class TablaXml extends DefaultHandler {

    private ArrayList<Tabla> tabla;
    private Tabla tablaAux;
    private String dateRep;
    private boolean dateRepEncontrado = false;
    private String day;
    private boolean dayEncontrado = false;
    private String month;
    private boolean monthEncontrado = false;
    private String year;
    private boolean yearEncontrado = false;
    private String continentExp;
    private boolean continentExpEncontrado = false;
    private String countriesAndTerritories;
    private boolean countriesAndTerritoriesEncontrado = false;
    private String cases;
    private boolean casesEncontrado = false;
    private String deaths;
    private boolean deathsEncontrado = false;
    private String geoId;
    private boolean geoIdEncontrado = false;
    private String countryterritoryCode;
    private boolean countryterritoryCodeEncontrado = false;
    private String popData2018;
    private boolean popData2018Encontrado = false;

    public TablaXml() {
        super();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       super.startElement(uri, localName, qName, attributes); //To change body of generated methods, choose Tools | Templates.
       if(qName.equals("records")){
            this.tabla = new ArrayList<Tabla>();
        }else if(qName.equals("record")){
            this.tablaAux = new Tabla();
            dateRepEncontrado = true;
           dayEncontrado = true;
            monthEncontrado = true;
            yearEncontrado = true;
            continentExpEncontrado = true;
            countriesAndTerritoriesEncontrado = true;
            casesEncontrado = true;
            deathsEncontrado = true;
            geoIdEncontrado = true;
            countryterritoryCodeEncontrado = true;
            popData2018Encontrado = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
       super.endElement(uri, localName, qName); //To change body of generated methods, choose Tools | Templates.    
            if(qName.equals("dateRep")){
            if(dateRepEncontrado){
                this.tablaAux.setDateRep(dateRep);
                dateRepEncontrado = false;
            }
        }else if(qName.equals("day")){
            if(dayEncontrado){
                this.tablaAux.setDay(day);
                dayEncontrado = false;
            }
        }else if(qName.equals("month")){
            if(monthEncontrado){
                this.tablaAux.setMonth(month);
                monthEncontrado = false;
            }
        }else if(qName.equals("year")){
            if(yearEncontrado){
                this.tablaAux.setYear(year);
                yearEncontrado = false;
            }
        }else if(qName.equals("continentExp")){
            if(continentExpEncontrado){
                this.tablaAux.setContinentExp(continentExp);
                continentExpEncontrado = false;
            }
        }else if(qName.equals("countriesAndTerritories")){
            if(countriesAndTerritoriesEncontrado){
                this.tablaAux.setCountriesAndTerritories(countriesAndTerritories);
                countriesAndTerritoriesEncontrado = false;
            }
        }else if(qName.equals("cases")){
            if(casesEncontrado){
                this.tablaAux.setCases(cases);
                casesEncontrado = false;
            }
        }else if(qName.equals("deaths")){
            if(deathsEncontrado){
                this.tablaAux.setDeaths(deaths);
                deathsEncontrado = false;
            }
        }else if(qName.equals("geoId")){
            if(geoIdEncontrado){
                this.tablaAux.setGeoId(geoId);
                geoIdEncontrado = false;
            }
        }else if(qName.equals("countryterritoryCode")){
            if(countryterritoryCodeEncontrado){
                this.tablaAux.setCountryterritoryCode(countryterritoryCode);
                countryterritoryCodeEncontrado = false;
            }
        }else if(qName.equals("popData2018")){
            if(popData2018Encontrado){
                this.tablaAux.setPopData2018(popData2018);
                popData2018Encontrado = false;
            }
        }else if(qName.equals("record")){
            this.tabla.add(this.tablaAux);
        }    
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
       super.characters(ch, start, length); //To change body of generated methods, choose Tools | Templates.
        if(dateRepEncontrado){
            this.dateRep = new String(ch, start, length);
        }
        if(dayEncontrado){
            this.day = new String(ch, start, length);
        }
        if(monthEncontrado){
            this.month = new String(ch, start, length);
        }
        if(yearEncontrado){
            this.year = new String(ch, start, length);
        }
        if(continentExpEncontrado){
            this.continentExp = new String(ch, start, length);
        }
        if(countriesAndTerritoriesEncontrado){
            this.countriesAndTerritories = new String(ch, start, length);
        }
        if(casesEncontrado){
            this.cases = new String(ch, start, length);
        }
        if(deathsEncontrado){
            this.deaths = new String(ch, start, length);
        }
        if(geoIdEncontrado){
            this.geoId = new String(ch, start, length);
        }
        if(countryterritoryCodeEncontrado){
            this.countryterritoryCode = new String(ch, start, length);
        }
        if(popData2018Encontrado){
            this.popData2018 = new String(ch, start, length);
        }
       
    }

    public ArrayList<Tabla> getTabla() {
        return tabla;
    }

    public void setTabla(ArrayList<Tabla> tabla) {
        this.tabla = tabla;
    }
}
