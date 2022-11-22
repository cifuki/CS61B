import java.util.ArrayList;

/**
 * @className NBody
 * @author caifuqi
 * @createDate 2022-11-22 16:02
 */
public class NBody {
    /**
     * @description 读取画图的半径
     * @param file 数据文件
     * @return 画图半径
     */
    public static double readRadius(String file) {
        In in = new In(file);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /**
     * @description 读取每个星球的参数，包括x、y轴坐标与速度，行星质量以及图片
     * @param file 数据文件
     * @return 各行星参数
     */
    public static Planet[] readPlanets(String file) {
        In in = new In(file);
        int num = in.readInt();
        int start = 0;
        double radius = in.readDouble();
        Planet[] planets = new Planet[num];
        while (start != num) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            planets[start] = planet;
            start += 1;
        }

        return planets;
    }

    public static void main(String[] args) {
        /* 读取入参 */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String file = args[2];

        double radius = readRadius(file);
        Planet[] planets = readPlanets(file);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        /*
         * 画图
         * 让卫星按时间更新位置
         * 算受力-->算加速度-->算速度-->算位置-->更新位置
         */
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];

        for (int t = 0; t < T; t += dt) {
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            /* 当各行星的位置更新后，需重新计算受力 */
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            /* 绘图 */
            for (int i = 0; i < planets.length; i++) {
                planets[i].draw();
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.show();
            StdDraw.pause(10);
            StdDraw.clear();
        }

        /* 标准化打印最终行星的各项参数 */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}