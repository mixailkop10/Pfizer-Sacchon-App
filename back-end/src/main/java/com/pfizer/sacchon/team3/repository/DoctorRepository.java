package com.pfizer.sacchon.team3.repository;

import com.pfizer.sacchon.team3.model.Doctor;

import javax.persistence.EntityManager;
import javax.print.Doc;
import java.util.List;
import java.util.Optional;

public class DoctorRepository {

    private EntityManager entityManager;

    public DoctorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Doctor> findById(Long id) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        return doctor != null ? Optional.of(doctor) : Optional.empty();
    }

    public List<Doctor> findAll() {
        return entityManager.createQuery("from Doctor").getResultList();
    }

    public Optional<Doctor> findByName(String name) {
        Doctor doctor = entityManager.createQuery("SELECT b FROM Doctor b WHERE b.name = :name", Doctor.class)
                .setParameter("name", name)
                .getSingleResult();
        return doctor != null ? Optional.of(doctor) : Optional.empty();
    }

    public Optional<Doctor> findByNameNamedQuery(String name) {
        Doctor doctor = entityManager.createNamedQuery("Product.findByName", Doctor.class)
                .setParameter("name", name)
                .getSingleResult();
        return doctor != null ? Optional.of(doctor) : Optional.empty();
    }


    public Optional<Doctor> save(Doctor doctor){

        try {
            entityManager.getTransaction().begin();
            entityManager.persist (doctor);
            entityManager.getTransaction().commit();
            return Optional.of(doctor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public Optional<Doctor> update(Doctor doctor) {

        Doctor in = entityManager.find(Doctor.class, doctor.getId());
        in.setFirstName(doctor.getFirstName());
        in.setLastName(doctor.getLastName());
        in.setEmail(doctor.getEmail());
        in.setPassword(doctor.getPassword());
        in.setLastActive(doctor.getLastActive());

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(in);
            entityManager.getTransaction().commit();
            return Optional.of(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean remove(Long id){
        Optional<Doctor> odoctor = findById(id);
        if (odoctor.isPresent()){
            Doctor p = odoctor.get();
            try{
                entityManager.getTransaction().begin();
                entityManager.remove(p);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
