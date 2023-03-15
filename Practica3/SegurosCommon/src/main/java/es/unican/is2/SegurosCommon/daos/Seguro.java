package es.unican.is2.SegurosCommon.daos;




import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

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
	
    
    @XmlAttribute(required = true)
    private int potencia;
    
    @XmlAttribute(required = true)
    private String matricula;
    
    @XmlAttribute(required = true)
    private Cobertura cobertura;
    
    @XmlAttribute(name="fecha", required=true)
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate fechaContratacion;
    
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
    	return 0;
    }

}
