import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BombSpriteOne extends Sprite{
	public GalagaGame game;
	private BufferedImage bombImage;
	
	public   BombSpriteOne(GalagaGame game, Image image, int x, int y,int dx, int dy) {
	super(image, x, y);//Sprite 생성자에게 이미지, 캐릭터 위치를 전달.
	this.game = game;//받아온 갤러그 게임을 복사
	
	this.dx=dx;//움직일 거리정보를 받아옴
	this.dy=dy;//움직일 거리 정보를 받아옴
	
	}
	
	@Override
	public void move() {
	super.move();//폭탄이 받아온 거리값만큼 움직이게함
	if (y > 620) {//화면 밖으로 폭탄이 나가면, 폭탄이 아래쪽 프레임 벽을 벗어나면
	game.removeSprite(this);//폭탄 삭제	
	}
	}
	@Override
	public void   handleCollision(Sprite other) {
	if (other instanceof   StarShipSprite) {//other가 가리키는 객체가 우주선이면
	game.sprites.remove(this);//폭탄 제거
	game.sprites.remove(other);//우주선 제거
	game.endGame(this,other);//게임 종료
	}
	}
}
