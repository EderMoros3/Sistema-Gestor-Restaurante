import java.util.LinkedList;
import java.util.Scanner;

public class Restaurante {
    // Atributos especiales
    Scanner sc = new Scanner(System.in);

    // Atributos
    LinkedList<Mesa> mesas = new LinkedList<>();
    LinkedList<Plato> cartaPlatos = new LinkedList<>();
    LinkedList<Pedido> pedidos = new LinkedList<>();

    public void registarMesa() {
        int numeroMesa;
        int capacidad;
        do {
            System.out.println("Ingresa un numero de mesa: ");
            numeroMesa = sc.nextInt();
        } while (numeroMesa <= 0);

        do {
            System.out.println("Ingresa la capacidad de la mesa: ");
            capacidad = sc.nextInt();
        } while (capacidad <= 0);

        Mesa mesa = new Mesa(numeroMesa, capacidad);
        mesas.add(mesa);
        System.out.println("Mesa creada correctamente");
    }

    private Plato crearPlato() {
        String codigoPlato; // P-01
        String nombrePlato;
        double precioPlato;

        System.out.println("Ingresa un codigo de plato: ");
        codigoPlato = sc.nextLine();
        sc.next();

        System.out.println("Ingresa el nombre del plato: ");
        nombrePlato = sc.nextLine();
        sc.next();

        do {
            System.out.println("Ingresa el precio del plato: ");
            precioPlato = sc.nextDouble();
        } while (precioPlato <= 0);

        Plato plato = new Plato(codigoPlato, nombrePlato, precioPlato);
        return plato;
    }
    
    public void registrarPlatoCarta() {
        Plato plato = this.crearPlato();
        this.cartaPlatos.add(plato);
        System.out.println("Plato creado correctamente");
    }

    public void registrarPlato() {
        String codigoPlato; // P-01
        String nombrePlato;
        double precioPlato;

        System.out.println("Ingresa un codigo de plato: ");
        codigoPlato = sc.nextLine();
        sc.next();

        System.out.println("Ingresa el nombre del plato: ");
        nombrePlato = sc.nextLine();
        sc.next();

        do {
            System.out.println("Ingresa el precio del plato: ");
            precioPlato = sc.nextDouble();
        } while (precioPlato <= 0);

        Plato plato = new Plato(codigoPlato, nombrePlato, precioPlato);
        cartaPlatos.add(plato);
        System.out.println("Plato creada correctamente");
    }
    
    public void registrarPedido() {
        Mesa mesaEncontrada = null;
        int numeroMesa;

        do {
            System.out.println("Introduce el numero de una mesa existente: ");
            numeroMesa = sc.nextInt();
            for(Mesa mesa : this.mesas) {
                if(numeroMesa == mesa.getNumero()) {
                    mesaEncontrada = mesa;
                    break;
                }
            }
            if (mesaEncontrada == null) {
                System.out.println("No se ha encontrado esa mesa");
                
            } 
        } while (mesaEncontrada == null);

        LinkedList<Plato> listaPlatosPedidos = new LinkedList<>();

        boolean terminar = false;
        String codigo;
        while(!terminar) {
            
            System.out.println("Introduce el codigo de los platos: ");
            System.out.println("Si se introduce un 0, parara de preguntar platos");
            codigo = sc.next();

            if (codigo.equals("0")) {
                terminar = true;
            } else {
                for(Plato plato : this.cartaPlatos) {
                    if (codigo.equals(plato.getCodigo())) {
                        listaPlatosPedidos.add(plato);
                    }
                }
            }
        }
        Pedido pedido = new Pedido(mesaEncontrada, listaPlatosPedidos);
        pedidos.add(pedido);
        System.out.println("Pedido creado correctamente");
    }

    private Pedido getPedidoNumeroMesa() {
        if (!this.pedidos.isEmpty()) {
            do {
                System.out.println("Introduce un numero de mesa asociado al pedido: ");
                int numero = sc.nextInt();
                for (Pedido pedido : this.pedidos) {
                    if (numero == pedido.getNumeroMesa()) {
                        return pedido;
                    }
                }
                System.out.println("Introduce un numero valido");
            } while (true);
        }
        return null;
    }
    
