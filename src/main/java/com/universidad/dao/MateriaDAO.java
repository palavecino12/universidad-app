package com.universidad.dao;

import com.universidad.clases.Materia;
import java.util.List;

public interface MateriaDAO {

    void crearMateria(Materia materia);

    Materia buscarPorCodigo(String codigo);

    List<Materia> listarMaterias();

    void modificarMateria(Materia materia);

    void eliminarMateria(String codigo);
}