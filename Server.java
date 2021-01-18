import java.net.*;
import java.util.*;
import java.io.*;

public class Server
{
	public static void main(String args[]) throws IOException
	{
	
		InputStream in=null;
		BufferedReader bin=null;
		PrintWriter out=null;
		
		int control=1,operation,count=0,unum;
		
		System.out.println("Client bekleniyor...");
		
		ServerSocket ss= new ServerSocket(7213);
		Socket sc = ss.accept();
		
		System.out.println("Client bağlandı.");
			
		Random rand=new Random();
		int rnum=rand.nextInt(10)+1;
		System.out.println("Random Değer: "+rnum);
		
		while(control==1)
		{
		
			in=sc.getInputStream();
			bin = new BufferedReader(new InputStreamReader(in));
			unum = Integer.parseInt(bin.readLine());
			System.out.println("Yapılan Tahmin: "+unum);
			
			if(unum>0 && unum<=10)
			{
				if(rnum != unum && count==2) 
				{
					control=0;
					System.out.println("Doğru sayı bulunamadı.");
					System.out.println("Doğru Sayı: "+rnum);	
					out = new PrintWriter(sc.getOutputStream(),true);
					out.println(rnum);
						
				}
				
				if(rnum == unum)
				{
					control=0;
					System.out.println("Doğru sayı bulundu.");
					operation=111;
					out = new PrintWriter(sc.getOutputStream(),true);
					out.println(operation);	
				}
				
					
				if(rnum > unum && rnum!=unum)
				{
					if(count<2)
						System.out.println("Yükseltmeli");
					count++;
					operation=222;
					out = new PrintWriter(sc.getOutputStream(),true);
					out.println(operation);
				}

				if(rnum < unum && rnum!=unum)
				{
					
					if(count<2)
						System.out.println("Düşürmeli");
					count++;
					operation=333;
					out = new PrintWriter(sc.getOutputStream(),true);
					out.println(operation);
				}
			}
			
			else
			{
				operation=444;
				count++;
				System.out.println("Geçersiz sayı girildi.");
				if(count!=3)
				{
					out = new PrintWriter(sc.getOutputStream(),true);
					out.println(operation);
				}
				
				if(count==3)
				{
					out = new PrintWriter(sc.getOutputStream(),true);
					out.println(rnum);
					System.out.println("Doğru Sayı: "+rnum);	
					control=0;
				}
			}
		}
		sc.close();
	}
}
