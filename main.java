

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.TreeMap;
import java.util.Vector;

public class main {
	public static void main(String[] args){
	try{
		convert con=new convert();
		con.run();
	//	PersonTest.readFile("D:/LR/pre.csv", "D:/LR/rank.csv");
		con.gensubmit();
		evaluation.evaluate();
	}catch(Exception e){
		e.printStackTrace();
	}
	}
}
