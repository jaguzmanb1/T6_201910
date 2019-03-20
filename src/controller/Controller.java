package controller;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import com.opencsv.CSVReader;

import model.data.structures.ArregloDinamico;
import model.data.structures.IteratorCola;
import model.data.structures.LinearProbingHash;
import model.data.structures.Queue;
import model.data.structures.SeparateChainingHash;
import model.vo.VOMovingViolation;
import view.MovingViolationsManagerView;

@SuppressWarnings("unused")
public class Controller 
{
	private MovingViolationsManagerView view;

	// TODO Definir las estructuras de datos para cargar las infracciones del periodo definido
	private Queue<VOMovingViolation> cola;

	// Muestra obtenida de los datos cargados 
	Comparable<VOMovingViolation> [ ] aux;

	// Copia de la muestra de datos a ordenar 

	private String[] listaMes;

	private SeparateChainingHash<String, ArregloDinamico<VOMovingViolation>> datosChaining;

	private LinearProbingHash<String, ArregloDinamico<VOMovingViolation>>datosLinear;

	public Controller() {
		view = new MovingViolationsManagerView();
		//TODO inicializar las estructuras de datos para la carga de informacion de archivos
		cola = new Queue<VOMovingViolation>();
		datosChaining=new SeparateChainingHash<>();
		datosLinear = new LinearProbingHash<>(5);
		listaMes= new String[6];
		listaMes[0]= "january";
		listaMes[1]= "february";
		listaMes[2]="march";
		listaMes[3]="april";
		listaMes[4]="may";
		listaMes[5]="june";
	}

	/**
	 * Leer los datos de las infracciones de los archivos. Cada infraccion debe ser Comparable para ser usada en los ordenamientos.
	 * Todas infracciones (MovingViolation) deben almacenarse en una Estructura de Datos (en el mismo orden como estan los archivos)
	 * A partir de estos datos se obtendran muestras para evaluar los algoritmos de ordenamiento
	 * @return numero de infracciones leidas 
	 */
	/**
	 * Leer los datos de las infracciones de los archivos. Cada infraccion debe ser Comparable para ser usada en los ordenamientos.
	 * Todas infracciones (MovingViolation) deben almacenarse en una Estructura de Datos (en el mismo orden como estan los archivos)
	 * A partir de estos datos se obtendran muestras para evaluar los algoritmos de ordenamiento
	 * @return numero de infracciones leidas 
	 */

