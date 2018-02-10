package com.ipiecoles.java.java340.repository;

import com.ipiecoles.java.java340.model.Commercial;
import com.ipiecoles.java.java340.model.Employe;
import org.assertj.core.api.Assertions;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeRepositoryTest {

    Commercial pierreDurand = new Commercial("Durand", "Pierre", "C12345", new LocalDate(), 1500d, 0d,0);

    Commercial jeanJacques = new Commercial("Jacques", "Jean", "C12346", new LocalDate(), 1500d, 0d,0);

    Commercial jacquesDupond = new Commercial("Dupond", "Jacques", "C12347", new LocalDate(), 1500d, 0d,0);

    @Before
    public void setUp(){
        commercialRepository.deleteAll();
        employeRepository.deleteAll();
        employeRepository.save(pierreDurand);
        employeRepository.save(jeanJacques);
        employeRepository.save(jacquesDupond);
    }

    @Autowired
    EmployeRepository employeRepository;

    @Autowired
    CommercialRepository commercialRepository;

    @Test
    public void testFindByNomOrPrenomAllIgnoreCasePrenom(){
        //Given

        //When
        List<Employe> employes = employeRepository.findByNomOrPrenomAllIgnoreCase("pierre");
        Assertions.assertThat(employes).hasSize(1);
        Assertions.assertThat(employes.get(0)).isEqualTo(pierreDurand);
    }

    @Test
    public void testFindByNomOrPrenomAllIgnoreCaseNom(){
        //Given

        //When
        List<Employe> employes = employeRepository.findByNomOrPrenomAllIgnoreCase("durand");
        Assertions.assertThat(employes).hasSize(1);
        Assertions.assertThat(employes.get(0)).isEqualTo(pierreDurand);
    }

    @Test
    public void testFindByNomOrPrenomAllIgnoreCaseNomPrenom(){
        //Given

        //When
        List<Employe> employes = employeRepository.findByNomOrPrenomAllIgnoreCase("jacques");
        Assertions.assertThat(employes).hasSize(2);
        Assertions.assertThat(employes).contains(jeanJacques, jacquesDupond);
    }

    @After
    public void tearDown(){
        employeRepository.deleteAll();
    }
}
