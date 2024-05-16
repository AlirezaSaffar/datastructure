#include <cmath>
#include <string>
#include <iostream>
#include "data.h"
#include "../src/UnboundedInteger.h"
#include "../src/ISort.h"
#include "../src/RadixSort.h"

using namespace std;

void testCalculateD();
void testGetDigit();
void testCountingSort();
void testSort();
Data<UnboundedInteger> *getData1();
Data<UnboundedInteger> *getData2();
void printData1(Data<UnboundedInteger> *data, int n);
void printData2(Data<UnboundedInteger> *data, int n);

int main()
{
   cout << "=============================================" << endl;
   cout << "Test 1. calculateD" << endl;
   cout << "=============================================" << endl;
   testCalculateD();
   cout << "\n\n";
   cout << "=============================================" << endl;
   cout << "Test 2. getDigit" << endl;
   cout << "=============================================" << endl;
   testGetDigit();
   cout << "\n";
   cout << "=============================================" << endl;
   cout << "Test 3. CountingSort" << endl;
   cout << "=============================================" << endl;
   testCountingSort();
   cout << "\n\n";
   cout << "=============================================" << endl;
   cout << "Test 4. Sort" << endl;
   cout << "=============================================" << endl;
   testSort();

   return 0;
}

void testCalculateD()
{
   RadixSort<Data<UnboundedInteger>> *radixSort = new RadixSort<Data<UnboundedInteger>>();
   int n = 10;
   int maxR = 4;
   Data<UnboundedInteger> *data = getData1();

   cout << "\nInitial array:\n";
   printData1(data, n);

   int correctRes[] = {25, 13, 9, 7};
   int studentRes[maxR];

   cout << "\nCalculated maxDigit for 1 <= r <= 4:\n";
   for (int i = 0; i < maxR; i++)
   {
      int res = radixSort->calculateD(data, n, i + 1);
      studentRes[i] = res;
      cout << res << " ,";
   }

   cout << "\n\nExpected results:\n";
   for (int i = 0; i < maxR; i++)
   {
      cout << correctRes[i] << " ,";
   }

   for (int i = 0; i < maxR; i++)
   {
      if (studentRes[i] != correctRes[i])
      {
         cout << endl;
         throw runtime_error("Incorrect calculateD function.");
      }
   }

   delete[] data;
}

void testGetDigit()
{
   Data<UnboundedInteger> *data = getData1();
   int correctRes[3][10] = {
       {902, 77136, 86380, 80238, 86929, 22, 7, 24079, 82974, 86380},
       {0, 0, 0, 9851, 0, 0, 0, 69740, 99618, 0},
       {0, 0, 0, 0, 0, 0, 0, 69125, 0, 0}};
   int neededDigit[] = {1, 3, 5};
   int studentRes[3][10];
   int resNumbr = 0;
   int r = 5;
   int n = 10;

   cout << "\nInitial array:\n";
   printData1(data, n);

   for (int j : neededDigit)
   {
      cout << "\nCalculated digit for j = " << j << ":\n";
      for (int i = 0; i < n; i++)
      {
         int digit = data[i].getKey().getDigit(r, j);
         studentRes[resNumbr][i] = digit;
         cout << digit << " ,";
      }

      cout << "\nExpected digit for j = " << j << ":\n";
      for (int i = 0; i < n; i++)
      {
         cout << correctRes[resNumbr][i] << " ,";
      }
      cout << "\n";
      resNumbr++;
   }

   for (int i = 0; i < resNumbr; i++)
   {
      for (int j = 0; j < n; j++)
      {
         if (studentRes[i][j] != correctRes[i][j])
         {
            cout << "\n";
            throw runtime_error("Incorrect getDigit function.");
         }
      }
   }

   delete[] data;
}

void testCountingSort()
{
   Data<UnboundedInteger> *data = getData2();
   RadixSort<Data<UnboundedInteger>> *radixSort = new RadixSort<Data<UnboundedInteger>>();
   int n = 10;
   string expectedKeys[] = {"2", "7", "2", "2", "10", "12", "10", "30", "30", "44"};
   int expectedValues[] = {14, 16, 18, 19, 10, 11, 17, 12, 13, 15};

   cout << "\nInitial array:\n";
   printData2(data, n);
   cout << endl;

   int r = 1;
   int k = 100;
   int d = 2;

   radixSort->countingSort(data, n, k, r, d);

   cout << "Sorted array:\n";
   printData2(data, n);

   cout << "\nExpected results:\n";
   for (int i = 0; i < 10; i++)
   {
      cout << "(" << expectedKeys[i] << " , " << expectedValues[i] << "), ";
   }
   cout << endl;

   for (int i = 0; i < 10; i++)
   {
      if (expectedKeys[i] != data[i].getKey().getVal())
      {
         cout << endl;
         throw runtime_error("Incorrect sort.");
      }
   }
   delete[] data;
}

