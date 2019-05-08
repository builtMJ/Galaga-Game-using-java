import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BombSpriteOne extends Sprite{
	public GalagaGame game;
	private BufferedImage bombImage;
	
	public   BombSpriteOne(GalagaGame game, Image image, int x, int y,int dx, int dy) {
	super(image, x, y);//Sprite �����ڿ��� �̹���, ĳ���� ��ġ�� ����.
	this.game = game;//�޾ƿ� ������ ������ ����
	
	this.dx=dx;//������ �Ÿ������� �޾ƿ�
	this.dy=dy;//������ �Ÿ� ������ �޾ƿ�
	
	}
	
	@Override
	public void move() {
	super.move();//��ź�� �޾ƿ� �Ÿ�����ŭ �����̰���
	if (y > 620) {//ȭ�� ������ ��ź�� ������, ��ź�� �Ʒ��� ������ ���� �����
	game.removeSprite(this);//��ź ����	
	}
	}
	@Override
	public void   handleCollision(Sprite other) {
	if (other instanceof   StarShipSprite) {//other�� ����Ű�� ��ü�� ���ּ��̸�
	game.sprites.remove(this);//��ź ����
	game.sprites.remove(other);//���ּ� ����
	game.endGame(this,other);//���� ����
	}
	}
}
