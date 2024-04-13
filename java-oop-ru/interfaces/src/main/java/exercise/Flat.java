package exercise;

// BEGIN
class Flat implements Home {
    private final double area;
    private final double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    public double getArea() {
        return area + balconyArea;
    }

    public int compareTo(Home another) {
        double anotherArea = another.getArea();
        if (this.area > anotherArea) {
            return 1;
        } else if (this.area < anotherArea) {
            return -1;
        } else {
            return 0;
        }
    }



@Override
    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + floor + " этаже";
    }
}
