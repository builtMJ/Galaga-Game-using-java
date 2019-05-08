import java.awt.Image;//매개변수로 이미지를 받아오기위해 사용하는 라이브러리

public class StarShipSprite extends Sprite {//Sprite를 상속받음.
	private GalagaGame game;
/*
 * 갤러그 게임에 구현하기 위해 첫번째 변수는 GalagaGame, 다음 변수들은 이미지, 캐릭터들의 위치를 받아오는 생성자
 */
	public   StarShipSprite(GalagaGame game, Image image, int x, int y) {
	super(image, x, y);//Sprite 클래스에 이미지, 캐릭터 위치 전달.
	this.game = game;//받아온 Galaga   game 저장
	dx = 0;//가로 움직임 구현 변수
	dy = 0;//세로 움직임 구현 변수(근데 어차피 우주선은 세로로 못움직임)
	}

	@Override
	/*우주선의 움직임 구현메소드 */
	public void move() {
	
	if ((dx < 0)   && (x < 10)) {//우주선이 왼쪽방향으로 움직이면서 패널 왼쪽 면에 부딪히면
	return;//움직임 중지
	}
	if ((dx > 0)   && (x > 800)) {//우주선이 오른쪽 방향으로 움직이면서 패널 오른쪽 면에 부딪히면
	return;//움직임 중지
	}
	super.move();// 위에 두 경우를 제외하고선 우주선의 움직임을 가능하게함
	}
	
	/*sprite에서 선언했던 메소드 오버라이딩*/
	@Override
	public void   handleCollision(Sprite other) {//다른 객체와 충돌시
	if (other instanceof AlienSprite) {//만약에 부딪힌 객체가 외계인이면
	game.sprites.remove(this);
	game.sprites.remove(other);
	game.endGame(this,other);//게임 종료
	}
	}
}