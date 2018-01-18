import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
class AlarmClockGui extends JFrame implements ActionListener, Runnable
{
 boolean flag;
 Thread t;
 Random r=new Random();
 JLabel lblDT;
 JLabel lblTITLE;
 JLabel l1,l2;
 JLabel p5,p2,p3,p4;
 JButton bttnSS;
 JPanel p1;
 Date d;
 int a,b,c;
 SimpleDateFormat sdf;
 String str[]=new String[]{"RED","YELLOW","BLUE","GREEN","PINK","ORANGE"};
 AlarmClockGui(String s)
 {
  d = new Date();
  
  sdf = new SimpleDateFormat("HH:mm:ss @ dd/MM/yyyy");
  initComponents();
  setTitle(s);
  setSize(400, 300);//w,h
  setResizable(false);
  setDefaultCloseOperation(EXIT_ON_CLOSE);//jvm exit
  setVisible(true);//render it
  a=b=c=-1;
  flag = true;
  t = new Thread(this);
  t.start();
 }

 void initComponents()
 {
  //define the components
  lblDT = new JLabel(getDateTime());
  l1 = new JLabel(gx1(0));
  l2 = new JLabel(gx(0));
  lblTITLE = new JLabel("Alarm Clock");
  bttnSS = new JButton("Set Alarm");

  //few component properties
  Font f = new Font("Arial", Font.BOLD, 26);//name,style,size
  
  bttnSS.setFont(f);
  bttnSS.setForeground(Color.RED);

  p5 = new JLabel("**************************************");
  p2 = new JLabel("**************************************");
  p3 = new JLabel("*************");
  p4 = new JLabel("*************");

  p5.setFont(f);
  p5.setHorizontalAlignment(JLabel.CENTER);
  p2.setFont(f);
  p2.setHorizontalAlignment(JLabel.CENTER);
  lblTITLE.setFont(f);
  lblTITLE.setForeground(Color.RED);
  
  lblTITLE.setHorizontalAlignment(JLabel.CENTER);

  l1.setFont(f);
  l1.setForeground(Color.WHITE);
  l1.setHorizontalAlignment(JLabel.CENTER);
  l1.setVerticalAlignment(JLabel.CENTER);
  l2.setFont(f);
  l2.setForeground(Color.WHITE);
  l2.setVerticalAlignment(JLabel.CENTER);

  lblDT.setFont(f);
  lblDT.setHorizontalAlignment(JLabel.CENTER);

  lblDT.setForeground(Color.magenta);
  p1 = new JPanel(new FlowLayout());
  //add the components into the window
  p1.add(p5);
  p1.add(lblDT);
  p1.add(l1);
  p1.add(l2);
  p1.add(p2);
//  p1.setBackground(Color.GREEN);
  add(bttnSS);
  
  //size and position them
  setLayout(new BorderLayout());
  add(lblTITLE, BorderLayout.NORTH);
  add(p1, BorderLayout.CENTER);
//  add(p3, BorderLayout.WEST);
//  add(p4, BorderLayout.EAST);
  add(bttnSS, BorderLayout.SOUTH);
  //enable event handling for the button
  bttnSS.addActionListener(this);    
 } 

 //interface ActionListener method (event procdure)
 public void actionPerformed(ActionEvent e)
 {//callback method
   String txt = bttnSS.getText();
   if(txt.equalsIgnoreCase("Set Alarm"))
     fx();
   else if(txt.equalsIgnoreCase("Dissable Alarm"))
     fx1();
  
 }

 String getDateTime()
 {
   d.setTime(System.currentTimeMillis());
   return sdf.format(d);
 }
 String gx(int k)
 {
  if(k==0)
  return "";
  else
  return "Alarm Time-";
 }
 String gx1(int k)
 {
  if(k==0)
  return "";
  else
  return (""+a+":"+b+":"+c+"");
 }
 void fx()
 {//START
  System.out.println("Enter the hour:minute:second");
  Scanner sc=new Scanner(System.in);
  System.out.print("HOUR:-");
  a=sc.nextInt();
  System.out.print("MINUTE:-");
  b=sc.nextInt();
  System.out.print("SECOND:-");
  c=sc.nextInt();
  l1.setText(gx(1));
  l2.setText(gx1(1));
  bttnSS.setText("Dissable Alarm");
  bttnSS.setForeground(Color.GREEN);
  
 }

 void fx1()
 {//STOP
  a=b=c=-1;
  l1.setText(gx(0));
  l2.setText(gx1(0));
  bttnSS.setText("Set Alarm");
  bttnSS.setForeground(Color.RED);
 }

 public void run()
 {
  while(flag)
  {
   try
   {
    lblDT.setText(getDateTime());
    switch(r.nextInt(5))
    {
      case 0:
        p5.setForeground(Color.RED);
        p2.setForeground(Color.RED);
        p1.setBackground(Color.GREEN);
      break;
      case 1:
        p5.setForeground(Color.GREEN);
        p2.setForeground(Color.GREEN);
        p1.setBackground(Color.RED);
      break;
      case 2:
        p5.setForeground(Color.BLUE);
        p2.setForeground(Color.BLUE);
        p1.setBackground(Color.YELLOW);
      break;
      case 3:
        p5.setForeground(Color.ORANGE);
        p2.setForeground(Color.ORANGE);
        p1.setBackground(Color.BLUE);
      break;
      case 4:
        p5.setForeground(Color.PINK);
        p2.setForeground(Color.PINK);
        p1.setBackground(Color.RED);
      break;
    }
    Calendar now = Calendar.getInstance();
    int hour=now.get(Calendar.HOUR_OF_DAY);
    int minute=now.get(Calendar.MINUTE);
    int second=now.get(Calendar.SECOND);
    if(hour==a&&minute==b&&second==c)
    {
        int i;
        for(i=0;i<50000;i++)
         Toolkit.getDefaultToolkit().beep();
        l1.setText(gx(0));
        l2.setText(gx1(0));
        bttnSS.setText("Alarm Not Set");
        bttnSS.setForeground(Color.RED);
    }
    Thread.sleep(990);
   }
   catch(Exception ex)
   {}
  }//while
 }//run
 
 public static void main(String args[])
 {
  new AlarmClockGui("AlarmClockGui");
  
 }//main
}
