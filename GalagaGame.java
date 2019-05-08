import java.awt.Color;//배경색 설정을 위한 라이브러리
import java.awt.Font;
import java.awt.Graphics;//매개변수로 이미지를 불러와 사용하기 위한 라이브러리
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;//눌려지는 키들이 어떻게 이벤트를 처리할 것인지 구현한 라이브러리
import java.awt.event.KeyListener;//눌려지는 키들의 반응을 감지하기위한 라이브러리
import java.awt.image.BufferedImage;//이클립스 외부에서 받아온 이미지를 저장할 때 자료형이 BufferedImage인것만 받아올 수 있다. 그래서 캐릭터들을 BufferedImage 자료형으로 선언한 것이다.
import java.io.File;//자바프로젝트안에 있는 이미지를 불러올 때 매개변수가 File인것을 받아온다. 따라서 받아온 이미지를 File자료형으로 바꿔서 매개변수로 전달하기위한 라이브러리이다.
import java.io.IOException;//예외상황, 즉 에러가 생겼을 때 처리하기 위한 라이브러리
import java.util.ArrayList;//캐릭터들을 저장할 자료구조 라이브러리
import java.util.Random;
import javax.imageio.ImageIO;//이미지를 받아오는데 사용하는 메소드가 들어있는 라이브러리
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;//프로그램 구축 틀을 만들어주는 라이브러리
import javax.swing.JLabel;
import javax.swing.JPanel;//구축 틀의 뼈대가 되는 라이브러리
import javax.swing.Timer;
import com.sun.prism.Image;


public class GalagaGame extends JPanel implements KeyListener,   ActionListener 
{

	public static ArrayList sprites = new ArrayList();// 캐릭터들을 저장하는 자료구조
	private static Sprite starship;// 우주선 변수 선언
	private static Sprite king;
	private static BufferedImage alienImage;// 외계인 이미지 받아올 변수설정
	private static BufferedImage shotImage;// 미사일 이미지 받아올 변수 설정
	private static BufferedImage shipImage;// 우주선 이미지 받아올 변수 설정
	private static BufferedImage kingImage;//우주대마왕 이미지 받아올 변수 설정
	private static BufferedImage fruitImage;//과일 이미지 받아올 변수 설정
	private static BufferedImage spaceBackground;//우주 배경 받아올 변수설정
	private static BufferedImage greatBackground;//모든 적을 처치하면 보여줄 화면 변수 설정
	private static int score=0;//적을 맞출 때 증가하는 점수 변수
	private static int scorex=0;//점수 증가화면 출력 위치 지장
	public static JFrame frame;
	public static void setScore(int s){score+=s;}//점수값 설정
	public static int getScore(){return score;}//점수값 반환
	public static void resetScore(){score=scorex=0;}
	
