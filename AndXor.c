#include<stdio.h>
#include<stdlib.h>
#include<string.h>

void main ()
{
  char str[] = "Hello World";
  char str3[11];
  strcpy(str3, str);
  puts(str3);
  printf("\n");
  char str1[11];
  char str2[11];
  /*strcpy(str2, str);
  puts(str2); */
  
  int i, len;
  len = strlen(str);
  for (i = 0; i < len; i++)
    {
      str1[i] = str[i] & 127;
      //printf ("%c", str1[i]);
    }
    puts(str1);
  printf ("\n");
  for (i = 0; i < len; i++)
    {
      str2[i] = str3[i] ^ 127;
      //printf ("%c\n", str2[i]);
    }
    puts(str2);
    printf ("\n");
}

