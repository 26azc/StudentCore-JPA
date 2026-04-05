package com.studentcore.persistencia;

import com.studentcore.Alumno;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;

public class AlumnoJpaController {

    // 1) Factoría de EntityManager para la unidad de persistencia 'UnidadUsuarios'
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadUsuarios");

    // 2) Constructor vacío (se podría inyectar la fábrica en lugar de crear aquí)
    public AlumnoJpaController() {
        // constructor
    }

    // 3) Método create(Alumno): persistir una entidad de tipo Alumno
    public void create(Alumno alu) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(alu);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Alumno> findAlumnoEntities() {

        EntityManager em = emf.createEntityManager();
        try {
            CriteriaQuery<Alumno> cq = em.getCriteriaBuilder().createQuery(Alumno.class);
            cq.select(cq.from(Alumno.class));
            TypedQuery<Alumno> q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void edit(Alumno alu) {
        EntityManager em = emf.createEntityManager();

        try {
            // Hacemos que la transición sea segura
            em.getTransaction().begin();
            /**
             * Merge busca el objeto y actualiza
             * sus columnas en la DB
             */
            em.merge(alu);
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

    public Alumno findAlumno(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public void destroy(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Alumno alu;
            // Verifacmos si existe el ID
            alu = em.find(Alumno.class, id);

            if (alu != null) {
                em.remove(alu);
                em.getTransaction().commit();
                System.out.println("Alumno con ID " + id + " Elminado");
            } else {
                System.out.println("No se encontro el alumno con el ID " + id);
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }
}
