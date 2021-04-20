package com.trie.index.implement;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] subStr=new String[n];
        int[] arr=new int[n];
        int i;
        int mid=(n/2)-1;
        String[] buffer=new String[100];
        for(i=0;i<100;i++)
        {
            buffer[i]="";
        }
        for(int a0 = 0; a0 < n; a0++){
            int x = in.nextInt();
            String s = in.next();
        if(a0<=mid)
            s="-";
            arr[a0]=x;
            buffer[arr[a0]]+=s+" ";
        }
            
        String mainStr="";
        //Arrays.stream(buffer).forEach(System.out::println);
        for(i=0;i<buffer.length;i++)
        {
            mainStr+=(buffer[i]!=""?buffer[i].trim():"")+(i!=buffer.length-1 && buffer[i]!=""?" ":"");
        }
        System.out.println(mainStr.trim());
        in.close();
    }
}