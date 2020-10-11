Sample story
Narrative:
Tendo como objetivo melhorar o desenvolvimento de software e melhorar a comunicação com a área de negócio adotou-se a metodologia BDD.
A tecnologia utilizada é a JBehave.

Scenario: Conjunto de passos necessários para a consulta do serviço REST aulas
Given uma aulaResource é criada
When eu busco aula 1
Then o resultado deve ter id 1

Scenario: Conjunto de passos necessários para a consulta do serviço REST disciplinas
Given uma disciplinaResource é criada
When eu busco disciplina 1