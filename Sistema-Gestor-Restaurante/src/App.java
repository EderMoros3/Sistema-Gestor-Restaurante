import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Restaurante restaurante = new Restaurante();
        
        int opcion;
        
        do {
            System.out.println("\nMenu de Gestion de Restaurante");
            System.out.println("1. Registrar Mesa");
            System.out.println("2. Registrar Plato");
            System.out.println("3. Registrar Pedido");
            System.out.println("4. Modificar Pedido");
            System.out.println("5. Borrar Pedido");
            System.out.println("6. Modificar o Borrar Plato");
            System.out.println("7. Guardar Datos");
            System.out.println("8. Cargar Datos");
            System.out.println("9. Salir");
            System.out.println("Seleccione una opcion");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    restaurante.registarMesa();
                }
                case 2 -> {
                    restaurante.registrarPlato();
                }
                case 3 -> {
                    //registrarPedido();
                }
                case 4 -> {
                    //modificarPedido();
                }
                case 5 -> {
                    //borrarPedido();
                }
                case 6 -> {
                    //modificarOBorrarPlato();
                }
                case 7 -> {
                    //guardarDatos();
                }
                case 8 -> {
                    //cargarDatos();
                }
                case 9 -> {
                    System.out.println("Saliendo del sistema...");
                }
                default -> {
                    System.out.println("Opcion no valida");
                }
            }
        } while (opcion != 9);
    }
}
