import java.awt.Color;//���� ������ ���� ���̺귯��
import java.awt.Font;
import java.awt.Graphics;//�Ű������� �̹����� �ҷ��� ����ϱ� ���� ���̺귯��
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;//�������� Ű���� ��� �̺�Ʈ�� ó���� ������ ������ ���̺귯��
import java.awt.event.KeyListener;//�������� Ű���� ������ �����ϱ����� ���̺귯��
import java.awt.image.BufferedImage;//��Ŭ���� �ܺο��� �޾ƿ� �̹����� ������ �� �ڷ����� BufferedImage�ΰ͸� �޾ƿ� �� �ִ�. �׷��� ĳ���͵��� BufferedImage �ڷ������� ������ ���̴�.
import java.io.File;//�ڹ�������Ʈ�ȿ� �ִ� �̹����� �ҷ��� �� �Ű������� File�ΰ��� �޾ƿ´�. ���� �޾ƿ� �̹����� File�ڷ������� �ٲ㼭 �Ű������� �����ϱ����� ���̺귯���̴�.
import java.io.IOException;//���ܻ�Ȳ, �� ������ ������ �� ó���ϱ� ���� ���̺귯��
import java.util.ArrayList;//ĳ���͵��� ������ �ڷᱸ�� ���̺귯��
import java.util.Random;
import javax.imageio.ImageIO;//�̹����� �޾ƿ��µ� ����ϴ� �޼ҵ尡 ����ִ� ���̺귯��
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;//���α׷� ���� Ʋ�� ������ִ� ���̺귯��
import javax.swing.JLabel;
import javax.swing.JPanel;//���� Ʋ�� ���밡 �Ǵ� ���̺귯��
import javax.swing.Timer;
import com.sun.prism.Image;


public class GalagaGame extends JPanel implements KeyListener,   ActionListener 
{

	public static ArrayList sprites = new ArrayList();// ĳ���͵��� �����ϴ� �ڷᱸ��
	private static Sprite starship;// ���ּ� ���� ����
	private static Sprite king;
	private static BufferedImage alienImage;// �ܰ��� �̹��� �޾ƿ� ��������
	private static BufferedImage shotImage;// �̻��� �̹��� �޾ƿ� ���� ����
	private static BufferedImage shipImage;// ���ּ� �̹��� �޾ƿ� ���� ����
	private static BufferedImage kingImage;//���ִ븶�� �̹��� �޾ƿ� ���� ����
	private static BufferedImage fruitImage;//���� �̹��� �޾ƿ� ���� ����
	private static BufferedImage spaceBackground;//���� ��� �޾ƿ� ��������
	private static BufferedImage greatBackground;//��� ���� óġ�ϸ� ������ ȭ�� ���� ����
	private static int score=0;//���� ���� �� �����ϴ� ���� ����
	private static int scorex=0;//���� ����ȭ�� ��� ��ġ ����
	public static JFrame frame;
	public static void setScore(int s){score+=s;}//������ ����
	public static int getScore(){return score;}//������ ��ȯ
	public static void resetScore(){score=scorex=0;}
	
	/*
	 * ������ �⺻����, ĳ���͵��� �̹����� �޾ƿ���, ���ּ��� �ܰ����� �⺻�����ϴ� ������
	 */
	public GalagaGame() {
	
	frame = new JFrame("Galaga Game");// GalagaGame�̶�� ������ �ϳ� ����
	frame.setSize(1000,   700);// ������ ������ ���� 800, ���� 600
	frame.add(this);// �����Ӿȿ� ���� �ۼ����� Galaga   Game Ŭ������ ������ �ִ´�
	frame.setResizable(false);// ����â�� ������ ������ �Ұ����ϰ� �Ѵ�.
	frame.setVisible(true);// �����ϸ� â�� ���̰��Ѵ�.
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ��ư�� �ݱ� ��ư�ְ��ϱ�
	frame.setLocation(300,   100);
	try {
		alienImage = ImageIO.read(new File("alien.png"));// �ܰ����̹��� ������ �ܰ��� �̹��� ����
		spaceBackground= ImageIO.read(new File("spacebackground.jpg"));		
		shotImage = ImageIO.read(new File("fire.png"));//�̻��� �̹��� ������ �̻��� �̹��� ����
		shipImage = ImageIO.read(new File("starship.png"));//���ּ� �̹��� ������ ���ּ� �̹��� ����
		kingImage = ImageIO.read(new File("kingsprite.png"));
		fruitImage = ImageIO.read(new File("fruit.png"));
		greatBackground = ImageIO.read(new File("great.png"));
	}   
	catch (IOException e) 
	{//���� �̹����� ����� �������� ���ߴٸ�,
		e.printStackTrace();// ������ ���� �����ְ� �������� ����մϴ�.
	}
	GraphicPanel panel = new GraphicPanel();
	panel.setFocusable(true);
	frame.getContentPane().add(panel);
	frame.pack();
	frame.setSize(1000,   700);
	frame.setVisible(true);
	this.requestFocus();// ���ּ��� ��� �������� �� �ְ� �����ϰ���
	this.initSprites();// �ܰ��ΰ� ���ּ��� �⺻�����ϴ� �޼ҵ带 ����
	addKeyListener(this);// Ű�� ������ �����ϰ���.
	}
	
