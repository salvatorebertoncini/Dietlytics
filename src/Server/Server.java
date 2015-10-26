package Server;

import java.net.*;

public class Server implements Runnable 
{
   Socket socket;
   static ServerSocket ssock;
   
   Server(Socket csocket) 
   {
      this.socket = csocket;
   }
   
   @Override
   public void run()
   {
   	// TODO Auto-generated method stub
   }  

   public static void main(String args[])
   {
	  try
	  {
	      ssock = new ServerSocket(6001);
	      System.out.println("In attesa del client...");

	      while (true) 
	      {
	         Socket sock = ssock.accept();
	         System.out.println("Effettuata nuova connessione al Server");
	      }
	  }catch(Exception e){
		  System.out.println(e.getMessage()+"->"+ssock);
	  }
	  finally
	  {
         if (ssock != null) 
         {
             try 
             {
            	 ssock.close();
             } 
             catch (Exception e) {}
         }
      }
   }   

}
      
   