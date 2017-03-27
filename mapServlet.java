import com.mongodb.*; 
import java.io.*;  
import javax.servlet.*;
import javax.servlet.http.*;
public class mapServlet extends HttpServlet{  
   public void handleRequest(HttpServletRequest req,HttpServletResponse res ) throws ServletException,IOException {
      res.setContentType("text/html"); 
      PrintWriter out = res.getWriter();     
	String lat=req.getParameter("lattitude");
	String aadhar=req.getParameter("aadhar");
	String lon=req.getParameter("longitude");
	String msg=req.getParameter("message");
  try{ 
	out.println("message:"+msg);
    	MongoClient mongoClient = new MongoClient( "localhost" , 27017 ); 
        DB db = mongoClient.getDB("map");
 	DBCollection danger=db.getCollection("danger");
   	out.println("Connect to database ");
	if(msg.equals("SOS"))
	{
 	out.println("insert into collection Danger");
	BasicDBObject doc= new BasicDBObject();
	doc.put("AadharNum",aadhar);
	doc.put("Lattitude",lat);
	doc.put("Longitude",lon);
	danger.insert(doc);
	out.println("Inserted document");  
	}
	else if(msg.equals("IMS"))
	{
	//delete from collection Danger
	BasicDBObject query = new BasicDBObject();
	query.append("AadharNum", aadhar);
	danger.remove(query);
	out.println("Deleted Data Successfully");

}
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