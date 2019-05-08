import java.awt.Image;//매개변수로 이미지를 받아오기위해 사용하는 라이브러리

/*
 * 외계인을 구현하는 클래스
 */
public class AlienSprite extends Sprite {
	private GalagaGame game;

	/*
	 * 갤러그 게임과, 외계인의 이미지, 외계인 위치를 받아오는 생성자
	 */
	public   AlienSprite(GalagaGame game, Image image, int x, int y) {
	super(image, x, y);//외계인 이미지, 외계인 위치를 Sprite에게 전달
	this.game = game;//GalagaGame 저장
	dx = -2;//가로 방향으로 -3만큼 계속 움직임. 하지만 프레임에 부딪히면 +3. -3, +3 반복
	}
/*
 * 외계인의 움직임을 통제하는 메소드.
 */
	@Override
	public void move() {
	
	if(((dx < 0)   && (x < 10))){
	moveLeft();
	y += 10;//아래로 한줄 내려감
	if (y > 800) {//외계인이 우주선과 접촉하면
	game.sprites.remove(this);//게임 끝. 프로그램 종료.
	}
	}
	else if(((dx > 0)   && (x > 950)))
	{
	//moveRight();
	y += 10;//아래로 한줄 내려감
	if (y > 800) {//외계인이 우주선과 접촉하면
	game.sprites.remove(this);//게임 끝. 프로그램 종료.
	}
	}
	super.move();//프레임 밖을 벗어나지 않으면 외계인은 계속 움직임.
	}
	
	public void moveLeft()//한 방향으로 도는 메소드
    {	
    	dx = -2;//거리 2만큼 계속이동
    	if (x<=5)//왼쪽 프레임에 다가가면
    	x = 950;//다시 오른쪽 프레임에서 나타나게함
    }
 

}