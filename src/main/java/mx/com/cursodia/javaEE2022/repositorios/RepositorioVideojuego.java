package mx.com.cursodia.javaEE2022.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;

@Repository
public interface RepositorioVideojuego extends JpaRepository<Videojuego, Integer> 
{//un repositorio es una plantilla de operaciones basicas que se hacen con una entidad

}
