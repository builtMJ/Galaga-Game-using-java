
import java.awt.Image;//�Ű������� �̹����� �޾ƿ������� ����ϴ� ���̺귯��
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/*
 * �̻����� �����ϴ� Ŭ����
 */
public class ShotSprite extends Sprite {//Sprite�� ��ӹ���.
	public GalagaGame game;

	private FruitSprite fruit;
	private BufferedImage fruitImage;
	
/*	
 * ������ ���ӿ� �����ϱ� ���� ù��° ������ GalagaGame, ���� �������� �̹���, ĳ���͵��� ��ġ�� �޾ƿ�
 */
	public   ShotSprite(GalagaGame game, Image image, int x, int y) {
	super(image, x, y);//Sprite �����ڿ��� �̹���, ĳ���� ��ġ�� ����.
	this.game = game;//�޾ƿ� ������ ������ ����
	//game.num=1;
	dy = -3;//���ι������� ���� �Ÿ� 3��ŭ ������.
	try {	
	fruitImage = ImageIO.read(new File("fruit.png"));
	}   catch (IOException e) {//���� �̹����� ����� �������� ���ߴٸ�,
	e.printStackTrace();// ������ ���� �����ְ� �������� ����մϴ�.
	}
	}
/*
 * �̻��� ������ ���� �� ���� �޼ҵ�
 */
	@Override
	public void move() {
	
	super.move();//�̻����� ��� �Ÿ� 3��ŭ ���� �����̰� ����.
	
	if (y < -50) {//ȭ�� ������ �̻����� ������, �̻����� ���� ������ ���� �����
	
	
	game.removeSprite(this);//�̻��� ����
	
	}
	
	}
/*
 * �̻����� �ܰ��ΰ� �ε����� �� �̻��ϰ� �ܰ����� ���ŵǴ� �޼ҵ�
 */
	@Override
	public void   handleCollision(Sprite other) {

	if (other instanceof AlienSprite) {//other�� ����Ű�� ��ü�� �ܰ����̸�
	game.setScore(100);
	game.removeSprite(this);//arraylist����  �̻��� ����
	game.removeSprite(other);//arraylist���� �ܰ��� ����
	fruit =new FruitSprite(game, fruitImage,other.getX() + 10,other.getY() - 30);
	game.sprites.add(fruit);
	
	}
    }
}