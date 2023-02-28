
#include <iostream>
using namespace std;

void bubble_sort(int tab[], int n)
{
    for(int i=0; i<n; i++)
    {
        for(int j=n-1; j>=1; j--)
        {
            if( tab[j-1] > tab[j] )
            {
                int bufor;
                bufor = tab[j-1];
                tab[j-1] = tab[j];
                tab[j] = bufor;
            }
        }
    }
}

bool Element(int x, int tab[])
{
    int i = 0;
    while( tab[i] != -1 )
    {
        if( tab[i] == x ) return true;
        i++;
    }

    return false;
}

void Add(int x, int tab[])
{
    // last index
    int i = 0;
    while( tab[i] != (-1) )
    {
        i++; // last index
    }

    // dodanie elementu 
    if( x >= 1 && x <= 4095 )
    {
        if( !Element(x, tab) )
        {
            tab[i] = x;
            tab[i+1] = -1;  
        }
    }

    // ile elementow 
    int power = 0;
    while( tab[power] != (-1) )
    {
        power++;
    }
    
    // sortowanie
    bubble_sort(tab, power);
}  

void Union(int tab1[], int tab2[], int suma[])
{
    int i_tab1=0;
    int i_tab2=0;
    int i_suma=0;

    // przepisanie 1. zbioru
    while( tab1[i_tab1] != (-1) )
    {
        suma[i_suma] = tab1[i_tab1];

        i_suma++; //kończy z indeksem wartości -1 
        i_tab1++; //kończy z indeksem wartości -1
    }
    
    // dodanie elementow z 2. zbioru
    while( tab2[i_tab2] != -1 )
    {
        bool is = false;
        i_tab1=0;
        while( tab1[i_tab1] != -1 )
        {
            if( tab1[i_tab1] == tab2[i_tab2]) 
            {
                is = true;
            }
            i_tab1++; //kończy z indeksem wartości -1
        }
        if( !is )
        {
            suma[i_suma] = tab2[i_tab2];
            i_suma++;
        }
        i_tab2++;
    }

    // sortowanie
    bubble_sort(suma, i_suma);

    // dodanie wartości -1
    suma[i_suma] = -1;
}

bool Empty(int tab[])
{
    if( tab[0] == -1 ) return true;
    else return false;
}

bool Nonempty(int tab[])
{
    if( !Empty(tab) ) return true;
    else return false;
}

void Intersection(int tab1[], int tab2[], int wsp[])
{
    int i_tab1=0;
    int i_tab2=0;
    int i_wsp=0;
    
    // dodanie elementow z 2. zbioru
    while( tab1[i_tab1] != -1 )
    {
        bool is = false;
        i_tab2=0;
        while( tab2[i_tab2] != -1 )
        {
            if( tab1[i_tab1] == tab2[i_tab2]) 
            {
                is = true;
            }
            i_tab2++; //kończy z indeksem wartości -1
        }
        if( is )
        {
            wsp[i_wsp] = tab1[i_tab1];
            i_wsp++;
        }
        i_tab1++;
    }

    // sortowanie
    bubble_sort(wsp, i_wsp);

    // dodanie wartości -1
    wsp[i_wsp] = -1;
}

void Complement(int tab1[], int tab2[])
{   
    int i_tab = 0;
    for(int i=1; i<=4095; i++)
    {
        if( !Element(i, tab1) )
        {
            tab2[i_tab] = i;
            i_tab++;
        }
    }

    bubble_sort(tab2, i_tab);
    // dodanie -1 na koncu
    tab2[i_tab] = -1;
}

bool Equal(int tab1[], int tab2[])
{
    int i_tab1=0;
    int i_tab2=0;
    // ile elementow ma tab1
    int power_1 = 0;
    while( tab1[i_tab1] != (-1) )
    {
        power_1++;
        i_tab1++;
    }
    // ile elementow ma tab2
    int power_2 = 0;
    while( tab2[i_tab2] != (-1) )
    {
        power_2++;
        i_tab2++;
    }

    // porownanie
    bool the_same = true;
    if( Empty(tab1) && Empty(tab2) ) the_same = true;
    else if( power_1 != power_2 ) the_same = false;
    else // power_1 == power_2 != 0
    {
        for(int i=0; i<power_1; i++)
        {
            if( !Element(tab1[i], tab2) ) the_same = false;           
        }
    }

    return the_same;
}

