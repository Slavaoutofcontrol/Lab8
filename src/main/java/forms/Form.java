package forms;

public abstract class Form<T> {
    /**
     * Абстрактный метод для построения формы
     * @return parameter of type <T>
     */
    public abstract T build();
}
