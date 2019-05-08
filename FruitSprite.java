import java.awt.Image;

public class FruitSprite extends Sprite {
	public GalagaGame game;
	
	public   FruitSprite(GalagaGame game, Image image, int x, int y) {
	super(image, x, y);//Sprite �����ڿ��� �̹���, ĳ���� ��ġ�� ����.
	this.game = game;//�޾ƿ� ������ ������ ����
	dy = +3;//���ι������� ���� �Ÿ� 3��ŭ ������.
	}
	
public void move() {
	
	super.move();//������ ��� �Ÿ� 3��ŭ ���� �����̰� ����.
	
	
	if (y > 620) {//ȭ�� ������ ������ ������, ������ �Ʒ��� ������ ���� �����
	
	
	game.removeSprite(this);//���� ����

	}
	
	}

	public void   handleCollision(Sprite other) {

	if (other instanceof   StarShipSprite) {//other�� ����Ű�� ��ü�� ���ּ��̸�
	game.setScore(50);
	game.removeSprite(this);//arraylist����  ���� ����
	
	
	}
	}
}
