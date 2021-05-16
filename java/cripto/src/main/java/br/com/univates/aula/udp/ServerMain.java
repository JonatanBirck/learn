package br.com.univates.aula.udp;

import br.com.univates.aula.xor.Task;

public class ServerMain {
	public static void main(String[] args) {
		
        String message = "A vaca tava doida demais";
        String key = "VACA";

        Task taskEncrypt = Task.encrypt( message, key );

		Server server = new Server();
        server.setTask( taskEncrypt );
		server.start();
	}
}
