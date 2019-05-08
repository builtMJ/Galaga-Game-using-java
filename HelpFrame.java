import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class HelpFrame extends JPanel implements   ActionListener{
	private JButton back;//MainFrame���� ���� ��ư
	private BufferedImage helpBackground;//���ȭ�� ����
	private JFrame frame2;
	private JPanel panel2;
	
	HelpFrame(){
	
	panel2 = new JPanel();
	panel2.setLocation(300,   400);
	back = new JButton("BACK");
	

	
	frame2 = new JFrame("HELP");
	try {
	helpBackground = ImageIO.read(new File("help.png"));//���ȭ�� �Է�
	}   catch (IOException e) {
	e.printStackTrace();
	}
	
	panel2.add(back);
	
	add(panel2);
	frame2.setSize(1000,700);
	frame2.add(this);
	frame2.setResizable(false);
	frame2.setVisible(true);
	frame2.setLocation(300,100);
	frame2.setDefaultCloseOperation(frame2.EXIT_ON_CLOSE);
	back.addActionListener(this);
	

	}
	
	public void paint(Graphics g){
	super.paint(g);
	g.setColor(Color.blue);
	g.drawImage(helpBackground, 1, 1, 1000,   700, null, this);
	
	
      }    
	
	public void   actionPerformed(ActionEvent e){
	if(back==e.getSource())//back��ư Ŭ����
	{
	this.setVisible(false);//helpFrame������
	new MainFrame();//MainFrame ����
	frame2.dispose();
	}
	
	}
	
}
