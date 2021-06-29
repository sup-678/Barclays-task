package com.task;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MessageDecrypt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

               System.out.print("Enter the path file : ");
               String filePath = sc.nextLine();
               System.out.println();

               System.out.print("Enter initial triplet : ");
               String initialTriplet = sc.nextLine().toUpperCase();
               System.out.println();
               sc.close();

               try{

                    Scanner scanner = new Scanner(Paths.get(filePath));
                    scanner.useDelimiter("\n");

                   Map<String, String> leftPaddingContent = new HashMap<>();
                   Map<String, StringBuilder> rightCounterpartPadding = new HashMap<>();

                   String encodedContent;
                   String[] partitions;

                   while(scanner.hasNext()){
                       encodedContent = scanner.nextLine();
                       partitions = encodedContent.split("#");

                       leftPaddingContent.put(partitions[0].trim().toUpperCase(),partitions[1]);
                       rightCounterpartPadding.put(partitions[0].trim().toUpperCase(),new StringBuilder(partitions[2].trim().toUpperCase()));

                   }
                   StringBuilder sb = new StringBuilder();

                   while (leftPaddingContent.get(initialTriplet) != null){
                       sb.append(leftPaddingContent.get(initialTriplet));
                       initialTriplet = rightCounterpartPadding.get(initialTriplet).reverse().toString();
                   }
               System.out.println("Decoded message : " +sb);
                   scanner.close();
               }
               catch(IOException e){
                   System.out.println("Specified file not found : " + filePath);
               } catch (Exception e){
                   System.out.println("Unexpected error coming while processing file : " + e.getMessage());
               }
        }
    }

