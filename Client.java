import java.net.*;
import java.util.*;
import java.io.*;

public class Client
{	
	public static void main(String args[]) throws IOException, UnknownHostException
	{
		PrintWriter out=null;
		InputStream in=null;
		BufferedReader bin=null;
		int unum,operation,control=1;
		
		Scanner num=new Scanner(System.in);
		Socket socket = new Socket("localhost",7213);
		System.out.println("Server'a bağlanıldı.");
		
		int counter=0,counter2=0,temp;
		System.out.println("1-10 arasında bir tam sayı giriniz (1 ve 10 da dahil).");
	
		while(control==1)
		{
		
			unum = num.nextInt();
			
			out = new PrintWriter(socket.getOutputStream(),true);
			out.println(unum);
			
			in=socket.getInputStream();
			bin = new BufferedReader(new InputStreamReader(in));
			operation=Integer.parseInt(bin.readLine());
				
			counter++;
			temp=3-counter;
			
				if(operation==111) 
				{
					control=0;
					System.out.println("Doğru sayıyı buldunuz!");	
				}
				
				else if(operation==222)
				{
					System.out.println("Yükseltin!");
					System.out.println("Kalan hak sayınız:"+temp);
					counter2++;
				}
				
				else if(operation==333)
				{
					System.out.println("Düşürün!");
					System.out.println("Kalan hak sayınız:"+temp);
					counter2++;
				}
			  
			  	else if(operation==444)
				{
					counter2++;
					System.out.println("Geçersiz sayı girdiniz. Lütfen 1-10 arasında bir tam sayı giriniz.");
					System.out.println("Kalan hak sayınız:"+temp);
					if(counter2==3)
						control=0;	
				}
				
			 	else
				{
					if(unum>0 && unum<=10)
					{
						control=0;
						System.out.println("Doğru sayıyı bulamadınız.");
						System.out.println("Doğru Sayı: "+operation);		
					}
					
					else
					{
						control=0;
						System.out.println("Geçersiz sayı girdiniz.");
						System.out.println("Doğru Sayı: "+operation);	
					}		
				}
		}	
		socket.close();
	}
}	
