public class Paqueteria {
    private  int tracking;
    private double peso;
    private String ciudadEntrega;
    private String ciudadRecepcion;
    private String cedulaReceptor;
    private String estado;

    public Paqueteria(int tracking, double peso, String ciudadEntrega, String ciudadRecepcion, String cedulaReceptor) {
        this.tracking = tracking;
        this.peso = peso;
        this.ciudadEntrega = ciudadEntrega;
        this.ciudadRecepcion = ciudadRecepcion;
        this.cedulaReceptor = cedulaReceptor;
        this.estado ="Receptado";
    }

    @Override
    public String toString() {
        return  "Numero de tracking=  " + tracking +
                " \nPeso=  " + peso +
                " \nCiudad de Entrega=  " + ciudadEntrega +
                " \nCiudad de Recepcion=  " + ciudadRecepcion +
                " \nCedula del Receptor=  " + cedulaReceptor +
                " \nEstado= " + estado ;
    }

    public int getTracking() {
        return tracking;
    }

    public void setTracking(int tracking) {
        this.tracking = tracking;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCiudadEntrega() {
        return ciudadEntrega;
    }

    public void setCiudadEntrega(String ciudadEntrega) {
        this.ciudadEntrega = ciudadEntrega;
    }

    public String getCiudadRecepcion() {
        return ciudadRecepcion;
    }

    public void setCiudadRecepcion(String ciudadRecepcion) {
        this.ciudadRecepcion = ciudadRecepcion;
    }

    public String getCedulaReceptor() {
        return cedulaReceptor;
    }

    public void setCedulaReceptor(String cedulaReceptor) {
        this.cedulaReceptor = cedulaReceptor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
