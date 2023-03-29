package es.unican.is2.SegurosCommon.daos;




import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import es.unican.is2.SegurosCommon.interfaces.IClientesDAO;

/**
 * Clase que representa un seguro de coche.
 * Un seguro se identifica por la matrícula
 * del coche para el que se contrata el seguro
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Seguro")
public class Seguro {
	
	private static final double PORCENTAJE_TRAMO_1 = 0.95;
	private static final double PORCENTAJE_TRAMO_2 = 0.80;
	private static final int INICIO_TRAMO_1= 90;
	private static final int FIN_TRAMO_1=110;
	private static final double DESCUENTO_PRIMER_ANHO = 0.8;
	private static final double DESCUENTO_SEGUNDO_ANHO = 0.9;
	
	private static final int BASE_TODO_RIESGO = 1000;
	private static final int BASE_TERCEROS_LUNAS = 600;
	private static final int BASE_TERCEROS = 400;
	
	
	private static final double MINUSVALIA = 0.75;
	
	Cliente cliente;
    
    public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	@XmlAttribute(required = true)
    private int potencia;
    
    @XmlAttribute(required = true)
    private String matricula;
    
    @XmlAttribute(required = true)
    private Cobertura cobertura;
    
    @XmlAttribute(name="fecha", required=true)
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate fechaContratacion;
    
   
    
    
	public Seguro(Cobertura cobertura, int potencia, LocalDate fechaContratacion, Cliente cliente) {
		this.cliente = cliente;
		this.potencia = potencia;
		this.cobertura = cobertura;
		this.fechaContratacion = fechaContratacion;
		 
	}
	
	 public Seguro() {}


	/**
	 * Retorna la matrícula del coche 
	 * asociado al seguro
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Define el valor para la matrícula
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * Retorna el tipo de cobertura del seguro
	 */
	public Cobertura getCobertura() {
		return cobertura;
	}

	/**
	 * Define el valor para la cobertura
	 */
	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}


	/**
     * Retorna la potencia del coche asociado 
     * al seguro. 
     */
    public int getPotencia() {
        return potencia;
    }

    /**
     * Define el valor de la potencia.
     */
    public void setPotencia(int value) {
        this.potencia = value;
    }
    
    /**
     * Retorna el precio del seguro
     * @return
     */
    public double precio() {
    	double total = 0.0;
    	switch(cobertura) {
    	case TODORIESGO:
    		if(potencia >= INICIO_TRAMO_1 &&  potencia <= FIN_TRAMO_1) {
    			total = BASE_TODO_RIESGO * PORCENTAJE_TRAMO_1;
    		}else if(potencia > FIN_TRAMO_1) {
    			total = BASE_TODO_RIESGO * PORCENTAJE_TRAMO_2;
    		}
    		break;
    	case TERCEROS:
    		if(potencia >= INICIO_TRAMO_1 &&  potencia <= FIN_TRAMO_1) {
    			total = BASE_TERCEROS * PORCENTAJE_TRAMO_1;
    		}else if(potencia > FIN_TRAMO_1) {
    			total = BASE_TERCEROS * PORCENTAJE_TRAMO_2;
    		}
    		break;
    		
    	case TERCEROSLUNAS:
    		if(potencia >= INICIO_TRAMO_1 &&  potencia <= FIN_TRAMO_1) {
    			total = BASE_TERCEROS_LUNAS * PORCENTAJE_TRAMO_1;
    		}else if(potencia > FIN_TRAMO_1) {
    			total = BASE_TERCEROS_LUNAS * PORCENTAJE_TRAMO_2;
    		}	
    		break;
    	}
    	int anios = fechaContratacion.getYear();
    	int anio_actual = LocalDate.now().getYear();
    	if (( anio_actual - anios) == 1) {
    		total = total * DESCUENTO_PRIMER_ANHO;
    	}
    	if (((anios - anio_actual) == 0) && (fechaContratacion.getMonthValue() - LocalDate.now().getMonthValue()) != 0) {
    		total = total * DESCUENTO_SEGUNDO_ANHO;
    	}
    	if (cliente.getMinusvalia() == true) {
    		total = total * MINUSVALIA;
    	}
    	return total;
    }

}
