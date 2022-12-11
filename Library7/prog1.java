import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class prog1 extends JFrame
{
JLabel l;
JTable tb;
ResultSet rs;
Statement st=null;
Connection con=null;

public prog1()
{
Container d=getContentPane();
d.setLayout(null);
l=new JLabel("Book List",JLabel.CENTER);
Font f=new Font("Courier",Font.BOLD,32);
l.setFont(f);
String hd[]={"BookId","Title","Author","Publication","Price","Status"};
Object dt[][]={{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""}};
tb=new JTable(dt,hd);
JScrollPane jsp=new JScrollPane(tb);
jsp.setBounds(100,150,600,150);
l.setBounds(0,10,800,40);
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("Jdbc:Odbc:libdsn","","");
st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
rs=st.executeQuery("select * from Book");
int i=0;
while(rs.next())
{
tb.setValueAt(rs.getInt("BookId")+"",i,0);
tb.setValueAt(rs.getString("Title")+"",i,1);
tb.setValueAt(rs.getString("Author")+"",i,2);
tb.setValueAt(rs.getString("Publicat")+"",i,3);
tb.setValueAt(rs.getFloat("Price")+"",i,4);
tb.setValueAt(rs.getString("Status")+"",i,5);
i++;
}
}catch(Exception eee){}
d.add(l);d.add(jsp);
addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent tt){
dispose();
}});
setSize(1000,600);
setVisible(true);
}
public static void main(String[] args)
{
new prog1();
}}