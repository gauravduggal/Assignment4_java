import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class group 
{
  public String name;
  public admin su;
  public ArrayList<entity> memberlist;
  public ArrayList<entity> requestlist;
  public ArrayList<message> mlist;
  public group()
  {
   memberlist = new ArrayList<entity>();
   requestlist = new ArrayList<entity>();
   mlist = new ArrayList<message>(); 
   su = new admin();
  }
  public void set_name(String name)
  {
    this.name=name;
  }
  public void set_admin(admin su)
  {
    this.su = su;
    this.memberlist.add(su);
  }
  public void memberrequest(entity e)
  {
    if(!this.requestlist.contains(e) & !this.memberlist.contains(e) )
    {
    this.requestlist.add(e);
    }
    else
      System.out.println("Request already sent");
  }
  public String get_name()
  {
    return this.name;
  }
  public void print_requestlist()
  {
    System.out.print(", requests:");
    for(int i=0;i<this.requestlist.size();i++)
    {
      System.out.print(" "+(i+1)+"."+requestlist.get(i).get_name());
    }
  }

  public void print_memberlist()
  {
    System.out.print(", members:");
    for(int i=0;i<this.memberlist.size();i++)
    {
      System.out.print(" "+(i+1)+"."+memberlist.get(i).get_name());
    }
  }
  public void request_action(int i,int a)
  {

    if(i>=0 & i<=this.requestlist.size())
    {
      if(a==0)
        {
          this.requestlist.remove(i);

        }
      else if(a==1)
        {
          entity e = this.requestlist.remove(i);
          this.memberlist.add(e);
          e.accepted(this);
        }
      
    }
  }
  public void remove_entity(int entity_number)
  {
    if (entity_number >=0 & entity_number < this.memberlist.size()) 
      {
        if(!this.su.equals(this.memberlist.get(entity_number)))
          {
            entity e = this.memberlist.get(entity_number); 
            e.glist.remove(this);
            this.memberlist.remove(entity_number);

          }
        else
          System.out.println("Admin cannot leave group, consider deleting group");
        
      }
  }
  public boolean ismember(entity e)
  {
    return this.memberlist.contains(e);
  }
 public entity getmember_from_list(int i)
 {
  if (i>=0 && i < this.memberlist.size())
  return this.memberlist.get(i);
else
  return null;
 }

 public void add_message(message m)
 {
  this.mlist.add(m);
 }

 
 public void print_mlist()
 {
  System.out.println();
  System.out.println("                                      Message List");

  for(int i = 0;i<this.mlist.size();i++)
  {
    message m = mlist.get(i);
    entity e_message_owner = m.get_from();
    reply message_r = m.r;
    entity reply_owner = m.r.get_from();
      System.out.println("    "+(i+1)+". "+m.get_text()+" -"+e_message_owner.get_name());
    if (message_r.get_from()!=null)
    {
      System.out.println("        "+"- "+message_r.get_text()+" -"+reply_owner.get_name());
    }

  }
 }

 public message get_message(int i)
 {
  if(i>=0 & i < this.mlist.size())
    return this.mlist.get(i);
  else
    return null;
 }
}