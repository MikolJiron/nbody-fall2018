/**
 * @author Michael Jiron
 *
 * All of the constructors and methods for the Body class
 */
public class Body {
    private double myXPos;
    private double myYPos;
    private double myXVel;
    private double myYVel;
    private double myMass;
    private String myFileName;
    public static final double GRAV = 6.67E-11;

    /**
     * Create a body from parameters
     * @param xp
     * @param yp
     * @param xv
     * @param yv
     * @param mass
     * @param filename
     */
    public Body(double xp, double yp, double xv, double yv, double mass, String filename){
        myXPos = xp;
        myYPos = yp;
        myXVel = xv;
        myYVel = yv;
        myMass = mass;
        myFileName = filename;
    }

    /**
     * Copy constructor: copy instance variables from one body to this body
     * @param b
     */
    public Body(Body b) {
        this(b.getX(), b.getY(), b.getXVel(), b.getYVel(), b.getMass(), b.getName());
    }

    /**
     *
     * @return myXPos
     */
    public double getX(){ return myXPos;}

    /**
     *
     * @return myYPos
     */
    public double getY(){ return myYPos; }

    /**
     *
     * @return myXVel
     */
    public double getXVel(){ return myXVel; }

    /**
     *
     * @return myYVel
     */
    public double getYVel(){ return myYVel; }

    /**
     *
     * @return myMass
     */
    public double getMass(){ return myMass; }

    /**
     *
     * @return myFileName
     */
    public String getName(){ return myFileName; }

    /**
     *
     * @param b
     * @return DISTANCE FROM this BODY TO BODY b
     */
    public double calcDistance(Body b){
        double dx = this.myXPos - b.getX();
        double dy = this.myYPos - b.getY();

        double dist = Math.pow(dx, 2) + Math.pow(dy, 2);
        return Math.sqrt(dist);
    }

    /**
     *
     * @param p
     * @return the force exertedy by this body on p
     */
    public double calcForceExertedBy(Body p){
        double force;

        double r = calcDistance(p);
        double mm = this.myMass * p.getMass();
        force = mm;
        force /= Math.pow(r, 2);
        force *= GRAV;
        return force;
    }

    public double calcForceExertedByX(Body p){
        double force = calcForceExertedBy(p);
        double dx = p.getX() - this.myXPos;
        double r = this.calcDistance(p);
        force *= dx;
        force /= r;
        return force;

    }

    public double calcForceExertedByY(Body p){
        double force = calcForceExertedBy(p);
        double dy = p.getY() - this.myYPos;
        double r = this.calcDistance(p);
        force *= dy;
        force /= r;
        return force;
    }

    public double calcNetForceExertedByX(Body[] bodies){
        double netForce = 0;

        for(Body b: bodies){
            if(!b.equals(this)){
                netForce += this.calcForceExertedByX(b);
            }
        }
        return netForce;
    }

    public double calcNetForceExertedByY(Body[] bodies){
        double netForce = 0;

        for(Body b: bodies){
            if(!b.equals(this)){
                netForce += this.calcForceExertedByY(b);
            }
        }
        return netForce;
    }

    public void update(double deltaT, double xforce, double yforce){
        double ax = xforce / this.myMass;
        double ay = yforce / this.myMass;

        double nvx = myXVel + deltaT * ax;
        double nvy = myYVel + deltaT * ay;

        double nx = myXPos + deltaT * nvx;
        double ny = myYPos + deltaT * nvy;

        myXPos = nx;
        myYPos = ny;
        myXVel = nvx;
        myYVel = nvy;
    }

    public void draw(){
        StdDraw.picture(myXPos, myYPos, "images/" + myFileName);
    }

}
