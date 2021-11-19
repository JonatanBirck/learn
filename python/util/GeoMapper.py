'''
GeoMapper - TIPO ABSTRATO DE DADOS

IDEIA
    Estrutura de dados com coordenadas para mapear áreas rurais.
    Implementar um tipo abstrato de dados que a partir de coordenadas de localização seja possível mapear áreas com suas dimensões.

UTILIZAÇÃO
    Muitos produtores rurais precisam realizar a medição de seus terrenos. Para a documentação, produção entre outras razões.
    Assim, caso seja necessário medir uma área, agora basta ir em qualquer serviço de mapa(google maps), buscar as coordenadas necessárias, utilizar o TAD e obter as informações.

O QUE A ESTRUTURA DEVE FAZER?
    Ao adicionar alguns pontos de geolocalização (obtidos no google maps por exemplo).
    Será possível mapear uma área e assim conseguir informações como área, perímetro, comprimento dos vetores...

COMO UTILIZAR?
    1. Ir ao https://www.google.com.br/maps
    2. Buscar a área a ser mapeada
    3. Pense em uma área, após vejá seus pontos (vértice)
    4. Mantenha o mouse no ponto, clique com o botão direito e clique na coordenada (para copiar)
    5. Adicione a coordenada ao TAD - GeoMapper
    6. Só utilizar os métodos para obter as informações

    Ao inserir 2 pontos (linha) você conseguirá algumas informações como comprimento.
    Ao inserir 3 ou mais pontos (triângulo, retângulo...) você conseguirá mais algumas informações como área, ângulos...
    Código de exemplo abaixo.
'''

### Code ###
from decimal import Decimal
from math import radians, cos, sin, asin, sqrt

class GeoMapper:

    def __init__(self):
        self.points = []
        self.area = 0
        self.perimeter = 0
        self.earthRadius = 6371
        return

    def recalculateArea(self):
        self.area = 0

        for i in range(len(self.points)-1):
            # Converte coordenadas de graus para radianos
            latitude1, longitude1, latitude2, longitude2 = map(radians, [ self.points[i][0], self.points[i][1], self.points[i+1][0], self.points[i+1][1] ] )

            # Formula de Haversine
            dlon = longitude2 - longitude1
            dlat = latitude2 - latitude1
            hav = sin(dlat/2)**2 + cos(latitude1) * cos(latitude2) * sin(dlon/2)**2
            self.perimeter += 2 * self.earthRadius * asin( sqrt(hav) ) * 1000

    def recalculateDistance(self):
        self.perimeter = 0

        for i in range(len(self.points)-1):
            # Converte coordenadas de graus para radianos
            latitude1, longitude1, latitude2, longitude2 = map(radians, [ self.points[i][0], self.points[i][1], self.points[i+1][0], self.points[i+1][1] ] )

            # Formula de Haversine
            dlon = longitude2 - longitude1
            dlat = latitude2 - latitude1
            hav = sin(dlat/2)**2 + cos(latitude1) * cos(latitude2) * sin(dlon/2)**2
            self.perimeter += 2 * self.earthRadius * asin( sqrt(hav) ) * 1000

    def recalculate(self):
        #Area
        if (len(self.points) > 3):
            self.recalculateArea()

        #Perimeter
        if (len(self.points) > 1):
            self.recalculateDistance()

    #   [+-] [++]
    #   [--] [-+]
    def insertPoint(self, latitude: Decimal, longitude: Decimal):
        self.points.append((latitude, longitude))
        self.recalculate()

### Example ###
geoMapper = GeoMapper()
geoMapper.insertPoint(-30.029082964249575, -51.18378382720551)
geoMapper.insertPoint(-29.764187829720434, -51.12816554758252)
#geoMapper.insertPoint(-30.029082964249575, -51.18378382720551)

print(geoMapper.points)
print(geoMapper.area)
print(geoMapper.perimeter)

#https://www.youtube.com/watch?v=D5dIN83UG_c
#https://www.youtube.com/watch?v=lVQ9gomBzfs
