package es.uvigo.esei.proii.core;

import nu.xom.Element;
import nu.xom.ParsingException;

public abstract class Persona {
    
    private String nombre;
    private int dni;

    private static final String TAG_PERSONA = "Persona";
    private static final String TAG_NOMBRE = "Nombre";
    private static final String TAG_DNI = "DNI";
    
    public Persona(String nombre, int dni) {
        this.setNombre(nombre);
        this.setDni(dni);
    }
    
    public Persona(Element e) throws ParsingException{
        Element eDNI = e.getFirstChildElement(TAG_DNI);
        Element eNombre = e.getFirstChildElement(TAG_NOMBRE);
        
        if (eDNI == null) {
            throw new ParsingException("No se ha encontrado la etiqueta DNI");
        }
        if (eNombre == null) {
            throw new ParsingException("No se ha encontrado la etiqueta Nombre");
        }
        this.setNombre(eNombre.getValue().trim());
        
        try {
            this.setDni(Integer.parseInt(eDNI.getValue().trim()));
        } catch (NumberFormatException ex) {
            throw new ParsingException("Error al leer la etiqueta DNI");
        }
    }

    public Element toDOM(){
        Element raiz = new Element(TAG_PERSONA);
        
        Element eDNI = new Element(TAG_DNI);
        eDNI.appendChild(Integer.toString(dni));
        raiz.appendChild(eDNI);
        
        Element eNombre = new Element(TAG_NOMBRE);
        eNombre.appendChild(nombre);
        raiz.appendChild(eNombre);
        
        return raiz;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    /**
     * Comprueba si 2 personas son iguales, es decir, si sus DNIs y sus nombres son iguales
     * @param p
     * @return 
     */
    public boolean equals(Persona p){
        return this.dni == p.getDni() && this.nombre.equalsIgnoreCase(p.getNombre());
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\tnombre: ").append(nombre);
        sb.append("\tdni: ").append(dni);
        return sb.toString();
    }
    
    
}
