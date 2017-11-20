import java.util.*;
public abstract class entity
{
  
  //Attributes
  public String username;
  public String phonenumber;
  public String address;
  public ArrayList<group> glist;

  public entity() {
   
     glist = new ArrayList<group>();
   }
 
   public String get_name()
   {
      return this.username;
   } 
   public String get_number()
   {
      return this.phonenumber;
   }
   public void set_number(String n)
   {
      this.phonenumber = n;
   }

   public void set_address(String address)
   {
      this.phonenumber = address;
   }

   public void set_name(String name)
   {
      this.username = name;
   }
   public void req_add(group g)
   {
      g.memberrequest(this);
   }
  public void print_glist()
  {
  	System.out.print(", groups:");
  	for(int i=0;i<this.glist.size();i++)
  		System.out.print(" "+(i+1)+"."+this.glist.get(i).get_name());
  }
  
  public void accepted(group g)
   {
    this.glist.add(g);
   }

   public void send_message(group destgroup,String text)
   {
    message m = new message();
    m.set_destgroup(destgroup);
    m.set_from(this);
    m.set_text(text);
    destgroup.add_message(m);
   }
   
}