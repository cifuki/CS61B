public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    /* 勾股定理 */
    public double calcDistance(Planet planet) {
        double dx = this.xxPos - planet.xxPos;
        double dy = this.yyPos - planet.yyPos;
        double r = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return r;
    }

    /* 万有引力定律 */
    public double calcForceExertedBy(Planet planet) {
        double F;
        double r = calcDistance(planet);
        if (r == 0) {
            F = 0;
        } else {
            F = G * this.mass * planet.mass / Math.pow(r, 2);
        }
        return F;
    }

    /*
    * 计算单个星球在x轴上的引力
    */
    public double calcForceExertedByX(Planet planet) {
        double fx;
        double dx = planet.xxPos - this.xxPos;
        double r = calcDistance(planet);
        double F = calcForceExertedBy(planet);
        if (r == 0) {
            fx = 0;
        } else {
            fx = F * dx / r;
        }
        return fx;
    }

    /*
     * 计算单个星球在y轴上的引力
     */
    public double calcForceExertedByY(Planet planet) {
        double fy;
        double dy = planet.yyPos - this.yyPos;
        double r = calcDistance(planet);
        double F = calcForceExertedBy(planet);
        if (r == 0) {
            fy = 0;
        } else {
            fy = F * dy / r;
        }
        return fy;
    }

    /*
     * 计算多个星球在x轴上的引力总和
     */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netFx = 0;
        for (Planet planet : allPlanets) {
            double fx = calcForceExertedByX(planet);
            netFx += fx;
        }
        return netFx;
    }


    /*
     * 计算多个星球在y轴上的引力总和
     */
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netFy = 0;
        for (Planet planet : allPlanets) {
            double fy = calcForceExertedByY(planet);
            netFy += fy;
        }
        return netFy;
    }

    public void update(double time, double netFx, double netFy) {
        //计算各方向的加速度
        double ax = netFx / this.mass;
        double ay = netFy / this.mass;

        //计算各方向的速度
        double xxNewVel = this.xxVel + time * ax;
        double yyNewVel = this.yyVel + time *ay;

        //计算各方向的位移
        double xxNewPos = this.xxPos + time * xxNewVel;
        double yyNewPos = this.yyPos + time * yyNewVel;

        //更新速度和位置
        this.xxVel = xxNewVel;
        this.yyVel = yyNewVel;
        this.xxPos = xxNewPos;
        this.yyPos = yyNewPos;
    }
    
}
