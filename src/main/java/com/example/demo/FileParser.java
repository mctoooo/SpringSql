/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Student
 */
public class FileParser {
/**
 * 
 * @param pathName-path of the file.csv
 * @return ArrayList<String[]> where each cell contains one file line element. (else if the file does not exist return null)
 */    
  public static ArrayList<String[]> Parser(String pathName){
      try{
    
            CSVReader reader = new CSVReader(new FileReader(pathName)); 

                ArrayList TableCSV = new ArrayList<String[]>();
                String[] lineInArray;

                for(lineInArray = reader.readNext(); lineInArray != null; lineInArray = reader.readNext()){
                    TableCSV.add (lineInArray);
                }
//                
//                System.out.println("file: " + pathName);
//                System.out.println();
//                
//             reader = new CSVReader(new FileReader(pathName));
//                while((lineInArray  = reader.readNext()) != null)
//                {
//                    for(String line: lineInArray)
//                        System.out.print(line + " ");
//                    System.out.println();
//                }
                            
       return TableCSV;
        } catch (Exception ex) 
        {
            
            return  new ArrayList<>();
        }
  }
      
        public static String[][] ArrayListToArray(ArrayList<String[]>list){
            try{
            Object [] arr = list.toArray();
            String [][] array = null;
            int i=0;
            array = new String [arr.length][];
            for (Object row:arr)
            {
                array[i++]=(String[])row;
            }           
//           array=list.toArray(new String[list.size()][]);
//               for(String [] rows :array){ 
//                   for(String el: rows){
//            System.out.print(el+" ");
//                   } 
//                 System.out.println();  
//               }
            return array;
            } catch(Exception ex)
            {
                
            return new String[1][];
            }
        
        }
        
          
    }


