package com.studentcore.persistencia;

import com.studentcore.Carrera;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;

public class CarreraJpaController {

    // 1) Factoría de EntityManager para la unidad de persistencia 'UnidadUsuarios'
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadUsuarios");

    // 2) Constructor vacío (se podría inyectar la fábrica en lugar de crear aquí)
    public CarreraJpaController() {
        // constructor
    }

    // 3) Método create(Alumno): persistir una entidad de tipo Alumno
    public void create(Carrera carre) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(carre);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Carrera> findCarreraEntities() {

        EntityManager em = emf.createEntityManager();
        try {
            CriteriaQuery<Carrera> cq = em.getCriteriaBuilder().createQuery(Carrera.class);
            cq.select(cq.from(Carrera.class));
            TypedQuery<Carrera> q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void edit(Carrera carre) {
        EntityManager em = emf.createEntityManager();

        try {
            // Hacemos que la transición sea segura
            em.getTransaction().begin();
            /**
             * Merge busca el objeto y actualiza
             * sus columnas en la DB
             */
            em.merge(carre);
            em.getTransaction().commit();
        } catch (Exception e) {
            /**
             * Si el ID no existe o algo falla, la operacion se cancela
             */
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    public Carrera findCarrera(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Carrera.class, id);
        } finally {
            em.close();
        }
    }

    public void destroy(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Carrera carre;
            // Verifacmos si existe el ID
            carre = em.find(Carrera.class, id);

            if (carre != null) {
                em.remove(carre);
                em.getTransaction().commit();
                System.out.println("Carrera con ID " + id + " Elminado");
            } else {
                System.out.println("No se encontro la carrera con el ID " + id);
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }
}
