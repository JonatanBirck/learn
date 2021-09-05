#Implemente um programa em Python que receba/armazene duas datas e realize as seguintes operações listadas abaixo:
#
#- Retornar o dia (um número) de uma das datas, você programador escolherá. 
#- Retornar o mês (um número) de uma das datas, você programador escolherá. 
#- Retornar o ano (um número) de uma das datas, você programador escolherá. 
#- Pegar o dia da semana
#- Testar se uma data é igual a uma outra data, retornado (True ou False)
#- Testar se uma data é menor que uma outra data, retornado (True ou False)
#- Testar se uma data é maior que uma outra data, retornado (True ou False)
#
#Desafio TDA:
#
#numDays( otherDate ) - Retorna o número de dias como um inteiro positivo entre esta data e a outra Data “otherDate”.

from datetime import datetime
import calendar
import locale

# Just for example, don't do like that!
class DateCalculator:
    
    def __init__(self, firstDate: str, secondDate: str):
        self.firstDate = datetime.strptime( firstDate, '%d/%m/%Y').date()
        self.secondDate = datetime.strptime( secondDate, '%d/%m/%Y').date()
        try:
            locale.setlocale(locale.LC_ALL, 'pt_BR')
            calendar.LocaleHTMLCalendar(calendar.SUNDAY, 'pt_BR')
        except:
            locale.setlocale(locale.LC_ALL, 'Portuguese_Brazil')
            calendar.LocaleHTMLCalendar(calendar.SUNDAY, 'Portuguese_Brazil')

    def dayOfFirstDate(self):
        return self.firstDate.day

    def dayOfSecondDate(self):
        return self.secondDate.day
    
    def monthOfFirstDate(self):
        return self.firstDate.month

    def monthOfSecondDate(self):
        return self.secondDate.month

    def yearOfFirstDate(self):
        return self.firstDate.year

    def yearOfSecondDate(self):
        return self.secondDate.year

    def weekdayOfFirstDate(self):
        return calendar.day_name[self.firstDate.weekday()]

    def weekdayOfSecondDate(self):
        return calendar.day_name[self.secondDate.weekday()]

    def firstDateEqualsOfSecondDate(self):
        return self.firstDate == self.secondDate

    def firstDateBeforeOfSecondDate(self):
        return self.firstDate < self.secondDate

    def firstDateAfterOfSecondDate(self):
        return self.firstDate > self.secondDate

    def numDaysBetweenFirstAndSecondDate(self):
        return (self.secondDate - self.firstDate).days

    pass

# Example
def teste():

    print('executing...')
    print('')

    dateCalculator = DateCalculator('12/08/2019', '12/09/2019')

    print('Primeira data!')
    print('Dia:', dateCalculator.dayOfFirstDate() )
    print('Mês:', dateCalculator.monthOfFirstDate() )
    print('Ano:', dateCalculator.yearOfFirstDate() )
    print('Semana:', dateCalculator.weekdayOfFirstDate() )
    print('')

    print('Segunda data!')
    print('Dia:', dateCalculator.dayOfSecondDate() )
    print('Mês:', dateCalculator.monthOfSecondDate() )
    print('Ano:', dateCalculator.yearOfSecondDate() )
    print('Semana:', dateCalculator.weekdayOfSecondDate() )
    print('')

    print('Primeiro dia é igual ao segundo?', dateCalculator.firstDateEqualsOfSecondDate())
    print('Primeiro dia vem antes?', dateCalculator.firstDateBeforeOfSecondDate())
    print('Segundo dia vem antes?', dateCalculator.firstDateAfterOfSecondDate())
    print('Dias de diferença entre as datas?', dateCalculator.numDaysBetweenFirstAndSecondDate())
    print('')

    print('executed')

teste()





