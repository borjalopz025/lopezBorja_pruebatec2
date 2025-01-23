package com.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class GenericoJPA<T, ID> {

    private Class<T> entidadGenerica;

    public GenericoJPA(Class<T> entidadGenerica) {
        this.entidadGenerica = entidadGenerica;
    }

    // TODO: 07/01/2025 Metodo generico create
    public void createGenerico(T nuevaEntidad) {
        EntityManager em = ConfigJPA.getEntityManager();

        try{
            em.getTransaction().begin();
            //Guardar el registro en la tabla
            em.persist(nuevaEntidad);
            em.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            em.close();
        }
    }

    public Optional<T> findOneGenerico(Integer idBuscado){
        EntityManager em = ConfigJPA.getEntityManager();
        try{
            return  Optional.ofNullable(em.find(this.entidadGenerica, idBuscado));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            em.close();
        }
        return  Optional.ofNullable(null);
    }

    public List<T> findAllGenerico(){
        EntityManager em = ConfigJPA.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entidadGenerica);
        Root<T> root = cq.from(this.entidadGenerica);
        CriteriaQuery<T> all = cq.select(root);
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public void updateGenerico(T actualizarElemento){
        EntityManager em = ConfigJPA.getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(actualizarElemento);
            em.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            em.close();
        }
    }

    public void deleteGenerico(Integer idEliminar){
        EntityManager em = ConfigJPA.getEntityManager();
        try{
            em.getTransaction().begin();

            Optional<T> elementoEncontrado = Optional.ofNullable(em.find(this.entidadGenerica, idEliminar));

            if (elementoEncontrado.isPresent()){
                em.remove(elementoEncontrado.get());
            }else{
                System.out.println("El id " + idEliminar + " de la tabla " + this.entidadGenerica.getSimpleName() + " no existe");
            }
            em.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            em.close();
        }
    }

}
