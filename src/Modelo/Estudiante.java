/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Modulo 2
 */
public class Estudiante implements Runnable {

    private String codigo;
    private boolean conectado;
    private boolean existe;
    private int puntos;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String documento;
    private DataInputStream dis;
    private DataOutputStream dos;
    private int puerto;
    private String ip;
    private Socket socket;
    private PreguntaMultiple pm;

    public Estudiante() {
        puerto = 1399;
        ip = "127.0.0.1";
        configurar();
    }

    public Estudiante(String codigo, int puntos, String nombre, String apellido1, String apellido2) {
        this.codigo = codigo;
        this.puntos = puntos;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        conectado = false;
        existe = false;
    }

    public void configurar() {
        try {
            socket = new Socket(ip, puerto);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Errorr: " + ex.getMessage());
        }

    }

    public Estudiante(String codigo, int puntos, String nombre, String apellido) {
        this.codigo = codigo;
        this.puntos = puntos;
        this.nombre = nombre;
        puerto = 1399;
        ip = "127.0.0.1";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public int getPuntos() {
        return puntos;
    }

    public PreguntaMultiple getPm() {
        return pm;
    }

    public void setPm(PreguntaMultiple pm) {
        this.pm = pm;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
    
    
    
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void enviarRespuesta(String respuesta, String idPregunta) {
        try {
            dos.writeUTF("respuesta" + ";" + respuesta+";"+this.codigo+";"+idPregunta);
        } catch (IOException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void guardarPuntos(String puntos){
        try {
            dos.writeUTF("puntos;"+puntos);
        } catch (IOException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public void manejador(String evento) {
        String[] datos = evento.split(";");
        if (datos[0].equals("conectado")) {
            this.setCodigo(datos[1]);
            this.setNombre(datos[3]);
            this.setApellido1(datos[4]);
            this.setPuntos(Integer.parseInt(datos[2]));
            this.conectado = true;
        } else if (datos[0].equals("nologin")) {
            this.conectado = false;
        } else if (datos[0].equals("existe")) {
            this.existe = true;

        } else if (datos[0].equals("pregunta")) {
            System.out.println("estoy llegando con mi pregunta"+datos.length);
            pm = new PreguntaMultiple(datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], 5, Integer.parseInt(datos[7]));
            System.out.println("la regunta es: "+pm.getPregunta()+" el id: "+pm.getId());
        } else if (datos[0].equals("nopregunta")) {
            System.out.println("estoy llegando con no pregunta"+datos.length);
            pm = new PreguntaMultiple("No hay preguntas", "", "", "", "", "", 0, -1);

        }
    }

    public void solicitarPregunta() {
        try {
            dos.writeUTF("pregunta");
        } catch (IOException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void conectar(String usuario, String pass) {
        try {
            System.out.println("usu: " + usuario + " pas: " + pass);
            dos.writeUTF("conectar;" + usuario + ";" + pass);
            String res = dis.readUTF();
            manejador(res);

        } catch (IOException ex) {
            System.out.println("Error al conectar: " + ex.getMessage() + " en: " + ex.getLocalizedMessage());
        }

    }

    @Override
    public void run() {
        try {
            while (true) {
                String respuesta = dis.readUTF();
                manejador(respuesta);
            }
        } catch (IOException ex) {
            System.out.println("se desconecto el estudiante");

        }

    }

}
