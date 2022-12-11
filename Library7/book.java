import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class book extends JFrame
{
JLabel l,l1,l2,l3,l4,l5;
JTextField t1,t2,t3,t4,t5;
JButton b1,b2,b3,b4,b5;
ResultSet rs;
Statement st=null;
public book()
{
Container d=getContentPane();
d.setLayout(null);
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con=DriverManager.getConnection("Jdbc:Odbc:libdsn","","");
st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
rs=st.executeQuery("select * from Book");
}catch(Exception ee){System.out.println(ee);}
l=new JLabel("Book Entry Form",JLabel.CENTER);
l1=new JLabel("Book ID :");
l2=new JLabel("Book Title :");
l3=new JLabel("Author :");
l4=new JLabel("Publisher :");
l5=new JLabel("Price :");

t1=new JTextField(20);
t2=new JTextField(20);
t3=new JTextField(20);
t4=new JTextField(20);
t5=new JTextField(20);

b1=new JButton("Add");
b2=new JButton("Save");
b3=new JButton("Upd");
b4=new JButton("Del");
b5=new JButton("Exit");


Font f=new Font("Courier",Font.BOLD,32);
l.setFont(f);
f=new Font("Courier",Font.BOLD,22);
l1.setFont(f);l2.setFont(f);l3.setFont(f);l4.setFont(f);l5.setFont(f);
t1.setFont(f);t2.setFont(f);t3.setFont(f);t4.setFont(f);t5.setFont(f);
b1.setFont(f);b2.setFont(f);b3.setFont(f);b4.setFont(f);b5.setFont(f);


l.setBounds(0,10,1000,40);

l1.setBounds(10,100,150,30);
t1.setBounds(150,100,100,30);

l2.setBounds(400,100,200,30);
t2.setBounds(600,100,300,30);

l3.setBounds(10,200,150,30);
t3.setBounds(150,200,200,30);

l4.setBounds(400,200,200,30);
t4.setBounds(600,200,300,30);

l5.setBounds(10,300,150,30);
t5.setBounds(150,300,200,30);

b1.setBounds(50,400,100,30);
b2.setBounds(200,400,100,30);
b3.setBounds(350,400,100,30);
b4.setBounds(500,400,100,30);
b5.setBounds(650,400,100,30);

b1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
t1.setText("");
t2.setText("");
t3.setText("");
t4.setText("");
t5.setText("");

}});

b2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
try
{
rs.moveToInsertRow();
rs.updateString("Title",t2.getText().trim());
rs.updateString("Author",t3.getText().trim());
rs.updateString("Publicat",t4.getText().trim());
rs.updateFloat("Price",Float.parseFloat(t5.getText().trim()));
rs.updateString("status","Library");
rs.insertRow();
JOptionPane.showMessageDialog(null,"Saved","MyProj",2);
}catch(Exception ee){System.out.println(ee);}
}});

b3.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
try
{
rs.updateInt("BookId",Integer.parseInt(t1.getText()));
rs.updateString("Title",t2.getText().trim());
rs.updateString("Author",t3.getText().trim());
rs.updateString("Publicat",t4.getText().trim());
rs.updateString("Price",t5.getText().trim());
rs.updateRow();
JOptionPane.showMessageDialog(null,"Updated","MyProj",2);
}catch(Exception ee){System.out.println(ee);}
}});

b4.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
try{
rs.deleteRow();
JOptionPane.showMessageDialog(null,"Deleted","MyProj",2);
}catch(Exception ee){}
}});

b5.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
dispose();
}});

d.add(l);
d.add(l1);
d.add(l2);
d.add(l3);
d.add(l4);
d.add(l5);

d.add(t1);
d.add(t2);
d.add(t3);
d.add(t4);
d.add(t5);

d.add(b1);
d.add(b2);
d.add(b3);
d.add(b4);
d.add(b5);

addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent tt){
dispose();
}});
setSize(1000,600);
setVisible(true);
}

}