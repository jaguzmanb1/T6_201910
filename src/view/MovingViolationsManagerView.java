package view;

public class MovingViolationsManagerView 
{
	public MovingViolationsManagerView() {
		
	}
	
	public void printMenu() {
		System.out.println("------------- Estructuras de datos-----------------");
		System.out.println("---------------------Taller 6----------------------");
		System.out.println("1. Cargar datos de infracciones en movimiento");
		System.out.println("2. Infracciones con accidente por ADDRESS_ID");
		System.out.println("10. Salir");
		System.out.println("Digite el numero de opcion para ejecutar la tarea, luego presione enter: (Ej., 1):");
		
	}
	
	public void printDatosMuestra(Comparable [ ] muestra)
	{
		for ( Comparable dato : muestra)
		{	System.out.println(  dato.toString() );    }
	}
	
	public void printMensage(String mensaje) {
		System.out.println(mensaje);
	}
}
