# método publico, apenas utilizado para minimizar os parâmetros necessários
def getIndexValueByBinarySearch( vector, number ):
    size = len(vector) - 1
    middle = int( size/2 )
    return _getIndexValueByBinarySearch( vector, middle, number, 0 )


# método privado, recursivo, cada comparação se não encontrar chama novamente o método
def _getIndexValueByBinarySearch( vector, middle, number, count ):

    count += 1
    print("Comparação: ", count, " Tentativa: ", vector[middle] )

    # Verifica se achou o numero
    if number == vector[middle]:
        return middle
    else:
        size = ( len(vector) - 1) - middle

    # Se não achou, ele chama novamente o método atualizando a variável middle
    if number > vector[middle]:
        newMiddle = int( (size/2) + middle ) 
    else:
        newMiddle = int( middle/2 ) 

    return _getIndexValueByBinarySearch( vector, newMiddle, number, count )

# aplicação
vector = [1, 2, 4, 5, 8, 10, 12, 13, 15, 18, 20, 22, 25, 28, 30, 32, 35 ]
print( "Vetor: ", vector )


index = getIndexValueByBinarySearch( vector, 4 )
print( "Encontrado! Index: ", index ) 