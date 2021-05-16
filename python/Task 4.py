# metodo publico, apenas utilizado para minimizar os parametros necessarios
def getIndexValueByBinarySearch( vector, number ):
    size = len(vector) - 1
    middle = int( size/2 )
    return _getIndexValueByBinarySearch( vector, middle, number, 0 )


# metodo privado, recursivo, cada comparação se não encontrar chama novamente o metodo
def _getIndexValueByBinarySearch( vector, middle, number, count ):

    count += 1
    print("Comparação: ", count, " Tentativa: ", vector[middle] )

    # Verifica se achou o numero
    if number == vector[middle]:
        return middle
    else:
        size = ( len(vector) - 1) - middle

    # Se não achou, ele chama novamente o metodo atualizando a variavel middle
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