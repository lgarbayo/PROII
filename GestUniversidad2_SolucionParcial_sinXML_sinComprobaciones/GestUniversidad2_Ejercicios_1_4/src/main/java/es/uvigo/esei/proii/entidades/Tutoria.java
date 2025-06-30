
package es.uvigo.esei.proii.entidades;

/**
 *
 * @author nrufino
 */
public class Tutoria {
    private Docente profesor;
    private Estudiante estudiante;
    private Fecha fechaTutoria;
    private Hora horaTutoria;

    public Tutoria(Docente profesor, Estudiante estudiante, 
            Fecha fechaTutoria, Hora horaTutoria) {
        this.profesor = profesor;
        this.estudiante = estudiante;
        this.fechaTutoria = fechaTutoria;
        this.horaTutoria = horaTutoria;
    }

    public Docente getProfesor() {
        return profesor;
    }

    public void setProfesor(Docente profesor) {
        this.profesor = profesor;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Fecha getFechaTutoria() {
        return fechaTutoria;
    }

    public void setFechaTutoria(Fecha fechaTutoria) {
        this.fechaTutoria = fechaTutoria;
    }

    public Hora getHoraTutoria() {
        return horaTutoria;
    }

    public void setHoraTutoria(Hora horaTutoria) {
        this.horaTutoria = horaTutoria;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        
        toret.append("\nTutoria");
        toret.append("\nProfesor: ").append(getProfesor());
        toret.append("\nEstudiante: ").append(getEstudiante());
        toret.append("\nFecha Tutoria: ").append(getFechaTutoria());
        toret.append("\tHora Tutoria: ").append(getHoraTutoria());

        return toret.toString();
    }
    
    
}