	 public void actionPerformed(ActionEvent e){}
	
private static class GraphicPanel extends JPanel implements KeyListener, ActionListener
{
	private Timer t;
	public GraphicPanel()
	{
		t = new Timer(3, this);
		t.start();
		addKeyListener(this);
	}
	public void keyTyped(KeyEvent e) {}   
	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyCode()==KeyEvent.VK_P) //���� ����
			t.stop();
			
	} 
	public void keyPressed(KeyEvent e){}
	public void actionPerformed(ActionEvent e)	// on timer event
	{
		for (int i = 0; i < sprites.size(); i++) 
		{
			Sprite   sprite = (Sprite) sprites.get(i);// arraylist�ȿ� �ִ� ĳ���͵��� �ҷ���
			sprite.move();//ĳ���͵��� �����̰� ��Ų��.
		}
		FruitSprite   fruit;
		BombSpriteOne   bomb;
		for (int p = 0; p < sprites.size(); p++) 
		{// arraylist�ȿ��ִ� ���� �ٸ� �����͵��� �񱳽�Ŵ(�ε����� �����ϱ�����)
			for (int s = p + 1; s < sprites.size(); s++) 
			{//0��° ĳ���ʹ� 1,2,3..������ ��	1���� ĳ���ʹ� 2,3,4.. ������ ��				
				Sprite   me = (Sprite) sprites.get(p);// arraylist�ȿ� �ִ� ĳ���͸� �ҷ���
				Sprite   other = (Sprite) sprites.get(s);// arraylist�ȿ� �ִ� �� �ٸ�ĳ���͸� �ҷ���		
				if (me.checkCollision(other)) {// ���� �ΰ��� ĳ���Ͱ� �浹�ϸ�	
					me.handleCollision(other);//me���̻���, other�� �ܰ����̸� �̻���,�ܰ��� ���� | me�� ���ּ�, other�� �ܰ����̸� ���� ����
					other.handleCollision(me);//other�� �̻���, me�� �ܰ����̸� �̻���, �ܰ��� ���� | other�� ���ּ�, me�� �ܰ����̸� ���� ����
				}
			}
		}
		repaint();//���� �ܰ����� ���ŵ� �Ŀ� �ٽñ׸���
		try 
		{
			Thread.sleep(8);//���α׷� ���� �ӵ� ����. ���� Ŀ������ ĳ���͵��� õõ�� ������ 
		}   catch (Exception e1) 
			{
			}
		
	}
	
@Override
public void paint(Graphics   g) 
{
		
	if (score == 0)//������ 0�̸�
		scorex = 80;//ȭ���� x��ǥ 80�� ��ġ
	else if (score >=250   && score<1000)//������ 250���� 1000�̸�
		scorex = 75;//ȭ���� x��ǥ 75�� ��ġ
	else//�� ��
		scorex = 70;//ȭ���� x��ǥ 70�� ��ġ
	String   zcore = (String.valueOf(score));//������ ���ڿ��� �ٲپ� ȭ�� ��� �����ϰ���.
	super.paint(g);// �θ�Ŭ������ paint�޼ҵ忡 �� ����
	g.setColor(Color.black);// ���� ���������� ����. ���߿� ���� ������� �ٲܰ���
	g.drawImage(spaceBackground, 1, 1, 1000, 700, null, this);
	for (int i = 0; i < sprites.size(); i++) 
	{
		Sprite   sprite = (Sprite) sprites.get(i);// arraylist�ȿ� �ִ� ĳ���͵��� �ҷ���
		sprite.draw(g);//ĳ���͵��� ȭ�鿡 �׸���.
	}
	g.setFont(new Font("���ü",Font.BOLD,30));//ȭ�鿡 ��Ÿ���� ������ �۾�ü�� ũ�� ����
	g.drawString("SCORE", 40, 560);//SCORE�̶�� �۾��� ���̰���
	g.drawString(zcore, scorex, 600);//ȭ�鿡 ������ ���̰�  �� ��.
	if(sprites.size()<=1)//�ܰ��ΰ� ���� �븶���� óġ�ϸ�
	{
		g.drawImage(greatBackground, 1, 1, 1000, 700, null, this);//�¸��ߴٴ� â�� �߰���
		g.drawString("����� ������", 400, 550);
		g.drawString(zcore,420, 600);
	}
	
	}
}
	 
	 
	 
	 
/*
 * �ܰ��ΰ� ���ּ��� �⺻�����ϴ� �޼ҵ�
 */
	private void initSprites()   
	{
		starship = new   StarShipSprite(this, shipImage, 450, 620);// ���ּ� ������ ���ּ��̹���, ��ġ�� ����
		king = new KingSprite(this,kingImage, 450, 10);
		sprites.add(king);
		sprites.add(starship);// arraylist�� ���ּ��� �߰�
		for (int y = 0; y < 5; y++) 
		{// ���� �ܰ����߰�
			for (int x = 0; x < 12; x++) 
			{// ����� �ܰ����߰� (����� �߰���, ���� �ܰ��� �߰�)
				Sprite   alien = new AlienSprite(this, alienImage, 100 + (x * 60), (80) + y * 30);
				// �ܰ��ε��� ó�� ������ ��ġ��� ����.
				sprites.add(alien);// arraylist�� �ܰ��ε� �߰�
			}
		}
	}
	
