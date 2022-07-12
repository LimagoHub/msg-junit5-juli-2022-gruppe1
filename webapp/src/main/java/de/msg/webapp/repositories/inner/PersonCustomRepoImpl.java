package de.msg.webapp.repositories.inner;

import de.msg.webapp.repositories.PersonCustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class PersonCustomRepoImpl implements PersonCustomRepo {


    @Override
    public long xyz() {
        return 10L;
    }
}
