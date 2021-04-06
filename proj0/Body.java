public class Body{
      public double xxPos;
      public double yyPos;
      public double xxVel;
      public double yyVel;
      public double mass;
      public String imgFileName;
      public static final double G = 6.67*Math.pow(10,-11);
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
          this(b.xxPos,b.yyPos,b.xxVel,b.yyVel,b.mass,b.imgFileName);
      }
      public double calcDistance(Body b2){
          double r_2;
          double r;
          double dx;
          double dy;
          dx = Math.abs(this.xxPos - b2.xxPos);
          dy = Math.abs(this.yyPos - b2.yyPos);
          r = Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
          return r;
      }
      public double calcForceExertedBy(Body b2){
          double F;
          double r;
          r = this.calcDistance(b2);
          F = (G*this.mass*b2.mass)/(r*r);
          return F;

      }
      public double calcForceExertedByX(Body b2){
          double F;
          double r;
          double Fx;
          double dx;
          if(this.xxPos >= b2.xxPos){
            dx = this.xxPos - b2.xxPos;
          } else{
            dx = b2.xxPos - this.xxPos;
          }

          r = this .calcDistance(b2);
          F = this .calcForceExertedBy(b2);
          Fx = F*dx/r;
          return Fx;

      }
      public double calcForceExertedByY(Body b2){
          double F;
          double r;
          double Fy;
          double dy;
          if(this.yyPos >= b2.yyPos){
            dy = this.yyPos - b2.yyPos;
          }else{
            dy = b2.yyPos - this.yyPos;
          }
          r = this .calcDistance(b2);
          F = this .calcForceExertedBy(b2);
          Fy = F*dy/r;
          return Fy;

      }
      public double calcNetForceExertedByX(Body[] allBodys){
          double F_netx= 0.0;
          for(int i= 0;i<allBodys.length;i++){
              double Fx;

              if(this.equals(allBodys[i])){
                  continue;
              }
              if(this.xxPos<=allBodys[i].xxPos){
                Fx = this.calcForceExertedByX(allBodys[i]);
              }else{
                Fx = -this.calcForceExertedByX(allBodys[i]);

              }
              F_netx +=Fx;
          }
          return F_netx;
      }
      public double calcNetForceExertedByY(Body[] allBodys){

          double F_nety= 0.0;
          for(int i= 0;i<allBodys.length;i++){
              double Fy;
              if(this.equals(allBodys[i])){
                  continue;
              }
              if(this.yyPos<=allBodys[i].yyPos){
                Fy = this.calcForceExertedByY(allBodys[i]);
              }else{
                Fy = -this.calcForceExertedByY(allBodys[i]);
              }
              F_nety +=Fy;

          }
          return F_nety;

      }
      public void update(double dt,double F_netx,double F_nety){
          double a_netX;
          double a_netY;

          a_netX = F_netx/mass;
          a_netY = F_nety/mass;
          xxVel = xxVel + dt*a_netX;
          yyVel = yyVel + dt*a_netY;
          xxPos = xxPos + dt*xxVel;
          yyPos = yyPos + dt*yyVel;
      }
      public void draw(){
          StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);


}
}
