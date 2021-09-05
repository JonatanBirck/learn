# Em relação aos scripts Python desenvolvidos em aula, escreva uma nova função para inserir novos elementos no array, não permitindo valores duplicados, 
# ou seja, o script deve verificar cada item no array antes de fazer uma nova inserção, não permitindo duplicidades.

print("Executing...")

print("Exemplo O(1)")

def printFirstNumber( array ):
    print( array[0] )

array = [4,8,3,5,1,10]

print( printFirstNumber( array ) )

print("Done!")


print("Executing...")

print("Exemplo O(n)")

def printLastNumber( array ):
    print( array[len(array)-1] )

array = [4,8,3,5,1,10]

print( printLastNumber( array ) )

print("Done!")


# Considerando a leitura do artigo "Iniciando com a notação Big O" e baseado em seus conhecimentos prévios ou pesquisas feitas sobre o assunto: 

# 2 - Coloque um comparativo de complexidade de tempo entre dois códigos Python (ou linguagem a sua escolha), 
#     um com complexidade O(1) e o código similar com complexidade O(n). Escreva os dois scripts aqui e aponte e explique o Big O de cada um deles.
# 3 - Considerando as funções desenvolvidas na atividade do "Vetor não ordenado", quais seriam o valores de complexidade Big O das funções abaixo: