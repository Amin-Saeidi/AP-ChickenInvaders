package game.objects.hens;

import java.awt.Graphics2D;
import engine.Animatable;

public class RectangleGroup implements Animatable {
	public SingleHen[][] rectangleHens;
	public double deltaX =65;
	public double deltaY =65;
	public int row , cul;
	public double hensHorizontalVelocity;
	private double heightOfImage , widthOfImage;
	private String whichHenEven;
	private String whichHenOdd;
	public boolean painCheck = true;

	public RectangleGroup(int row , int cul , double heightOfImage , double widthOfImage , String whichHenEven , String whichHenOdd) {
		this.heightOfImage=heightOfImage;
		this.widthOfImage=widthOfImage;
		this.whichHenEven =whichHenEven;
		this.whichHenOdd =whichHenOdd;
		this.row = row;
		this.cul = cul;
		init();
	}

	private void init() {
		rectangleHens = new SingleHen[row][cul];
		hensHorizontalVelocity = 1 ;
		for (int i=0 ; i < row ; i++ ) {
			for (int j=0 ; j<cul ; j++) {
				if (j%2 ==0 ) {
					rectangleHens[i][j] = new SingleHen((j+1) * deltaX +100 , (i+0.6) * deltaY -300, hensHorizontalVelocity , 0 ,heightOfImage,widthOfImage,"rectangle",whichHenEven,this);
				}
				else {
					rectangleHens[i][j] = new SingleHen((j+1) * deltaX +100 , (i+0.6) * deltaY -300, hensHorizontalVelocity , 0 ,heightOfImage,widthOfImage,"rectangle",whichHenOdd ,this);

				}
			}
		}

	}

	@Override
	public void paint(Graphics2D g2) {
		if (painCheck==true) {
			for (int i=0 ; i < row ; i++ ) {
				for (int j=0 ; j<cul ; j++) {
					rectangleHens[i][j].paint(g2);
					if (isHeatBoundary(i, j , 10) || isHeatBoundary(i, j , 1000)) {
						for (int u=0 ; u < row ; u++ ) {
							for (int k=0 ; k<cul ; k++) {
								rectangleHens[u][k].setVx(-rectangleHens[u][k].getVx());
							}
						}
					}

				}
			}
		}
	}

	private boolean isHeatBoundary(int i, int j , int boundary) {
		return rectangleHens[i][j].getX() <(boundary + hensHorizontalVelocity) &&
				(boundary - hensHorizontalVelocity)< rectangleHens[i][j].getX();
	}

	public void deadHen(int i ,int  j) {
		if (rectangleHens[i][j].hitToDeath <= 0) {
			rectangleHens[i][j].paintCheck = false;	
			rectangleHens[i][j].hit = true;
		}
	}


	@Override
	public void move() {
	}

}
