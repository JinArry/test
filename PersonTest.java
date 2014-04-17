import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class PersonTest {
//	static int N;
	static ArrayList<Person> list=new ArrayList<Person>();
	public static void readFile(String inputfile,String outputfile) {
		File input=new File(inputfile);
		File output=new File(outputfile);
		BufferedReader br=null;
		BufferedWriter wr=null;
		try {
			br=new BufferedReader(new FileReader(input));
			wr=new BufferedWriter(new FileWriter(output));
			String line;
			while ((line=br.readLine())!=null) {
				String s[]=line.split(",");
				Person p=new Person(s[0],s[1],Double.parseDouble(s[2]));
				list.add(p);
			}
			PersonComparator comparator=new PersonComparator();
			Collections.sort(list,comparator);
			for (int i = 0; i < list.size(); i++) {//按score把每个user_Id对应的brand_Id进行排序
				wr.write(list.get(i)+"");
				wr.newLine();
				wr.flush();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
