import java.awt.Image;//�Ű������� �̹����� �޾ƿ������� ����ϴ� ���̺귯��

public class StarShipSprite extends Sprite {//Sprite�� ��ӹ���.
	private GalagaGame game;
/*
 * ������ ���ӿ� �����ϱ� ���� ù��° ������ GalagaGame, ���� �������� �̹���, ĳ���͵��� ��ġ�� �޾ƿ��� ������
 */
	public   StarShipSprite(GalagaGame game, Image image, int x, int y) {
	super(image, x, y);//Sprite Ŭ������ �̹���, ĳ���� ��ġ ����.
	this.game = game;//�޾ƿ� Galaga   game ����
	dx = 0;//���� ������ ���� ����
	dy = 0;//���� ������ ���� ����(�ٵ� ������ ���ּ��� ���η� ��������)
	}

	@Override
	/*���ּ��� ������ �����޼ҵ� */
	public void move() {
	
	if ((dx < 0)   && (x < 10)) {//���ּ��� ���ʹ������� �����̸鼭 �г� ���� �鿡 �ε�����
	return;//������ ����
	}
	if ((dx > 0)   && (x > 800)) {//���ּ��� ������ �������� �����̸鼭 �г� ������ �鿡 �ε�����
	return;//������ ����
	}
	super.move();// ���� �� ��츦 �����ϰ� ���ּ��� �������� �����ϰ���
	}
	
	/*sprite���� �����ߴ� �޼ҵ� �������̵�*/
	@Override
	public void   handleCollision(Sprite other) {//�ٸ� ��ü�� �浹��
	if (other instanceof AlienSprite) {//���࿡ �ε��� ��ü�� �ܰ����̸�
	game.sprites.remove(this);
	game.sprites.remove(other);
	game.endGame(this,other);//���� ����
	}
	}
}