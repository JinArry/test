import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class Top100 {//取矩阵分解之后的前100名推荐给用户
	static ArrayList<String> user_Id=new ArrayList<String>();
	static ArrayList<String> brand_Id=new ArrayList<String>();
	public static void list(String user_IdFile,String brand_IdFile) {
		File user=new File(user_IdFile);
		File brand=new File(brand_IdFile);
		BufferedReader user_br=null;
		BufferedReader brand_br=null;
		try {
			user_br=new BufferedReader(new FileReader(user));
			brand_br=new BufferedReader(new FileReader(brand));
			String user_line,brand_line;
			int countUser = 0,countBrand=0;
			while ((user_line=user_br.readLine())!=null) {
				user_Id.add(user_line);
				countUser=countUser+1;
			}
			while ((brand_line=brand_br.readLine())!=null) {
				brand_Id.add(brand_line);
				countBrand=countBrand+1;
			}
			System.out.println(countUser);
			System.out.println(countBrand);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	public static void PredictResult(String inputfile,String outputfile) {
		File scoreFile=new File(inputfile);
		File pairFile=new File(outputfile);
		BufferedReader scorebr=null;
		BufferedWriter scorewr=null;
		int a=0;
		try {
			scorebr=new BufferedReader(new FileReader(scoreFile));
			scorewr=new BufferedWriter(new FileWriter(pairFile));
			String line;
			String depart="|";
			while ((line=scorebr.readLine())!=null) {
				if (line.indexOf(depart)>=0) {
					a=Integer.parseInt(line.substring(0, line.indexOf("|")));
					System.out.println(a);
				}
				else {
					int b=Integer.parseInt(line.split("\t")[0]);
					scorewr.write(user_Id.get(a)+","+brand_Id.get(b)+","+line.split("\t")[3]);
					scorewr.newLine();
					scorewr.flush();
				}
			}
			scorewr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	

}
