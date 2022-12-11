import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
public class bookret extends JFrame
{
JLabel l1,l2,l3,l4,l5,l6;
JTextField t1,t2,t3,t4,t5;
JButton b1,b2,b3,b4;
ResultSet rs;
Statement st=null;
Connection con=null;
int sno=-1;
public bookret()
{
Container d=getContentPane();
d.setLayout(null);
l1=new JLabel("Return",JLabel.CENTER);
l2=new JLabel("BookId");
l3=new JLabel("MemId");
l4=new JLabel("IssueDate");
l5=new JLabel("ReturnDate");
l6=new JLabel("FineAmt");
t1=new JTextField(20);
t2=new JTextField(20);
t3=new JTextField(20);
t4=new JTextField(20);
t5=new JTextField(20);
b1=new JButton("Show");
b2=new JButton("Add");
b3=new JButton("Save");
b4=new JButton("Exit");

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("Jdbc:Odbc:libdsn","","");
st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
}catch(Exception ee){}
Font f=new Font("Courier",Font.BOLD,32);
l1.setFont(f);
f=new Font("Courier",Font.BOLD,20);
b1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
try
{
String str1="select * from bookissret where returndate is null and bookid="+Integer.parseInt(t1.getText());
rs=st.executeQuery(str1);
rs.first();
t2.setText(rs.getInt("memid")+"");
t3.setText(rs.getDate("issuedate")+"");
sno=rs.getInt("srno");
GregorianCalendar gg=new GregorianCalendar();
t4.setText(gg.get(Calendar.YEAR)+"-"+(gg.get(Calendar.MONTH)+1)+"-"+gg.get(Calendar.DAY_OF_MONTH));


String xx=t3.getText().trim();
 
//java.util.Date d1 = new java.util.GregorianCalendar(,xx.substring(xx.indexOf('-')+1,,23,59).getTime();
int yr=Integer.parseInt(xx.substring(0,xx.indexOf('-')));
int mn=Integer.parseInt(xx.substring(xx.indexOf('-')+1,xx.lastIndexOf('-')));
mn--;
int da=Integer.parseInt(xx.substring(xx.lastIndexOf('-')+1));
java.util.Date d1 = new GregorianCalendar(yr, mn, da, 23, 59).getTime();
    /** Today's date */
    java.util.Date today = new java.util.Date();

    // Get msec from each, and subtract.
    long diff = today.getTime() - d1.getTime();
int days=(int)(diff / (1000 * 60 * 60 * 24));
float fine=0;
if(days>7)
fine=(days-7)*1.25f;
t5.setText(fine+"");
    
}catch(Exception ee){}
}});

b2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
try
{
t1.setText("");
t2.setText("");
t3.setText("");
t5.setText("");
t4.setText("");
}catch(Exception ee){}
}});

b3.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
try
{
String sss1="select * from bookissret where srno="+sno;
rs=st.executeQuery(sss1);
rs.first();
java.sql.Date dt1=java.sql.Date.valueOf(t4.getText());
rs.updateDate("returndate",dt1);
rs.updateFloat("Fineamt",Float.parseFloat(t5.getText()));
rs.updateRow();

PreparedStatement pst=con.prepareStatement("update member set TotalBook=TotalBook-1,FineAmt=FineAmt+? where MemId=?");
pst.setFloat(1,Float.parseFloat(t5.getText()));
pst.setInt(2,Integer.parseInt(t2.getText()));
int yy=pst.executeUpdate();
PreparedStatement pst1=con.prepareStatement("update book set status='Library' where bookId=?");
pst1.setInt(1,Integer.parseInt(t1.getText()));
yy=pst1.executeUpdate();

}catch(Exception ee){}
}});

b4.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
try
{
dispose();
}catch(Exception ee){}
}});

l2.setFont(f);
l3.setFont(f);
l4.setFont(f);
l5.setFont(f);
l6.setFont(f);
t1.setFont(f);
t2.setFont(f);
t3.setFont(f);
t4.setFont(f);
t5.setFont(f);
b1.setFont(f);
b2.setFont(f);
b3.setFont(f);
b4.setFont(f);
d.add(l1);
d.add(l2);
d.add(l3);
d.add(l4);
d.add(l5);
d.add(l6);
d.add(t1);
d.add(t2);
d.add(t3);
d.add(t4);
d.add(t5);
d.add(b1);
d.add(b2);
d.add(b3);
d.add(b4);

l1.setBounds(0,10,1000,40);

l2.setBounds(10,100,100,30);
t1.setBounds(200,100,100,30);

b1.setBounds(400,100,100,30);

l3.setBounds(10,200,100,30);
t2.setBounds(200,200,200,30);

l4.setBounds(500,200,200,30);
t3.setBounds(630,200,200,30);

l5.setBounds(10,300,200,30);
t4.setBounds(200,300,200,30);

l6.setBounds(500,300,100,30);
t5.setBounds(630,300,200,30);

b2.setBounds(200,500,100,30);
b3.setBounds(400,500,100,30);
b4.setBounds(600,500,100,30);

addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent tt){
dispose();
}});
setSize(1000,600);
setVisible(true);
}
public static void main(String[] args)
{
new bookret();
}
}