    private Plato platoByCodigo() {
        String codigo;
        if (!this.cartaPlatos.isEmpty()) {
            do {
                System.out.println("Introduce un codigo de plato de la carta: ");
                codigo = sc.nextLine();
                sc.next();
                for (Plato plato : this.cartaPlatos) {
                    if (plato.getCodigo().equals(codigo)) {
                        return plato;
                    }
                }
                System.out.println("No se ha encontrado ningun plato con ese codigo");
            } while (true);
        }
        return null;
    }

    private Mesa mesaByNumber() {
        int numero;
        if (!this.mesas.isEmpty()) {
            do {
                System.out.println("Introduce un numero de la nueva mesa: ");
                numero = sc.nextInt();
                for (Mesa mesa : this.mesas) {
                    if (numero == mesa.getNumero()) {
                        return mesa;
                    }
                }
                System.out.println("No se ha encontrado ninguna mesa con ese numero");
            } while (true);
        }
        return null;
    }

    public void menuModificarPedido() {
        Pedido pedido = this.getPedidoNumeroMesa();
        int opcion;
        do{
            System.out.println("Que quieres hacer?");
            System.out.println("1. Cambiar estado (Completado/No completado)");
            System.out.println("2. Añadir plato");
            System.out.println("3. Borrar plato");
            System.out.println("4. Cambiar mesa");
            System.out.println("5. Aplicar descuento");
            System.out.println("6. Borrar Pedido");
            System.out.println("7. Salir");
            System.out.println("Introduce una opcion: ");
            opcion = sc.nextInt();

        } while (opcion != 7);

        switch (opcion) {
            case 1 -> pedido.cambiarCompletado();

            case 2 -> {
                System.out.println("1. Nuevo plato");
                System.out.println("2. Añadir plato desde la carta");
                System.out.println("Selecciona una opcion: ");
                int subOpcion = sc.nextInt();
                
                switch (subOpcion) {
                    case 1 -> pedido.addPlato(this.crearPlato());        
                    case 2 -> pedido.addPlato(this.platoByCodigo());
                }
            }

            case 3 -> {
                String codigo;
                boolean control;

                do {
                    System.out.println("Introduce un codigo de un plato: ");
                    codigo = sc.nextLine();
                    sc.next();
                    control = pedido.removePlato(codigo);
                    if (control) {
                        System.out.println("Plato borrado correctamente");
                    } else {
                        System.out.println("No se ha encontrado ningun plato con ese codigo");
                    }
                } while (!control);
            }

            case 4 -> pedido.setMesa(this.mesaByNumber());
            case 5 -> pedido.aplicarDescuento(10);
            case 6 -> {
                this.pedidos.remove(pedido);
                System.out.println("Pedido borrado correctamente");
            }
        }
    }

    public void menuModificarOBorrarPlato() {
        Plato plato = this.platoByCodigo();
        int opcion;
        do {
            System.out.println("Que quieres hacer?:");
            System.out.println("1. Modificar Plato.");
            System.out.println("2. Borrar Plato.");
            System.out.println("3. Salir");
            System.out.println("Introduce una opción: ");
            opcion = sc.nextInt();
        } while (opcion != 3);

        switch (opcion) {
            case 1 -> {
                System.out.println("1. Modificar Nombre");
                System.out.println("2. Modificar Precio");
                System.out.println("Introduce una opción: ");
                int subopcion = sc.nextInt();

                switch (subopcion) {
                    case 1 -> {
                        System.out.println("Introduce un nuevo nombre:");
                        String nombre = sc.nextLine();
                        sc.next();
                        plato.setNombre(nombre);
                    }
                    case 2 -> {
                        System.out.println("Introduce un nuevo precio:");
                        Double precio = sc.nextDouble();
                        plato.setPrecio(precio);
                    }
                }
            }
            case 2 -> {
                this.cartaPlatos.remove(plato);
                System.out.println("Plato eliminado con exito");
            }
        }
    }

    public LinkedList<Object>[] getDatos() {
    
        LinkedList[] datos = new LinkedList[3];
        datos[0] = this.mesas;
        datos[1] = this.cartaPlatos;
        datos[2] = this.pedidos;

        return datos;
    }

    public void setDatos(LinkedList<?>[] datos) {
        this.mesas = (LinkedList<Mesa>) datos[0];
        this.cartaPlatos = (LinkedList<Plato>) datos[1];
        this.pedidos = (LinkedList<Pedido>) datos[2];
    }
}


