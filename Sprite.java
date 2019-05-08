import java.awt.Graphics; //ĳ���͵��� �̹����� ���α׷��� �׸� �� ���� ���̺귯��
import java.awt.Image;   //�Ű������� ���� �޾ƿ� ĳ���͵��� �̹����� �ʵ忡 ������ �� ���� ���̺귯��
import java.awt.Rectangle;//this�� �ٸ� ĳ���Ͱ� �����ϴ��� Ȯ���ϱ� ���ؼ� �ʿ��� ���̺귯��
import java.awt.event.ActionListener;

/*
 * ĳ������ �����ʵ�� �޼ҵ带 �����ϴ� �θ�Ŭ����
 */
import javax.swing.*;
import java.awt.*;

public class Sprite {
	protected int x; // ���� ��ġ�� x��ǥ
	protected int y; // ���� ��ġ�� y��ǥ
	protected int dx; // �����ð��� �����̴� x���� �Ÿ�
	protected int dy; // �����ð��� �����̴� y���� �Ÿ�
	private Image image; // ĳ���Ͱ� ������ �ִ� �̹���
	
	// ������
	public Sprite(Image   image, int x, int y) 
	{
	
	this.image = image;//�޾ƿ� �̹��� ����
	this.x = x;//�޾ƿ� ĳ������ ������ǥ ��ġ ����
	this.y = y;//�޾ƿ� ĳ������  ������ǥ ��ġ ����
	
	}

	/* ĳ������ ���� ���̸� ��ȯ�ϴ� �޼ҵ�*/
	public int getWidth() 
	{
	return   image.getWidth(null);
	}

	/* ĳ������ ���� ���̸� ��ȯ�ϴ� �޼ҵ� */
	public int getHeight() 
	{
	return   image.getHeight(null);
	}

	/* ĳ���͸� ȭ�鿡 �׸��� �޼ҵ� */
	public void draw(Graphics   g) 
	{
	g.drawImage(image,   x, y, null);
	}

	/* ĳ���͸� �����̴� �޼ҵ� */
	public void move() {
	x   += dx;
	y   += dy;
	}

	/* ĳ���Ͱ� �������� ��ŭ �������� �����ϴ� �޼ҵ� */
	public void setDx(int dx) {
	this.dx = dx;
	}

	/* ĳ���Ͱ� ���������� ��ŭ �������� �����ϴ� �޼ҵ�*/
	public void setDy(int dy) {
	this.dy = dy;
	}

	/* ĳ���Ͱ� �������� ��ŭ �����̴��� ��ȯ�ϴ� �޼ҵ� */
	public int getDx() {
	return dx;
	}

	// ĳ���Ͱ� ���������� ��ŭ �����̴��� ��ȯ�ϴ� �޼ҵ� */
	public int getDy() {
	return dy;
	}

	/* ĳ������ x��ǥ ��ġ�� ��ȯ�ϴ� �޼ҵ� */
	public int getX() {
	return x;
	}

	/* ĳ������ y��ǥ ��ġ�� ��ȯ�ϴ� �޼ҵ� */
	public int getY() {
	return y;
	}

	/* �ٸ� ĳ���Ϳ��� �浹 ���θ� ����Ѵ�. �浹�̸� true�� ��ȯ�ϴ� �޼ҵ�. Rectangle���̺귯�� �ȿ� intersects�� �����ϱ� ������ ĳ���͵��� Rectangle ������ �ٲپ �浹 ���� ��. */
	public boolean   checkCollision(Sprite other) 
	{
	Rectangle   myRect = new Rectangle();
	Rectangle   otherRect = new Rectangle();
	myRect.setBounds(x,   y, getWidth(), getHeight());//x,y��ġ ���� �簢���� ���� ���α��� ����
	otherRect.setBounds(other.getX(),   other.getY(), other.getWidth(), other.getHeight());//x,y��ġ ���� �� �簢���� ���� ���α��� ����
	return   myRect.intersects(otherRect);//�ΰ��� �簢���� ��ġ�� ũ�Ⱑ ������ �˻�. �� ���� �簢���� ũ��, ��ġ�� ������ true�� ��ȯ.
	}

	/* �浹�� ó���Ѵ�.*/
	public void   handleCollision(Sprite other) {//

	}
}