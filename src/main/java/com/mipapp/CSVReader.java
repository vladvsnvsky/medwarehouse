package com.mipapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class CSVReader {
    public static String delimiter = ",";
    public static void populate(String csvFileSource){
        try{
            File file = new File(csvFileSource);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line ="";
            String [] tempArr;
            int l=0;
            while((line = br.readLine())!= null){
                tempArr = line.split(delimiter);

                if(l>0) {
                    Product product = new Product(tempArr[0], tempArr[1], tempArr[2], tempArr[3], Integer.parseInt(tempArr[4]), tempArr[5]);
                    System.out.print(product.toString());

                    DatabaseController.insertProduct(product);
                }

                System.out.println();
                l++;
            }
            br.close();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void populateProducts(String csvFileSource){

    }
}
