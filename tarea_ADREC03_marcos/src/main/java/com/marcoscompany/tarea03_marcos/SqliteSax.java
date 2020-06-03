/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcoscompany.tarea03_marcos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Marcos
 */
public class SqliteSax {
    
    private static Connection conexion = null;
    private static ResultSet execute = null;
    
    public SqliteSax() {
        super();
    }
    
    public static void leer_xml(String xml){ 
       XMLReader procesadorXml = null;
        try{
            procesadorXml = XMLReaderFactory.createXMLReader();
            TablaXml recordXml = new TablaXml();
            procesadorXml.setContentHandler(recordXml);
            InputSource archivoXml = new InputSource(xml);
            procesadorXml.parse(archivoXml);
            ArrayList<Tabla> values = recordXml.getTabla();

            for(Tabla item: values){
               volcar_datos("record", item.getDateRep(), Integer.parseInt(item.getDay().trim()), Integer.parseInt(item.getCases().trim()), Integer.parseInt(item.getDeaths().trim()), item.getCountriesAndTerritories());
            }
        } catch (SAXException e){
            e.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }        
    }
    
    public static void abrir_conexion(String db) {
         String pathDb = "jdbc:sqlite:" + db;        
        try{
            conexion = DriverManager.getConnection(pathDb);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
          
    public static void cerrar_conexion(){
      try{
        conexion.close();
      } catch(SQLException e){
          e.printStackTrace();
          System.out.println("ERROR AL CERRAR LA CONEXION");
      }
    }
      
    public static void insertTable(String db){
      try{
          String query = "CREATE TABLE IF NOT EXISTS record (id integer PRIMARY KEY AUTOINCREMENT, dateRep text, day integer, cases integer, deaths integer, countriesAndTerritories text);";
          Statement sentencia = conexion.createStatement();
          sentencia.execute(query);
      } catch(SQLException e){
          e.printStackTrace();
      } 
      
    }

     public static void volcar_datos(String tabla, String dateRep, int day, int cases, int deaths, String countriesAndTerritories){
        try{
            String query = "INSERT INTO "+tabla+"(dateRep, day, cases, deaths, countriesAndTerritories) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement item = conexion.prepareStatement(query);
            
            item.setString(1, dateRep);
            item.setInt(2, day);
            item.setInt(3, cases);
            item.setInt(4, deaths);
            item.setString(5, countriesAndTerritories);
            item.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        
    }
     
    public static ArrayList<String> paises_casos(int cant){
        ArrayList<String> consulta = new ArrayList ();
        try{            
            String query = "SELECT countriesAndTerritories, SUM(cases) FROM record GROUP BY countriesAndTerritories HAVING SUM(cases) > ?;";
            PreparedStatement sentencia = conexion.prepareStatement(query);            
            sentencia.setInt(1, cant);
            execute = sentencia.executeQuery();
            if(execute.next() == false){
                System.out.println("No se han encontrado paises");
            }else{
                while(execute.next()){
                    consulta.add("Pais: "+execute.getString(1) + "\nNumero de casos: " + execute.getString(2)+"\n\n");
                }
            }            
        } catch(SQLException e){
            e.printStackTrace();
        }
        return consulta;
    }
    
   public static ArrayList<String> numero_muertes(){
        ArrayList<String> consulta = new ArrayList ();
        try{
            String query = "SELECT countriesAndTerritories, MAX(deaths), dateRep FROM record GROUP BY countriesAndTerritories;";
            Statement stmt = conexion.createStatement();          
            execute = stmt.executeQuery(query);
                if(execute.next() == false){
                    System.out.println("No se han encontrado paises!!!");
                }else{
                    while(execute.next()){
                        consulta.add("Pais: "+execute.getString(1) + "\nMuertes: " + execute.getInt(2) + "\nDía: " + execute.getString(3)+"\n\n");
                    }
                }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return consulta;
    }
    
    
}
