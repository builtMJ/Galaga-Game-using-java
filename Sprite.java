import java.awt.Graphics; //캐릭터들의 이미지를 프로그램에 그릴 때 쓰는 라이브러리
import java.awt.Image;   //매개변수를 통해 받아온 캐릭터들의 이미지를 필드에 복사할 때 쓰는 라이브러리
import java.awt.Rectangle;//this와 다른 캐릭터가 접촉하는지 확인하기 위해서 필요한 라이브러리
import java.awt.event.ActionListener;

/*
 * 캐릭터의 공통필드와 메소드를 구현하는 부모클래스
 */
import javax.swing.*;
import java.awt.*;

public class Sprite {
	protected int x; // 현재 위치의 x좌표
	protected int y; // 현재 위치의 y좌표
	protected int dx; // 단위시간에 움직이는 x방향 거리
	protected int dy; // 단위시간에 움직이는 y방향 거리
	private Image image; // 캐릭터가 가지고 있는 이미지
	
	// 생성자
	public Sprite(Image   image, int x, int y) 
	{
	
	this.image = image;//받아온 이미지 저장
	this.x = x;//받아온 캐릭터의 가로좌표 위치 저장
	this.y = y;//받아온 캐릭터의  세로좌표 위치 저장
	
	}

	/* 캐릭터의 가로 길이를 반환하는 메소드*/
	public int getWidth() 
	{
	return   image.getWidth(null);
	}

	/* 캐릭터의 세로 길이를 반환하는 메소드 */
	public int getHeight() 
	{
	return   image.getHeight(null);
	}

	/* 캐릭터를 화면에 그리는 메소드 */
	public void draw(Graphics   g) 
	{
	g.drawImage(image,   x, y, null);
	}

	/* 캐릭터를 움직이는 메소드 */
	public void move() {
	x   += dx;
	y   += dy;
	}

	/* 캐릭터가 왼쪽으로 얼만큼 움직일지 설정하는 메소드 */
	public void setDx(int dx) {
	this.dx = dx;
	}

	/* 캐릭터가 오른쪽으로 얼만큼 움직일지 설정하는 메소드*/
	public void setDy(int dy) {
	this.dy = dy;
	}

	/* 캐릭터가 왼쪽으로 얼만큼 움직이는지 반환하는 메소드 */
	public int getDx() {
	return dx;
	}

	// 캐릭터가 오른쪽으로 얼만큼 움직이는지 반환하는 메소드 */
	public int getDy() {
	return dy;
	}

	/* 캐릭터의 x좌표 위치를 반환하는 메소드 */
	public int getX() {
	return x;
	}

	/* 캐릭터의 y좌표 위치를 반환하는 메소드 */
	public int getY() {
	return y;
	}

	/* 다른 캐릭터와의 충돌 여부를 계산한다. 충돌이면 true를 반환하는 메소드. Rectangle라이브러리 안에 intersects가 존재하기 때문에 캐릭터들을 Rectangle 변수로 바꾸어서 충돌 여부 비교. */
	public boolean   checkCollision(Sprite other) 
	{
	Rectangle   myRect = new Rectangle();
	Rectangle   otherRect = new Rectangle();
	myRect.setBounds(x,   y, getWidth(), getHeight());//x,y위치 설정 사각형의 높이 가로길이 설정
	otherRect.setBounds(other.getX(),   other.getY(), other.getWidth(), other.getHeight());//x,y위치 설정 및 사각형의 가로 세로길이 설정
	return   myRect.intersects(otherRect);//두개의 사각형이 위치와 크기가 같은지 검사. 두 개의 사각형의 크기, 위치가 같으면 true를 반환.
	}

	/* 충돌을 처리한다.*/
	public void   handleCollision(Sprite other) {//

	}
}