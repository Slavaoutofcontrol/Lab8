package collectionClasses;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable, Comparable<Coordinates> {
    @Serial
    private static final long serialVersionUID = 6947875585164853381L;
    /**
     * Координата x, не может быть null
     */
    private Integer x;
    /**
     * Координата y, максимальное значение поля:214
     */
    private float y;

    /**
     * Конструктор для создания координат из переданных значений
     * @param x
     * @param y
     */
    public Coordinates(Integer x, float y) {
        this.y = y;
        this.x = x;
    }

    /**
     * Возвращает координату y
     * @return
     */
    public float getY() {
        return y;
    }

    /**
     * Возвращает координату x
     * @return
     */
    public Integer getX() {
        return x;
    }

    @Override
    public String toString() {
        return "x= " + x +
                ", y= " + y ;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public int compareTo(Coordinates o) {
        if (Objects.isNull(o)) return 1;
        return Double.compare((Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2)),
                (Math.pow(o.getX(), 2) + Math.pow(o.getY(), 2)));
    }
}
