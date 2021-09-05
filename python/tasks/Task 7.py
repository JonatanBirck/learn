# Escreva um programa chamado BatchSort que implementa um algoritmo de ordenação in-place (baseado em código-fonte eficiente obtido online). 
# Esse programa deve ler o arquivo numbers-1.txt (em anexo) para a memória, ordená-lo com o algoritmo in-place e gravar o resultado ordenado (em modo texto) no arquivo partial-1.txt. 
# O mesmo deve ser feito em seguida para o arquivo numbers-2.txt, gerando então o arquivo partial-2.txt. Verifique se os dois arquivos de saída estão corretamente ordenados. 

# - read "numbers-1.txt" into array
# - sort array using quicksort (in-place)
# - write array into "partial-1.txt"
# 
# - read "numbers-2.txt" into array
# - sort array using quicksort (in-place)
# - write array into "partial-2.txt"

# Atividade 02
def read( pathFile ):
    
    array = []

    with open( pathFile ) as file:
        lines = file.readlines()

    for line in lines:
        array.append( int(line.replace('\n','')) )
    
    return array

def write( path, array ):

    file = open( path , "w")

    for value in array:
        file.write( str(value) + "\n" )

def quickSort( array ):

    elements = len( array )

    if elements < 2:
        return array
    
    currentPosition = 0 

    #Partitioning loop
    for i in range(1, elements): 
         if array[i] <= array[0]:
              currentPosition += 1
              temp = array[i]
              array[i] = array[currentPosition]
              array[currentPosition] = temp

    temp = array[0]
    array[0] = array[currentPosition] 
    array[currentPosition] = temp #Brings pivot to it's appropriate position
    
    #Sorts the elements to the left of pivot
    left = quickSort(array[0:currentPosition]) 

    #sorts the elements to the right of pivot
    right = quickSort(array[currentPosition+1:elements]) 

    #Merging everything together
    array = left + [array[currentPosition]] + right 
    
    return array        

array = read("util\\numbers-1.txt")

sorted_array = quickSort( array )

write('util\partial-1.txt', sorted_array )