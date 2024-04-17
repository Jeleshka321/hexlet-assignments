package exercise;

// BEGIN
public class App {
    public static void printSquare(Circle circle) {
        try {
            double square = circle.getSquare();
            System.out.print(Math.round(square));
        } catch (NegativeRadiusException e) {
            System.out.print("Не удалось посчитать площадь");
        } finally {
            System.out.println("\nВычисление окончено");
        }
    }
}
// END
