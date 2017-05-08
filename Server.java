import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

class ThreadHandler extends Thread
{
    
    private static final String port1 = null;
	Socket News;
    int n;
     static String q ;
	 static int k;
	static File log = new File("Volunteer.txt");
     ThreadHandler(Socket s,int v)
     {
       News=s;
       n=v;
     }

     public void run()
     { 
    	 
      try
        {    Scanner scan = new Scanner(System.in);
             DataInputStream  inp = new DataInputStream(News.getInputStream());
             DataOutputStream oup = new DataOutputStream(News.getOutputStream());
             String c = inp.readLine();
             System.out.println(" "+c );
             if(c.charAt(0)== 'V')
             {
            	 InetAddress addr = News.getInetAddress();
                 int         port = News.getPort();
                 String vport = Integer.toString(port-50000);
                 oup.writeBytes(vport);
                 oup.writeByte('\n');
                 
                 System.out.println("Thread created for Volunteer" );
                 System.out.println("client Details: IP: "+addr+"  Port:"+vport );
                 String Ram = inp.readLine();
                 String Disk = inp.readLine();
                 
                 
                   
                
                 String x=addr+" "+vport+" "+Ram+" "+Disk;
                
                 
                 
                 try{
                	        if(!log.exists()){
                	       
                	        log.createNewFile();
                	        }
                	 
                	        FileWriter fileWriter = new FileWriter(log, true);
                	        BufferedWriter br1 = new BufferedWriter(fileWriter);
                	 
                        br1.write(x);
                        br1.write('\n');
                	    br1.close();
                	    
                	    
                	} catch (IOException e) {
                		System.out.println("COULD NOT LOG!!");
                	}
                 
                              
                
                 
             }
             else
             {
                 System.out.println("Thread created for client" );
                 
                          String clientRam = inp.readLine();
          		  String clientDisk = inp.readLine();
          		  System.out.println("Client needs: Ram "+clientRam+" Disk: "+clientDisk);  
                          
                          String clientusername = inp.readLine();
          		  String clientuserpassord = inp.readLine();
    	      // Scanner scan = new Scanner(System.in);
              // DataInputStream  inp = new DataInputStream(News.getInputStream());
              // DataOutputStream oup = new DataOutputStream(News.getOutputStream());

                 BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(log))); 
                 String VolunteerDetails; 
       		     String line;
       		     while ((line = br.readLine()) != null) 
                 {
       			    VolunteerDetails=line;
                    String words[] = VolunteerDetails.split(" ");
                    
                 	System.out.println("Ip: "+words[0]+"Prot : "+words[1]+"Ram size: "+words[2]+"Disk Size : "+words[3]);
                 	
	                 	if(words[2].equals(clientRam))
	                 	{
	                 		if(words[3].equals(clientDisk))
	                 		{
	                 			 q =words[0].substring(1);
	                                         k =Integer.parseInt(words[1]);
                                                System.out.println(q+" "+k); 
	                 			
	                 		}
	                 	}
                     
                 }
                
       		 
       		     
       		     
                char ch;
                do    
		        {
		            String cl = inp.readLine();
		            int choice = Integer.parseInt(cl);           
		            switch (choice)
		            {
		            case 1 : 
		            	   System.out.println("Connecting to Volunteer :"); 
		            	  Socket sch=new Socket(q,k);
		                  System.out.println("connected with volunteer");
		                  DataInputStream  sinp = new DataInputStream(sch.getInputStream());
		                  DataOutputStream soup = new DataOutputStream(sch.getOutputStream());
		                  String schoice = Integer.toString(choice);
		                  soup.writeBytes(schoice);
		                  soup.writeByte('\n');
		                 
                                  System.out.println("Request sent to volunteer");
                                  soup.writeBytes(clientusername);
		                  soup.writeByte('\n');
                                  soup.writeBytes(clientuserpassord);
		                  soup.writeByte('\n');

		                  String vstatus = sinp.readLine();
		                  System.out.println(vstatus);
		                  
		                 
		                  
		                  oup.writeBytes(vstatus);
		                  oup.writeByte('\n'); 
		                  oup.writeBytes(q);
		                  oup.writeByte('\n'); 
		                
		                 
		                break;                          
		                                  
		                                     
		                 
		            default : 
		                System.out.println("Invalid Entry from the client  ");
		                break;   
            }
            
          
  
            
            String ctr = inp.readLine();
            ch = ctr.charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');  
             
             
       }
           
	                      
         News.close();
        }
        
        catch(Exception e) 
        {System.out.println(e);}  
	
  }  
}



public class Server
 {  
     public static void main(String[] args)
	{  
       int req=1001;
	   try	
	    {      int port=1234;
               
               ServerSocket ss=new ServerSocket(port); 
               
               for(;;)
               { 
                 Socket s=ss.accept();   //establishes connection  
                 System.out.println("Server started ");
                 Thread T =new ThreadHandler(s,req);
                 T.start();

                 req++;
               }

 	    }
          catch(Exception e) 
          {System.out.println(e);}  
        }  

}  

