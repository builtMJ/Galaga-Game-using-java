import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class KingSprite extends Sprite{
	private GalagaGame game;
	static int num=0;//우주 대마왕 맞은 횟수 계산 값

	private Image kingImage;//우주 대마왕 이미지 불러오기
	private BufferedImage bombImage;//폭탄이미지 불러오기
	private BombSpriteOne bomb0,bomb1,bomb2,bomb3,bomb4,bomb5,bomb6;//폭탄 매개변수 선언
	/*
	 * 갤러그 게임과, 우주대마왕의 이미지, 우주 대마왕 위치를 받아오는 생성자
	 */
	public   KingSprite(GalagaGame game, Image image, int x, int y) {
	super(image, x, y);//우주 대마왕 이미지, 우주 대마왕 위치를 Sprite에게 전달
	this.game = game;//GalagaGame 저장
	dx = -2;//가로 방향으로 -2만큼 계속 움직임.
	kingImage=image;//우주 대마왕 이미지 저장
	try {
	bombImage = ImageIO.read(new File("bomb.png"));//폭탄 이미지 선언

	}   catch (IOException e) {//만약 이미지를 제대로 저장하지 못했다면,
	e.printStackTrace();// 에러난 것을 보여주고 에러문을 출력합니다.
	}
	
	}
	
	@Override
public void move() 
{
	int r= (int)(((Math.random()*2)+1)*100);//폭탄 쏠 위치를 랜덤으로 선언: 100~400으로 임의값 선정.
	
	int r1=r+200;
	int r2=r+400;
	if(x==r)
	{//랜덤값과 좌표값이 같으면 폭탄 날리기
		bomb0 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),3,3);
		bomb1 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),1,3);
		bomb2 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),2,3);
		bomb3 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),0,3);
		bomb4 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),-1,3);
		bomb5 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),-2,3);
		bomb6 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),-3,3);
         game.sprites.add(bomb0);
         game.sprites.add(bomb1);
         game.sprites.add(bomb2);
         game.sprites.add(bomb3);
         game.sprites.add(bomb4);
         game.sprites.add(bomb5);
         game.sprites.add(bomb6);
	}

	if(x==r1)
	{//랜덤값과 x좌표값이 같으면 폭탄 날리기
 	 bomb0 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),3,3);
	 bomb1 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),1,3);
	 bomb2 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),2,3);
	 bomb3 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),0,3);
	 bomb4 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),-1,3);
	 bomb5 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),-2,3);
	 bomb6 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),-3,3);
	 game.sprites.add(bomb0);
	 game.sprites.add(bomb1);
	 game.sprites.add(bomb2);
	 game.sprites.add(bomb3);
	 game.sprites.add(bomb4);
	 game.sprites.add(bomb5);
	 game.sprites.add(bomb6);
	}

	if(x==r2)
	{//랜덤값과 x좌표값이 같으면 폭탄 날리기
 	 bomb0 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),3,3);
	 bomb1 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),1,3);
	 bomb2 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),2,3);
	 bomb3 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),0,3);
	 bomb4 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),-1,3);
	 bomb5 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),-2,3);
	 bomb6 = new BombSpriteOne(game,bombImage,this.getX(),this.getDy(),-3,3);
	 game.sprites.add(bomb0);
	 game.sprites.add(bomb1);
	 game.sprites.add(bomb2);
	 game.sprites.add(bomb3);
	 game.sprites.add(bomb4);
	 game.sprites.add(bomb5);
	 game.sprites.add(bomb6);
	}
	
	if(((dx < 0)   && (x < 10))){//이동 메소드
	
	moveLeft();
	
	}
	
	super.move();//프레임 밖을 벗어나지 않으면 우주 대마왕은 계속 움직임.
	
}

	public void moveLeft()
	{	
	dx = -2;
	if (x<=5)
	x = 950;
	}
	
	@Override
	public void   handleCollision(Sprite other) {//미사일과 충돌시 10번 맞으면 제거 메소드
	if (other instanceof ShotSprite) {//미사일과 충돌시
	if(num>=10){//10번이상 맞으면
	game.setScore(1000);//1000점 획득
	game.sprites.remove(this);//우주 대마왕을 물리침
	}
	else {//10>num이면
	game.sprites.remove(other);//미사일 그림 제거
	num++;//맞은 횟수 증가
	
	}
	
	}
	}
}
