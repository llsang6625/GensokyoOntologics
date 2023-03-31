package github.thelawf.gensokyoontology.common.libs.logoslib.math;


import github.thelawf.gensokyoontology.common.libs.logoslib.annotations.Degree;
import github.thelawf.gensokyoontology.common.libs.logoslib.annotations.GlobalCoordinate;
import github.thelawf.gensokyoontology.common.libs.logoslib.annotations.Radian;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

import java.awt.*;
import java.math.MathContext;
import java.util.ArrayList;

import static net.minecraft.util.math.MathHelper.lerp;

public class MathUtil {

    private MathUtil() {}

    /**
     * 这里的Point类是java.awt里面的类
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 上述两点间的距离
     */
    public static double distanceOf2D(Point p1, Point p2) {
        return Math.pow(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2), 0.5);
    }

    /**
     * 传入四个双精度浮点数
     * @param x1 第一个点的x坐标
     * @param y1 第一个点的y坐标
     * @param x2 第二个点的x坐标
     * @param y2 第二个点的y坐标
     * @return 上述两点间的距离
     */
    public static double distanceOf2D(double x1, double y1, double x2, double y2){
        return Math.sqrt(square(x1 - x2) + square(y1 - y2));
    }

    public static double distanceOf3D(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.pow(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 + z2, 2), 0.5);
    }

    public static RectangularCoordinate intersection2D(LineSegment l1, LineSegment l2) {
        return intersection2D(new Point(l1.dotA), new Point(l1.dotB),
                new Point(l2.dotA), new Point(l2.dotB));
    }

    /**
     * 获取平面上两条直线的交点坐标
     * @param p1 直线1的起点
     * @param p2 直线1的终点
     * @param p3 直线2的起点
     * @param p4 直线2的终点
     * @return 交点的平面直角坐标
     */
    public static RectangularCoordinate intersection2D(Point p1, Point p2, Point p3, Point p4) {

        double a1 = p1.getY() - p2.getY();
        double b1 = p2.getX() - p1.getX();
        double c1 = a1 * p1.getX() + b1 * p1.getY();

        double a2 = p3.getY() - p4.getY();
        double b2 = p4.getX() - p3.getX();
        double c2 = a2 * p3.getX() + b2 * p3.getY();

        double detK = a1 * b2 - a2 * b1;

        if(Math.abs(detK)<0.00001){
            return null;
        }

        double a = b2 / detK;
        double b = -1 * b1 / detK;
        double c = -1 * a2 / detK;
        double d = a1 / detK;

        double x = a * c1 + b * c2;
        double y = c * c1 + d * c2;

        return new RectangularCoordinate(x, y,0);

    }

    public static RectangularCoordinate intersection3D(LineSegment3D l1, LineSegment3D l2) {
        try {
            return intersection3D(l1.x1, l1.y1, l1.z1, l1.x2, l1.y2, l1.z2,
                    l2.x1, l2.y1, l2.z1, l2.x2, l2.y2, l2.z2);
        } catch (PointNonExistException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 设两条线段的端点分别为 (x1,y1,z1), (x2,y2,z2) 和 （x3,y3,z3), (x4,y4,z4)，那么第一条线段方程为U(t1)
     * <p>
     * x=x1+(x2-x1)t1
     * <p>
     * y=y1+(y2-y1)t1
     * <p>
     * z=z1+(z2-z1)t1
     * <p>
     * 0<=t<=1
     * <p>
     * 同样，第二条线段方程为V(t2)
     * <p>
     * x=x3+(x4-x3)t2
     * <p>
     * y=y3+(y4-y3)t2
     * <p>
     * z=z3+(z4-z3)t2
     * <p>
     * 0<=t<=1
     * <p>
     * 我们的问题就成为是否存在t1,t2,使得U(t1)=V(t2)，也就是求t1,t2,使得
     * <p>
     * x1+(x2-x1)t1=x3+(x4-x3)t2
     * <p>
     * y1+(y2-y1)t1=y3+(y4-y3)t2
     * <p>
     * z1+(z2-z1)t1=z3+(z4-z3)t2
     * <p>
     * 可以通过前面两条方程求出t1,t2,然后带入第三条方程进行检验解是否符合。此外还要求0<=t1,t2<=1，否则还是不相交
     * <p>
     * @param x1 直线1的起点x坐标
     * @param y1 直线1的起点y坐标
     * @param z1 直线1的起点z坐标
     * @param x2 直线1的终点x坐标
     * @param y2 直线1的终点y坐标
     * @param z2 直线1的终点z坐标
     * @param x3 直线2的起点x坐标
     * @param y3 直线2的起点y坐标
     * @param z3 直线2的起点z坐标
     * @param x4 直线2的终点x坐标
     * @param y4 直线2的终点y坐标
     * @param z4 直线2的终点z坐标
     * @throws PointNonExistException 直线不存在交点
     * @return 直线1和直线2的交点在三维空间中的坐标
     */
    public static RectangularCoordinate intersection3D(double x1, double y1, double z1,
                                                       double x2, double y2, double z2,
                                                       double x3, double y3, double z3,
                                                       double x4, double y4, double z4)
            throws PointNonExistException{

        double t1 = ((y1 -y3) * (x3 - x4) - (y3 - y4) * (x1 - x3)) /
                ((y3 - y1) * (x1 - x2) - (x3 -x4) * (y1 - y2));
        double t2 = ((x1 - x3) + (x1 - x2) * t1) / (x3 - x4);

        if (t1 <= 0 || t1 >= 1 || t2 <= 0 || t2 >= 1) {
            throw new PointNonExistException("两直线平行或线性相关，不存在交点");
        }
        else {
            double x = x1 + (x1 - x2) * t1;
            double y = y1 + (y1 - y2) * t1;
            double z = z1 + (z1 - z2) * t1;
            return new RectangularCoordinate(x,y,z);
        }

    }

    public static Vector3d getIntersectVec(Vector3d p1, Vector3d v1,
                                           Vector3d p2, Vector3d v2) {
        Vector3d intersection = Vector3d.ZERO;
        if (v1.dotProduct(v2) == 1)
        {
            // 两线平行
            return null;
        }

        Vector3d startPointSeg = p2.subtract(p1);
        Vector3d vecS1 = v1.crossProduct(v2);            // 有向面积1
        Vector3d vecS2 = startPointSeg.crossProduct(v2); // 有向面积2
        double num = startPointSeg.dotProduct(vecS1);

        // 判断两这直线是否共面
        if (num >= 1E-05f || num <= -1E-05f)
        {
            return null;
        }

        // 有向面积比值，利用点乘是因为结果可能是正数或者负数
        double num2 = vecS2.dotProduct(vecS1) / vecS1.lengthSquared();

        Vector3d vector3d = new Vector3d(v1.x * num2, v1.y * num2, v1.z* num2 );
        intersection = p1.add(vector3d);

        return intersection;
    }

    public static ArrayList<RectangularCoordinate> getCirclePoints2D(RectangularCoordinate center,
                                                           double radius, int count) {

        ArrayList<RectangularCoordinate> coordinates = new ArrayList<>();
        double radians = (Math.PI / 180) * Math.round(360d / count);
        for (int i = 0; i < count; i++) {
            double x = center.getX() + radius * Math.sin(radians * i);
            double y = center.getY() + radius * Math.cos(radians * i);
            RectangularCoordinate coordinate = new RectangularCoordinate(x,y,0);
            coordinates.add(coordinate);
        }
        return coordinates;
    }

    public static RectangularCoordinate getPointOnCircle(@GlobalCoordinate RectangularCoordinate center,
                                                         double radius, @Degree double angle) {
        return new RectangularCoordinate(
                center.getX() + radius * Math.cos(Math.toDegrees(angle)),
                center.getY() + radius * Math.sin(Math.toDegrees(angle)),
                0);
    }

    public static RectangularCoordinate getPointOnOvalByAngle(@GlobalCoordinate RectangularCoordinate center,
                                                              double lengthX, double lengthY,@Degree double angle) {
        return new RectangularCoordinate(
                center.getX() + lengthX * Math.cos(Math.toDegrees(angle)),
                center.getY() + lengthY * Math.sin(Math.toDegrees(angle)),
                0);
    }

    public static RectangularCoordinate getPointOnOval(@GlobalCoordinate RectangularCoordinate center,
                                                       double lengthX, double lengthY,@Radian double angle) {
        if (lengthX > lengthY) {
            double x = (lengthX * lengthY) / Math.sqrt(square(lengthX) + square(lengthY) + square(Math.tan(angle)));
            double y = (lengthX * lengthY * Math.tan(angle)) / Math.sqrt(square(lengthX) + square(lengthY) + square(Math.tan(angle)));
            return new RectangularCoordinate(center.getX() + x, center.getY() + y, 0);
        }
        else if (lengthX < lengthY) {
            double y = (lengthX * lengthY) / Math.sqrt(square(lengthX) + square(lengthY) + square(Math.tan(angle)));
            double x = (lengthX * lengthY * Math.tan(angle)) / Math.sqrt(square(lengthX) + square(lengthY) + square(Math.tan(angle)));
            return new RectangularCoordinate(center.getX() + x, center.getY() + y, 0);

        }
        else {
            return getPointOnCircle(center, lengthX, toDegree(angle));
        }
    }

    public static Vector3d bezier2(Vector3d start, Vector3d p, Vector3d end, float time) {
        return lerp(time, lerp(time, start, p), lerp(time, p, end));
    }

    /**
     * 用于小于等于90度的水平转弯
     * @param start 起始点
     * @param p 控制点
     * @param end 终点
     * @param time 步长
     * @return 在控制点的作用下，以起止点为基准的下一个点在曲线上的位置
     */
    public static Vector3d bezier2(Vector3d start,Vector3d end,  Vector3d p, double time) {
        if (time > 1 || time < 0)
            return null;
        Vector3d v1 = start.scale(pow2(1 - time));
        Vector3d v2 = p.scale(2 * time * (1 - time));
        Vector3d v3 = end.scale(pow2(time));

        return v1.add(v2.add(v3));
    }

    public static Vector3d bezier3(Vector3d p1, Vector3d p2, Vector3d q1, Vector3d q2, float time) {
        if (time > 1 || time < 0)
            return null;
        Vector3d v1 = lerp(time, p1, q1);
        Vector3d v2 = lerp(time, q1, q2);
        Vector3d v3 = lerp(time, q2, p2);
        Vector3d inner1 = lerp(time, v1, v2);
        Vector3d inner2 = lerp(time, v2, v3);
        return lerp(time, inner1, inner2);
    }

    public static Vector3d bezierDerivative(Vector3d p1, Vector3d p2, Vector3d q1, Vector3d q2, float t) {
        return p1.scale(-3 * t * t + 6 * t - 3)
                .add(q1.scale(9 * t * t - 12 * t + 3))
                .add(q2.scale(-9 * t * t + 6 * t))
                .add(p2.scale(3 * t * t));
    }

    public static Vector3d lerp(float p, Vector3d from, Vector3d to) {
        return from.add(to.subtract(from).scale(p));
    }

    /**
     *
     * @param start 本地坐标系的起点向量
     * @param end 本地坐标系的终点向量
     * @param startRotationRad 起点向量基于自身的本地坐标系的旋转角度（弧度值）
     * @param endRotationRad 终点向量基于自身的本地坐标系的旋转角度（弧度值）
     * @return 起点到终点的弧长
     */
    public static double getArcLengthFormTo(Vector3d start,
                                            Vector3d end,
                                            double startRotationRad,
                                            double endRotationRad) {
        double middle = Math.abs(start.x - end.x) / 2;
        double centerAngle = Math.PI / 4 - Math.abs(startRotationRad - endRotationRad);
        return Math.PI * 2 * middle * (centerAngle / (Math.PI / 2));
    }
    
    /**
     * 这里不会抛出一个{@link TriangleNotUniqueException} 异常
     * @param vertexAIn 三角形∠A所对应的点的坐标
     * @param vertexBIn 三角形∠B所对应的点的坐标
     * @param vertexCIn 三角形∠C所对应的点的坐标
     * @return 返回一个平面三角形对象
     */
    public static Triangle2D solveTriangle(Point vertexAIn, Point vertexBIn, Point vertexCIn) {
        return new Triangle2D(vertexAIn, vertexBIn, vertexCIn);
    }

    /**
     * 求三维向量模长的运算
     * @param x 三维向量的x坐标
     * @param y 三维向量的y坐标
     * @param z 三维向量的z坐标
     * @return 返回三维向量的模长，或者立方体体对角线的长度
     */
    public static double toModulus3D(double x, double y, double z) {
        return Math.pow(x * x + y * y + z * z, 0.5);
    }

    public static double toModulus2D(double x, double y) {
        return Math.pow(x * x + y * y, 0.5);
    }

    /**
     * 球坐标转直角坐标，注意计算机图形学中的空间坐标与数学的空间坐标不同，x, z轴为水平轴，而y轴为竖直轴，
     * @param sc 球坐标对象，是一个三维的向量
     * @return 返回空间直角坐标系对象
     */
    public static RectangularCoordinate toRectCoordinate(SphericalCoordinate sc) {
        return new RectangularCoordinate(sc.radius * Math.sin(sc.theta) * Math.cos(sc.phi),
                sc.radius * Math.cos(sc.theta),
                sc.radius * Math.sin(sc.theta) * Math.sin(sc.phi));
    }

    /**
     * 直角坐标转球坐标，注意计算机图形学中的空间坐标与数学的空间坐标不同，x, z轴为水平轴，而y轴为竖直轴，
     * @param rc 空间直角坐标系对象，同样是一个三维的向量
     * @return 返回球坐标系对象
     */
    public static SphericalCoordinate toSphereCoordinate(RectangularCoordinate rc) {
        double r = MathUtil.toModulus3D(rc.x, rc.y, rc.z);
        return new SphericalCoordinate(r, Math.acos(rc.y / r), Math.atan(rc.z / rc.x));
    }

    public static SphericalCoordinate toSphereCoordinate(double x, double y, double z) {
        return toSphereCoordinate(new RectangularCoordinate(x, y, z));
    }

    public static RectangularCoordinate toLocalCoordinate(RectangularCoordinate newOriginIn,
                                                          RectangularCoordinate globalIn) {
        return new RectangularCoordinate(globalIn.getX() - newOriginIn.getX(),
                globalIn.getY() - newOriginIn.getY(),
                globalIn.getZ() - newOriginIn.getZ());

    }

    /**
     *
     * @param x 空间向量的x坐标
     * @param y 空间向量的y坐标
     * @param z 空间向量的z坐标
     * @return 返回一个空间向量在x-z平面上的投影同x轴的夹角， 在x-y平面上的投影同y轴的夹角，以及在z-y平面上的投影同z轴的夹角
     */
    public static double[] toRotations(double x, double y, double z) {
        return new double[]{Math.atan(x / z), Math.atan(y / x), Math.atan(z / y)};
    }

    /**
     * 计算方法：设斜边为r，两条直角边为x和y，斜边与y轴夹角为d，那么——
     * <p>
     * 1. 求出 tan(d) 的值，为一个常量，用a表示，由已知条件可得：tan()函数表示的是对边比邻边，即x比上y，且两条直角边的平方和等于斜边的平方，所以——
     * <p>
     * 2. 设方程① -- x / y = a;
     * <p>
     * 3. 设方程② -- x^2 + y^2 = r^2;
     * <p>
     * 4. 联立方程①②可得：
     * <p>
     *     x = a * y;
     * <p>
     *     a^2 * y^2 + y^2 = r^2;
     * <p>
     *     y^2 * (a^2 + 1) = r^2;
     * <p>
     *   ∴ y = √(z^2 / a^2 + 1)
     * @param hypotenuse 斜边长，即空间向量在该平面上的投影线段
     * @param roll 斜边与y轴的夹角
     * @return 返回一个仅有可知的两边组成的平面向量坐标
     */
    public static RectangularCoordinate toRollCoordinate(double hypotenuse, double roll) {
        double x = Math.sqrt(Math.pow(hypotenuse, 2) / Math.pow(Math.tan(roll), 2) + 1);
        return new RectangularCoordinate(x, Math.tan(roll) * x, 0);
    }

    public static RectangularCoordinate toYawCoordinate(double hypotenuse, double yaw) {
        double z = Math.sqrt(Math.pow(hypotenuse, 2) /  Math.pow(Math.tan(yaw), 2) + 1);
        return new RectangularCoordinate(Math.tan(yaw) * z, 0, z);
    }

    public static RectangularCoordinate toPitchCoordinate(double hypotenuse, double pitch) {
        double y = Math.sqrt(Math.pow(hypotenuse, 2) /  Math.pow(Math.tan(pitch), 2) + 1);
        return new RectangularCoordinate(0, y, Math.tan(pitch) * y);
    }

    public static RectangularCoordinate vecToRectCoord(Vector3d vector3d) {
        return new RectangularCoordinate(vector3d.x, vector3d.y, vector3d.z);
    }

    public static Vector3d rectCoordToVec(RectangularCoordinate coordinate) {
        return new Vector3d(coordinate.x, coordinate.y, coordinate.z);
    }

    /**
     * 弧度值除以Math.PI的结果为【180度的几分之几】
     * @param radIn 弧度值
     * @return 角度值
     */
    public static double toDegree(double radIn) {
        return radIn / Math.PI * 180d;
    }

    /**
     * Math.PI 除以180度的结果为【每一角度等于多少弧度】
     * @param degIn 角度值
     * @return 弧度值
     */

    public static double toRadian(double degIn) {
        return degIn * Math.PI / 180d;
    }

    public static double pow2(double base) {
        return square(base);
    }

    public static double pow3(double base) {
        return cube(base);
    }

    public static double square(double base){
        return base * base;
    }

    public static double cube(double base) {
        return base * base * base;
    }
}
