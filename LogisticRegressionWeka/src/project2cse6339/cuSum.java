package project2cse6339;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class cuSum {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        Scanner stdin = new Scanner(System.in);
        int count=1;
        do {
            //System.out.println("Current list is " + list);
            
            if (list.size()<20) {
                System.out.println("Enter number"+count+" : ");
                list.add(stdin.nextInt());
                count++;
            } else {
                break;
            }
        } while (true);

       

        System.out.println("List is " + list);
        System.out.println("Enter refernce mean :");
        int mean = stdin.nextInt();
        System.out.println("your mean is ="+mean);
        System.out.println("Enter value of standard deviation:");
        int stdev = stdin.nextInt();
        System.out.println("your standard deviation is ="+stdev);
        System.out.println("Enter value of Sigma:");
        int sigma = stdin.nextInt();
        System.out.println("your sigma is ="+sigma);
        stdin.close();
        findCuSum(list,mean,stdev,sigma);
    }

	private static void findCuSum(List<Integer> list, int mean, int stdev, int sigma) {
		List<Integer> diff = new ArrayList<Integer>();
		for(Integer doc:list){
			int sub = doc-mean;
			diff.add(sub);
		}
		System.out.println("Difference from mean is = "+diff);
		List<Integer> cusum = new ArrayList<Integer>();
		cusum.add(diff.get(0));
		for(int i=1; i<diff.size();i++){
			int calc = diff.get(i)+cusum.get(i-1);
			cusum.add(calc);
		}
		System.out.println("cusum is :"+cusum);
		int threshold = stdev*sigma;
		System.out.println("threshold is :"+threshold);
		List<Integer> alert = new ArrayList<Integer>();
		for(Integer comp:cusum){
			if(comp>=threshold ){
				alert.add(comp);
			}
		}
		System.out.println("Alert values are :"+alert);
	     try {
	    	 File file = new File("C:/Users/Anurag/Desktop/cusum.csv");
		     if (!file.exists()) {
		     file.createNewFile();
		     }
		     FileWriter fw = new FileWriter(file.getAbsoluteFile());
	         BufferedWriter bw = new BufferedWriter(fw);
	         bw.write("cuSum \n");
	        
	         for(int k=0; k<cusum.size();k++){
	        	 bw.write(String.valueOf(cusum.get(k)));
	        	 bw.newLine();
	         }
	         
	         bw.close();
	     } catch (IOException e) {
			e.printStackTrace();
		}
	}
}
