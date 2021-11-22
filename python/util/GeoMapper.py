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
from math import radians, degrees, acos, cos, sin, asin, sqrt

class GeoMapper:

    def __init__(self):
        self.points = []
        self.area = 0
        self.perimeter = 0
        self.earthRadius = 6371
        return
    
    def result(self):
        print('Pontos: ',self.points)
        print('Área: ', "{:.2f}".format(self.area) + ' m²')
        print('Perímetro: ', "{:.2f}".format(self.perimeter) + ' m')

    #   [+-] [++]
    #   [--] [-+]
    def insertPoint(self, latitude: Decimal, longitude: Decimal):
        self.points.append((abs(latitude), abs(longitude)))
        self.recalculate()

    def recalculateArea(self):
        self.area = 0

        if(len(self.points) == 3):
            ab = self.calculateDistance2Points(self.points[0][0], self.points[0][1], self.points[1][0], self.points[1][1])
            bc = self.calculateDistance2Points(self.points[1][0], self.points[1][1], self.points[2][0], self.points[2][1])
            ac = self.calculateDistance2Points(self.points[2][0], self.points[2][1], self.points[0][0], self.points[0][1])
            self.area = self.calculateAreaTriangle(ab,bc,ac)
        
        if(len(self.points) == 4):
            h1 = self.calculateDistance2Points(self.points[0][0], self.points[0][1], self.points[2][0], self.points[2][1])
            h2 = self.calculateDistance2Points(self.points[1][0], self.points[1][1], self.points[3][0], self.points[3][1])

            ab = self.calculateDistance2Points(self.points[0][0], self.points[0][1], self.points[1][0], self.points[1][1])
            bc = self.calculateDistance2Points(self.points[1][0], self.points[1][1], self.points[2][0], self.points[2][1])
            cd = self.calculateDistance2Points(self.points[2][0], self.points[2][1], self.points[3][0], self.points[3][1])
            da = self.calculateDistance2Points(self.points[3][0], self.points[3][1], self.points[0][0], self.points[0][1])

            if (h1>h2):
                self.area += self.calculateAreaTriangle(ab,bc,h1)
                self.area += self.calculateAreaTriangle(cd,da,h1)
            else:
                self.area += self.calculateAreaTriangle(da,ab,h2)
                self.area += self.calculateAreaTriangle(bc,cd,h2)

        # create a rectangle for the figure to be inside and after that
        # break the bigger picture into pieces, calculate these pieces and remove from the larger area

        # create a rectangle
        # can be used on any polygon
        if(len(self.points) > 4):
            
            # get max and min
            latMax = abs(self.points[0][0])
            lonMax = abs(self.points[0][1])
            latMin = abs(self.points[0][0])
            lonMin = abs(self.points[0][1])
            
            # get the most extreme point
            for point in self.points:
                lat,lon = point
                latMax = abs(lat) if (abs(lat) > latMax) else latMax
                lonMax = abs(lon) if (abs(lon) > lonMax) else lonMax
                latMin = abs(lat) if (abs(lat) < latMin) else latMin
                lonMin = abs(lon) if (abs(lon) < lonMin) else lonMin

            # the larger area
            ra = [latMax, lonMax]
            rb = [latMax, lonMin]
            rc = [latMin, lonMin]
            rd = [latMin, lonMax]

            # outside rectangle area (rectangle using the most extreme points)
            areaRetangle = self.calculateDistance2Points(ra[0], ra[1], rb[0], rb[1]) * self.calculateDistance2Points(ra[0], ra[1], rd[0], rd[1])  

            x = 0
            y = 0

            for i in range(len(self.points)):
                lat1,lon1 = self.points[i]
                iNext = (i+1) if (i+1) < len(self.points) else 0
                lat2,lon2 = self.points[iNext]

                x += (lat1 * lon2)
                y += (lon1 * lat2)

            ua = abs(x-y) / 2

            # rule of 3
            self.area = (areaRetangle * ua) / ((latMax - latMin) * (lonMax - lonMin))
            

    def recalculatePerimeter(self):
        self.perimeter = 0

        for i in range(len(self.points)-1):
            self.perimeter += self.calculateDistance2Points(self.points[i][0], self.points[i][1], self.points[i+1][0], self.points[i+1][1])

        if(len(self.points) > 2):
            self.perimeter += self.calculateDistance2Points(self.points[0][0], self.points[0][1], self.points[len(self.points)-1][0], self.points[len(self.points)-1][1])

    def recalculate(self):
        self.recalculateArea()
        self.recalculatePerimeter()

    def calculateDistance2Points(self, latitude1: Decimal, longitude1: Decimal, latitude2: Decimal, longitude2: Decimal):
        # Converte coordenadas de graus para radianos
        latitude1, longitude1, latitude2, longitude2 = map(radians, [latitude1, longitude1, latitude2, longitude2] )

        # Formula de Haversine
        dlon = longitude2 - longitude1
        dlat = latitude2 - latitude1
        hav = sin(dlat/2)**2 + cos(latitude1) * cos(latitude2) * sin(dlon/2)**2
        perimeter = 2 * self.earthRadius * asin( sqrt(hav) ) * 1000
        return perimeter
    
    def calculateTriangleAngle(ab: Decimal, bc: Decimal, ac: Decimal, whoPoint: str):
        if (whoPoint == 'a'):
            return degrees(acos((ab**2 + bc**2 - ac**2) / (2 * ab * bc)))

        if (whoPoint == 'b'):
            return degrees(acos((ab**2 + ac**2 - bc**2) / (2 * ab * ac)))

        if (whoPoint == 'c'):
            return degrees(acos((ac**2 + bc**2 - ab**2) / (2 * ac * bc)))
    
    def calculateAreaTriangle(self, ab: Decimal, bc: Decimal, ac: Decimal):
        # Formula de Heron
        p = (ab + bc + ac) / 2
        area = sqrt(p * (p - ab) * (p - bc) * (p - ac))
        return area

