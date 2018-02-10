package com.ipiecoles.java.java340.model;


import com.ipiecoles.java.java340.exception.EmployeException;
import org.apache.tomcat.jni.Local;
import org.assertj.core.api.Assertions;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Enclosed.class)
public class CommercialTest {


    @RunWith(PowerMockRunner.class)
    @PrepareForTest(LocalDate.class)
    public static class CommercialStandardTest{
        @Test
        public void getPrimeAnnuelleWithCA0(){
            //Given
            Commercial commercial = new Commercial();
            commercial.setCaAnnuel(0d);

            //When
            Double prime = commercial.getPrimeAnnuelle();

            //Then
            Assertions.assertThat(prime).isEqualTo(500d);

        }

        @Test
        public void getPrimeAnnuelleWithCA100000(){
            //Given
            Commercial commercial = new Commercial();
            commercial.setCaAnnuel(100000d);

            //When
            Double prime = commercial.getPrimeAnnuelle();

            //Then
            Assertions.assertThat(prime).isEqualTo(5000d);

        }

        @Test
        public void getPrimeAnnuelleWithCA9000(){
            //Given
            Commercial commercial = new Commercial();
            commercial.setCaAnnuel(9000d);

            //When
            Double prime = commercial.getPrimeAnnuelle();

            //Then
            Assertions.assertThat(prime).isEqualTo(500d);

        }

        @Test
        public void getPrimeAnnuelleWithCANull(){
            //Given
            Commercial commercial = new Commercial();
            commercial.setCaAnnuel(null);

            //When
            Double prime = commercial.getPrimeAnnuelle();

            //Then
            Assertions.assertThat(prime).isEqualTo(500d);

        }

        @Test
        public void testGetNombreAnneeAncienneteN() throws Exception {
            //Given
            Commercial commercial = new Commercial();
            commercial.setDateEmbauche(new LocalDate(2017, 2, 10));
            PowerMockito.mockStatic(LocalDate.class);
            BDDMockito.given(LocalDate.now()).willReturn(new LocalDate(2017, 2, 12));

            //When
            Integer nbAnneeAnciennete = commercial.getNombreAnneeAnciennete();

            //Then
            Assertions.assertThat(nbAnneeAnciennete).isEqualTo(0);
        }

        @Test
        public void testGetNombreAnneeAncienneteNmoins1() throws Exception {
            //Given
            Commercial commercial = new Commercial();
            commercial.setDateEmbauche(new LocalDate(2017, 2, 10));
            PowerMockito.mockStatic(LocalDate.class);
            BDDMockito.given(LocalDate.now()).willReturn(new LocalDate(2018, 2, 12));

            //When
            Integer nbAnneeAnciennete = commercial.getNombreAnneeAnciennete();

            //Then
            Assertions.assertThat(nbAnneeAnciennete).isEqualTo(1);
        }

        @Test
        public void testGetNombreAnneeAncienneteNplus1() throws Exception {
            //Given
            Commercial commercial = new Commercial();
            commercial.setDateEmbauche(new LocalDate(2018, 2, 10));
            PowerMockito.mockStatic(LocalDate.class);
            BDDMockito.given(LocalDate.now()).willReturn(new LocalDate(2017, 2, 12));

            //When
            Integer nbAnneeAnciennete = commercial.getNombreAnneeAnciennete();

            //Then
            Assertions.assertThat(nbAnneeAnciennete).isEqualTo(0);
        }

        @Test
        public void testSetDateEmbaucheBeforeNow() throws Exception {
            //Given
            Commercial commercial = new Commercial();
            LocalDate dateEmbauche = new LocalDate(2017, 2, 10);
            PowerMockito.mockStatic(LocalDate.class);
            BDDMockito.given(LocalDate.now()).willReturn(new LocalDate(2018, 2, 12));

            //When
            commercial.setDateEmbauche(dateEmbauche);

            //Then
            Assertions.assertThat(commercial.getDateEmbauche()).isEqualTo(dateEmbauche);
        }

        @Test(expected = EmployeException.class)
        public void testSetDateEmbaucheAfterNow() throws Exception {
            //Given
            Commercial commercial = new Commercial();
            LocalDate dateEmbauche = new LocalDate(2018, 2, 10);
            PowerMockito.mockStatic(LocalDate.class);
            BDDMockito.given(LocalDate.now()).willReturn(new LocalDate(2017, 2, 12));

            //When
            commercial.setDateEmbauche(dateEmbauche);

            //Then exception
        }

        @Test
        public void testSetDateEmbaucheAfterNow2() {
            //Given
            Commercial commercial = new Commercial();
            LocalDate dateEmbauche = new LocalDate(2018, 2, 10);
            PowerMockito.mockStatic(LocalDate.class);
            BDDMockito.given(LocalDate.now()).willReturn(new LocalDate(2017, 2, 12));

            //When
            try {
                commercial.setDateEmbauche(dateEmbauche);
                Assertions.fail("SetDateEmbauche aurait dû lancer une exception");
            }
            catch (EmployeException e){
                Assertions.assertThat(e.getMessage()).isEqualTo("La date d'embauche ne peut être postérieure à la date courante");
            }

        }

    }

    @RunWith(value = Parameterized.class)
    public static class CommercialParameterizedTest {
        @Parameterized.Parameter(value = 0)
        public Integer perf;

        @Parameterized.Parameter(value = 1)
        public Note expectedNote;

        @Parameterized.Parameters(name = "{index}: performance {0} équivalent à {1}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {0, Note.INSUFFISANT},
                    {50, Note.INSUFFISANT},
                    {100, Note.PASSABLE},
                    {150, Note.BIEN},
                    {200, Note.TRES_BIEN},
                    {null, null},
                    {600, null}
            });
        }

        @Test
        public void testEquivalenceNote(){
            //Given
            Commercial commercial = new Commercial();
            commercial.setPerformance(perf);

            //When
            Note note = commercial.equivalenceNote();

            //Then
            Assertions.assertThat(note).isEqualTo(expectedNote);
        }
    }

}
