package com.eam.agencia.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AgenciaServidor {
    private ArrayList<Cliente> clientes;
    private ArrayList<Reserva> reservas;
    private ArrayList<PaqueteTuristico> paquetesTuristicos;
    public AgenciaServidor() {
        this.clientes = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.paquetesTuristicos = new ArrayList<>();
        inicializarPaquetesTuristicos();
    }

    public synchronized void  agregarCliente(Cliente c) throws Exception{
        c.setId(listarClientes().size()+1);
        clientes.add(c);
    }
    public ArrayList<Cliente> listarClientes() {
        return clientes;
    }

    public synchronized void registrarReserva(Reserva reserva){
        reservas.add(reserva);
    }

    private void inicializarPaquetesTuristicos(){
        PaqueteTuristico paqueteTuristicoUno = new PaqueteTuristico(1, "Las vegas",
                LocalDate.of(2024,8,22),3,
                8000000L, List.of("Las vegas"));

        PaqueteTuristico paqueteTuristicoDos = new PaqueteTuristico(2, "Europa Tour",
                LocalDate.of(2024,4,8),15,
                9000000L, List.of("España", "Alemania", "Italia", "Francia"));

        PaqueteTuristico paqueteTuristicoTres = new PaqueteTuristico(3, "Cafe Tour",
                LocalDate.of(2024,12,15),4,
                5000000L, List.of( "Brasil", "Colombia"));

        paquetesTuristicos.add(paqueteTuristicoUno);
        paquetesTuristicos.add(paqueteTuristicoDos);
        paquetesTuristicos.add(paqueteTuristicoTres);
    }

    public List<Reserva> listarReservas(){return reservas;}

    public List<PaqueteTuristico> listarPaquetesTuristicos(){return paquetesTuristicos;}

    public Optional<PaqueteTuristico> obtenerPaqueteTuristicoPorId(Integer id){
        return paquetesTuristicos.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst();
    }
    public Optional<Cliente> obtenerClientePorId(String id){
        return clientes.stream().filter(c -> Objects.equals(c.getIdentificacion(), id)).findFirst();
    }


}
