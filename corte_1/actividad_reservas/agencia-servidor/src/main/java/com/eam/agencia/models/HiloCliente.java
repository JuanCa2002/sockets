package com.eam.agencia.models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class HiloCliente  implements Runnable{
    private final Socket socket;
    private final AgenciaServidor agencia;
    public HiloCliente(Socket socket, AgenciaServidor agencia){
        this.socket = socket;
        this.agencia = agencia;
    }
    @Override
    public void run() {
        try {
            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream( socket.getOutputStream() );
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            //Se lee el mensaje enviado por el cliente
            Mensaje mensaje = (Mensaje) in.readObject();
            //Se captura el tipo de mensaje
            String tipo = mensaje.getTipo();
            //Se captura el contenido del mensaje
            Object contenido = mensaje.getContenido();
            //Según el tipo de mensaje se invoca el método correspondiente
            switch (tipo) {
                case "agregarCliente":
                    agregarCliente((Cliente) contenido, out, in);
                    break;
                case "listarClientes":
                    listarClientes(out);
                    break;

            }
            //Se cierra la conexión del socket para liberar los recursos asociados
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            //log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void agregarCliente(Cliente cliente, ObjectOutputStream out, ObjectInputStream in) throws IOException {
        try {
            List<Object> respuestasPositivas = new ArrayList<>();
            respuestasPositivas.add("Cliente agregado correctamente");
            respuestasPositivas.add(generarPaquetesTuristicos());
            agencia.agregarCliente(cliente);
            List<Cliente> clientes = agencia.listarClientes();
            System.out.println("Los clientes registrados hasta el momento son: "+"\n"+
                    clientes);
            out.writeObject(new Mensaje("Respuesta positiva", respuestasPositivas));
            Mensaje mensaje = (Mensaje) in.readObject();
            registrarReserva((Reserva) mensaje.getContenido(), out);
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }
    public void listarClientes(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.listarClientes());
    }

    public List<PaqueteTuristico> generarPaquetesTuristicos() {
        return agencia.listarPaquetesTuristicos();
    }

    public void registrarReserva(Reserva reserva, ObjectOutputStream out) throws IOException {
        reserva.setId(agencia.listarReservas().size()+1);
        agencia.registrarReserva(reserva);
        System.out.println("Se ha realizado una reserva");
        Optional<PaqueteTuristico> paqueteTuristico = agencia.obtenerPaqueteTuristicoPorId(reserva.getPaqueteTuristicoId());
        Optional<Cliente> cliente = agencia.obtenerClientePorId(reserva.getIdentificacionCliente());
        if(paqueteTuristico.isPresent() && cliente.isPresent()){
            Cliente clienteEncontrado = cliente.get();
            PaqueteTuristico paqueteTuristicoEncontrado = paqueteTuristico.get();
            List<Object> objects = new ArrayList<>();
            objects.add(reserva);
            objects.add(paqueteTuristicoEncontrado);
            objects.add(clienteEncontrado);
            out.writeObject(new Mensaje("Reserva creada con exito", objects));
        }
    }

}

