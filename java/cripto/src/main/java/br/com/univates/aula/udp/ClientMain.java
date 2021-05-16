package br.com.univates.aula.udp;

import com.google.gson.Gson;

import br.com.univates.aula.xor.Task;

public class ClientMain {
	public static void main(String[] args) {
		
        System.out.println("[INFO] Iniciando client...");
        System.out.println("[INFO]");
        
        String serverAddress = "192.168.0.105"; //local
		Client client = new Client( serverAddress );
		
        // get Task and Resolve
        String jsonTask = client.sendMessage( "send me a task" );

        Gson gson = new Gson(); 
        Task task = gson.fromJson( jsonTask, Task.class);

        Task taskResolved = Task.resolve(task);

        // send solution
        String responseSolved = client.sendMessage( "solved: " + gson.toJson( taskResolved ) );

        // send close server
        String responseClose = client.sendMessage( "close server" );

        System.out.println("[INFO]");
        System.out.println("[INFO] Client finalizado!");
	}
}