/*
 * ���� �����ϴ� �޼ҵ�. ������ �ߴµ� ������� ����. 
 */
	private void startGame() 
	{
		sprites.clear();// arraylist�� ����Ǿ� �ִ� ���ּ��� �ܰ��ε��� ��� ������
		initSprites();// �ܰ��ΰ� ���ּ��� �ʱ�ȭ ��Ű�� �޼ҵ�
	}
	
/*
* ������ ������ �޼ҵ�
*/
	public void endGame(Sprite   a,Sprite b) 
	{//������ ������ �޼ҵ�
		sprites.remove(a);//�浹�� ĳ���͸� ����
		sprites.remove(b);//�浹�� ĳ���͸� ����
		new OverFrame(getScore());//���ӿ��� â�� �߰���
		this.setVisible(false);//���� ����â �Ⱥ��̰��ϱ�
		frame.dispose();//���� ����â�� ����
	}

/*
 * �̻��Ͽ� �ܰ����� ������ arraylist���� �̻���,�ܰ��� ��� �����ϴ� �޼ҵ�
 */
public void   removeSprite(Sprite sprite) 
{
	sprites.remove(sprite);
}

/*
 * ��� ��ü�� �����ϴ� �޼ҵ�
 */

public void remeveAllSprite()
{
	sprites.clear();
	score=scorex=0;
}
	
/*
 * �̻��� �⺻���� �޼ҵ�
 */
public void fire() 
{
	ShotSprite   shot = new ShotSprite(this, shotImage, starship.getX() + 10, starship.getY() - 30);
	// �̻��� �̹��������� �̻����� ��� ��ǥ�� ��ġ���� ����
	sprites.add(shot);// arraylist�� �̻��� ���� �߰�
}

/*
 * ������ Ű���� �̺�Ʈ�� ó���ϴ� �޼ҵ�
 */
	@Override
public void   keyPressed(KeyEvent e) 
{
	if (e.getKeyCode()   == KeyEvent.VK_LEFT)//������ Ű�� ����Ű��
	starship.setDx(-3);//���ּ��� �������� 3��ŭ ������
	if (e.getKeyCode()   == KeyEvent.VK_RIGHT)//������ Ű�� ������Ű�̸�
	starship.setDx(+3);//���ּ��� ���������� 3��ŭ ������
	if (e.getKeyCode()   == KeyEvent.VK_SPACE)//������ Ű�� �����̽����̸�
	fire();	
	if(e.getKeyCode()==KeyEvent.VK_Q)// ���� ����
	System.exit(0);
	if(e.getKeyCode()==KeyEvent.VK_S)// ���� ����
	this.startGame();
}
/*
 * Ű�� �����ٰ� �� �� �̺�Ʈ ó���ϴ� �޼ҵ�
 */

@Override
public void   keyReleased(KeyEvent e) 
{
	if (e.getKeyCode()   == KeyEvent.VK_LEFT)//����Ű�� �����ٰ� ����
	starship.setDx(0);//�������� ���� ����
	if (e.getKeyCode()   == KeyEvent.VK_RIGHT)//������ Ű�� �����ٰ� ����
	starship.setDx(0);//���������� ���� ����.
	if(e.getKeyCode()==KeyEvent.VK_Q)// ���� ����
	{
	System.exit(0);
	}
//	if(e.getKeyCode()==KeyEvent.VK_P) //���� ����
//	running=false;
}

@Override
public void   keyTyped(KeyEvent arg0) 
{//�Ⱦ��� �޼ҵ����� KeyListener �ȿ� �ֱ� ������ �������̵�
}


}
