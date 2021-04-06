public class NBody{
    public static String imageToDraw = "images/starfield.jpg";
    public static double readRadius(String s){
        In in = new In(s);
        int number = in.readInt();
        double radius = in.readDouble();

        return radius;
    }
    public static Body[] readBodies(String s){
        In in = new In(s);
        int number = in.readInt();
        double radius = in.readDouble();
        Body[] allBodys =  new Body[number];
        for(int i = 0;i < number; i++){
            allBodys[i] = new Body(in.readDouble(),in.readDouble(),in.readDouble(),
                                    in.readDouble(),in.readDouble(),in.readString());

        }
        return allBodys;
    }
    public static void main(String[] args){
        double T  = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] allBodys = readBodies(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);
        for(Body b : allBodys){
              b.draw();
        }
        StdDraw.show();
        double i = 0;
        while(i<=T){
          double[] xForces = new double[allBodys.length];
          double[] yForces = new double[allBodys.length];
          for(int j = 0; j<allBodys.length; j++){
              xForces[j] = allBodys[j].calcNetForceExertedByX(allBodys);
              yForces[j] = allBodys[j].calcNetForceExertedByY(allBodys);

          }
          for(int j = 0; j<allBodys.length; j++){
              allBodys[j].update(dt,xForces[j],yForces[j]);

          }
          StdDraw.enableDoubleBuffering();
          StdDraw.setScale(-radius,radius);
          StdDraw.clear();
          StdDraw.picture(0, 0, imageToDraw);
          for(Body b : allBodys){
                b.draw();
          }
          StdDraw.show();
      		StdDraw.pause(10);
          i = i + dt;
        }
        StdOut.printf("%d\n", allBodys.length);
        StdOut.printf("%.2e\n", radius);
        for (int j = 0; j < allBodys.length; j++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          allBodys[j].xxPos, allBodys[j].yyPos, allBodys[j].xxVel,
                          allBodys[j].yyVel, allBodys[j].mass, allBodys[j].imgFileName);
}

    }
}