bool Subset(int tab1[], int tab2[])
{
    // tab1 - zawierany, tab2 - zawierający
    int i_tab1 = 0;
    int i_tab2 = 0;

    // jezeli pusta
    if( Empty(tab2) && Nonempty(tab1) ) return false;
    else if( Empty(tab2) && Empty(tab1) ) return true;
    else if ( Empty(tab1) && Nonempty(tab2) ) return true;
    
    else //oba niepuste
    {
        
        while( tab1[i_tab1] != -1 )
        {
            bool zawiera = false;
            i_tab2 = 0;
            while( tab2[i_tab2] != -1 )
            {
                if( tab1[i_tab1] == tab2[i_tab2] )
                {
                    zawiera = true;
                }
                i_tab2++;
            }
            
            if( zawiera == false ) return false;

            i_tab1++;
        }

        return true;
    }
}

double Arithmetic(int tab[])
{
    // power zbioru
    int power = 0;
    int i = 0;
    while( tab[i] != (-1) )
    {
        power++;
        i++;
    }

    i = 0;
    double sum = 0;
    // jezeli pusty
    if( power == 0 ) return 0;
    else
    {
         while( tab[i] != (-1) )
         {
             sum += tab[i];
             i++;
         }
        return sum/power;
    }

}

double Harmonic(int tab[])
{
    // power zbioru
    int power = 0;
    int i = 0;
    while( tab[i] != (-1) )
    {
        power++;
        i++;
    }

    i = 0;
    double mian = 0;
    // jezeli pusty
    if( power == 0 ) return 1;

    while( tab[i] != (-1) )
    {
        mian += (1/(double)tab[i]);
        i++;
    }
    return power/mian;
}

void MinMax(int tab[], int *min, int &max)
{
    // power zbioru
    int power = 0;
    int i = 0;
    while( tab[i] != (-1) )
    {
        power++;
        i++;
    }

    // a co jak jest jeden element? nie przyjmuje czy moze min = max

    if( power != 0 )
    {
        if( power == 1 )
        {
            *min = tab[0];
            max = tab[0];
        }
        else
        {
            *min = tab[0];
            max = tab[0];
            // min
            int i=0;
            while( tab[i] != (-1) )
            {
                if( tab[i] < *min) *min = tab[i];
                i++;
            }

            // max
            i = 0;
            while( tab[i] != (-1) )
            {
                if( tab[i] > max) max = tab[i];
                i++;
            }
        }     
    }
    
}

void Create(int n, int tab[], int result[])
{
    result[0] = -1;
    for(int i=0; i<n; i++)
        Add(tab[i], result);

    // power
    int power = 0;
    int j = 0;
    while( result[j] != (-1) )
    {
        power++;
        j++; // last index
    }

    // sortowanie
    bubble_sort(result, power);
}

void Cardinality(int tab[], int *power)
{
    *power = 0;
    int i = 0;
    while( tab[i] != (-1) )
    {
        *power += 1;
        i++;
    }
}

void Difference(int tab1[], int tab2[], int result[])
{
    int i_tab1 = 0;
    int i_tab2 = 0;
    int i_result = 0;

    while( tab1[i_tab1] != -1 )
    {
        bool is = false;
        i_tab2 = 0;
        while( tab2[i_tab2] != -1 )
        { 
            if( tab1[i_tab1] == tab2[i_tab2] )
            {
                is = true;
            }
            i_tab2++;
        }

        if( !is )
        {
            result[i_result] = tab1[i_tab1];
            i_result++;
        }

        i_tab1++;
    }
    bubble_sort(result, i_result);

    result[i_result] = -1;
}

void Properties(int tab[], char action[], double &arithmentic, double *harmonic, int &min, int *max, int &power)
{
    int index = 0;
    while( action[index] != 0 )
    {
        if( action[index] == 'a' )
        {
            arithmentic = Arithmetic(tab);
        }
        else if( action[index] == 'h' )
        {
            *harmonic = Harmonic(tab);
        }
        else if( action[index] == 'm' )
        {
            MinMax(tab, &min, *max);
        }
        else if( action[index] == 'c' )
        {
            Cardinality(tab, &power);
        }

        index++;
    }
}

void Symmetric(int tab1[], int tab2[], int result[])
{
    // result = A/B
    Difference(tab1,tab2,result);

    // power result
    int i = 0;
    int power_r = 0;
    while( result[i] != (-1) )
    {
        power_r++;
        i++; // last index
    }

    // dodanie B/A
    int i_tab1 = 0;
    int i_tab2 = 0;
    int i_result = power_r;

    while( tab2[i_tab2] != -1 )
    {
        bool is = false;
        i_tab1 = 0;
        while( tab1[i_tab1] != -1 )
        { 
            if( tab2[i_tab2] == tab1[i_tab1] )
            {
                is = true;
            }
            i_tab1++;
        }

        if( !is )
        {
            result[i_result] = tab2[i_tab2];
            i_result++;
        }

        i_tab2++;
    }
    
    bubble_sort(result, i_result);

    result[i_result] = -1;
}





