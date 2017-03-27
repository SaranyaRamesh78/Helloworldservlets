 import com.mongodb.*; 
import java.io.*;  
import javax.servlet.*;
import javax.servlet.http.*;
public class contactServlet extends HttpServlet{  
   public void handleRequest(HttpServletRequest req,HttpServletResponse res ) throws ServletException,IOException {
      res.setContentType("text/html"); 
      PrintWriter out = res.getWriter();  
	String user_aadhar=req.getParameter("user_aadhar");   
	String n1=req.getParameter("n1");
	String p1=req.getParameter("p1");
    	String n2=req.getParameter("n2");
	String p2=req.getParameter("p2");
    	String n3=req.getParameter("n3");
	String p3=req.getParameter("p3");
	try{ 
	out.println("Contacts");
    	MongoClient mongoClient = new MongoClient( "10.201.151.153" , 27017 ); 
        DB db = mongoClient.getDB("user");
 	DBCollection tc=db.getCollection("TrustedContacts");
   	out.println("Connect to database ");
	out.println("insert into Trusted Contacts");
	BasicDBObject doc= new BasicDBObject();
	doc.put("User Id",user_aadhar);
	doc.put("Contact1",n1);
	doc.put("Number1",p1);
	doc.put("Contact2",n2);
	doc.put("Number2",p2);
	doc.put("Contact3",n3);
	doc.put("Number3",p3);
	tc.insert(doc);
	out.println("Inserted ");  
     }
	catch(MongoException e){ 
         System.err.println( e.getClass().getName() + ": " + e.getMessage() ); 
      } 
   }
   public void doGet(HttpServletRequest request,HttpServletResponse response )throws ServletException,IOException 
	 {
	    handleRequest(request,response );

	}
	   public void doPost(HttpServletRequest request,HttpServletResponse response )throws ServletException,IOException 
	 {
	    handleRequest(request,response );

	}
} 