class NBody{
	public static void main(String[] args){
		if(args.length == 0){
			System.out.println("Please provide information as the command arguments.");
			System.out.println("i.e. java NBody 157788000.0 25000.0 data/planets.txt");
			return;
		}
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double r = readRadius(filename);
		Body[] bodies = readBodies(filename);

		// draw background
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-r, r);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");

		// draw bodies
		for(Body body:bodies){
			body.draw();
		}

		// show
		StdDraw.show();
		StdDraw.pause(2000);

        // start animation
		for(double t=0.0d; t<=T; t+=dt){
			// calculate xF and yF for each body
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];
			for(int i=0; i<bodies.length; i++){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}

			// update each body
			for(int i=0; i<bodies.length; i++){
				bodies[i].update(dt, xForces[i], yForces[i]);
			}

			// draw background
		    StdDraw.picture(0, 0, "images/starfield.jpg");

		    // draw bodies
		    for(Body body:bodies){
				body.draw();
			}

			// show
			StdDraw.show();
			StdDraw.pause(10);
		}

		// print out the final state of the universe
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}
	}

	public static double readRadius(String file){
		if(!"".equals(file)){
			In in = new In(file);
			in.readInt();
			return in.readDouble();
		}
		return 0.0;
	}

	public static Body[] readBodies(String file){
		if(!"".equals(file)){
			In in = new In(file);
			int N = in.readInt();
			Body[] bodies = new Body[N];
			in.readDouble();
			for(int i=0; i<N; i++){
				bodies[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
			}
			return bodies;
		}
		return null;
	}
}