import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;



class ThreadHandler1 extends Thread
{
  
     
    private static final String port1 = null;
	Socket vNews;
    int vn;
    
     ThreadHandler1(Socket s,int v)
     {
       vNews=s;
       vn=v;
     }

     public void run()
     { 
      try
        {   
           System.out.println("Thread created " );
    	   Scanner scan = new Scanner(System.in);
           DataInputStream  sinp = new DataInputStream(vNews.getInputStream());
           DataOutputStream soup = new DataOutputStream(vNews.getOutputStream());
            
          
		   char ch;
           do    
           {
            String c = sinp.readLine();
            int choice = Integer.parseInt(c);           
            switch (choice)
            {
            case 1 : 
            	  System.out.println(" Volunteer Initilized :"); 
                  String clientuser = sinp.readLine(); 
                  String clientpasswrd = sinp.readLine();                
                  String ip15="Success...Userid and password Received.";
                //  String cred=clientuser+" "+clientpasswrd+" ";
                 //  File credentials = new File("credentials.txt");

PrintWriter f0 = new PrintWriter(new FileWriter("credentials.txt"));

    f0.println(clientuser);
f0.println(clientpasswrd);
f0.close();
                           

                          
       
                     /*     try{
                	       
                	           FileWriter fileWriter = new FileWriter(credentials, true);
                	            BufferedWriter br1 = new BufferedWriter(fileWriter);
                	 
                       		 br1.write(clientuser);
                                 br1.write('\n');
                                 br1.write(clientpasswrd);
                                 br1.write('\n');

                	         br1.close();
                	    
                	    
                	     } 
                          catch (IOException e) {
                		System.out.println("COULD NOT LOG!!");
                	     }*/

                  System.out.println(clientuser+" "+clientpasswrd);
                  soup.writeBytes(ip15);
                  soup.writeByte('\n'); 

                    try {



                         String target = new String("/home/lab-4/hello.sh");
                         Runtime rt = Runtime.getRuntime();
                         Process proc = rt.exec(target);
                         proc.waitFor();
                         StringBuffer output = new StringBuffer();
                         BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                         String line = "";
                         while ((line = reader.readLine())!= null) {
                                output.append(line + "\n");
                         }
                         System.out.println("### " + output);
                         } catch (Throwable t) {
                             t.printStackTrace();
                        }




                   break;                          
                                  
            default : 
                           System.out.println("Invalid Entry from the Manager  ");
                break;   
            }
            
          
  
            
            String ctr = sinp.readLine();
            ch = ctr.charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');  

           
	                      
       // News.close();
        }
        
        catch(Exception e) 
        {System.out.println(e);}  
	
  }  
}



public class Volunteer
 {  
	static int listenport;
     public static void main(String[] args)
	{  
       int req=1002;
       
	   try	
	    {     
		   Scanner scan = new Scanner(System.in);
		   System.out.println("\nDo you want to Donate Resources? If so, then enter 'V'\n");
		   char c = (char) System.in.read();
		   
		   if(c=='V')
		   {
			                 
               System.out.println("Enter Ip to connect Main server:");
               String ip=scan.next();
 
               System.out.println("Enter port to connect Main server:");
               String sport=scan.next();
                              
               System.out.println("connecting to the server ");
               
               int port = Integer.parseInt(sport);
               
               
               Socket V=new Socket(ip,port);
               System.out.println("Volunteer ready to donate Resources "); 
               DataInputStream  inp = new DataInputStream(V.getInputStream());
               DataOutputStream oup = new DataOutputStream(V.getOutputStream());
               String vla=String.valueOf('V');
    		   oup.writeBytes(vla);
               oup.writeByte('\n');
               
               String thisport = inp.readLine();
               listenport = Integer.parseInt(thisport);
               System.out.println("Listen Port:"+listenport);
               
               System.out.println("Enter the Ram Size:");
               String ram=scan.next();
               oup.writeBytes(ram);
               oup.writeByte('\n');
               System.out.println("Enter the disk size:");
               String disk=scan.next();
               oup.writeBytes(disk);
               oup.writeByte('\n');
		   }
		   
		       
               ServerSocket ss=new ServerSocket(listenport); 
               
               for(;;)
               { 
                 Socket s=ss.accept();   //establishes connection  
                 System.out.println("Volunteer started ");
                 Thread T =new ThreadHandler1(s,req);
                 T.start();

                 req++;
               }

 	    }
          catch(Exception e) 
          {System.out.println(e);}  
        }  

}  
