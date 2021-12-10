public class Point3D {

	private double x,y,z;

	public Point3D(double _x,double _y,double _z) {
		x = _x;
		y = _y;
		z = _z;
	}

	public void set(Point3D oth) {
		setX(oth.getX());
		setY(oth.getY());
		setZ(oth.getZ());
	}

	public void setX(double _x) {
		x = _x;
	}

	public void setY(double _y) {
		y = _y;
	}

	public void setZ(double _z) {
		z = _z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public double getD(int d) {
		if (d == 0)
			return x;
		if (d == 1)
			return y;
		return z;
	}

	public boolean equals(Object obj) {

		if (obj == null || this.getClass() != obj.getClass())
			return false;

		Point3D _obj = (Point3D) obj; 

		return getX() == _obj.getX() && getY() == _obj.getY() && getZ() == _obj.getZ();

	}

	public String toString() {
		return "(" + getX() + "," + getY() + "," + getZ() + ")";
	}

}