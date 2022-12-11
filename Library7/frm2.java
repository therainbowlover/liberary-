import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class frm2 extends JFrame
{
JLabel l1;
JMenuBar mb;
JMenu m1,m2;
JMenuItem i1, i2,i3, i4, ii1,ii2,ii3;
public frm2()
{
Container d=getContentPane();
l1=new JLabel(new ImageIcon("lib1.jpg"));
d.add("Center",l1);
mb=new JMenuBar();
m1=new JMenu("Form");
m2=new JMenu("Report");
i1=new JMenuItem("Member");
i2=new JMenuItem("Book");
i3=new JMenuItem("Issue");
i4=new JMenuItem("Return");
ii1=new JMenuItem("Member List");
ii2=new JMenuItem("Book List");
ii3=new JMenuItem("Fine Amount");
m1.add(i1);
m1.add(i2);
m1.add(i3);
m1.add(i4);
m2.add(ii1);
m2.add(ii2);
m2.add(ii3);
mb.add(m1);
mb.add(m2);
setJMenuBar(mb);
i1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
new member();
}});

i2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
new book();
}});

i3.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
new issue();
}});

ii1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
new prog2();
}});

ii2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
new prog1();
}});

ii3.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
new prog();
}});

i4.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent tt){
new bookret();
}});


addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent tt){
dispose();
}});
setSize(1200,700);
setVisible(true);
}
}