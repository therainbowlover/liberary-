import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class member extends JFrame implements ActionListener
{
JLabel l,l1,l2,l3,l4,l5,l6,l7;
JTextField t1,t2,t3,t4,t5;
JTextArea ta1;
JRadioButton r1,r2;
JButton b1,b2,b3,b4,b5,b6;
ResultSet rs;
public member()
{
Container d=getContentPane();
d.setLayout(null);
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con=DriverManager.getConnection("Jdbc:Odbc:libdsn","","");
Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
rs=st.executeQuery("select * from member");
}catch(Exception ee){System.out.println(ee);}
l=new JLabel("Members Entry Form",JLabel.CENTER);
l1=new JLabel("MemberId");
l2=new JLabel("Name");
l3=new JLabel("Type");
l4=new JLabel("Address");
l5=new JLabel("City");
l6=new JLabel("MobileNo");
l7=new JLabel("EmailId");
t1=new JTextField(20);
t2=new JTextField(20);
t3=new JTextField(20);
t4=new JTextField(20);
t5=new JTextField(20);
ta1=new JTextArea(4,60);
JScrollPane jsp1=new JScrollPane(ta1);
r1=new JRadioButton("Teacher");
r2=new JRadioButton("Student");
ButtonGroup bg=new ButtonGroup();
bg.add(r1);
bg.add(r2);
b1=new JButton("Add");
b2=new JButton("Save");
b3=new JButton("Update");
b4=new JButton("Delete");
b5=new JButton("Search");
b6=new JButton("Exit");
Font f1=new Font("Courier",Font.BOLD,32);
l.setFont(f1);
Font f=new Font("Courier",Font.BOLD,20);
l1.setFont(f);
l2.setFont(f);
l3.setFont(f);
l4.setFont(f);
l5.setFont(f);
l6.setFont(f);
l7.setFont(f);
t1.setFont(f);
t2.setFont(f);
t3.setFont(f);
t4.setFont(f);
t5.setFont(f);
ta1.setFont(f);
r1.setFont(f);
r2.setFont(f);
b1.setFont(f);
b2.setFont(f);
b3.setFont(f);
b4.setFont(f);
b5.setFont(f);
b6.setFont(f);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
b5.addActionListener(this);
b6.addActionListener(this);

l.setBounds(0,10,1000,40);
l1.setBounds(100,150,150,40);
t1.setBounds(250,150,200,40);


l2.setBounds(600,150,150,40);
t2.setBounds(750,150,200,40);


l3.setBounds(100,250,150,40);
r1.setBounds(250,250,150,40);
r2.setBounds(400,250,150,40);


l4.setBounds(600,250,150,40);
jsp1.setBounds(750,250,200,100);

l5.setBounds(100,350,150,40);
t3.setBounds(250,350,200,40);


l6.setBounds(100,450,150,40);
t4.setBounds(250,450,200,40);


l7.setBounds(600,450,150,40);
t5.setBounds(750,450,200,40);

b1.setBounds(50,600,120,40);
b2.setBounds(200,600,120,40);
b3.setBounds(350,600,120,40);
b4.setBounds(500,600,120,40);
b5.setBounds(650,600,120,40);
b6.setBounds(800,600,120,40);

d.add(l);
d.add(l1);
d.add(l2);
d.add(l3);
d.add(l4);
d.add(l5);
d.add(l6);
d.add(l7);
d.add(t1);
d.add(t2);
d.add(t3);
d.add(t4);
d.add(t5);
d.add(jsp1);
d.add(r1);
d.add(r2);
d.add(b1);
d.add(b2);
d.add(b3);
d.add(b4);
d.add(b5);
d.add(b6);
addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent tt){
dispose();
}});
setSize(1000,700);
setVisible(true);
}
public void actionPerformed(ActionEvent tt)
{
try
{
if(tt.getSource()==b1)
{
t1.setText("");
t2.setText("");
t3.setText("");
t4.setText("");
t5.setText("");
ta1.setText("");
}
else
if(tt.getSource()==b2)
{
try
{
System.out.println("1");
rs.moveToInsertRow();
System.out.println("1");
rs.updateString("MemName",t2.getText().trim());
System.out.println("1");
if(r1.isSelected()==true)
rs.updateString("MemType","Teacher");
else
rs.updateString("MemType","Student");
System.out.println("1");
rs.updateString("Addr",ta1.getText().trim());
System.out.println("1");
rs.updateString("City",t3.getText().trim());
System.out.println("1");
rs.updateString("MobNo",t4.getText().trim());
System.out.println("1");
rs.updateString("EmailId",t5.getText().trim());
System.out.println("1");
rs.updateInt("TotalBook",0);
System.out.println("1");
rs.updateFloat("FineAmt",0);
System.out.println("1");
rs.insertRow();
JOptionPane.showMessageDialog(null,"Saved","myproj",2);
}catch(Exception eeee){System.out.println("aa"+eeee);}
}
else
if(tt.getSource()==b3)
{
rs.updateInt("MemId",Integer.parseInt(t1.getText()));
rs.updateString("MemName",t2.getText().trim());
if(r1.isSelected()==true)
rs.updateString("MemType","Teacher");
else
rs.updateString("MemType","Student");
rs.updateString("Addr",ta1.getText().trim());
rs.updateString("City",t3.getText().trim());
rs.updateString("MobNo",t4.getText().trim());
rs.updateString("EmailId",t5.getText().trim());
rs.updateRow();
JOptionPane.showMessageDialog(null,"Updated","myproj",2);
}
else
if(tt.getSource()==b4)
{
rs.deleteRow();
JOptionPane.showMessageDialog(null,"Deleted","myproj",2);
}
else
if(tt.getSource()==b5)
{
int xx=Integer.parseInt(JOptionPane.showInputDialog("MemId"));
boolean flag=false;
try
{
rs.first();
do
{
if(rs.getInt("MemId")==xx)
{
flag=true;
t1.setText(xx+"");
t2.setText(rs.getString("MemName"));
if(rs.getString("MemType").equals("Teacher"))
r1.setSelected(true);
else
r2.setSelected(true);
ta1.setText(rs.getString("Addr"));
t3.setText(rs.getString("City"));
t4.setText(rs.getString("MobNo"));
t5.setText(rs.getString("EmailId"));
break;
}
}while(rs.next());
}catch(Exception ee){}
if(flag==false)
JOptionPane.showMessageDialog(null,"No Such Member Exit","myproj",2);
}
else
{
dispose();
}
}catch(Exception ee){System.out.println(ee);}
}
}
