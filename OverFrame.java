import java.awt.Color;//���� ������ ���� ���̺귯��
import java.awt.Font;
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

//import com.sun.prism.Image;
public class OverFrame extends JPanel implements ActionListener   {
	private JButton retry;//������ ����ȭ�� ������
	private BufferedImage overBackground;//���ӿ��� ��漳��
	private JFrame frame;
	private JPanel panel1;
	static public GalagaGame g;
	private int score;
	OverFrame(int score){
	this.score=score;
	panel1 = new JPanel();
	panel1.setLocation(300,   400);
	retry = new JButton("RETRY");
	frame = new JFrame("GAME OVER");
	try {
	overBackground = ImageIO.read(new File("gameover.png"));
	}   catch (IOException e) {
	e.printStackTrace();
	}
	panel1.add(retry);
	add(panel1);
	frame.setSize(1000,   700);
	frame.add(this);
	frame.setResizable(false);
	frame.setVisible(true);
	frame.setLocation(300,   100);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	retry.addActionListener(this);
	}
	
	public void paint(Graphics  g)
	{
		super.paint(g);
		g.setColor(Color.blue);
		g.drawImage(overBackground, 1, 1, 1000,   700, null, this);
		String   zcore = (String.valueOf(score));
		g.setFont(new Font("���ü",Font.BOLD,30));
		g.drawString("����� ������", 400, 400);
		g.drawString(zcore,450, 460);
	}     
	public void   actionPerformed(ActionEvent e){
		if(retry==e.getSource())//retry��ư Ŭ����
		{
			this.setVisible(false);
			frame.dispose();
			new MainFrame();	
		}
	}

}
