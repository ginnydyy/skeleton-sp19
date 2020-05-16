class Body{

	public final static double G = 6.67e-11;
	// Its current x position
	double xxPos;
	// Its current y position
    double yyPos;
    // Its current velocity in the x direction
    double xxVel;
    // Its current velocity in the y direction
    double yyVel;
    // Its mass
    double mass;
    // The name of the file that corresponds to the image that depicts the body
    String imgFileName;

    public Body(double xP, double yP, double xV,
              double yV, double m, String img){
    	xxPos = xP;
    	yyPos = yP;
    	xxVel = xV;
    	yyVel = yV;
    	mass = m;
    	imgFileName = img;
    }

    public Body(Body b){
    	this(b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName);
    }

    public double calcDistance(Body body){
    	if(body != null){
    		return Math.sqrt(Math.pow(this.xxPos - body.xxPos, 2) + Math.pow(this.yyPos - body.yyPos, 2));
    	}
    	return 0.0d;
    }

    public double calcForceExertedBy(Body body){
    	if(body != null){
    		return (G * this.mass * body.mass) / Math.pow(calcDistance(body), 2);
    	}
    	return 0.0d;
    }

    public double calcNetForceExertedByX(Body[] bodies){
    	double force = 0.0;
    	for(Body body: bodies){
    		if(!this.equals(body)){
    			force += calcForceExertedBy(body) * (body.xxPos - this.xxPos) / calcDistance(body);
    		}
    	}
    	return force;
    }

    public double calcNetForceExertedByY(Body[] bodies){
    	double force = 0.0;
    	for(Body body: bodies){
    		if(!this.equals(body)){
    			force += calcForceExertedBy(body) * (body.yyPos - this.yyPos) / calcDistance(body);
    		}
    	}
    	return force;
    }

    public void update(double dt, double fX, double fY){
    	double aX = fX / this.mass;
    	this.xxVel = this.xxVel + dt * aX;
    	this.xxPos = this.xxPos + dt * this.xxVel;

    	double aY = fY / this.mass;
    	this.yyVel = this.yyVel + dt * aY;
    	this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }

}