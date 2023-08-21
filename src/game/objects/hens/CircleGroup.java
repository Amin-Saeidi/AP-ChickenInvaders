package game.objects.hens;

import java.awt.Graphics2D;
import java.util.ArrayList;

import engine.Animatable;

public class CircleGroup implements Animatable {
	public double center_x;
	public double center_y;
	public double center_vx;
	public double center_vy;
	public double radius_one;
	public double radius_two;
	public double radius_three;
	public double numberOfHensInLayer_one;
	public double numberOfHensInLayer_two;
	public double numberOfHensInLayer_three;
	public String layerOneHens;
	public String layerTwoHens;
	public String layerThreeHens;
	private double heightOfImage;
	private double widthOfImage;
	public boolean paintCheck = true;
	public ArrayList<SingleHen> circularHenArray1,circularHenArray2;
	public ArrayList<SingleHen> circularHenArray3;

	public CircleGroup(double numberOfHensInLayer_one, double numberOfHensInLayer_two, double numberOfHensInLayer_three,
			double heightOfImage , double widthOfImage, double center_x , double center_y,double center_vx , double center_vy ,String whichHen1,String whichHen2,String whichHen3) {
		this.numberOfHensInLayer_one = numberOfHensInLayer_one;
		this.numberOfHensInLayer_two = numberOfHensInLayer_two;
		this.numberOfHensInLayer_three = numberOfHensInLayer_three;
		this.layerOneHens = whichHen1;
		this.layerTwoHens = whichHen2;
		this.layerThreeHens = whichHen3;
		this.center_vx = center_vx;
		this.center_vy = center_vy;
		this.heightOfImage=heightOfImage;
		this.widthOfImage=widthOfImage;
		this.center_x=center_x;
		this.center_y=center_y;
		circularHenArray1=new ArrayList<SingleHen>();
		circularHenArray2=new ArrayList<SingleHen>();
		circularHenArray3=new ArrayList<SingleHen>();
		init();
	}

	private void init() {
		radius_one = raduisDeterminate(numberOfHensInLayer_one);
		radius_two = raduisDeterminate(numberOfHensInLayer_two);
		radius_three = raduisDeterminate(numberOfHensInLayer_three);
		circularMath(numberOfHensInLayer_one , radius_one,circularHenArray1 , layerOneHens);
		circularMath(numberOfHensInLayer_two , radius_two,circularHenArray2 , layerTwoHens);
		circularMath(numberOfHensInLayer_three , radius_three,circularHenArray3 , layerThreeHens);
	}



	private void circularMath(double numberOfHens , double r , ArrayList<SingleHen> circularHenArray , String whHens) {
		for (int i = 0; i < numberOfHens; i++) {
			double degree = (2*(1+i)*Math.PI)/numberOfHens;
			circularHenArray.add(new SingleHen(center_x + r * Math.cos(degree),
					center_y + -r * Math.sin(degree),heightOfImage,widthOfImage,"circular",whHens , this));
			circularHenArray.get(i).circularRadius = r;
			circularHenArray.get(i).degree = degree;
		}


	}
	//	private void circular(double numberOfHens , double r) {
	//		for (int i = 0; i < numberOfHens; i++) {
	//			double degree = (2*(1+i)*Math.PI)/numberOfHens;
	//			deltaX = +r * (Math.cos(degree + Math.PI/280)-Math.cos(degree));
	//			deltaY = -r * (Math.sin(degree + Math.PI/280)-Math.sin(degree));
	//		}
	//	}

	private double raduisDeterminate(double k) {
		double radius= k *(Math.sqrt(Math.pow(heightOfImage , 2)+Math.pow(widthOfImage, 2)))/ (2 * Math.PI);
		return radius;
	}

	@Override
	public void paint(Graphics2D g2) {
		painting(g2);
	}

	@Override
	public void move() {
	}

	public void painting(Graphics2D g3) {
		if (paintCheck==true) {
			center_x += center_vx;
			center_y += center_vy;
			for(SingleHen hen1 : circularHenArray1) {
				hen1.setX(hen1.getX() + center_vx);
				hen1.paint(g3);
			}
			for(SingleHen hen2 : circularHenArray2) {
				hen2.setX(hen2.getX() + center_vx);
				hen2.paint(g3);
			}
			for(SingleHen hen3 : circularHenArray3) {
				hen3.setX(hen3.getX() + center_vx);
				hen3.paint(g3);
			}
			if(center_x >= 830) {
				center_vx *= -1;
			}
			if(center_y >= 210) {
				if (center_x <= 180) {
					center_vx *= -1;
				}
			}
		}

	}

}
