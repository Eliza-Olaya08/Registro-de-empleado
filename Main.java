import java.util.Scanner;

public class Main {

    public static String[] nombre = new String[100];
    public static float[] sueldo = new float[100];
    public static String[] cargo = new String[100];
    public static boolean[] ocupado = new boolean[100];

    public static Scanner entrada = new Scanner(System.in);

    public static int cantidadEmpleados = 0;

public static void main(String[] args) {

    System.out.println("==========REGISTRO DE EMPLEADOS==========");

    // Registrar empleados inicialmente
    for (int empleado = 0; empleado < 100; empleado++) {

        leerDatos(empleado);
        ocupado[empleado] = true;
        cantidadEmpleados++;

        System.out.print("¿Desea añadir otro empleado? (s/n): ");
        String respuesta = entrada.nextLine();

        if (!respuesta.equalsIgnoreCase("s")) {
            break;
        }

        if (cantidadEmpleados == 100) {
            System.out.println("Ya tiene registrados los 100 empleados.");
            break;
        }
    }

    System.out.println("\nTiene " + cantidadEmpleados + " empleados registrados.");
    System.out.println("Tiene espacio para " + (100 - cantidadEmpleados)
            + " empleados más.");

    boolean continuar = true;

    while (continuar) {

        System.out.println("========== REGISTRO DE EMPLEADOS ==========");
        System.out.println("1. Ver empleados");
        System.out.println("2. Añadir empleado");
        System.out.println("3. Editar empleado");
        System.out.println("4. Eliminar empleado");
        System.out.println("5. Salir");
        System.out.println("===========================================");
        System.out.print("Seleccione una opcion: ");

        int opcion = entrada.nextInt();
        entrada.nextLine();

        switch (opcion) {

            case 1:
                verEmpleados();
                break;

            case 2:
                agregarEmpleado();
                break;

            case 3:
                editarEmpleado();
                break;

            case 4:
                eliminarEmpleado();
                break;

            // cerrar programa
            case 5:
                continuar = false;
                System.out.println("Programa finalizado.");
                break;

            default:
                System.out.println("Opción invalida.");
        }

        if (continuar) {

            System.out.print("¿Desea realizar alguna otra accion del menú? (s/n): ");
            String otraAccion = entrada.nextLine();

            if (!otraAccion.equalsIgnoreCase("s")) {
                continuar = false;
                System.out.println("Programa finalizado.");
            }
        }
    }

    entrada.close();
}

// Crear empleado - Leer datos del empleado
public static void leerDatos(int nroEmpleado) {

    System.out.println("========== EMPLEADO #" + nroEmpleado + " ==========");

    System.out.print("Escriba el nombre: ");
    nombre[nroEmpleado] = entrada.nextLine();

    System.out.print("Escriba el cargo: ");
    cargo[nroEmpleado] = entrada.nextLine();

    System.out.print("Escriba el sueldo: ");
    sueldo[nroEmpleado] = entrada.nextFloat();
    entrada.nextLine();
    System.out.println("================================================");
}

// Crear empleado - Añadir empleado
public static void agregarEmpleado() {

    if (cantidadEmpleados >= 100) {
        System.out.println("\nNo hay espacio para más empleados.");
        return;
    }

    boolean agregarOtro = true;

    while (agregarOtro && cantidadEmpleados < 100) {

        // Buscar espacio disponible
        int posicion = -1;

        for (int empleado = 0; empleado < 100; empleado++) {

            if (!ocupado[empleado]) {
                posicion = empleado;
                break;
            }
        }

        if (posicion != -1) {

            leerDatos(posicion);
            ocupado[posicion] = true;
            cantidadEmpleados++;

            System.out.println("Empleado añadido correctamente.");
            System.out.println("Empleados registrados: "
                    + cantidadEmpleados);
            System.out.println("Espacios disponibles: "
                    + (100 - cantidadEmpleados));

            if (cantidadEmpleados < 100) {

                System.out.print(
                    "¿Desea añadir otro empleado? (s/n): "
                );

                String respuesta = entrada.nextLine();

                if (!respuesta.equalsIgnoreCase("s")) {
                    agregarOtro = false;
                }

            } else {

                System.out.println(
                    "Ya alcanzó el límite de 100 empleados."
                );

                agregarOtro = false;
            }
        }
    }
}

// Ver empleados - Mostrar todos los empleados
public static void verEmpleados() {

    System.out.println("========== LISTA DE EMPLEADOS ==========");

    for (int empleado = 0; empleado < 100; empleado++) {

        if (ocupado[empleado]) {

            System.out.println("Empleado #" + empleado);
            verDatos(empleado);
            System.out.println("----------------------------------------");
        }
    }

    System.out.println("Total de empleados: " + cantidadEmpleados);
    System.out.println("Espacios disponibles: "
            + (100 - cantidadEmpleados));
}

// Ver datos - Mostrar datos de un empleado
public static void verDatos(int nroEmpleado) {

    System.out.println("Nombre: " + nombre[nroEmpleado]);
    System.out.println("Cargo: " + cargo[nroEmpleado]);
    System.out.println("Sueldo: " + sueldo[nroEmpleado]);
}

// Editar empleado
public static void editarEmpleado() {

    boolean editarOtro = true;

    while (editarOtro) {

        System.out.print(
            "Escriba el numero del empleado que desea editar (0-99): "
        );

        int empleado = entrada.nextInt();
        entrada.nextLine();

        if (empleado >= 0 && empleado < 100 && ocupado[empleado]) {

            System.out.println("Datos actuales:");
            verDatos(empleado);

            System.out.println("========== EDITAR EMPLEADO ==========");

            System.out.print("Nuevo nombre: ");
            nombre[empleado] = entrada.nextLine();

            System.out.print("Nuevo cargo: ");
            cargo[empleado] = entrada.nextLine();

            System.out.print("Nuevo sueldo: ");
            sueldo[empleado] = entrada.nextFloat();
            entrada.nextLine();

            System.out.println("Empleado actualizado correctamente.");

            System.out.print("¿Desea editar otro usuario? (s/n): ");
            String respuesta = entrada.nextLine();

            if (!respuesta.equalsIgnoreCase("s")) {
                editarOtro = false;
            }

        } else {

            System.out.println("El empleado no existe.");

            System.out.print(
                "¿Desea intentar con otro empleado? (s/n): "
            );

            String respuesta = entrada.nextLine();

            if (!respuesta.equalsIgnoreCase("s")) {
                editarOtro = false;
            }
        }
    }
}

// Eliminar empleado
public static void eliminarEmpleado() {

    boolean eliminarOtro = true;

    while (eliminarOtro) {

        System.out.print(
            "Escriba el numero del empleado que desea eliminar (0-99): "
        );

        int empleado = entrada.nextInt();
        entrada.nextLine();

        if (empleado >= 0 && empleado < 100 && ocupado[empleado]) {

            System.out.println("Empleado seleccionado:");
            verDatos(empleado);

            System.out.print(
                "¿Esta seguro de eliminar este empleado? (s/n): "
            );

            String confirmacion = entrada.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {

                nombre[empleado] = "";
                cargo[empleado] = "";
                sueldo[empleado] = 0;
                ocupado[empleado] = false;

                cantidadEmpleados--;

                System.out.println("Empleado eliminado correctamente.");
                System.out.println("Empleados registrados: "
                        + cantidadEmpleados);
                System.out.println("Espacios disponibles: "
                        + (100 - cantidadEmpleados));
            }

            System.out.print(
                "¿Desea eliminar otro usuario? (s/n): "
            );

            String respuesta = entrada.nextLine();

            if (!respuesta.equalsIgnoreCase("s")) {
                eliminarOtro = false;
            }

        } else {

            System.out.println("El empleado no existe.");

            System.out.print(
                "¿Desea intentar con otro empleado? (s/n): "
            );

            String respuesta = entrada.nextLine();

            if (!respuesta.equalsIgnoreCase("s")) {
                eliminarOtro = false;
            }
        }
    }
}

}