import java.awt.Color;//배경색 설정을 위한 라이브러리
import java.awt.Font;
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

//import com.sun.prism.Image;
public class OverFrame extends JPanel implements ActionListener   {
	private JButton retry;//누르면 메인화면 보여줌
	private BufferedImage overBackground;//게임오버 배경설정
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
		g.setFont(new Font("고딕체",Font.BOLD,30));
		g.drawString("당신의 점수는", 400, 400);
		g.drawString(zcore,450, 460);
	}     
	public void   actionPerformed(ActionEvent e){
		if(retry==e.getSource())//retry버튼 클릭시
		{
			this.setVisible(false);
			frame.dispose();
			new MainFrame();	
		}
	}

}
