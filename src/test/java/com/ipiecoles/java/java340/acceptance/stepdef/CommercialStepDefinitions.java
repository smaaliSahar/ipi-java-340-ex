package com.ipiecoles.java.java340.acceptance.stepdef;


import com.ipiecoles.java.java340.model.Commercial;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.assertj.core.api.Assertions;
import org.joda.time.format.DateTimeFormat;


public class CommercialStepDefinitions implements En {

    private Commercial com = null;

    public CommercialStepDefinitions() {
        
        When("^Je crée un commercial de prénom (.*), de nom (.*), de matricule (.*), embauché le (.*) avec un salaire de (.*) € avec un CA annuel de (.*) € et une performance de (.*)$",(String nom, String prenom, String matricule, String dateEmbauche, String salaire, String caAnnuel, String performance) -> {
            com = new Commercial(nom, prenom, matricule, DateTimeFormat.forPattern("dd/MM/YYYY").parseLocalDate(dateEmbauche), Double.parseDouble(salaire), Double.parseDouble(caAnnuel), Integer.parseInt(performance));
        });


        When("^Je l'augmente de (.*) %$", (Double pourcentage) -> {
            // Write code here that turns the phrase above into concrete actions
            com.augmenterSalaire(pourcentage);
        });

        Then("^Ce commercial a alors un salaire de (.*) €, une prime annuelle de (.*) €, (\\d+) congés et (\\d+) années d'ancienneté$", (Double salaire, Double primeAnnuelle, Integer nbConges, Integer nombreAnneeAnciennete) -> {
            Assertions.assertThat(com.getSalaire()).isEqualTo(salaire);
            Assertions.assertThat(com.getPrimeAnnuelle()).isEqualTo(primeAnnuelle);
            Assertions.assertThat(com.getNbConges()).isEqualTo(nbConges);
            Assertions.assertThat(com.getNombreAnneeAnciennete()).isEqualTo(nombreAnneeAnciennete);
        });
    }

}
