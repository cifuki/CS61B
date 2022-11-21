import java.util.ArrayList;

public class NBody {
    /*
    读取画图的半径
     */
    public static double readRadius(String file) {
        In in = new In(file);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /*
    读取每个星球的参数
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
        //读取入参
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String file = args[2];

        double radius = readRadius(file);
        Planet[] planets = readPlanets(file);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        /*
        画图
        让卫星按时间更新位置
        算受力-->算加速度-->算速度-->算位置-->更新位置
         */
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];

        for (int t = 0; t < T; t += dt) {
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            //重新计算受力
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            //绘图
            for (int i = 0; i < planets.length; i++) {
                planets[i].draw();
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.show();
            StdDraw.pause(10);
            StdDraw.clear();
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}