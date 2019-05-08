import java.awt.Image;//�Ű������� �̹����� �޾ƿ������� ����ϴ� ���̺귯��

/*
 * �ܰ����� �����ϴ� Ŭ����
 */
public class AlienSprite extends Sprite {
	private GalagaGame game;

	/*
	 * ������ ���Ӱ�, �ܰ����� �̹���, �ܰ��� ��ġ�� �޾ƿ��� ������
	 */
	public   AlienSprite(GalagaGame game, Image image, int x, int y) {
	super(image, x, y);//�ܰ��� �̹���, �ܰ��� ��ġ�� Sprite���� ����
	this.game = game;//GalagaGame ����
	dx = -2;//���� �������� -3��ŭ ��� ������. ������ �����ӿ� �ε����� +3. -3, +3 �ݺ�
	}
/*
 * �ܰ����� �������� �����ϴ� �޼ҵ�.
 */
	@Override
	public void move() {
	
	if(((dx < 0)   && (x < 10))){
	moveLeft();
	y += 10;//�Ʒ��� ���� ������
	if (y > 800) {//�ܰ����� ���ּ��� �����ϸ�
	game.sprites.remove(this);//���� ��. ���α׷� ����.
	}
	}
	else if(((dx > 0)   && (x > 950)))
	{
	//moveRight();
	y += 10;//�Ʒ��� ���� ������
	if (y > 800) {//�ܰ����� ���ּ��� �����ϸ�
	game.sprites.remove(this);//���� ��. ���α׷� ����.
	}
	}
	super.move();//������ ���� ����� ������ �ܰ����� ��� ������.
	}
	
	public void moveLeft()//�� �������� ���� �޼ҵ�
    {	
    	dx = -2;//�Ÿ� 2��ŭ ����̵�
    	if (x<=5)//���� �����ӿ� �ٰ�����
    	x = 950;//�ٽ� ������ �����ӿ��� ��Ÿ������
    }
 

}