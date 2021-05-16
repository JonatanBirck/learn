package br.com.univates.aula.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.Gson;

import br.com.univates.aula.xor.Task;

public class Server extends Thread
{
    private int port = 8000;
    private DatagramSocket socket = null;
    private boolean running = false;
    private byte[] buffer = new byte[1024];
    private Task task = null;
    private List<Task> taskSolved = new ArrayList<>();

    public Server() 
    {
        try
        {
            socket = new DatagramSocket( port );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public Server( int port ) 
    {
        try
        {
            socket = new DatagramSocket( port );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        } 
    }

    public void run() 
    {
        try
        {
            running = true;

            System.out.println("[INFO] Servidor iniciado!");
            System.out.println("[INFO] ");

            while( running ) 
            {
                // received
                buffer = new byte[1024];
                
                DatagramPacket packet = new DatagramPacket( buffer, buffer.length );
                socket.receive( packet );
                
                InetAddress inetAddress = packet.getAddress();
                String address = inetAddress.toString().substring( 1, inetAddress.toString().length() );
                int port = packet.getPort();

                packet = new DatagramPacket( buffer, buffer.length, inetAddress, port );
                String received = new String( packet.getData(), 0, packet.getLength() ).trim();

                System.out.println("[INFO] ["+ address + ":" + port + "] diz: " + received );

                String message = "...";
                
                if( received.equals("send me a task") )
                {                   
                    if( task != null )
                    {
                        Gson gson = new Gson();
                        message = gson.toJson( task );
                    }
                } 
                else if( received.startsWith("solved:") )
                {
                    String solvedJson = received.substring( 7, received.length() ).trim();

                    System.out.println("[REALLY?] " + solvedJson );
                    
                    Gson gson = new Gson(); 
                    Task taskSolved = gson.fromJson( solvedJson, Task.class);

                    addTaskSolved(taskSolved);

                    message = "thanks bro";
                }
                else if( received.equals("close server") )
                {
                    running = false;
                    message = "Encerrando servidor...";
                }  

                DatagramPacket response = new DatagramPacket( message.getBytes(), 0, message.getBytes().length, inetAddress, port );
                socket.send( response );

                System.out.println("[INFO] [SERVER] responde: " + message );
                System.out.println("[INFO]");
            }

            socket.close();

            System.out.println("[INFO] ");
            System.out.println("[INFO] Servidor encerado!");
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }


    public Server(int port, DatagramSocket socket, boolean running, byte[] buffer, Task task, List<Task> taskSolved) {
        this.port = port;
        this.socket = socket;
        this.running = running;
        this.buffer = buffer;
        this.task = task;
        this.taskSolved = taskSolved;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public DatagramSocket getSocket() {
        return this.socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public boolean isRunning() {
        return this.running;
    }

    public boolean getRunning() {
        return this.running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public Task getTask() {
        return this.task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Task> getTaskSolved() {
        return this.taskSolved;
    }

    public void addTaskSolved( Task task ) {
        this.taskSolved.add( task );
    }

    public void setTaskSolved(List<Task> taskSolved) {
        this.taskSolved = taskSolved;
    }

    public Server port(int port) {
        setPort(port);
        return this;
    }

    public Server socket(DatagramSocket socket) {
        setSocket(socket);
        return this;
    }

    public Server running(boolean running) {
        setRunning(running);
        return this;
    }

    public Server buffer(byte[] buffer) {
        setBuffer(buffer);
        return this;
    }

    public Server task(Task task) {
        setTask(task);
        return this;
    }

    public Server taskSolved(List<Task> taskSolved) {
        setTaskSolved(taskSolved);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Server)) {
            return false;
        }
        Server server = (Server) o;
        return port == server.port && Objects.equals(socket, server.socket) && running == server.running && Objects.equals(buffer, server.buffer) && Objects.equals(task, server.task) && Objects.equals(taskSolved, server.taskSolved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(port, socket, running, buffer, task, taskSolved);
    }

    @Override
    public String toString() {
        return "{" +
            " port='" + getPort() + "'" +
            ", socket='" + getSocket() + "'" +
            ", running='" + isRunning() + "'" +
            ", buffer='" + getBuffer() + "'" +
            ", task='" + getTask() + "'" +
            ", taskSolved='" + getTaskSolved() + "'" +
            "}";
    }
}
