package model.vo;

/**
 * Representation of a MovingViolation object
 */
public class VOMovingViolation implements Comparable<VOMovingViolation> 
{

	// TODO Definir los atributos de una infraccion


	private String objectId;
	private String violationDescription;
	private String location;
	private String totalPaid;
	private String accidentIndicator;
	private String ticketIssueDate;
	private String addressId;


	/**
	 * constructor
	 */
	public VOMovingViolation(String pObjectId, String pViolationDescription, String pLocation, String pTotalPaid, String pAccidentIndicator, String pTicketIssueDate, String pViolationCode, String pFineAMT, String pStreet, String pAddress_Id,String pPenalty1,String pPenalty2)
	{ 
		objectId=pObjectId;
		violationDescription=pViolationDescription;
		location=pLocation;
		totalPaid=pTotalPaid;
		accidentIndicator=pAccidentIndicator;
		ticketIssueDate=pTicketIssueDate;
		addressId = pAddress_Id;
	}
	/**
	 * @return id 
	 */
	public int objectId() 
	{
		// TODO Auto-generated method stub
		return Integer.parseInt(objectId);
	}	

	/**
	 * @return location 
	 */
	public String getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	/**
	 * @return date 
	 */
	public String getTicketIssueDate() {
		// TODO Auto-generated method stub
		return ticketIssueDate;
	}

	/**
	 * @return totalPaid 
	 */
	public int getTotalPaid() {
		// TODO Auto-generated method stub
		return Integer.parseInt(totalPaid);
	}

	/**
	 * @return accidentIndicator 
	 */
	public String  getAccidentIndicator() {
		// TODO Auto-generated method stub
		return accidentIndicator;
	}

	/**
	 * @return description 
	 */
	public String  getViolationDescription() {
		// TODO Auto-generated method stub
		return violationDescription;
	}

	@Override
	public int compareTo(VOMovingViolation o) 
	{
		// TODO implementar la comparacion "natural" de la clase
		int rta=ticketIssueDate.compareTo(o.getTicketIssueDate());
		if(rta==0)
		{
	        rta=objectId.compareTo(o.objectId);
		}
		
		return rta;
	}
	public String getAddressId(){
		return addressId;
	}

	public String toString()
	{
		// TODO Convertir objeto en String (representacion que se muestra en la consola)
		return "La infraccion: "+objectId+" ocurrio en: "+location+" el dia: "+ticketIssueDate+" se tiene que pagar: "+totalPaid +" y tiene descripcion: "+violationDescription;
	}
}
