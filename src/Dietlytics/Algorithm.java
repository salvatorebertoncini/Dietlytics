package Dietlytics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Client.Client;

public class Algorithm {
	
	//LEGGI MATRICE DA FILE TXT
	public static int readmatrix(){
		FileReader f;
		BufferedReader b=null;
		String s, split;
		int i; 
	    try {
			f=new FileReader("matrici/"+Dietlytics.user.getId()+".txt");
		    b=new BufferedReader(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
	    	System.out.println("STAMPA LETTURA DA FILE");
			System.out.println(s=b.readLine()); 
				split = s.toString();
				String[] items = split.split(" ");
				for(i=0;i<items.length;i++)
					Dietlytics.user.setAbitudini(0, i, Integer.parseInt(items[i]));
			System.out.println(s=b.readLine());
				split = s.toString();
				items = split.split(" ");
				for(i=0;i<items.length;i++)
					Dietlytics.user.setAbitudini(1, i, Integer.parseInt(items[i]));
			System.out.println(s=b.readLine());
				split = s.toString();
				items = split.split(" ");
				for(i=0;i<items.length;i++)
					Dietlytics.user.setAbitudini(2, i, Integer.parseInt(items[i]));
			System.out.println(s=b.readLine());
				split = s.toString();
				items = split.split(" ");
				for(i=0;i<items.length;i++)
					Dietlytics.user.setAbitudini(3, i, Integer.parseInt(items[i]));
			System.out.println(s=b.readLine());
				split = s.toString();
				items = split.split(" ");
				for(i=0;i<items.length;i++)
					Dietlytics.user.setAbitudini(4, i, Integer.parseInt(items[i]));
			
			System.out.println("STAMPA VALORI DALLA MATRICE MOMENTANEA");
			for(i=0;i<5;i++)
			{
				for(int j=0;j<3;j++)
					System.out.print(Dietlytics.user.getAbitudini(i,j)+" ");
				System.out.println("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    
	    return 1;
	}
	
	public static double algorithm(){
	
		double MBR;
		double TID;
		
		int sex, height, weight, age;
		float lifestyle;
		StringBuilder l= new StringBuilder(""), s = new StringBuilder(""), h = new StringBuilder(""), w = new StringBuilder(""), a = new StringBuilder("");
		boolean ok = Client.algorithminformations(Dietlytics.user.getId(), l, s, h, w, a);
		
		//prelevo l'anno di nascita dalla data di nascita
		String split = a.toString();
		System.out.println(split);
		String[] items = split.split("-");
		String YYYYbirthday = items[0];
		//calcolo l'età
		age= 2016 - Integer.parseInt(YYYYbirthday);
		
		sex=Integer.parseInt(s.toString());
		height=Integer.parseInt(h.toString());
		weight=Integer.parseInt(w.toString());
		lifestyle=Float.parseFloat(l.toString());
		System.out.println(age);
		System.out.println(s);
		System.out.println(h);
		System.out.println(w);
		System.out.println(l);
		
		if(sex==1)
			MBR = (9.99*weight)+(4.92*height)+(4.92*age)+5;
		else
			MBR = (9.99*weight)+(4.92*height)+(4.92*age)-161;
		
		TID = MBR*0.10;
		
		return MBR*lifestyle+TID;
	}
	
}
