/**
 * @className NBody
 * @author caifuqi
 * @createDate 2022-11-22 16:05
 */
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

    /**
     * @description 勾股定理代码实现
     * @param planet 目标行星
     * @return 与目标行星的直线距离
     */
    public double calcDistance(Planet planet) {
        double dx = this.xxPos - planet.xxPos;
        double dy = this.yyPos - planet.yyPos;
        double r = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return r;
    }

    /**
     * @description 万有引力定律代码实现
     * @param planet 目标行星
     * @return 目标行星作用在本行星上的力
     */
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

    /**
     * @description 力的分解，计算单个星球在x轴上的引力
     * @param planet 目标行星
     * @return x轴上，目标行星作用在本行星上的力
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

    /**
     * @description 力的分解，计算单个星球在y轴上的引力
     * @param planet 目标行星
     * @return y轴上，目标行星作用在本行星上的力
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

    /**
     * @description 计算多个星球在x轴上的引力总和
     * @param allPlanets 其他各行星
     * @return x轴上，其他各行星作用在本行星上的力的总和
     */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netFx = 0;
        for (Planet planet : allPlanets) {
            double fx = calcForceExertedByX(planet);
            netFx += fx;
        }
        return netFx;
    }


    /**
     * @description 计算多个星球在y轴上的引力总和
     * @param allPlanets 其他各行星
     * @return y轴上，其他各行星作用在本行星上的力的总和
     */
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netFy = 0;
        for (Planet planet : allPlanets) {
            double fy = calcForceExertedByY(planet);
            netFy += fy;
        }
        return netFy;
    }

    /**
     * @description 更新行星的x、y轴的坐标以及速度
     * @param time 单位时间
     * @param netFx x轴的受力
     * @param netFy y轴的受力
     */
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

    /**
     * @description 在图上根据坐标绘制行星
     */
    public void draw() {
        String imagePath = "./images/";
        StdDraw.picture(this.xxPos, this.yyPos, imagePath + this.imgFileName);
    }
}
