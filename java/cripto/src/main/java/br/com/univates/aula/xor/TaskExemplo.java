package br.com.univates.aula.xor;

public class TaskExemplo {
	public static void main(String[] args) {
		
		String message = "A vaca tava doida demais";
        String key = "VACA";
		
		//================= Criptografa =====================
		Task taskEncrypt = Task.encrypt( message, key );
		System.out.println( taskEncrypt.toString() );

		//================= Descriptografa ==================
		Task taskDecrypt = Task.decrypt( taskEncrypt.getMessageEncrypt(), key );
		System.out.println( taskDecrypt.toString() );

		//================= Força Bruta =====================
		Task taskResolved = Task.resolve( taskEncrypt );
		System.out.println( taskResolved.toString() );

	}
}
