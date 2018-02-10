Feature: Commercial

  Scenario: Création d'un compte
    Given Je crée un commercial de prénom Jean, de nom Dupond, de matricule C55555, embauché le 12/02/2016 avec un salaire de 2500.0 € avec un CA annuel de 50000.0 € et une performance de 100
    When Je l'augmente de 0.15 %
    Then Ce commercial a alors un salaire de 2875.0 €, une prime annuelle de 2500.0 €, 25 congés et 2 années d'ancienneté
