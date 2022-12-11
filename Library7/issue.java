import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class issue extends JFrame
{
JLabel l,l1,l2,l3,l4;
JTextField t1,t2;
JComboBox c1,c2,c3,c4;
JButton b1,b2,b3;
ResultSet rs;
Statement st=null;
Connection con=null;
public issue()
{
Container d=getContentPane();
d.setLayout(null);

l=new JLabel("Issue Entry",JLabel.CENTER);
l1=new JLabel("Tr.No.");
l2=new JLabel("Book Id");
l3=new JLabel("Member.ID");
l4=new JLabel("IssueDate");

c1=new JComboBox();
c2=new JComboBox();
c3=new JComboBox();
c4=new JComboBox();

t1=new JTextField(20);
t2=new JTextField(20);

b1=new JButton("Add");
b2=new JButton("Save");
b3=new JButton("Exit");


try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("Jdbc:Odbc:libdsn","","");
st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
rs=st.executeQuery("select * from Book where status='Library'");
while(rs.next())
{
System.out.println("1");
c1.addItem(rs.getInt("BookId")+"");
c2.addItem(rs.getString("Title")+"");
}

rs=st.executeQuery("select * from member");
while(rs.next())
{
c3.addItem(rs.getInt("MemId")+"");
c4.addItem(rs.getString("MemName")+"");
}

rs=st.executeQuery("select * from bookissret");
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
t2.setText(gg.get(Calendar.YEAR)+"-"+(gg.get(Calendar.MONTH)+1)+"-"+gg.get(Calendar.DAY_OF_MONTH));

}catch(Exception ee){System.out.println(ee);}

c1.addItemListener(new ItemListener(){
public void itemStateChanged(ItemEvent tt){
c2.setSelectedIndex(c1.getSelectedIndex());
}});

c3.addItemListener(new ItemListener(){
public void itemStateChanged(ItemEvent tt){
c4.setSelectedIndex(c3.getSelectedIndex());
}});

Font f=new Font("Courier",Font.BOLD,32);
l.setFont(f);
f=new Font("Courier",Font.BOLD,22);
l1.setFont(f);l2.setFont(f);l3.setFont(f);l4.setFont(f);t1.setFont(f);t2.setFont(f);
c1.setFont(f);c2.setFont(f);c3.setFont(f);c4.setFont(f);b1.setFont(f);b2.setFont(f);b3.setFont(f);


l.setBounds(0,10,1000,40);

l1.setBounds(10,100,150,30);
t1.setBounds(200,100,200,30);

l2.setBounds(10,150,150,30);
c1.setBounds(200,150,100,30);
c2.setBounds(350,150,300,30);

l3.setBounds(10,200,150,30);
c3.setBounds(200,200,100,30);
c4.setBounds(350,200,300,30);

l4.setBounds(10,250,150,30);
t2.setBounds(200,250,200,30);

b1.setBounds(200,500,100,30);
b2.setBounds(400,500,100,30);
b3.setBounds(600,500,100,30);

d.add(l);
d.add(l1);
d.add(l2);
d.add(l3);
d.add(l4);
d.add(t1);
d.add(t2);
d.add(c1);
d.add(c2);
d.add(c3);
d.add(c4);
d.add(b1);
d.add(b2);
d.add(b3);
b1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
try
{

}catch(Exception ee){}
}});

b2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
try
{
rs=st.executeQuery("select * from bookissret");
rs.moveToInsertRow();
rs.updateInt("BookId",Integer.parseInt((String) c1.getSelectedItem()));
rs.updateInt("memId",Integer.parseInt((String) c3.getSelectedItem()));
java.sql.Date dt1=java.sql.Date.valueOf(t2.getText());
rs.updateDate("IssueDate",dt1);
rs.insertRow();
PreparedStatement pst=con.prepareStatement("update member set TotalBook=TotalBook+1 where MemId=?");
pst.setInt(1,Integer.parseInt((String) c3.getSelectedItem()));
int yy=pst.executeUpdate();
PreparedStatement pst1=con.prepareStatement("update book set status='Issue' where bookId=?");
pst1.setInt(1,Integer.parseInt((String) c1.getSelectedItem()));
yy=pst1.executeUpdate();


}catch(Exception ee){System.out.println(ee);}
}});

b3.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
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
new issue();
}
}