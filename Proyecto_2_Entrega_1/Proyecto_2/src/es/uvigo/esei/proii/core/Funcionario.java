package es.uvigo.esei.proii.core;

import nu.xom.Element;
import nu.xom.ParsingException;


public class Funcionario extends Docente {
    
    private Fecha tomaPosesion;

    public  static final String TAG_FUNCIONARIO = "Funcionario";
    private static final String TAG_TOMA_POSESION = "tomaDePosesion";
    public Funcionario(Fecha tomaPosesion, int despacho, String nrp, String nombre, int dni) {
        super(despacho, nrp, nombre, dni);
        this.setTomaPosesion(tomaPosesion);
    }

    public Funcionario(Element e) throws ParsingException{
        super(e);
        Element eFecha = e.getFirstChildElement(TAG_TOMA_POSESION);
        if (eFecha == null) {
            throw new ParsingException("No se ha encontrado la etiqueta Toma posesion");
        }
        setTomaPosesion(new Fecha(eFecha));
    }
    
    @Override
    public Element toDOM(){
        Element raiz = super.toDOM();
        raiz.setLocalName(TAG_FUNCIONARIO);
        
        Element eTP = tomaPosesion.toDOM();
        eTP.setLocalName(TAG_TOMA_POSESION);
        raiz.appendChild(eTP);
        
        return raiz;
    }
    /**
     * Comprueba si 2 Funcionarios son iguales, es decir, si son el mismo Docente y tienen la misma toma de posesion
     * @param f
     * @return 
     */
    public boolean equals(Funcionario f){
        return super.equals(f) && this.tomaPosesion.equals(f.getTomaPosesion());
    }
    
    public Fecha getTomaPosesion() {
        return tomaPosesion;
    }

    public void setTomaPosesion(Fecha tomaPosesion) {
        this.tomaPosesion = tomaPosesion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Funcionario.\n");
        sb.append(super.toString());
        sb.append("\ttomaPosesion: ").append(getTomaPosesion().toString());
        return sb.toString();
    }

    
    
    
    
}
