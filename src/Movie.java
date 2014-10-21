import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Movie
{
	public static void main(String[] args) {
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		String url="jdbc:hsqldb:hsql://localhost/";
		String uid="SA";
		String pwd="";
		String sql;
		
		
		
		int ch=0;
		Connection con = null;
		PreparedStatement ps1 = null,ps2 = null,ps3 = null,ps4 = null;
		ResultSet rs = null;
		try{
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			con= DriverManager.getConnection(url,uid,pwd);
			
			sql = "insert into movie(name,reason) values(?,?)";
			ps1 = con.prepareStatement(sql);
			
			sql = "select name,reason from movie";
			ps2 = con.prepareStatement(sql);
			
			sql = "update movie set reason = ? where name = ?";
			ps3 = con.prepareStatement(sql);
			
			sql = "delete from movie where name = ?";
			ps4 = con.prepareStatement(sql);
			
			String name,reason;
			
			while(ch!=5)
			{
				System.out.println("Press 1 to Add Movie ");
				System.out.println("Press 2 to Edit Movie ");
				System.out.println("Press 3 to Delete Movie ");
				System.out.println("Press 4 to Retrive Movie ");
				System.out.println("Press 5 to Exit ");
			
			
			while(!sc1.hasNext())
			{
				System.out.println("Enter the correct Input...");
				sc1.next();
			}
			ch = sc1.nextInt();
			switch(ch)
			{
			case 1: 
				System.out.println("Adding to DB");
				System.out.println("Enter the Movie Name:- ");
				name = sc2.nextLine();
				System.out.println("Enter The Reason:- ");
				reason = sc2.nextLine();
				
				ps1.setString(1, name);
				ps1.setString(2, reason);
				ps1.execute();
				break;
				
			case 2:
				System.out.println("Edit a Movie...");
				System.out.println("Enter the Movie name to Edit:- ");
				name = sc2.nextLine();
				System.out.println("Enter The new Reason:- ");
				reason = sc2.nextLine();
				
				ps3.setString(1, reason);
				ps3.setString(2, name);
				ps3.execute();
				break;
				
			case 3:
				System.out.println("Deleting a Movie/....");
				System.out.println("Enter a Movie name to Delete:- ");
				name = sc2.nextLine();
				
				ps4.setString(1, name);
				ps4.execute();
				System.out.println("Sussefully Deleted");
				break;
				
				
				
				
			case 4:
				System.out.println("Select Movies .. ");
				ps2.execute();
				rs = ps2.getResultSet();
				
				while(rs.next())
				{
					System.out.println("Name of the Movie :- "+rs.getString("name")+" Reason:- "+rs.getString("reason"));
				}
				break;
				
			case 5:
				System.out.println("Thank u visit again.......");
				System.exit(0);
				break;
			}
		}
	}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
			if(rs!=null)
				rs.close();
			if(ps1!=null)
				ps1.close();
			if(ps2!=null)
				ps2.close();
			if(con!=null)
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Finally");
			}
		}
		
		
	}
}