import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class KingSprite extends Sprite{
	private GalagaGame game;
	static int num=0;//���� �븶�� ���� Ƚ�� ��� ��

	private Image kingImage;//���� �븶�� �̹��� �ҷ�����
	private BufferedImage bombImage;//��ź�̹��� �ҷ�����
	private BombSpriteOne bomb0,bomb1,bomb2,bomb3,bomb4,bomb5,bomb6;//��ź �Ű����� ����
	/*
	 * ������ ���Ӱ�, ���ִ븶���� �̹���, ���� �븶�� ��ġ�� �޾ƿ��� ������
	 */
	public   KingSprite(GalagaGame game, Image image, int x, int y) {
	super(image, x, y);//���� �븶�� �̹���, ���� �븶�� ��ġ�� Sprite���� ����
	this.game = game;//GalagaGame ����
	dx = -2;//���� �������� -2��ŭ ��� ������.
	kingImage=image;//���� �븶�� �̹��� ����
	try {
	bombImage = ImageIO.read(new File("bomb.png"));//��ź �̹��� ����

	}   catch (IOException e) {//���� �̹����� ����� �������� ���ߴٸ�,
	e.printStackTrace();// ������ ���� �����ְ� �������� ����մϴ�.
	}
	
	}
	
	@Override
public void move() 
{
	int r= (int)(((Math.random()*2)+1)*100);//��ź �� ��ġ�� �������� ����: 100~400���� ���ǰ� ����.
	
	int r1=r+200;
	int r2=r+400;
	if(x==r)
	{//�������� ��ǥ���� ������ ��ź ������
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
	{//�������� x��ǥ���� ������ ��ź ������
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
	{//�������� x��ǥ���� ������ ��ź ������
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
	
	if(((dx < 0)   && (x < 10))){//�̵� �޼ҵ�
	
	moveLeft();
	
	}
	
	super.move();//������ ���� ����� ������ ���� �븶���� ��� ������.
	
}

	public void moveLeft()
	{	
	dx = -2;
	if (x<=5)
	x = 950;
	}
	
	@Override
	public void   handleCollision(Sprite other) {//�̻��ϰ� �浹�� 10�� ������ ���� �޼ҵ�
	if (other instanceof ShotSprite) {//�̻��ϰ� �浹��
	if(num>=10){//10���̻� ������
	game.setScore(1000);//1000�� ȹ��
	game.sprites.remove(this);//���� �븶���� ����ħ
	}
	else {//10>num�̸�
	game.sprites.remove(other);//�̻��� �׸� ����
	num++;//���� Ƚ�� ����
	
	}
	
	}
	}
}
