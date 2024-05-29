import java.util.ArrayList;
import java.util.List;

public class Lista {
   private  List<Paqueteria>serviEntrega;

    public List<Paqueteria> getServiEntrega() {
        return serviEntrega;
    }

    public void setServiEntrega(List<Paqueteria> serviEntrega) {
        this.serviEntrega = serviEntrega;
    }

    public Lista() {
        serviEntrega =new ArrayList<Paqueteria>();
    }
    public void adicionarelemnto(Paqueteria p) throws Exception{
        if (serviEntrega.isEmpty()){
            serviEntrega.add(p);
        }else{
            for(Paqueteria pa:serviEntrega ){
                if(pa.getTracking()==p.getTracking()){
                    throw new Exception("Pauete ya existe");
                }

            }
            serviEntrega.add(p);
        }
    }
    public String listarPaquetes(){
        String mensaje = "";
        for(Paqueteria p:serviEntrega){
            mensaje+=p+"\n";

        }
        return mensaje;
    }
    public int sumarTotalPaquetes(){
        return totalPaquete(0);
    }
    private  int totalPaquete(int indice){
        if(serviEntrega.size()==indice){
            return 0;
        }else {
            return 1+totalPaquete(indice+1);
        }
    }

    public double sumarToatlPeso(){
        return totalPeso(0);
    }

    private  double totalPeso(int indice){

        if (serviEntrega.size()==indice) {
            return 0;
        }  else{
            return serviEntrega.get(indice).getPeso()+totalPeso(indice+1);

        }

    }

    public double sumarTotalPesoCiudad(String ciudad){

        return totalPesoCiudad(0,ciudad);
    }

    private double totalPesoCiudad(int indice,String ciudad){

               if(serviEntrega.size()==indice ){
                   return 0;
               }else{
                   if(serviEntrega.get(indice).getCiudadRecepcion().equals(ciudad)) {
                       return serviEntrega.get(indice).getPeso() + totalPesoCiudad(indice + 1,ciudad);
                   }else{
                       return totalPesoCiudad(indice+1,ciudad);
                   }
               }


    }

   /*public  void modificarDatos(int tracking, Paqueteria nuevo) throws Exception{
        for (Paqueteria e:serviEntrega){
            if(e.getTracking()==tracking){
                throw  new Exception("El número de seguimiento ys está en uso");
            }else{

                e.setTracking(nuevo.getTracking());
                e.setPeso(nuevo.getPeso());
                e.setCiudadEntrega(nuevo.getCiudadEntrega());
                e.setCiudadRecepcion(nuevo.getCiudadRecepcion());
                e.setCedulaReceptor(nuevo.getCedulaReceptor());
                return;
            }

        }

    }*/

    public void modificarDatos(int tracking, Paqueteria nuevo) throws Exception {


        for (int i = 0; i < serviEntrega.size(); i++) {
            Paqueteria paquete = serviEntrega.get(i);
            if (paquete.getTracking() == tracking) {
                paquete.setPeso(nuevo.getPeso());
                paquete.setCiudadEntrega(nuevo.getCiudadEntrega());
                paquete.setCiudadRecepcion(nuevo.getCiudadRecepcion());
                paquete.setCedulaReceptor(nuevo.getCedulaReceptor());
                paquete.setEstado(nuevo.getEstado());
                return;
            }
        }
        throw new Exception("El paquete a modificar no se encontró en la lista.");
    }


    public int contarPaquetesPorEstado(String estado) {
        return ToatlPaquetesEstado(0, estado);
    }

    private int ToatlPaquetesEstado(int indice, String estado) {
        if (indice == serviEntrega.size()) {
            return 0;
        } else {
            if (serviEntrega.get(indice).getEstado().equals(estado)) {
                return 1 + ToatlPaquetesEstado(indice + 1, estado);
            } else {
                return ToatlPaquetesEstado(indice + 1, estado);
            }
        }
    }

    public  String  buscarPaquetesPorCedulaYEstado(String cedula, String estado){

        String mensaje = "Resultados de búsqueda:\n";

        for (Paqueteria paquete : serviEntrega) {
            if (paquete.getCedulaReceptor().equals(cedula) && paquete.getEstado().equals(estado)) {
                mensaje += paquete.toString() + "\n";

            }
        }
            return  mensaje;
    }












}
