import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.TreeMap;
import java.util.Vector;


public class evaluation {

	/**
	 * @param args
	 */
	static TreeMap<String,Vector<Integer>>Real=new TreeMap<String,Vector<Integer>>();
	public static void evaluate() {
		// TODO Auto-generated method stub
     TreeMap<String,Vector<Integer>>Prediction=new TreeMap<String, Vector<Integer>>();
     try{
    	 evaluation rev=new evaluation();
 		//rev.getResource();
    	BufferedReader br=new BufferedReader(new FileReader("D:/LR/4_16_4_11_LOG.txt"));
        BufferedWriter bw=new BufferedWriter(new FileWriter("resutlt.txt"));
    	String s=br.readLine();
    	int prenum=0;
    	int realnum=0;
    	while(s!=null){
    		String UID=s.split("\t")[0];
            if(!Prediction.containsKey(UID)){
            	Vector<Integer>BID=new Vector<Integer>();
            	String []sl=s.split("\t")[1].split(",");
            	for(int i=0;i<sl.length;i++)
            		BID.add(Integer.parseInt(sl[i]));
            	prenum+=sl.length;
            	Prediction.put(UID, BID);
            }
            s=br.readLine();
    	}
    	br=new BufferedReader(new FileReader("D:/LR/real7.txt"));
    	s=br.readLine();
    	while(s!=null){
    		String UID=s.split("\t")[0];
    		if(!Real.containsKey(UID)){
    			Vector<Integer>BID=new Vector<Integer>();
    			String []sl=s.split("\t")[1].split(",");
    			for(int i=0;i<sl.length;i++){
    				BID.add(Integer.parseInt(sl[i]));
    			}
    			realnum+=sl.length;
    			Real.put(UID, BID);
    		}
    		s=br.readLine();
    	}
    	int pB=0,hB=0;
    	for(String key:Prediction.keySet())
    	{
    		pB+=Prediction.get(key).size();
    	}
    	for(String key:Prediction.keySet()){
    		Vector<Integer> BID=Prediction.get(key);
    		for(Integer value:BID)
    		{
    			if(Real.containsKey(key))
    			if(Real.get(key).contains(value))
    				hB++;
    		}
    			}
    	double precision=(double)hB/pB;
    	bw.write("Precision: "+precision+"\n");
    	System.out.println("精确度="+precision);
    	System.out.println("hitBrand="+hB);
    	System.out.println("pBrands="+pB);
    	System.out.println("***************************");
    	int RbB=0,RhB=0;
    	for(String key:Real.keySet()){
    		RbB+=Real.get(key).size();
    	}
    	for(String key:Real.keySet()){
    		Vector<Integer>BID=Real.get(key);
    		for(Integer value:BID){
    			if(Prediction.containsKey(key))
    			if(Prediction.get(key).contains(value))
    				RhB++;
    		}
    	}
    	double Recall=(double)RhB/RbB; 
    	bw.write("Recall: "+Recall+"\n");
    	System.out.println("召回="+Recall);
    	System.out.println("hitBrand="+RhB);
    	System.out.println("rpBrands="+RbB);
    	double F1=2.0*precision*Recall/(precision+Recall);
    	bw.write("F1: "+F1+"\n");
    	System.out.println("F1: "+F1);
    	bw.write("realnum: "+realnum+"\n");
    	bw.write("prenum: "+prenum+"\n");
    	bw.close();
    	br.close();
     }catch(Exception e){
     e.printStackTrace();
	}
	}
}
