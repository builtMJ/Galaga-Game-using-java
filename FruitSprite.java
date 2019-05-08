import java.awt.Image;

public class FruitSprite extends Sprite {
	public GalagaGame game;
	
	public   FruitSprite(GalagaGame game, Image image, int x, int y) {
	super(image, x, y);//Sprite 생성자에게 이미지, 캐릭터 위치를 전달.
	this.game = game;//받아온 갤러그 게임을 복사
	dy = +3;//세로방향으로 위로 거리 3만큼 움직임.
	}
	
public void move() {
	
	super.move();//과일이 계속 거리 3만큼 위로 움직이게 만듬.
	
	
	if (y > 620) {//화면 밖으로 과일이 나가면, 과일이 아래쪽 프레임 벽을 벗어나면
	
	
	game.removeSprite(this);//과일 삭제

	}
	
	}

	public void   handleCollision(Sprite other) {

	if (other instanceof   StarShipSprite) {//other가 가리키는 객체가 우주선이면
	game.setScore(50);
	game.removeSprite(this);//arraylist에서  과일 삭제
	
	
	}
	}
}
