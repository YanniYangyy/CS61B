
public class Body {
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;
	
	public Body(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	
	public Body(Body b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
		
		//(xxPos, yyPos, xxVel, yyVel, mass, imgFileName) = b(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
	}
	
	public double calcDistance(Body b) {
		
		return Math.sqrt(Math.pow((this.xxPos - b.xxPos),2) + Math.pow((this.yyPos - b.yyPos), 2));
				
	}
	
	public double calcForceExertedBy(Body b) {
		double G = 6.67e-11;
		
		return G * this.mass * b.mass / Math.pow(this.calcDistance(b),2);
		
	}
	
	public double calcForceExertedByX(Body b) {
		
		return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);

	}
	
	public double calcForceExertedByY(Body b) {
		
		return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
		
	}
	
	public double calcNetForceExertedByX(Body[] allBodys) {
		
		double[] Fx = new double [allBodys.length];
		
		for(int i = 0; i < allBodys.length; i++) {
			if(this.equals(allBodys[i])) {
				Fx[i] = 0;
			} else {
				Fx[i] = this.calcForceExertedByX(allBodys[i]);
			}
		}
		
		double sum = 0;
		
		for (int j = 0; j < allBodys.length; j++) {
			sum += Fx[j];
		}
		return sum;
	}
	
	public double calcNetForceExertedByY(Body[] allBodys) {
		
		double[] Fy = new double [allBodys.length];
		
		for(int i = 0; i < allBodys.length; i++) {
			if(this.equals(allBodys[i])) {
				Fy[i] = 0;
			}else {
				Fy[i] = this.calcForceExertedByY(allBodys[i]);	
			}
		}
		
		double sum = 0;
		
		for (int j = 0; j < allBodys.length; j++) {
			sum += Fy[j];
		}
		return sum;
	}
	
	public void update(double dt, double fX, double fY) {
		double accelerationX = fX / this.mass;
		double accelerationY = fY / this.mass;
		
		this.xxVel += accelerationX * dt;
		this.yyVel += accelerationY * dt;
		
		this.xxPos += this.xxVel * dt;
		this.yyPos += this.yyVel * dt;

	}
	
	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}

}