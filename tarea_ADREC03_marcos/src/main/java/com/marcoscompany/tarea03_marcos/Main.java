/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcoscompany.tarea03_marcos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Marcos
 */
public class Main {
    
    static boolean salir = false;
    static Scanner lector = new Scanner(System.in);
    static String archivo = "coronavirus.db";
    static File file = new File(archivo);
    static SqliteSax conexion = new SqliteSax();
    static int opc = 0;
    static String aux = "";      
        
    public static void main (String[] args) throws IOException{
        menu();        
    }
    
    public static void menu() throws IOException{
        do {
            System.out.println("_____________Bienvenido a la tarea 03 de Acceso a Datos_____________\n                       |_Gestion de datos_|\n");
            System.out.println("1.- Volcar datos. \n2.- Ver datos de paises por numero de casos.\n3.- Ver datos por dia de muertes.\n4.- Salir.\n");            
            aux = lector.nextLine();
            if(comprobarNum(aux)){
                opc = Integer.parseInt(aux);
            }
            switch (opc){
                case 1:
                    //EJERCICIO 1
                    if (!file.exists()) {
                        file.createNewFile();
                        conexion.abrir_conexion(archivo);
                        conexion.insertTable(archivo);
                        System.out.println("Esto puede tardar varios segundos...\n\n");
                        conexion.leer_xml("coronavirus.xml");            
                        conexion.cerrar_conexion();
                    }else{
                        System.out.println("Los datos ya se encuentran almacenados en la base de datos!\n\n");
                    }
                 break;
                 case 2:
                    ejercicio2();
                 break;
                 case 3:
                    ejercicio3();
                 break;
                 case 4:
                    out();
                 break;
                default:
                    System.out.println("__Opción no valida__");
                 break;
            }            
        }while (salir == false);    
    }
    
    public static void out(){
        System.out.println("Deseas salir (s/n)?");
        String fuera;
        fuera = lector.nextLine();
        if (fuera.toLowerCase().equals("s")){
            salir = true;
        }
    }
    
    public static boolean comprobarNum(String cadena) {
        boolean resultado;
            try {
                Integer.parseInt(cadena);
                resultado = true;
            } catch (NumberFormatException ex) {                
                resultado = false;
            }
            return resultado;
    }
    
    ///EJERCICIO 2
    public static void ejercicio2() throws IOException{
        System.out.println("Indicar que numero de caso buscar:");
        ArrayList datos = new ArrayList();
        int casos = lector.nextInt();
        conexion.abrir_conexion(archivo);
        datos = conexion.paises_casos(casos);
        conexion.cerrar_conexion();
        if(datos != null){
            for(int i = 0; i < datos.size(); i++){
                System.out.println(datos.get(i));
            }
        }
        lector.nextLine();
    }
    
    ///EJERCICIO 3
    public static void ejercicio3() throws IOException{
        ArrayList datos = new ArrayList();        
        conexion.abrir_conexion(archivo);
        datos = conexion.numero_muertes();
        conexion.cerrar_conexion();
         if(datos != null){
            for(int i = 0; i < datos.size(); i++){
                System.out.println(datos.get(i));
            }
        }
      //  lector.nextLine();
    }
}