	/*
	 * 프레임 기본설정, 캐릭터들의 이미지를 받아오고, 우주선과 외계인을 기본설정하는 생성자
	 */
	public GalagaGame() {
	
	frame = new JFrame("Galaga Game");// GalagaGame이라는 프레임 하나 생성
	frame.setSize(1000,   700);// 프레임 사이즈 가로 800, 세로 600
	frame.add(this);// 프레임안에 지금 작성중인 Galaga   Game 클래스의 내용을 넣는다
	frame.setResizable(false);// 실행창의 사이즈 변경이 불가능하게 한다.
	frame.setVisible(true);// 실행하면 창이 보이게한다.
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 버튼은 닫기 버튼있게하기
	frame.setLocation(300,   100);
	try {
		alienImage = ImageIO.read(new File("alien.png"));// 외계인이미지 변수에 외계인 이미지 저장
		spaceBackground= ImageIO.read(new File("spacebackground.jpg"));		
		shotImage = ImageIO.read(new File("fire.png"));//미사일 이미지 변수에 미사일 이미지 저장
		shipImage = ImageIO.read(new File("starship.png"));//우주선 이미지 변수에 우주선 이미지 저장
		kingImage = ImageIO.read(new File("kingsprite.png"));
		fruitImage = ImageIO.read(new File("fruit.png"));
		greatBackground = ImageIO.read(new File("great.png"));
	}   
	catch (IOException e) 
	{//만약 이미지를 제대로 저장하지 못했다면,
		e.printStackTrace();// 에러난 것을 보여주고 에러문을 출력합니다.
	}
	GraphicPanel panel = new GraphicPanel();
	panel.setFocusable(true);
	frame.getContentPane().add(panel);
	frame.pack();
	frame.setSize(1000,   700);
	frame.setVisible(true);
	this.requestFocus();// 우주선이 계속 움직임일 수 있게 가능하게함
	this.initSprites();// 외계인과 우주선을 기본설정하는 메소드를 실행
	addKeyListener(this);// 키가 눌리면 반응하게함.
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
		if(e.getKeyCode()==KeyEvent.VK_P) //게임 멈춤
			t.stop();
			
	} 
	public void keyPressed(KeyEvent e){}
	public void actionPerformed(ActionEvent e)	// on timer event
	{
		for (int i = 0; i < sprites.size(); i++) 
		{
			Sprite   sprite = (Sprite) sprites.get(i);// arraylist안에 있던 캐릭터들을 불러옴
			sprite.move();//캐릭터들을 움직이게 시킨다.
		}
		FruitSprite   fruit;
		BombSpriteOne   bomb;
		for (int p = 0; p < sprites.size(); p++) 
		{// arraylist안에있는 나와 다른 데이터들을 비교시킴(부딪히면 제거하기위해)
			for (int s = p + 1; s < sprites.size(); s++) 
			{//0번째 캐릭터는 1,2,3..순서로 비교	1번쨰 캐릭터는 2,3,4.. 순서로 비교				
				Sprite   me = (Sprite) sprites.get(p);// arraylist안에 있던 캐릭터를 불러옴
				Sprite   other = (Sprite) sprites.get(s);// arraylist안에 있던 또 다른캐릭터를 불러옴		
				if (me.checkCollision(other)) {// 위의 두개의 캐릭터가 충돌하면	
					me.handleCollision(other);//me가미사일, other이 외계인이면 미사일,외계인 제거 | me가 우주선, other이 외계인이면 게임 종료
					other.handleCollision(me);//other가 미사일, me가 외계인이면 미사일, 외계인 제거 | other가 우주선, me가 외계인이면 게임 종료
				}
			}
		}
		repaint();//여러 외계인이 제거된 후에 다시그리기
		try 
		{
			Thread.sleep(8);//프로그램 실행 속도 조절. 수가 커질수록 캐릭터들이 천천히 움직임 
		}   catch (Exception e1) 
			{
			}
		
	}
	
@Override
public void paint(Graphics   g) 
{
		
	if (score == 0)//점수가 0이면
		scorex = 80;//화면은 x좌표 80에 위치
	else if (score >=250   && score<1000)//점수가 250에서 1000이면
		scorex = 75;//화면은 x좌표 75에 위치
	else//그 외
		scorex = 70;//화면은 x좌표 70에 위치
	String   zcore = (String.valueOf(score));//점수를 문자열로 바꾸어 화면 출력 가능하게함.
	super.paint(g);// 부모클래스의 paint메소드에 값 전달
	g.setColor(Color.black);// 배경색 검은색으로 지정. 나중에 멋진 배경으로 바꿀거임
	g.drawImage(spaceBackground, 1, 1, 1000, 700, null, this);
	for (int i = 0; i < sprites.size(); i++) 
	{
		Sprite   sprite = (Sprite) sprites.get(i);// arraylist안에 있던 캐릭터들을 불러옴
		sprite.draw(g);//캐릭터들을 화면에 그린다.
	}
	g.setFont(new Font("고딕체",Font.BOLD,30));//화면에 나타나는 점수의 글씨체와 크기 조정
	g.drawString("SCORE", 40, 560);//SCORE이라는 글씨가 보이게함
	g.drawString(zcore, scorex, 600);//화면에 점수가 보이게  출 력.
	if(sprites.size()<=1)//외계인과 우주 대마왕을 처치하면
	{
		g.drawImage(greatBackground, 1, 1, 1000, 700, null, this);//승리했다는 창이 뜨게함
		g.drawString("당신의 점수는", 400, 550);
		g.drawString(zcore,420, 600);
	}
	
	}
}
	 
	 
	 
	 
/*
 * 외계인과 우주선을 기본설정하는 메소드
 */
	private void initSprites()   
	{
		starship = new   StarShipSprite(this, shipImage, 450, 620);// 우주선 변수에 우주선이미지, 위치값 저장
		king = new KingSprite(this,kingImage, 450, 10);
		sprites.add(king);
		sprites.add(starship);// arraylist에 우주선을 추가
		for (int y = 0; y < 5; y++) 
		{// 행대로 외계인추가
			for (int x = 0; x < 12; x++) 
			{// 열대로 외계인추가 (열대로 추가후, 행대로 외계인 추가)
				Sprite   alien = new AlienSprite(this, alienImage, 100 + (x * 60), (80) + y * 30);
				// 외계인들이 처음 나오는 위치장소 저장.
				sprites.add(alien);// arraylist에 외계인들 추가
			}
		}
	}
	
