import java.io.*;  
import java.net.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;


 
class Client {
    public static void main(String args[])
        {   
                  
            try 
              {

                 Scanner scan = new Scanner(System.in);
                 
                 System.out.println("Enter Ip to connect server:");
                 
                 String ip=scan.next();
                 System.out.println("Enter port to connect server:");
                 
                 String sport=scan.next();
                                
              System.out.println("connecting to the server ");
                 
           //   BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Config.txt")));         
                   
                    int port = Integer.parseInt(sport);
                     Socket s=new Socket(ip,port);
                     System.out.println("Client Intitialized");
                     DataInputStream  inp = new DataInputStream(s.getInputStream());
                     DataOutputStream oup = new DataOutputStream(s.getOutputStream());

                    char ch;
      		  
		     do    
        		{
           		      System.out.println("\nDo you want to use Resources ? Id so,Press 'C'\n");
           		      String cla=scan.next();
           		      oup.writeBytes(cla);
                      oup.writeByte('\n');
                      
                      System.out.println("How much ram you need:");
                      String ram1=scan.next();
                      oup.writeBytes(ram1);
                      oup.writeByte('\n');
                      System.out.println("How much disk you need:");
                      String disk1=scan.next();
                      oup.writeBytes(disk1);
                      oup.writeByte('\n');
                      System.out.println("Create User Name:");
                      String user=scan.next();
                      oup.writeBytes(user);
                      oup.writeByte('\n');
                      System.out.println("Create User Password:");
                      String password=scan.next();
                      oup.writeBytes(password);
                      oup.writeByte('\n');
            		  System.out.println("Enter 1 to continue... ");
            		  
            		          
                 
           		         int choice = scan.nextInt(); 
                         String choice1 = Integer.toString(choice);
                         oup.writeBytes(choice1);
                         oup.writeByte('\n');
           
            		 switch (choice)
            		 {
            		  case 1 : 
                		  
                		         
                                    String ip21 = inp.readLine();
                                    System.out.println(ip21);
                                    String  Vip= inp.readLine();
                                    System.out.println("Volunteer IP is :"+Vip);
                                   break;                          
                                  
                         /* case 2 : 
                                    System.out.println("Enter key");
                                  oup.writeBytes(scan.next());
                                  oup.writeByte('\n');
                                    String ip6 = inp.readLine();
                                    System.out.println("Value = "+ip6 );
                                    break;  */
                                                          
                       
           		  default : 
                		   System.out.println("Wrong Entry  ");
                		   break;   
                           }
            
                       
            System.out.println("Do you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);   
             String str = Character.toString(ch);
             oup.writeBytes(str);
             oup.writeByte('\n');                     
        } while (ch == 'Y'|| ch == 'y'); 

  
                    } 
 
            catch (Exception e)
             {
            System.err.println("Error: " + e.getMessage());
             }
    }
}

