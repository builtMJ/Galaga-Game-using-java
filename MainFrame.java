import java.awt.Color;//배경색 설정을 위한 라이브러리
import java.awt.Graphics;//매개변수로 이미지를 불러와 사용하기 위한 라이브러리
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;//이클립스 외부에서 받아온 이미지를 저장할 때 자료형이 BufferedImage인것만 받아올 수 있다. 그래서 캐릭터들을 BufferedImage 자료형으로 선언한 것이다.
import java.io.File;//자바프로젝트안에 있는 이미지를 불러올 때 매개변수가 File인것을 받아온다. 따라서 받아온 이미지를 File자료형으로 바꿔서 매개변수로 전달하기위한 라이브러리이다.
import java.io.IOException;//예외상황, 즉 에러가 생겼을 때 처리하기 위한 라이브러리


import javax.imageio.ImageIO;//이미지를 받아오는데 사용하는 메소드가 들어있는 라이브러리
import javax.swing.JButton;
import javax.swing.JFrame;//프로그램 구축 틀을 만들어주는 라이브러리
import javax.swing.JPanel;//구축 틀의 뼈대가 되는 라이브러리



public class MainFrame extends JPanel implements ActionListener   
{
	private JButton start,help;//버튼 생성
	private BufferedImage skyBackground;//배경화면을 받아오는 변수
	private JFrame frame;//배경화면,패널을 놓을 프레임 생성
	private JPanel panel1;//패널 생성
	static public GalagaGame g;//GalagaGame 변수 생성
	
	MainFrame()
	{
	panel1 = new JPanel();//패널 생성
	panel1.setLocation(300,   400);//패널 위치 조정
	start = new JButton("START");//START버튼 구현
	help = new JButton("HELP");//HELP버튼 구현
	frame = new JFrame("Start Game");//창 이름 명명
	try 
	{
		skyBackground = ImageIO.read(new File("sky.png"));//배경화면을 받아옴
	}   catch (IOException e) 
		{
			e.printStackTrace();
		}
	panel1.add(start);//패널위에 버튼 추가
	panel1.add(help);//패널위에 버튼 추가
	add(panel1);//패널을 윈도우 위에 놓음
	frame.setSize(1000,   700);//창 사이즈 조절
	frame.add(this);//해당 객체를 프레임 위에 놓음
	frame.setResizable(false);
	frame.setVisible(true);
	frame.setLocation(300,   100);//창 위치 조절
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	start.addActionListener(this);
	help.addActionListener(this);
	}
	
	public void paint(Graphics   g)
	{
	super.paint(g);
	g.setColor(Color.blue);// 배경 색 조절
	g.drawImage(skyBackground, 1, 1, 1000,   700, null, this);// 배경 그림 설정
	}    
	public void   actionPerformed(ActionEvent e)
	{
		if(start==e.getSource())//START버튼 클릭시
		{
			g.sprites.clear();
			g.resetScore();
			g = new GalagaGame();//게임 실행
			this.setVisible(false);//배경그림 가리기
			frame.dispose();//배경그림 삭제
		}
		else if(help==e.getSource())
		{//HELP버튼 클릭시
			this.setVisible(false);//배경 그림 삭제
			new HelpFrame();//HELP 화면 불러오기
			frame.dispose();//배경 그림 삭제
		}
	}

	public   static void main(String[] args)
	{
	MainFrame   f = new MainFrame();
	}
}