/*
 * 게임 시작하는 메소드. 구현은 했는데 써먹지는 않음. 
 */
	private void startGame() 
	{
		sprites.clear();// arraylist에 저장되어 있던 우주선과 외계인들을 모두 삭제함
		initSprites();// 외계인과 우주선을 초기화 시키는 메소드
	}
	
/*
* 게임을 끝내는 메소드
*/
	public void endGame(Sprite   a,Sprite b) 
	{//게임을 끝내는 메소드
		sprites.remove(a);//충돌한 캐릭터를 삭제
		sprites.remove(b);//충돌한 캐릭터를 삭제
		new OverFrame(getScore());//게임오버 창이 뜨게함
		this.setVisible(false);//게임 진행창 안보이게하기
		frame.dispose();//게임 진행창은 버림
	}

/*
 * 미사일에 외계인이 맞으면 arraylist에서 미사일,외계인 모두 삭제하는 메소드
 */
public void   removeSprite(Sprite sprite) 
{
	sprites.remove(sprite);
}

/*
 * 모든 객체를 제거하는 메소드
 */

public void remeveAllSprite()
{
	sprites.clear();
	score=scorex=0;
}
	
/*
 * 미사일 기본설정 메소드
 */
public void fire() 
{
	ShotSprite   shot = new ShotSprite(this, shotImage, starship.getX() + 10, starship.getY() - 30);
	// 미사일 이미지설정과 미사일이 어느 좌표에 위치할지 지정
	sprites.add(shot);// arraylist에 미사일 변수 추가
}

/*
 * 눌려진 키들의 이벤트를 처리하는 메소드
 */
	@Override
public void   keyPressed(KeyEvent e) 
{
	if (e.getKeyCode()   == KeyEvent.VK_LEFT)//눌려진 키가 왼쪽키면
	starship.setDx(-3);//우주선이 왼쪽으로 3만큼 움직임
	if (e.getKeyCode()   == KeyEvent.VK_RIGHT)//눌려진 키가 오른쪽키이면
	starship.setDx(+3);//우주선이 오른쪽으로 3만큼 움직임
	if (e.getKeyCode()   == KeyEvent.VK_SPACE)//눌려진 키가 스페이스바이면
	fire();	
	if(e.getKeyCode()==KeyEvent.VK_Q)// 게임 종료
	System.exit(0);
	if(e.getKeyCode()==KeyEvent.VK_S)// 게임 종료
	this.startGame();
}
/*
 * 키를 눌렀다가 뗄 때 이벤트 처리하는 메소드
 */

@Override
public void   keyReleased(KeyEvent e) 
{
	if (e.getKeyCode()   == KeyEvent.VK_LEFT)//왼쪽키를 눌렀다가 뗀다
	starship.setDx(0);//왼쪽으로 가다 멈춤
	if (e.getKeyCode()   == KeyEvent.VK_RIGHT)//오른쪽 키를 눌렀다가 뗀다
	starship.setDx(0);//오른쪽으로 가다 멈춤.
	if(e.getKeyCode()==KeyEvent.VK_Q)// 게임 종료
	{
	System.exit(0);
	}
//	if(e.getKeyCode()==KeyEvent.VK_P) //게임 멈춤
//	running=false;
}

@Override
public void   keyTyped(KeyEvent arg0) 
{//안쓰는 메소드지만 KeyListener 안에 있기 때문에 오버라이드
}


}
