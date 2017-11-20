import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class admin extends entity
{
  //Attributes
  //private int strength;
  //public boolean flag;
 
  void admin()
  {
    
  }
  public void create_group(assignment4 app) throws IOException 
  {
    
     group g = new group();
     System.out.print("Enter Group Name: ");
     g.set_name(app.reader.readLine());
     g.set_admin(this);
     this.glist.add(g);
     app.globalglist.add(g);
   

   }

   public void delete_group(group g)
   {
      entity e;
      for(int i=0;i<g.memberlist.size();i++)
        {
          e = g.memberlist.get(i);
          e.glist.remove(g);
        }
      
   }
    
    

   


 
   
  
}