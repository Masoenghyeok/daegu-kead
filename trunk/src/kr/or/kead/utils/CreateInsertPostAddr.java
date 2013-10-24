package kr.or.kead.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CreateInsertPostAddr {
	
	public void makeInsertSql() {
		File readFile = new File("zipcode_20130201(1).txt");
		File sqlFile = new File("postAddr.sql");
		
		int seq;
		String zipcode;
		String sido;
		String gugun;
		String dong;
		String bunji;
		
		String[] data;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(readFile)));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sqlFile), "euc-kr"));
			String str;			
			br.readLine();
			while((str = br.readLine()) !=null) {				
				data = str.split("\t");
				//data[0]=zipcode, data[1]=sido, data[2]=gugun
				//data[3]=dong, data[4]=bunji, data[5]=seq				
				seq = Integer.parseInt(data[5]);
				zipcode = data[0];
				sido = data[1];
				gugun = data[2];
				dong = data[3];
				bunji = data[4];
				bunji = bunji.replace("(", "");
				bunji = bunji.replace(")", "");
				
				String sql = "insert into post values(" + seq + 
						",'"+ zipcode + "'" +
						",'"+ sido +  "'" +
						",'"+ gugun +  "'" +
						",'"+ dong + "'" +
						",'"+ bunji +  "');\n";
				bw.write(sql);
				bw.flush();
				System.out.print(sql);
			}
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
