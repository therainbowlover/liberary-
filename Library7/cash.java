import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
public class cash extends JFrame
{
JLabel l1,l2,l3,l4,l5,l6;
JTextField t1,t2,t3,t4;
JComboBox c1,c2;
JButton b1,b2,b3;
Statement st;
ResultSet rs;
Connection con;
public cash()
{
Container d=getContentPane();
d.setLayout(null);
l1=new JLabel("Cash & Collect ",JLabel.CENTER);
l1.setFont(new Font ("courier",Font.BOLD,30));
l2=new JLabel("Frno");
l3=new JLabel("FineAmt");
l4=new JLabel("PayDate");
l5=new JLabel("MemID");
l6=new JLabel("Payment");
t1=new JTextField(20);
t2=new JTextField(20);
t3=new JTextField(20);
t4=new JTextField(20);
c1=new JComboBox();
c2=new JComboBox();
b1=new JButton("Add");
b2=new JButton("Save");
b3=new JButton("Exit");

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("Jdbc:Odbc:libdsn","","");
st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

rs=st.executeQuery("select * from member where FineAmt>0");
while(rs.next())
{
c1.addItem(rs.getInt("MemId")+"");
c2.addItem(rs.getString("MemName")+"");
}

rs=st.executeQuery("select * from cash");
int large=0;
try
{
rs.first();
do
{
int e=rs.getInt("srno");
if(e>large)
large=e;
}while(rs.next());
}catch(Exception ueueue){}
large++;
t1.setText(large+"");
GregorianCalendar gg=new GregorianCalendar();
t3.setText(gg.get(Calendar.YEAR)+"-"+(gg.get(Calendar.MONTH)+1)+"-"+gg.get(Calendar.DAY_OF_MONTH));

}catch(Exception ee){System.out.println(ee);}

c1.addItemListener(new ItemListener(){
public void itemStateChanged(ItemEvent tt){
c2.setSelectedIndex(c1.getSelectedIndex());
try
{
String sss="Select * from member where memid="+(Integer.parseInt((String) c1.getSelectedItem()));
rs=st.executeQuery(sss);
rs.first();
t2.setText(rs.getFloat("FineAmt")+"");
}catch(Exception eee){}

}});

b1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
try
{

}catch(Exception ee){}
}});

b3.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
dispose();
}});

Font f= new Font("courier",Font.BOLD,20);
l2.setFont(f);
l3.setFont(f);
l4.setFont(f);
l5.setFont(f);
l6.setFont(f);
t1.setFont(f);
t2.setFont(f);
t3.setFont(f);
t4.setFont(f);
c1.setFont(f);
c2.setFont(f);
b1.setFont(f);
b2.setFont(f);
b3.setFont(f);

l1.setBounds(300,40,600,40);

l2.setBounds(150,150,210,40);
t1.setBounds(305,150,180,40);

l3.setBounds(150,200,185,40);
t2.setBounds(305,200,180,40);

l4.setBounds(150,250,185,40);
t3.setBounds(305,250,180,40);

l5.setBounds(550,150,150,40);
c1.setBounds(650,150,100,40);
c2.setBounds(780,150,100,40);

l6.setBounds(550,200,250,40);
t4.setBounds(650,200,150,40);

b1.setBounds(350,400,100,30);
b2.setBounds(460,400,100,30);
b3.setBounds(570,400,100,30);
d.add(l1);
d.add(l2);
d.add(l3);
d.add(l4);
d.add(l5);
d.add(l6);
d.add(b1);
d.add(b2);
d.add(b3);
d.add(t1);
d.add(t2);
d.add(t3);
d.add(t4);
d.add(c1);
d.add(c2);
b2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
try
{
rs=st.executeQuery("select * from cash");
rs.moveToInsertRow();
rs.updateInt("srno",Integer.parseInt(t1.getText()));
rs.updateInt("memId",Integer.parseInt((String) c1.getSelectedItem()));
rs.updateFloat("PayAmt",Float.parseFloat(t4.getText()));
java.sql.Date dt1=java.sql.Date.valueOf(t3.getText());
rs.updateDate("payDate",dt1);
rs.insertRow();

PreparedStatement pst=con.prepareStatement("update member set FineAmt=FineAmt-? where MemId=?");
pst.setFloat(1,Float.parseFloat(t4.getText()));
pst.setInt(2,Integer.parseInt((String) c1.getSelectedItem()));
int yy=pst.executeUpdate();

}catch(Exception ee){System.out.println(ee);}
}});

setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setSize(600,1000);
setVisible(true);
}
public static void main (String []args)
{
new cash();
}
}




