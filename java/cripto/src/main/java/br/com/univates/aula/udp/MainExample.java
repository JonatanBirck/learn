package br.com.univates.aula.udp;

import com.google.gson.Gson;

import br.com.univates.aula.xor.Task;

public class MainExample {
	public static void main(String[] args) {
        
        // ============== SERVER ===================
        String message = "A vaca tava doida demais";
        String key = "VACA";

        Task taskEncrypt = Task.encrypt( message, key );

		Server server = new Server();
        server.setTask( taskEncrypt );
		server.start();

        // ============== CLIENT ===================
        System.out.println("[INFO] Iniciando client...");
        System.out.println("[INFO]");
        
        String serverAddress = "192.168.0.105";
		Client client = new Client( serverAddress );

        // hello
		String responseHello = client.sendMessage( "hello server" );

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
