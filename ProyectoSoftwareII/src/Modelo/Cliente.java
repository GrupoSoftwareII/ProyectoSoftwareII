/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import javax.swing.JTextField;

/**
 *
 * @author
 */
public class Cliente{

    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    String message;
    int port;
    String name;
    String Server;
    boolean nuevoMsj;
    LinkedList<JTextField> jtxt;

    public Cliente(String Server, int port, LinkedList<JTextField> jtxt) throws IOException {
        this.port = port;
        this.name = "";
        this.message="";
        this.jtxt = jtxt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String[] obtenerPreguntas(String cedula) throws IOException{
        this.configure();
        String enviar = "preguntas,"+cedula;
        dos.writeUTF(enviar);
        String r = dis.readUTF();
        String[] preguntas = r.split(",");
        return preguntas;
    }
    
    public int responderPreg(String cedula) throws IOException{
        this.configure();
        String respuestas = "respuestas,"+cedula;
        for (JTextField txt : jtxt) {
            respuestas+=","+txt.getText();
        }
        dos.writeUTF(respuestas);
        String r = dis.readUTF();
        int respuestasC = Integer.parseInt(r);
        return respuestasC;
    }

    private void configure() throws IOException {
        socket = new Socket(this.Server, this.port);
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean getNuevoMsj(){
        return nuevoMsj;
    }
    
    public void setNuevoMsj(boolean nuevoMsj){
        this.nuevoMsj = nuevoMsj;
    }
    
    
}
