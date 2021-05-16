#include <stdio.h>

void calculateAge( int days ) {

   int daysInYear = 365;
   int daysInMonth = 30;

   int years = days/daysInYear;
   int months = ( days - ( years * daysInYear) ) / daysInMonth;
   int _days = days - ( years * daysInYear) - ( months * daysInMonth );

   printf("Você possui!\n");
   printf("%d anos!\n", years );
   printf("%d meses!\n", months );
   printf("%d dias!\n", _days );

}

int main() {

   calculateAge( 450 );

   return 0;
}