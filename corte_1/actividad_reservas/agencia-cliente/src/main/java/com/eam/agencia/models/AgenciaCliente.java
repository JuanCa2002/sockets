package com.eam.agencia.models;

import com.eam.agencia.exceptions.NotBlankFieldException;
import com.eam.agencia.exceptions.NotReservationWithZeroPeopleException;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgenciaCliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 1234;

    public static void main(String[] args) {
        generarMenuPrincipal();
    }

    public static void generarMenuPrincipal(){
        String menuPrincipal = """
                                   Hola Bienvenido!
                                   1. Registrarse
                                   2. Salir
                               """;
        try{
            int opcion = Integer.parseInt(JOptionPane.showInputDialog(menuPrincipal));
            switch (opcion){
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Hasta Pronto");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "No se encontro la opción desea, por favor rectifica");
                    generarMenuPrincipal();
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Debes ingresar un numero, correspondiente a una acción :)");
            generarMenuPrincipal();
        }

    }

    private static boolean verificarCampo(String campo){
        return campo == null || campo.equals("");
    }

    public static void registrarCliente(){
            //Se intenta abrir una conexión a un servidor remoto usando un objeto Socket
            try{
                String nombre = JOptionPane.showInputDialog("Digite el nombre: ");
                String identificacion = JOptionPane.showInputDialog("Digite la cedula: ");
                String email = JOptionPane.showInputDialog("Digite el correo: ");
                String direccion = JOptionPane.showInputDialog("Digite el direccion: ");
                String telefono = JOptionPane.showInputDialog("Digite el telefono: ");
                if(verificarCampo(nombre) || verificarCampo(identificacion) || verificarCampo(email) || verificarCampo(direccion) || verificarCampo(telefono)){
                    throw new NotBlankFieldException();
                }
                try (Socket socket = new Socket(HOST, PUERTO)){
                //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                    //Se crea un cliente con los datos obtenidos desde la ventana
                    Cliente cliente = new Cliente(0,nombre, identificacion, email, direccion, telefono);

                    //Se envía un mensaje al servidor con los datos de la petición
                    out.writeObject( new Mensaje("agregarCliente", cliente));
                    //Obtenemos la respuesta del servidor
                    Object respuesta = in.readObject();
                    Mensaje mensaje = (Mensaje) respuesta;
                    if(mensaje.getTipo().equals("Respuesta positiva")){
                        List<Object> objects =(List<Object>) mensaje.getContenido();
                        System.out.println(objects.get(0));
                        mostrarMenuReservas((List<PaqueteTuristico>) objects.get(1), cliente, out, in);
                    }
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
            }catch (NotBlankFieldException e){
               JOptionPane.showMessageDialog(null, e.getMessage());
               registrarCliente();
            }
        }


    public static void mostrarMenuReservas(List<PaqueteTuristico> paquetesTuristicos, Cliente cliente, ObjectOutputStream out, ObjectInputStream in ) throws IOException, ClassNotFoundException {
        StringBuilder menuPaquetes = new StringBuilder();
        menuPaquetes.append(" Los paquetes turisticos disponibles en este momento son:"+ "\n");
        paquetesTuristicos.forEach(paqueteTuristico -> {
            menuPaquetes.append(paqueteTuristico.getId()).append(". ").append(paqueteTuristico.getNombre()).append("\n");
        });
        menuPaquetes.append("Porfavor seleccione el paquete que más le llame la atención.");
        try{
            int opcionPaquete = Integer.parseInt(JOptionPane.showInputDialog(menuPaquetes));
            Optional<PaqueteTuristico> paqueteTuristicoEncontrado = paquetesTuristicos.stream().filter(p -> p.getId() == opcionPaquete).findFirst();

            PaqueteTuristico paqueteTuristicoSeleccionado = paqueteTuristicoEncontrado.orElse(null);

            if (paqueteTuristicoSeleccionado != null) {
                StringBuilder detallePaquete = new StringBuilder();
                detallePaquete.append("El paquete seleccionado fue:"+"\n");
                detallePaquete.append(paqueteTuristicoSeleccionado);
                detallePaquete.append("""
                                     ¿Desea realizar una reserva?
                                     
                                     --------------------------
                                         Si: S       No: N
                                     """);
                try{
                    char[] opcionRegistroReserva = JOptionPane.showInputDialog(detallePaquete).toUpperCase().toCharArray();
                    switch (opcionRegistroReserva[0]){
                        case 'S':
                            realizarReserva(cliente, paqueteTuristicoSeleccionado, out, in);
                            break;
                        case 'N':
                            mostrarMenuReservas(paquetesTuristicos, cliente, out, in);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción invalida, porfavor valida");
                            mostrarMenuReservas(paquetesTuristicos, cliente, out, in);
                            break;
                    }
                }catch (RuntimeException e){
                    JOptionPane.showMessageDialog(null, "Opción invalida, porfavor valida");
                    mostrarMenuReservas(paquetesTuristicos, cliente, out, in);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro ningun paquete turistico con el id especificado por favor vuelve a ingresar una opción");
                mostrarMenuReservas(paquetesTuristicos, cliente, out, in);
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Debes ingresar un numero, correspondiente a una acción :)");
            mostrarMenuReservas(paquetesTuristicos, cliente, out, in);
        }

    }

    public static void realizarReserva(Cliente cliente,PaqueteTuristico paqueteTuristico, ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException {
        try{
            int numeroPersonas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de personas que van: "));
            if(numeroPersonas == 0 || numeroPersonas<0){
                throw new NotReservationWithZeroPeopleException();
            }
            LocalDate fechaReserva = LocalDate.now();
            Reserva reserva = new Reserva(0, paqueteTuristico.getId(), cliente.getIdentificacion(), fechaReserva, numeroPersonas);
            out.writeObject(new Mensaje("reservar", reserva));
            Object respuesta = in.readObject();
            Mensaje mensaje = (Mensaje) respuesta;
            List<Object> objects = (List<Object>) mensaje.getContenido();
            Reserva reservaCreada = (Reserva) objects.get(0);
            PaqueteTuristico paqueteTuristicoSeleccionado = (PaqueteTuristico) objects.get(1);
            Cliente clienteCreado = (Cliente) objects.get(2);
            String confirmacion = mensaje.getTipo()+"\n";
            confirmacion += formatearRespuestaReserva(clienteCreado, paqueteTuristicoSeleccionado, reservaCreada);
            JOptionPane.showMessageDialog(null, confirmacion);
            in.close();
            out.close();
        }catch (NotReservationWithZeroPeopleException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            realizarReserva(cliente,paqueteTuristico, out, in);
        }
    }

    public static String formatearRespuestaReserva(Cliente cliente, PaqueteTuristico paqueteTuristico, Reserva reserva){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "   Información de la reserva: " + "\n" +
                "  id: " + reserva.getId() + "\n" +
                "  Paquete Turistico: " + paqueteTuristico.getNombre()+ "\n" +
                "  Cliente: " + cliente.getNombre() + " identificado con cedula:  "+cliente.getIdentificacion() + "\n" +
                "  Fecha Reserva: " + reserva.getFechaReserva().format(dateTimeFormatter) + "\n" +
                "  Numero Personas: " + reserva.getNumeroPersonas();

    }
    public ArrayList<Cliente> listarClientes(){
        //Se intenta abrir una conexión a un servidor remoto usando un objeto Socket
        try (Socket socket = new Socket(HOST, PUERTO)){
        //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( new Mensaje("agregarCliente"));
        //Obtenemos la respuesta del servidor
            Object respuesta = in.readObject();
        //Se hace un casting de la respuesta Object a un ArrayList<Cliente>
            ArrayList<Cliente> list = (ArrayList<Cliente>) respuesta;
        //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();
        //Se retorna a lista de clientes
            return list;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
