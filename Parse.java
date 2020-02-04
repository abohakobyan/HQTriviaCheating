package Core;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;






public class Parse {
	public static void Parse_string(String A) throws IOException {
		
		int count1=0;
		int count2=0;
		int count3=0;
		PrintWriter out = new PrintWriter("Answers.txt");
		out.println(A);
		out.close();
		String Answers = null;
		String Answer1 = null;
		String Answer2 = null;
		String Answer3 = null;
		
		
		if(A !=null) {
		for (int i = 0; i < A.length(); i++){
			char c = A.charAt(i);
			if(c=='?') {
				Answers = A.substring(i+1, A.length());{
				A = A.substring(0, i+1);}
				break;
			}
		}
	}
		if(Answers !=null) {
		Answers = Answers.trim().replaceAll("\n+", "\n");
		
		Answers = Answers.trim().replace('\r', '\n');
		
		for (int i = 0; i < Answers.length(); i++) {
			char q = Answers.charAt(i);
			if(q=='\n') {
				Answer1 = Answers.substring(0, i);
				Answers = Answers.substring(i+1, Answers.length());
				break;
			}
		}
		for (int i = 0; i < Answers.length(); i++) {
			char q = Answers.charAt(i);
			if(q=='\n') {
				Answer2 = Answers.substring(0, i);
				Answer3 = Answers.substring(i+1, Answers.length());
				
			}
		}
		}else {System.out.println("The answers are not here!");}
		A = A.trim().replaceAll("\n+", "\n");
		System.out.println(A);
		System.out.println(Answer1);
		System.out.println(Answer2);
		System.out.println(Answer3);
		String GLinks;
		A =  A.trim().replaceAll("\n", " ");
		A =  A.trim().replaceAll(" +", "+");
		
		GLinks = A;
		String Links[] = new String[30];
		
		Links = GoogleAPIJava.search(GLinks,0,Links);
		
		@SuppressWarnings("unused")
		boolean success= false;
		Document doc = null;
		
		int i =0;
		while(Links[i] != null) {
		try {
			doc = Jsoup.connect(Links[i])
				.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();
			success = true;
			//break;
		}catch(SocketTimeoutException ex){
            System.out.println("Failed connection to "+ Links[i]);
		}
		catch(IOException e) {}
		String All = null;
		try {
		Elements newsHeadlines = doc.select("*");
		//Elements newsHeadlines = doc.select("#mp-itn b a");
		All = newsHeadlines.text();
		//System.out.println(All);
		}catch (NullPointerException e) {}
		int count = All.length() - All.replace(Answer1+" ", "").length();
		count1 = count1+count;
		count = All.length() - All.replace(Answer2+" ", "").length();
		count2 = count2+count;
		count = All.length() - All.replace(Answer3+" ", "").length();
		count3 = count3+count;
		System.out.println(Answer1+":"+ count1);
		System.out.println(Answer2+":"+ count2);
		System.out.println(Answer3+":"+ count3);
		System.out.println("**************************************************");
		i++;
		}
		
		System.out.println("Numbers of Cycles:" + i);
		if(count1>count2 && count1>count3) {
			System.out.println("PICK " + Answer1 + " ANSWER 1");
		}else if(count2>count1 && count2>count3) {
			System.out.println("PICK " + Answer2 + " ANSWER 2");
		}else {
			System.out.println("PICK " + Answer3 + " ANSWER 3");
		}
		
		
		}
}
