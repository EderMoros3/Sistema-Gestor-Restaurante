import java.util.LinkedList;
import java.io.*;

public class Persistencia {
    private static final String PATH = "Sistema-Gestor-Restaurante/Sistema-Gestor-Restaurante/Sistema-Gestor-Restaurante/src/datos.dat"; 

    public static void guardarDatos(LinkedList<Object>[] datos) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PATH))) {
            out.writeObject(datos[0]); // mesas
            out.writeObject(datos[1]); // cartaPlatos
            out.writeObject(datos[2]); // pedidos
            System.out.println("Datos guardados correctamente");
        } catch (Exception e) {
            System.err.println("Error al guardar los datos " + e.getMessage());
        }
    }

    public static LinkedList<?>[] cargarDatos() {
        LinkedList<Mesa> listaMesa = null;
        LinkedList<Plato> listaPlatos = null;
        LinkedList<Pedido> listaPedidos = null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(PATH))) {
            listaMesa = ((LinkedList<Mesa>) in.readObject());
            listaPlatos = ((LinkedList<Plato>) in.readObject());
            listaPedidos = ((LinkedList<Pedido>) in.readObject());
            System.out.println("Datos cargados correctamente");
        } catch (Exception e) {
            System.err.println("Error al cargar los datos " + e.getMessage());
        }
        
        LinkedList<?>[] result = new LinkedList<?>[3];
        result[0] = listaMesa;
        result[1] = listaPlatos;
        result[2] = listaPedidos;
        return result;
        
    }
}
