import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class prog extends JFrame
{
JLabel l;
JTable tb;
ResultSet rs;
Statement st=null;
Connection con=null;
int r=0;
public prog()
{
Container d=getContentPane();
d.setLayout(null);
l=new JLabel("FineAmt List",JLabel.CENTER);
Font f=new Font("Courier",Font.BOLD,32);
l.setFont(f);
String hd[]={"MemID","Name","Type","FineAmt"};
Object dt[][]={{"1","sandip","stud","22000"},{"","","",""},{"","","",""},{"","","",""},{"","","",""}};
tb=new JTable(dt,hd);
JScrollPane jsp=new JScrollPane(tb);
jsp.setBounds(100,200,600,150);
l.setBounds(0,10,800,40);
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("Jdbc:Odbc:libdsn","","");
st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
rs=st.executeQuery("select * from member where FineAmt>0");
int i=0;
while(rs.next())
{
tb.setValueAt(rs.getInt("MemID")+"",i,0);
tb.setValueAt(rs.getString("MemName")+"",i,1);
tb.setValueAt(rs.getString("MemType")+"",i,2);
tb.setValueAt(rs.getString("FineAmt")+"",i,3);
i++;
}
}catch(Exception eee){}
d.add(l);d.add(jsp);
addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent tt){
dispose();
}});

addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent tt){
dispose();
}});
setSize(1000,600);
setVisible(true);
}
public static void main(String[] args)
{
new prog();
}}