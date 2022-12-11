import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class login extends JFrame implements ActionListener
{
JLabel l1,l2,l3;
JTextField t1;
JPasswordField p1;
JButton b1;
ResultSet rs;
public login()
{
Container d=getContentPane();
d.setLayout(null);
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con=DriverManager.getConnection("Jdbc:Odbc:libdsn","","");
Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
rs=st.executeQuery("select * from login");
}catch(Exception ee){}

l1=new JLabel("Sanmati Education Trust");
l2=new JLabel("Username");
l3=new JLabel("Password");
t1=new JTextField(20);
p1=new JPasswordField(8);
b1=new JButton("Login");

add(l1);
add(l2);
add(l3);
add(t1);
add(p1);
add(b1);
b1.addActionListener(this);
Font f=new Font("Courier",Font.BOLD,32);
l1.setFont(f);
b1.setFont(f);
f=new Font("Courier",Font.BOLD,20); 
l2.setFont(f);
l3.setFont(f);
t1.setFont(f);
p1.setFont(f);

setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setSize(600,500);
setVisible(true);
l1.setBounds(50,50,500,50);

l2.setBounds(150,150,200,30);
t1.setBounds(300,150,250,30);

l3.setBounds(150,190,200,30);
p1.setBounds(300,190,150,30);

b1.setBounds(200,275,200,70);
}
public void actionPerformed(ActionEvent tt)
{
try
{
boolean flag=false;
rs.first();
do
{
if(rs.getString("UserName").equals(t1.getText()) && rs.getString("password").equals(p1.getText()))
{
flag=true;
break;
}
}while(rs.next());
if(flag==true)
{
new frm2();
dispose();
}
else
{
t1.setText("");
p1.setText("");
JOptionPane.showMessageDialog(null,"InValid Parameters ","MyProj",2);
}
}catch(Exception ee){}
}
public static void main(String[] args)
{
new login();
}
}