	public int loadMovingViolations() 
	{
		int numDatos=0;
		for (int i = 0; i < 4; i++) 
		{
			String xMes=listaMes[i];
			try 
			{
				JsonReader lector=new JsonReader(new FileReader("."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_"+xMes+ "_2018.json"));

				ArregloDinamico<VOMovingViolation> x= crearArregloInfo(lector);

				for (int j = 0; j < x.darTamano(); j++) 
				{
					if(datosChaining.get(x.darElemento(j).getAddressId()) == null){
						ArregloDinamico<VOMovingViolation> prueba = new ArregloDinamico<>(1);
						prueba.agregar( x.darElemento(j) );
						datosChaining.put(x.darElemento(j).getAddressId() , prueba);
					}

					else{
						ArregloDinamico<VOMovingViolation> prueba = datosChaining.get(x.darElemento(j).getAddressId());
						prueba.agregar(x.darElemento(j));
						datosChaining.put(x.darElemento(j).getAddressId() , prueba);
					}
				}

				numDatos+=x.darTamano();
				lector.close();
			}
			catch (Exception e) 
			{
				// TODO: handle exception
				e.printStackTrace();
			}	
		}

		System.out.println("Num de llaves en la tabla: " + datosChaining.size());

		return numDatos;
	}




	public ArregloDinamico<VOMovingViolation> crearArregloInfo(JsonReader reader)throws IOException{
		ArregloDinamico<VOMovingViolation> xd= new ArregloDinamico<VOMovingViolation>(1000000);
		reader.beginArray();
		while(reader.hasNext()){
			xd.agregar(crearInfraccion(reader));
		}
		reader.endArray();
		return xd;
	}

	private VOMovingViolation crearInfraccion(JsonReader lector) throws IOException
	{
		String ObjectId="";
		String ViolationDescription="";
		String Location="";
		String TotalPaid="";
		String AccidentIndicator="";
		String TicketIssueDate="";
		String ViolationCode="";
		String FineAMT="";
		String Street="";
		String Address_Id="";
		String Penalty1="";
		String Penalty2="";

		lector.beginObject();
		while (lector.hasNext())
		{
			if(lector.peek() != JsonToken.NULL)
			{
				String info = lector.nextName();
				//						System.out.println(info);
				if (info.equals("OBJECTID")) 
				{
					ObjectId= lector.nextString();	
				} 
				else if (info.equals("LOCATION")) 
				{
					Location = lector.nextString();
				} 
				else if (info.equals("ADDRESS_ID")) 
				{
					try 
					{
						Address_Id = lector.nextString();

					} 
					catch (Exception e) 
					{
						// TODO: handle exception
						Address_Id="Null";
					}

					//					System.out.println("Adr::::"+Address_Id);
				}  
				else if (info.equals("STREETSEGID")) 
				{
					try 
					{
						Street = lector.nextString();
					} 
					catch (Exception e) 
					{
						// TODO: handle exception
						Street="Null";
					}
				} 
				else if (info.equals("VIOLATIONDESC")) 
				{
					ViolationDescription = lector.nextString();
				} 
				else if (info.equals("TOTALPAID")) 
				{
					TotalPaid = lector.nextString();
				} 
				else if (info.equals("ACCIDENTINDICATOR")) 
				{
					AccidentIndicator = lector.nextString();
				}
				else if (info.equals("TICKETISSUEDATE")) 
				{
					TicketIssueDate = lector.nextString();
				}
				else if (info.equals("VIOLATIONCODE")) 
				{
					ViolationCode = lector.nextString();
				}
				else if (info.equals("FINEAMT")) 
				{
					FineAMT = lector.nextString();
				}
				else if (info.equals("PENALTY1")) 
				{
					Penalty1 = lector.nextString();
				}
				else if (info.equals("PENALTY2")) 
				{
					try 
					{
						Penalty2 = lector.nextString();
					} 
					catch (Exception e) 
					{
						// TODO: handle exception
						Penalty2="";
					}
				}
				else 
				{
					lector.skipValue();
				}
			}
			else
			{
				lector.skipValue();
			}
		}
		lector.endObject();
		VOMovingViolation infraccion= new VOMovingViolation(ObjectId, ViolationDescription, Location, TotalPaid, AccidentIndicator, TicketIssueDate, ViolationCode, FineAMT, Street, Address_Id, Penalty1, Penalty2);
		return infraccion;

	}

	public SeparateChainingHash<String, VOMovingViolation> darChainingPorAddress(String address){

		SeparateChainingHash<String, VOMovingViolation> responseChaining = new SeparateChainingHash<>();
		ArregloDinamico<VOMovingViolation> porAddress = datosChaining.get(address);
		for (int i = 0 ; i < porAddress.darTamano() ; i++){
			String violationId = porAddress.darElemento(i).getAccidentIndicator();
			VOMovingViolation violation = porAddress.darElemento(i);
			responseChaining.put(violationId, violation);
		}
		return responseChaining;
	}

	

	public void run() 
	{
		long startTime;
		long endTime;
		long duration;

		int nDatos = 0;
		int nMuestra = 0;


		Scanner sc = new Scanner(System.in);
		boolean fin = false;

		while(!fin)
		{
			view.printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 1:
				// Cargar infracciones
				nDatos = this.loadMovingViolations();
				view.printMensage("Numero infracciones cargadas:" + nDatos);
				break;

			case 2:
				// Generar muestra de infracciones a ordenar
				view.printMensage("Dar address id: ");
				Scanner sc2 = new Scanner(System.in);
				String address = sc2.nextLine();
				SeparateChainingHash<String, VOMovingViolation> hashTable = this.darChainingPorAddress(address);
				view.printMensage(Integer.toString(hashTable.size()));
				aux = new Comparable[hashTable.size()];
				view.printMensage(Integer.toString(aux.length));

				int i = 0;
				while(hashTable.keys().hasNext()){
					view.printMensage(Integer.toString(i));
					i++;
				}
				view.printMensage(Integer.toString(i));
				
				break;

			case 3:
				break;

			case 4:
				break;

			case 5:
				break;

			case 6:
				break;

			case 7:
				break;

			case 8:	
				break;

			case 9:
				break;

			case 10:	
				fin=true;
				sc.close();
				break;
			}
		}
	}

}
