/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Rafa
 */
public class Nota
{

    private long id_alumno;
    private long id_asignatura;
    private int nota;

    public long getId_alumno()
    {
        return id_alumno;
    }

    public void setId_alumno(long id_alumno)
    {
        this.id_alumno = id_alumno;
    }

    public long getId_asignatura()
    {
        return id_asignatura;
    }

    public void setId_asignatura(long id_asignatura)
    {
        this.id_asignatura = id_asignatura;
    }

    public int getNota()
    {
        return nota;
    }

    public void setNota(int nota)
    {
        this.nota = nota;
    }

    public Nota()
    {
    }

}
