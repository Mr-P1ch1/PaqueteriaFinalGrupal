import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JPanel Ventana;
    private JTabbedPane tabbedPane1;
    private JSpinner spinner1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton agregarButton;
    private JButton totalPaquetesButton;
    private JButton totalPesoButton;
    private JComboBox comboBox3;
    private JButton totalPesoPorCiudadButton;
    private JList list1;
    private JButton modificarButton;
    private JTextField textField3;
    private JButton modificarEstadoButton;
    private JButton totalPaquetesEstadoButton;
    private JTextField textField4;
    private JTextField textField5;
    private JButton buscarButton;
    private JTextArea textArea1;
    private Lista paquete =new Lista();

    public Ventana() {
        quemarDatos();
        llenarJlist();
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try{
                   paquete.adicionarelemnto(new Paqueteria(Integer.parseInt(spinner1.getValue().toString()),
                           Double.parseDouble(textField1.getText()),
                           comboBox1.getSelectedItem().toString(),
                           comboBox2.getSelectedItem().toString(),
                           textField2.getText().toString()));
                   JOptionPane.showMessageDialog(null,"Paquete Agregado");
                   limpiarDatos();
                   System.out.println(paquete.listarPaquetes());
                   llenarJlist();
               } catch (Exception ex){
                   JOptionPane.showMessageDialog(null,ex.getMessage());

               }
            }
        });

        totalPaquetesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"El total de paquetes es: "+ paquete.sumarTotalPaquetes());
            }
        });
        totalPesoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Toal peso es: "+ paquete.sumarToatlPeso());
            }
        });
        totalPesoPorCiudadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                   // String ciudad = JOptionPane.showInputDialog(null, "Ingrese la ciudad:");
                    String ciudad =comboBox3.getSelectedItem().toString();
                    double pesoPorCiudad = paquete.sumarTotalPesoCiudad(ciudad);
                    JOptionPane.showMessageDialog(null, "Total peso por ciudad " + ciudad + ": " + pesoPorCiudad);

            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(list1.getSelectedIndex()!=-1){
                    int indice = list1.getSelectedIndex();
                    Paqueteria pa =paquete.getServiEntrega().get(indice);
                    spinner1.setValue(pa.getTracking());
                    textField1.setText(String.valueOf(pa.getPeso()));//MODIFICAR DEBE ESTAR FUNCIONANADO;
                    textField2.setText(String.valueOf(pa.getCedulaReceptor()));
                    comboBox1.setSelectedItem(pa.getCiudadRecepcion());
                    comboBox2.setSelectedItem(pa.getCiudadEntrega());
                    comboBox3.setSelectedItem(pa.getCiudadRecepcion());

                }
            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try{
                int tracking = Integer.parseInt(spinner1.getValue().toString());
                Paqueteria nuevo = new Paqueteria(tracking, Double.parseDouble(textField1.getText()),comboBox1.getSelectedItem().toString(),comboBox2.getSelectedItem().toString(),textField2.getText().toString());
                paquete.modificarDatos(tracking,nuevo);
                JOptionPane.showMessageDialog(null,"Modificacado correctamente");
                limpiarDatos();
                llenarJlist();

            }catch (Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage());
            }
            }
        });
        totalPaquetesEstadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Suponiendo que tienes un botón llamado "botonMostrarCantidadPorEstado" en tu interfaz de usuario

                int cantidadReceptados = paquete.contarPaquetesPorEstado("Receptado");
                int cantidadEnviados = paquete.contarPaquetesPorEstado("Enviado");
                int cantidadEntregados = paquete.contarPaquetesPorEstado("Entregado");


                String mensaje = "Cantidad de paquetes:\n";
                mensaje += "Receptados: " + cantidadReceptados + "\n";
                mensaje += "Enviados: " + cantidadEnviados + "\n";
                mensaje += "Entregados: " + cantidadEntregados;


                JOptionPane.showMessageDialog(null, mensaje);

            }
        });

        modificarEstadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
                try {
                    int tracking = Integer.parseInt(textField3.getText());
                    for (Paqueteria paquete : paquete.getServiEntrega()) {
                        if (paquete.getTracking() == tracking) {
                            if (paquete.getEstado().equals("Receptado")) {
                                paquete.setEstado("Enviado");
                                JOptionPane.showMessageDialog(null, "Paquete marcado como Enviado.");
                            } else if (paquete.getEstado().equals("Enviado")) {
                                paquete.setEstado("Entregado");
                                JOptionPane.showMessageDialog(null, "Paquete entregado.");
                            }
                            llenarJlist();
                            return;
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Paquete no encontrado.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número de seguimiento válido.");
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = textField4.getText();
                String estado = textField5.getText();

                if (!cedula.isEmpty() && !estado.isEmpty()) {
                    String resultadoBusqueda = paquete.buscarPaquetesPorCedulaYEstado(cedula, estado);
                    textArea1.setText(resultadoBusqueda);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese la cédula y el estado para realizar la búsqueda.");
                }
            }
        });
    }

    public void limpiarDatos() {
        spinner1.setValue(0);
        textField1.setText("");
        textField2.setText("");
        comboBox1.setSelectedItem("Quito");
        comboBox2.setSelectedItem("Quito");
        comboBox3.setSelectedItem("Quito");
    }


    public void quemarDatos()  {
        try {
            paquete.adicionarelemnto(new Paqueteria(1, 50, "Quito", "Guayaquil", "1010101"));
            paquete.adicionarelemnto(new Paqueteria(2, 40, "Cuenca", "Ibarra", "222222"));
            paquete.adicionarelemnto(new Paqueteria(4, 60, "Riobamba", "Quito", "333333"));
        }catch(Exception ex1){
        }

    }
    public void llenarJlist() {
        DefaultListModel dlm = new DefaultListModel<>();

        for (Paqueteria e:paquete.getServiEntrega()) {
            dlm.addElement(e);
        }
        list1.setModel(dlm);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,600);
        frame.pack();
        frame.setVisible(true);
    }


}
