package kr.or.kead.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateInsertPostAddr {
	
	public void makeInsertSql() {
		File readFile = new File("zipcode_20130201(1).txt");
		File sqlFile = new File("postAddr.sql");
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), "euc-kr"));
			String str;
			int cnt =0;
			while((str = br.readLine()) !=null) {
				cnt++;
				if(cnt ==10) break;
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