void testSort()
{
   int n = 10;
   Data<UnboundedInteger> *data = getData1();
   string expectedKeys[10] = {"7", "22", "902", "86380", "86380",
                              "86929", "93677136", "22749996187679482974",
                              "91828098514531380238", "6912507057697400202524079"};
   int expectedValues[10] = {87, 92, 98, 79, 83, 33, 63, 62, 92, 66};
   RadixSort<Data<UnboundedInteger>> *radixSort = new RadixSort<Data<UnboundedInteger>>();

   cout << "Initial array:\n";
   printData1(data, n);

   radixSort->sort(data, n);

   cout << "\nSorted array:\n";
   printData1(data, n);

   cout << "\nExpected results:\n";
   for (int i = 0; i < n; i++)
      cout << "(" << expectedKeys[i] << " , " << expectedValues[i] << "),\n";

   for (int i = 0; i < n; i++)
   {
      string key = data[i].getKey().getVal();
      if (expectedKeys[i] != key)
      {
         cout << endl;
         throw runtime_error("Incorrect sort.");
      }
   }

   for (int i = 0; i < n; i++)
   {
      if (expectedValues[i] != data[i].getValue())
      {
         cout << endl;
         throw runtime_error("Unstable sort.");
      }
   }

   delete[] data;
}

Data<UnboundedInteger> *getData1()
{
   int n = 10;
   Data<UnboundedInteger> *data = new Data<UnboundedInteger>[n];
   data[0] = Data<UnboundedInteger>(UnboundedInteger("902"), 98);
   data[1] = Data<UnboundedInteger>(UnboundedInteger("93677136"), 63);
   data[2] = Data<UnboundedInteger>(UnboundedInteger("86380"), 79);
   data[3] = Data<UnboundedInteger>(UnboundedInteger("91828098514531380238"), 92);
   data[4] = Data<UnboundedInteger>(UnboundedInteger("86929"), 33);
   data[5] = Data<UnboundedInteger>(UnboundedInteger("22"), 92);
   data[6] = Data<UnboundedInteger>(UnboundedInteger("7"), 87);
   data[7] = Data<UnboundedInteger>(UnboundedInteger("6912507057697400202524079"), 66);
   data[8] = Data<UnboundedInteger>(UnboundedInteger("22749996187679482974"), 62);
   data[9] = Data<UnboundedInteger>(UnboundedInteger("86380"), 83);
   return data;
}

Data<UnboundedInteger> *getData2()
{
   int n = 10;
   Data<UnboundedInteger> *data = new Data<UnboundedInteger>[n];
   data[0] = Data<UnboundedInteger>(UnboundedInteger("10"), 10);
   data[1] = Data<UnboundedInteger>(UnboundedInteger("12"), 11);
   data[2] = Data<UnboundedInteger>(UnboundedInteger("30"), 12);
   data[3] = Data<UnboundedInteger>(UnboundedInteger("30"), 13);
   data[4] = Data<UnboundedInteger>(UnboundedInteger("2"), 14);
   data[5] = Data<UnboundedInteger>(UnboundedInteger("44"), 15);
   data[6] = Data<UnboundedInteger>(UnboundedInteger("7"), 16);
   data[7] = Data<UnboundedInteger>(UnboundedInteger("10"), 17);
   data[8] = Data<UnboundedInteger>(UnboundedInteger("2"), 18);
   data[9] = Data<UnboundedInteger>(UnboundedInteger("2"), 19);
   return data;
}

void printData1(Data<UnboundedInteger> *data, int n)
{
   for (int i = 0; i < n; i++)
   {
      cout << "(" << data[i].getKey().getVal() << " , " << data[i].getValue() << "),\n";
   }
}

void printData2(Data<UnboundedInteger> *data, int n)
{
   for (int i = 0; i < n; i++)
   {
      cout << "(" << data[i].getKey().getVal() << " , " << data[i].getValue() << "), ";
   }
}