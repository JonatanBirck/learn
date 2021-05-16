class Gasto:
  def __init__(self, descricao: str, valor: float):
    self.descricao = descricao
    self.valor = valor

  def __repr__(self):
    return f'Gasto("{self.descricao}", {self.valor})'

  def __lt__(self, other):
    return self.valor < other.valor

  def __le__(self, other):
    return self.valor <= other.valor

  def __gt__(self, other):
    return self.valor > other.valor

  def __ge__(self, other):
    return self.valor >= other.valor

  def __ne__(self, other):
    return self.valor != other.valor

  def __eq__(self, other):
    return self.valor == other.valor

gastos = [
    Gasto( 'Arroz', 20.5 ),
    Gasto( 'Feijão', 10.9 ),
    Gasto( 'Óleo de soja', 20 ),
    Gasto( 'Sal', 2.2 ),
    Gasto( 'Açúcar', 22.8 ),
    Gasto( 'Café', 15.5 ),
    Gasto( 'Molho de tomate', 16 ),
    Gasto( 'Macarrão', 18.4 ),
    Gasto( 'Milho', 10.8 ),
    Gasto( 'Farinha de trigo', 21.2 ),
    Gasto( 'Farinha de mandioca', 22 ),
    Gasto( 'Leite', 24 ),
    Gasto( 'Manteiga', 24.5 ),
    Gasto( 'Chá', 24.8 ),
    Gasto( 'Carnes', 25 ),
    Gasto( 'Peixes', 26.4 ),
    Gasto( 'Ovos', 27.2 )
]

print( gastos )

gastos.sort()

print( gastos )