package poo;

public class Calificacion {

    private String asignatura;
    private double nota;

    public Calificacion(String asignatura, double nota) {
        this.asignatura = asignatura;
        this.nota = nota;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return asignatura + ": " + nota;
    }
}