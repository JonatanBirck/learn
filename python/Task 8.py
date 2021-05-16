# Escreva então um programa chamado BatchMerge que abre simultaneamente os dois arquivos anteriores (partial-1.txt e partial-2.txt), criando um novo arquivo ordenado sorted.txt, ou seja, fazendo a fusão dos dois vetores, utilizando por exemplo o Mergesort.
# Detalhe: você não deve carregar todos os dados em memória! A ideia é que o arquivo resultante seja construído diretamente, à medida em que os dois arquivos ordenados são lidos. Verifique o resultado.

# - open "partial-1.txt" and "partial-2.txt"
# - read V1 and V2 from each file
# - write into "sorted.txt" min(V1,V2)
# - repeat until all values where read

# Atividade 03
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

def mergeSort(arr):
    if len(arr) > 1:
 
         # Finding the mid of the array
        mid = len(arr)//2
 
        # Dividing the array elements
        L = arr[:mid]
 
        # into 2 halves
        R = arr[mid:]
 
        # Sorting the first half
        mergeSort(L)
 
        # Sorting the second half
        mergeSort(R)
 
        i = j = k = 0
 
        # Copy data to temp arrays L[] and R[]
        while i < len(L) and j < len(R):
            if L[i] < R[j]:
                arr[k] = L[i]
                i += 1
            else:
                arr[k] = R[j]
                j += 1
            k += 1
 
        # Checking if any element was left
        while i < len(L):
            arr[k] = L[i]
            i += 1
            k += 1
 
        while j < len(R):
            arr[k] = R[j]
            j += 1
            k += 1        

partial1 = read("util\\partial-1.txt")
partial2 = read("util\\partial-2.txt")

partial1.extend(partial2)

mergeSort(partial1)

write( "util\\sorted.txt", partial1 )