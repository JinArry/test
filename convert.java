

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.TreeMap;

import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.meta.Bagging;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class convert {
	public void run(){
		String s="";
		try{
			BufferedReader trainbr=new BufferedReader(new FileReader("D:/LR/new/train.arff"));
			BufferedReader testbr=new BufferedReader(new FileReader("D:/LR/new/test_original.arff"));
//			BufferedReader trainbr=new BufferedReader(new FileReader("D:/LR/trainnorbalance.arff"));
//			BufferedReader testbr=new BufferedReader(new FileReader("D:/LR/testnor.arff"));
			Instances train=new Instances(trainbr);
            Instances test=new Instances(testbr);
			train.setClassIndex(train.numAttributes()-1);
			test.setClassIndex(test.numAttributes()-1);
//**************************************************
			
//            J48 j48=new J48();
//            j48.setMinNumObj(3);
//            j48.setUnpruned(true);
//            j48.buildClassifier(train);
			
//**************************************************
//			RandomForest rf=new RandomForest();
//			rf.setNumTrees(200);
//          rf.buildClassifier(train);
//**************************************************
			
			Logistic logic=new Logistic();
			logic.buildClassifier(train);
			
//**************************************************
            
//             Bagging bag=new Bagging();
//             bag.setNumIterations(60);
//             bag.buildClassifier(train);
             
//**************************************************            
//             SimpleLogistic sLogistic=new SimpleLogistic();
//             sLogistic.buildClassifier(train);
//             sLogistic.setErrorOnProbabilities(true);  //true的效果优于false
//             sLogistic.setUseCrossValidation(true);  //true的效果比false的效果好         
//             sLogistic.setDebug(false);           //没作用，false和true的效果是一样的           
//            sLogistic.setHeuristicStop(20);      //尝试了很数字，但是，感觉没有什么用
//            sLogistic.setMaxBoostingIterations(50);//参数有一定的影响，当参数很小时，F值比较小。当参数大到一定程度时，F值也就跟没有这个参数时一样。
//             sLogistic.setNumBoostingIterations(100);//参数有一定的影响，当参数很小时，F值比较小。当参数大到一定程度时，F值也就跟没有这个参数时一样。            
//            sLogistic.setUseAIC(true);            //没有影响
//             sLogistic.setWeightTrimBeta(2.0);
             
             
			int num=test.numInstances();
//			System.out.println(num);
//		    double []result=new double[num];
			BufferedReader br=new BufferedReader(new FileReader("D:/LR/UB.csv"));
			BufferedWriter bw=new BufferedWriter(new FileWriter("D:/LR/pre.csv"));
			
//			for(int i=0;i<num;i++){
//				bw.write(logic.distributionForInstance(test.instance(i))[1]+"\n");
//			}
			String line;
			int i=0;
			while ((line=br.readLine())!=null) {
				bw.write(line+","+logic.distributionForInstance(test.instance(i))[1]+"\n");
				i++;
			}
			bw.close();
		}catch(Exception e){
			System.out.println(s);
			e.printStackTrace();
		}
	}
	
	public void gensubmit(){
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter("D:/LR/4_16_4_11_LOG.txt"));
			BufferedReader pre=new BufferedReader(new FileReader("D:/LR/rank.csv"));
			BufferedReader diffbuy=new BufferedReader(new FileReader("D:/LR/diffbuy.csv"));
			String s=diffbuy.readLine();
			TreeMap <String,Double>tm=new TreeMap<String,Double>();
			TreeMap <String,String>sub=new TreeMap<String,String>();
			while(s!=null){
			 tm.put(s.split(",")[0], Double.parseDouble(s.split(",")[1]));	
			 s=diffbuy.readLine();
			}
			s=pre.readLine();
			while(s!=null){
				String[]sl=s.split(",");
				double count=tm.get(sl[0])*1;
				if(count<1)
					count=count+3;
				else {
					if(count>=10)
						count=10;
				}
				while(s!=null&&s.split(",")[0].equals(sl[0])){
					if(count>0){
						if(sub.containsKey(sl[0])){
							String value=sub.get(sl[0])+","+s.split(",")[1];
							sub.put(sl[0], value);
						}
						else{
							sub.put(sl[0], s.split(",")[1]);
						}
						count--;
					}
					s=pre.readLine();	
				}
				
			}
			for(String key:sub.keySet()){
				bw.write(key+"\t");
					bw.write(sub.get(key));
						bw.write("\n");
			}
			bw.close();
			System.out.println("OK");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
