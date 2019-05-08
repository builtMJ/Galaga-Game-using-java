
import java.awt.Image;//매개변수로 이미지를 받아오기위해 사용하는 라이브러리
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/*
 * 미사일을 구현하는 클래스
 */
public class ShotSprite extends Sprite {//Sprite를 상속받음.
	public GalagaGame game;

	private FruitSprite fruit;
	private BufferedImage fruitImage;
	
/*	
 * 갤러그 게임에 구현하기 위해 첫번째 변수는 GalagaGame, 다음 변수들은 이미지, 캐릭터들의 위치를 받아옴
 */
	public   ShotSprite(GalagaGame game, Image image, int x, int y) {
	super(image, x, y);//Sprite 생성자에게 이미지, 캐릭터 위치를 전달.
	this.game = game;//받아온 갤러그 게임을 복사
	//game.num=1;
	dy = -3;//세로방향으로 위로 거리 3만큼 움직임.
	try {	
	fruitImage = ImageIO.read(new File("fruit.png"));
	}   catch (IOException e) {//만약 이미지를 제대로 저장하지 못했다면,
	e.printStackTrace();// 에러난 것을 보여주고 에러문을 출력합니다.
	}
	}
/*
 * 미사일 움직임 구현 및 통제 메소드
 */
	@Override
	public void move() {
	
	super.move();//미사일이 계속 거리 3만큼 위로 움직이게 만듬.
	
	if (y < -50) {//화면 밖으로 미사일이 나가면, 미사일이 윗쪽 프레임 벽을 벗어나면
	
	
	game.removeSprite(this);//미사일 삭제
	
	}
	
	}
/*
 * 미사일이 외계인과 부딪혔을 때 미사일과 외계인이 제거되는 메소드
 */
	@Override
	public void   handleCollision(Sprite other) {

	if (other instanceof AlienSprite) {//other가 가리키는 객체가 외계인이면
	game.setScore(100);
	game.removeSprite(this);//arraylist에서  미사일 삭제
	game.removeSprite(other);//arraylist에서 외계인 삭제
	fruit =new FruitSprite(game, fruitImage,other.getX() + 10,other.getY() - 30);
	game.sprites.add(fruit);
	
	}
    }
}