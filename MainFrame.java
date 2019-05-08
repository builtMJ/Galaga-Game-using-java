import java.awt.Color;//���� ������ ���� ���̺귯��
import java.awt.Graphics;//�Ű������� �̹����� �ҷ��� ����ϱ� ���� ���̺귯��
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;//��Ŭ���� �ܺο��� �޾ƿ� �̹����� ������ �� �ڷ����� BufferedImage�ΰ͸� �޾ƿ� �� �ִ�. �׷��� ĳ���͵��� BufferedImage �ڷ������� ������ ���̴�.
import java.io.File;//�ڹ�������Ʈ�ȿ� �ִ� �̹����� �ҷ��� �� �Ű������� File�ΰ��� �޾ƿ´�. ���� �޾ƿ� �̹����� File�ڷ������� �ٲ㼭 �Ű������� �����ϱ����� ���̺귯���̴�.
import java.io.IOException;//���ܻ�Ȳ, �� ������ ������ �� ó���ϱ� ���� ���̺귯��


import javax.imageio.ImageIO;//�̹����� �޾ƿ��µ� ����ϴ� �޼ҵ尡 ����ִ� ���̺귯��
import javax.swing.JButton;
import javax.swing.JFrame;//���α׷� ���� Ʋ�� ������ִ� ���̺귯��
import javax.swing.JPanel;//���� Ʋ�� ���밡 �Ǵ� ���̺귯��



public class MainFrame extends JPanel implements ActionListener   
{
	private JButton start,help;//��ư ����
	private BufferedImage skyBackground;//���ȭ���� �޾ƿ��� ����
	private JFrame frame;//���ȭ��,�г��� ���� ������ ����
	private JPanel panel1;//�г� ����
	static public GalagaGame g;//GalagaGame ���� ����
	
	MainFrame()
	{
	panel1 = new JPanel();//�г� ����
	panel1.setLocation(300,   400);//�г� ��ġ ����
	start = new JButton("START");//START��ư ����
	help = new JButton("HELP");//HELP��ư ����
	frame = new JFrame("Start Game");//â �̸� ���
	try 
	{
		skyBackground = ImageIO.read(new File("sky.png"));//���ȭ���� �޾ƿ�
	}   catch (IOException e) 
		{
			e.printStackTrace();
		}
	panel1.add(start);//�г����� ��ư �߰�
	panel1.add(help);//�г����� ��ư �߰�
	add(panel1);//�г��� ������ ���� ����
	frame.setSize(1000,   700);//â ������ ����
	frame.add(this);//�ش� ��ü�� ������ ���� ����
	frame.setResizable(false);
	frame.setVisible(true);
	frame.setLocation(300,   100);//â ��ġ ����
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	start.addActionListener(this);
	help.addActionListener(this);
	}
	
	public void paint(Graphics   g)
	{
	super.paint(g);
	g.setColor(Color.blue);// ��� �� ����
	g.drawImage(skyBackground, 1, 1, 1000,   700, null, this);// ��� �׸� ����
	}    
	public void   actionPerformed(ActionEvent e)
	{
		if(start==e.getSource())//START��ư Ŭ����
		{
			g.sprites.clear();
			g.resetScore();
			g = new GalagaGame();//���� ����
			this.setVisible(false);//���׸� ������
			frame.dispose();//���׸� ����
		}
		else if(help==e.getSource())
		{//HELP��ư Ŭ����
			this.setVisible(false);//��� �׸� ����
			new HelpFrame();//HELP ȭ�� �ҷ�����
			frame.dispose();//��� �׸� ����
		}
	}

	public   static void main(String[] args)
	{
	MainFrame   f = new MainFrame();
	}
}
