from Stack import Stack
import random

stack = Stack()

# Alimenta a pilha com 10 numeros aleatorios
for i in range(10):
    randomNumber = random.randint(0,99)
    stack.push(randomNumber)

print("Pilha com 10 elementos gerados aleatoriamente.")
print(stack.__str__)
print("")

evenStack = Stack()
oddStack = Stack()

# Separando pilha
for i in range(stack._size):
    number = stack.pop()

    if (number%2) == 0:
        evenStack.push(number)
    else:
        oddStack.push(number)

print("Pilha Par")
print(evenStack.__str__)
print("")
print("Pilha Impar")
print(oddStack.__str__)


    
