import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class assignment4
{
	
	public static int n_admins = 0;
	public static int n_users = 0;
	public static int n_groups = 0;
	public static ArrayList<group> globalglist;
	public static ArrayList<admin> globalalist;
	public static ArrayList<user> globalulist;
	public Scanner in = new Scanner(System.in);
	public BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        

	
	public static void main(String []args) throws IOException 
	{
		int option;
		assignment4 app = new assignment4();
		
				
		globalglist = new ArrayList<group>();
 		globalalist = new ArrayList<admin>();
		globalulist = new ArrayList<user>();
		//String name = app.reader.readLine();
		//System.out.println(name); 

		
		app.enter_admins(app);
		app.enter_users(app);

				

		//String fileName = new String("input.txt");
		//try (Scanner scanner = new Scanner(new File(fileName)))
		//{
		//	while (scanner.hasNext())
			while(true)
			{
        		//String line = scanner.nextLine();
        		//String [] part = line.split(" ");

        		System.out.println("###############################################################################");
				System.out.println();
				System.out.println("Enter \"1 <number from admin list>\" - Select Admin and create group");
				System.out.println("Enter \"2 <number from user list> <number from group list>\" - Select user and request group");
				System.out.println("Enter \"3 <number from admin list> <number from group list>\" - Select user and request group");
				System.out.println("Enter \"4 <number from admin list> <number from group list> \" - Select admin and group and accept/ ignore requests");
				System.out.println("Enter \"5 <number from admin list> <number from group list> \" - Select admin and delete group");
				System.out.println("Enter \"6 <number from group list> \" - User leaving group");
				System.out.println("Enter \"7 <number from group list> <number from memberlist> \" - Send message in group from member list");
        		System.out.println("Enter \"8 <number from group list> <number from memberlist> <number from message list> \" - reply to message in group from member list");
        		System.out.println("Enter \"9 <source group from group list> <destination group from group list> <message number from source group > <member number from source group> \" - Forward message from source group to destination group and member number from source group");
        		System.out.println("Enter \"10 <member number from member list> \" - Print info about member");
        		
        		System.out.println("###############################################################################");
				
				app.print_admins(app);
				app.print_users(app);
				app.print_groups(app);
				option = app.in.nextInt();
				switch(option)
				{
					case 1:
					app.admin_create_group(app);
					break;
					case 2:
					app.user_group_request(app);
					break;
					case 3:
					app.admin_group_request(app);
					break;
					case 4:
					app.admin_group_request_action(app);
					break;
					case 5:
					app.admin_group_delete(app);
					break;
					case 6:
					app.member_group_leave(app);
					break;
					case 7:
					app.member_send_message_group(app);
					break;
					case 8:
					app.member_send_reply_message_group(app);
					break;
					case 9:
					app.forward_message(app);
					break;
					default:
					System.out.println("Incorrect choice");

				}

				
								
			}
		//}
		//catch (IOException e)
        //{
        //  e.printStackTrace();
       // }
	
	}


	public void forward_message(assignment4 app)
	{
		int sg = app.in.nextInt();
		int dg = app.in.nextInt();
		int mesn = app.in.nextInt();
		int memn = app.in.nextInt();
		sg--;
		dg--;
		mesn--;
		memn--;

		if (sg>=0 & dg>=0 & sg<=app.globalglist.size() & dg<=app.globalglist.size())
		{
			group sourceg = app.globalglist.get(sg);
			group destg = app.globalglist.get(dg);
			entity e;
			message m = sourceg.get_message(mesn);
			if (m!= null)
			{
				e = sourceg.getmember_from_list(memn);
				if (e!=null)
				{
					if(sourceg.ismember(e) && destg.ismember(e))
					{
						System.out.println(e.get_name()+" forwarding message from "+sourceg.get_name()+" to "+destg.get_name());
						destg.add_message(m);
					}
				}
			}

		}

	}

	public void member_send_reply_message_group(assignment4 app) throws IOException 
	{
		int temp1 = app.in.nextInt();
		temp1 = temp1 -1;//group
		int temp2=app.in.nextInt();
		temp2 = temp2 -1; //member
		int temp3=app.in.nextInt();
		temp3 = temp3 -1; //message number to reply to
		String reply;
		if(temp1<app.globalglist.size() & temp1>=0)
		{
			group g = app.globalglist.get(temp1);
			entity e = g.getmember_from_list(temp2);
			if(e!=null)
			{
				message m = g.get_message(temp3);
				if(m!= null)
				{
					System.out.println("Enter reply to message");
					reply = app.reader.readLine();
					m.r.set_text(reply);
					m.r.set_from(e);
				}
				else
				{
					System.out.println("message not found");
				}
			}
			else
				System.out.println("entity not part of memberlist");
		}
		

		

	}

	public void member_send_message_group(assignment4 app) throws IOException 
	{
		int temp1 = app.in.nextInt();
		temp1 = temp1 -1;
		int temp2=app.in.nextInt();
		temp2 = temp2 -1;
		String message_text = new String();
		String temp3;
		if(temp1<app.globalglist.size() & temp1>=0)
		{
			group g = app.globalglist.get(temp1);
			entity e = g.getmember_from_list(temp2);
			if(e!=null)
				{
					System.out.print("Enter Message Text: ");
					//message_text = app.in.next();
					message_text = app.reader.readLine();
					//message_text = app.reader.readLine();
					e.send_message(g,message_text);
				}
			else
				System.out.println("entity not part of memberlist");
		}
	}
	public void member_group_leave(assignment4 app)
	{
		int temp1 = app.in.nextInt();
		temp1 = temp1 -1;
		int temp2=0;
		if(temp1<app.globalglist.size() & temp1>=0 )
		{
			group g = app.globalglist.get(temp1);
			System.out.println("Enter a number from memberlist of group "+g.get_name()+" who is leaving");
			temp2 = app.in.nextInt();
			temp2 = temp2 - 1;
			g.remove_entity(temp2);
		}

	}

	public void admin_group_delete(assignment4 app)
	{
		int temp1 = app.in.nextInt();
		temp1 = temp1 -1;
		int temp2 = app.in.nextInt();
		temp2 = temp2 -1;
		if(temp1<app.globalalist.size() & temp1>=0 & temp2 <app.globalglist.size() & temp2>=0)
		{
			admin a = app.globalalist.get(temp1);
			entity e;
			group g = app.globalglist.get(temp2);
			if(g.su.equals(a))
			{
				a.delete_group(g);			
				app.globalglist.remove(temp2);
			}
		}
	}
	public void admin_group_request_action(assignment4 app)
	{
		
		int temp1 = app.in.nextInt();
		int temp2 = app.in.nextInt();
		if(temp1<=app.globalalist.size() & temp1>=0 & temp2 <=app.globalglist.size() & temp2>=0)
		{
		admin a =  app.globalalist.get(temp1-1);
		group g = app.globalglist.get(temp2-1);
		if(g.su.equals(a))
		{
			System.out.println("Select user number from request list and 0 to ignore and 1 to add");
			int temp3 = app.in.nextInt();
			temp3 = temp3-1;
			int temp4 = app.in.nextInt();
			g.request_action(temp3,temp4);

		}
		else
		{
			System.out.println("admin " + a.get_name()+" has no control over "+g.get_name());
		}
		System.out.println();
		}
	}

	public void admin_create_group(assignment4 app) throws IOException 
	{
		
		int temp1 = app.in.nextInt();
		if(temp1<=app.globalalist.size())
		{
		admin a =  app.globalalist.get(temp1-1);
		a.create_group(app);
		System.out.println("done");
		}
	}
	public void user_group_request(assignment4 app)
	{
		int temp1 = app.in.nextInt();
		int temp2 = app.in.nextInt();
		user u;
		group g;
		if(temp1 <= app.globalulist.size() & temp2 <= app.globalglist.size() & temp1 >-1 & temp2 > -1)
		{
			u = app.globalulist.get(temp1-1);
			g = app.globalglist.get(temp2-1);
			g.memberrequest(u);
		}

	}
	public void admin_group_request(assignment4 app)
	{
		int temp1 = app.in.nextInt();
		int temp2 = app.in.nextInt();
		admin a;
		group g;
		if(temp1 <= app.globalalist.size() & temp2 <= app.globalglist.size() & temp1 >0 & temp2 > 0)
		{
			a = app.globalalist.get(temp1-1);
			g = app.globalglist.get(temp2-1);
			g.memberrequest(a);
		}

	}
	public void print_users(assignment4 app)
	{
		System.out.println("User List");
		user u;
		for (int i=0; i<app.globalulist.size();i++)
			{
				u = app.globalulist.get(i);
				System.out.print((i+1)+". "+u.get_name()+" ");
				u.print_glist();
				System.out.println();
			}
		System.out.println();
	}

	public void print_groups(assignment4 app)
	{
		System.out.println("Group List");
		group g;

		for (int i=0; i<app.globalglist.size();i++)
			{
				g = app.globalglist.get(i);
			System.out.print((i+1)+". "+g.get_name()+" ,"+" admin "+g.su.get_name());
			g.print_memberlist();
			g.print_requestlist();
			System.out.println();
			g.print_mlist();
			}
			
		System.out.println();
	}

	public void print_admins(assignment4 app)
	{
		System.out.println("Admin List");
		admin a;
		for (int i=0; i<app.globalalist.size();i++)
			{
			a = app.globalalist.get(i);
			System.out.print((i+1)+". "+a.get_name());
			a.print_glist();
			System.out.println();
			}

			
		System.out.println();
	}

	public void enter_admins(assignment4 app) throws IOException 
	{
		System.out.print("Enter number of admins: ");
		app.n_admins = in.nextInt();
		for(int i=0;i<app.n_admins;i++)
		{
			
			admin a = new admin();
			System.out.print("Enter name of Admin: ");
			a.set_name(app.reader.readLine());
			System.out.print("Enter phone number: ");
			a.set_number(app.reader.readLine());
			System.out.print("Enter address: ");
			a.set_address(app.reader.readLine());
			//a.create_group(app);
			app.globalalist.add(a);
		}
	}

	public void enter_users(assignment4 app) throws IOException 
	{
		System.out.print("Enter number of users: ");
		app.n_users = in.nextInt();
		for(int i=0;i<app.n_users;i++)
		{
			
			user u = new user();
			System.out.print("Enter name of User: ");
			u.set_name(app.reader.readLine());
			System.out.print("Enter phone number: ");
			u.set_number(app.reader.readLine());
			System.out.print("Enter address: ");
			u.set_address(app.reader.readLine());
			app.globalulist.add(u);
		}
	}


	public void enter_groups(assignment4 app) throws IOException 
	{
		Scanner in = new Scanner(System.in); 
		System.out.print("Enter number of groups: ");
		app.n_groups = in.nextInt();
		for(int i=0;i<app.n_groups;i++)
		{
			
			group g = new group();
			System.out.print("Enter name of group: ");
			g.set_name(app.reader.readLine());
			app.globalglist.add(g);
		}
	}


}