public class Plato {
    // Atributos
    private String codigo;
    private String nombre;
    private double precio;

    // Constructor
    public Plato(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public double getPrecio() { return this.precio; }
    public String getCodigo() { return this.codigo; }

}
