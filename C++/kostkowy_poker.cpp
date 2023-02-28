//Piotr Lezanski
#include <iostream>
using namespace std;

int main()
{
    int n, i=0;
    cin>> n;

    string p1, p2;
    int x1,x2,x3,x4,y1,y2,y3,y4;

    int w_a=0, w_b=0, w_c=0, w_d=0, w_e=0;
    int r_a=0, r_b=0, r_c=0, r_d=0, r_e=0; 
    int p_a=0, p_b=0, p_c=0, p_d=0, p_e=0;

    int sum1, sum2;
    int max1, max2;
    int lv1, lv2;

    int a_i=0, b_i=0, c_i=0, d_i=0, e_i=0;

    while(i<n)
    {
        cin>>p1>>x1>>x2>>x3>>x4>>p2>>y1>>y2>>y3>>y4;

    // !!!  MAIN BODY !!! 

        bool found1 = false;

        bool win_l = false;
        bool draw_l = false;
        bool lose_l = false;

        bool win_r = false;
        bool draw_r = false;
        bool lose_r = false;

    // FIRST NUMBER
        // 4 the same
        if(x1==x2 && x1==x3 && x3==x4 && !found1)
        {
            lv1 = 1;
            sum1 = 4*x1;
            max1 = x1;
            found1 = true;
        }

        // all different
        if(x1!=x2 && x1!=x3 && x1!=x4 && x3 != x4 && x2 != x3 && x2 != x4 && !found1)
        {
            lv1 = 2;
            sum1 = x1+x2+x3+x4;
            found1 = true;
        }

        // 2 pairs
        if(x1 == x2 && x3==x4 && !found1)
        {
            lv1 = 3;
            if(x1 > x3) max1 = x1;
            if(x1 < x3) max1 = x3;
            sum1 = 2*x1 + 2*x3;
            found1 = true;
        }

        if(x1 == x3 && x2==x4 && !found1)
        {
            lv1 = 3;
            if(x1 > x2) max1 = x1;
            if(x1 < x2) max1 = x2;
            sum1 = 2*x1 + 2*x2;
            found1 = true;
        }

        if(x1 == x4 && x2==x3 && !found1)
        {
            lv1 = 3;
            if(x1 > x2) max1 = x1;
            if(x1 < x2) max1 = x2;
            sum1 = 2*x1 + 2*x2;
            found1 = true;
        }

        // three
        if(x1 == x2 && x1 == x3 && x1 != x4 && !found1)
        {
            lv1 = 4;
            sum1 = 3*x1+x4;
            max1 = x1;
            found1 = true;
        }

        if(x2 == x3 && x2 == x4 && x2 != x1 && !found1)
        {
            lv1 = 4;
            sum1 = 3*x2+x1;
            max1 = x2;
            found1 = true;
        }

        if(x1 == x2 && x1 == x4 && x1 != x3 && !found1)
        {
            lv1 = 4;
            sum1 = 3*x1+x3;
            max1 = x1;
            found1 = true;
        }
        
        if(x1 == x3 && x1 == x4 && x1 != x2 && !found1)
        {
            lv1 = 4;
            sum1 = 3*x1+x2;
            max1 = x1;
            found1 = true;
        }

        // one pair
        if(x1 == x2 && x1 != x3 && x1 != x4 && x3 != x4 && !found1)
        {
            lv1 = 5;
            sum1 = 2*x1+x3+x4;
            max1 = x1;
            found1 = true;
        }

        if(x1 == x3 && x1 != x2 && x1 != x4 && x2 != x4 && !found1)
        {
            lv1 = 5;
            sum1 = 2*x1+x2+x4;
            max1 = x1;
            found1 = true;
        }

        if(x1 == x4 && x1 != x2 && x1 != x3 && x2 != x3 && !found1)
        {
            lv1 = 5;
            sum1 = 2*x1+x2+x3;
            max1 = x1;
            found1 = true;
        }

        if(x2 == x3 && x2 != x1 && x2 != x4 && x1 != x4 && !found1)
        {
            lv1 = 5;
            sum1 = 2*x2+x1+x4;
            max1 = x2;
            found1 = true;
        }

        if(x2 == x4 && x2 != x1 && x2 != x3 && x1 != x3 && !found1)
        {
            lv1 = 5;
            sum1 = 2*x2+x1+x3;
            max1 = x2;
            found1 = true;
        }

        if(x3 == x4 && x3 != x1 && x3 != x2 && x1 != x2 && !found1)
        {
            lv1 = 5;
            sum1 = 2*x3+x1+x2;
            max1 = x3;
            found1 = true;
        }

    // SECOND NUMBER
        bool found2 = false;

        // 4 the same
        if(y1==y2 && y1==y3 && y3==y4 && !found2)
        {
            lv2 = 1;
            sum2 = 4*y1;
            max2 = y1;
            found2 = true;
        }

        // all different
        if(y1 != y2 && y1 != y3 && y1 != y4 && y3 != y4 && y2 != y3 && y2 != y4 && !found2)
        {
            lv2 = 2;
            sum2 = y1+y2+y3+y4;
            found2 = true;
        }

        // 2 pairs
        if(y1 == y2 && y3==y4 && !found2)
        {
            lv2 = 3;
            if(y1 > y3) max2 = y1;
            if(y1 < y3) max2 = y3;
            sum2 = 2*y1 + 2*y3;
            found2 = true;
        }

        if(y1 == y3 && y2==y4 && !found2)
        {
            lv2 = 3;
            if(y1 > y2) max2 = y1;
            if(y1 < y2) max2 = y2;
            sum2 = 2*y1 + 2*y2;
            found2 = true;
        }

        if(y1 == y4 && y2==y3 && !found2)
        {
            lv2 = 3;
            if(y1 > y2) max2 = y1;
            if(y1 < y2) max2 = y2;
            sum2 = 2*y1 + 2*y2;
            found2 = true;
        }

        // three
        if(y1 == y2 && y1 == y3 && y1 != y4 && !found2)
        {
            lv2 = 4;
            sum2 = 3*y1+y4;
            max2 = y1;
            found2 = true;
        }

        if(y2 == y3 && y2 == y4 && y2 != y1 && !found2)
        {
            lv2 = 4;
            sum2 = 3*y2+y1;
            max2 = y2;
            found2 = true;
        }

        if(y1 == y2 && y1 == y4 && y1 != y3 && !found2)
        {
            lv2 = 4;
            sum2 = 3*y1+y3;
            max2 = y1;
            found2 = true;
        }
        
        if(y1 == y3 && y1 == y4 && y1 != y2 && !found2)
        {
            lv2 = 4;
            sum2 = 3*y1+y2;
            max2 = y1;
            found2 = true;
        }

        // one pair
        if(y1 == y2 && y1 != y3 && y1 != y4 && y3 != y4 && !found2)
        {
            lv2 = 5;
            sum2 = 2*y1+y3+y4;
            max2 = y1;
            found2 = true;
        }

        if(y1 == y3 && y1 != y2 && y1 != y4 && y2 != y4 && !found2)
        {
            lv2 = 5;
            sum2 = 2*y1+y2+y4;
            max2 = y1;
            found2 = true;
        }

        if(y1 == y4 && y1 != y2 && y1 != y3 && y2 != y3 && !found2)
        {
            lv2 = 5;
            sum2 = 2*y1+y2+y3;
            max2 = y1;
            found2 = true;
        }

        if(y2 == y3 && y2 != y1 && y2 != y4 && y1 != y4 && !found2)
        {
            lv2 = 5;
            sum2 = 2*y2+y1+y4;
            max2 = y2;
            found2 = true;
        }

        if(y2 == y4 && y2 != y1 && y2 != y3 && y1 != y3 && !found2)
        {
            lv2 = 5;
            sum2 = 2*y2+y1+y3;
            max2 = y2;
            found2 = true;
        }

        if(y3 == y4 && y3 != y1 && y3 != y2 && y1 != y2 && !found2)
        {
            lv2 = 5;
            sum2 = 2*y3+y1+y2;
            max2 = y3;
            found2 = true;
        }

        // CHECKING 
        
        if(lv1 > lv2)
        {
            lose_l = true;
            win_r = true;
        }

        if(lv1 < lv2)
        {
            win_l = true;
            lose_r = true;
        }

        if(lv1 == lv2)
        {
            // lv 1, 4, 5
            if(lv1 == 1 || lv1 == 4 || lv1 == 5)
            {
                if(max1 > max2)
                {
                    win_l = true;
                    lose_r = true;
                }
                else if(max1 < max2)
                {
                    lose_l = true;
                    win_r = true;
                }
                else
                {
                    if(sum1 > sum2)
                    {
                        win_l = true;
                        lose_r = true;
                    }
                    else if(sum1 < sum2)
                    {
                        lose_l = true;
                        win_r = true;
                    }
                    else
                    {
                        draw_l = true;
                        draw_r = true;
                    }
                }
            }

            // lv 2
            if(lv1 == 2)
            {
                if(sum1 > sum2)
                {
                    win_l = true;
                    lose_r = true;
                }
                else if(sum1 < sum2)
                {
                    lose_l = true;
                    win_r = true;
                }
                else
                {
                    draw_l = true;
                    draw_r = true;
                }
            }

            // lv 3
            if(lv1 == 3)
            {
                if(max1 > max2)
                {
                    win_l = true;
                    lose_r = true;
                }
                else if(max1 > max2)
                {
                    lose_l = true;
                    win_r = true;
                }
                else 
                {  
                    if(sum1 > sum2)
                    {
                        win_l = true;
                        lose_r = true;
                    }
                    else if(sum1 < sum2)
                    {
                        lose_l = true;
                        win_r = true;
                    }
                    else
                    {
                        draw_l = true;
                        draw_r = true;
                    }
                }
            }
        }

    // LETTERS
        // a
        if(p1 == "a")
        {
            a_i++;
            if(win_l) w_a++;
            if(draw_l) r_a++;
            if(lose_l) p_a++;
        }
        if(p2 == "a")
        {
            a_i++;
            if(win_r) w_a++;
            if(draw_r) r_a++;
            if(lose_r) p_a++;
        }

        // b
        if(p1 == "b")
        {
            b_i++;
            if(win_l) w_b++;
            if(draw_l) r_b++;
            if(lose_l) p_b++;
        }
        if(p2 == "b")
        {
            b_i++;
            if(win_r) w_b++;
            if(draw_r) r_b++;
            if(lose_r) p_b++;
        }

        // c
        if(p1 == "c")
        {
            c_i++;
            if(win_l) w_c++;
            if(draw_l) r_c++;
            if(lose_l) p_c++;
        }
        if(p2 == "c")
        {
            c_i++;
            if(win_r) w_c++;
            if(draw_r) r_c++;
            if(lose_r) p_c++;
        }

        // d
        if(p1 == "d")
        {
            d_i++;
            if(win_l) w_d++;
            if(draw_l) r_d++;
            if(lose_l) p_d++;
        }
        if(p2 == "d")
        {
            d_i++;
            if(win_r) w_d++;
            if(draw_r) r_d++;
            if(lose_r) p_d++;
        }

        // e
        if(p1 == "e")
        {
            e_i++;
            if(win_l) w_e++;
            if(draw_l) r_e++;
            if(lose_l) p_e++;
        }
        if(p2 == "e")
        {
            e_i++;
            if(win_r) w_e++;
            if(draw_r) r_e++;
            if(lose_r) p_e++;
        }

        i++;
    }

    // FINAL OUTPUT
    if(a_i != 0)
    {
        cout<< "gracz a"<<endl;
        if(w_a != 0)
            cout<< "    wygrane: "<< ((float)w_a/a_i)*100<<"%"<<endl;
        if(r_a != 0)
            cout<< "    remisy: "<< ((float)r_a/a_i)*100<<"%"<<endl;
        if(p_a != 0)
            cout<< "    przegrane: "<< ((float)p_a/a_i)*100<<"%"<<endl;
        cout<<endl;
    }
    
    if(b_i != 0)
    {
        cout<< "gracz b"<<endl;
        if(w_b != 0)
            cout<< "    wygrane: "<< ((float)w_b/b_i)*100<<"%"<<endl;
        if(r_b != 0)
            cout<< "    remisy: "<< ((float)r_b/b_i)*100<<"%"<<endl;
        if(p_b != 0)
            cout<< "    przegrane: "<< ((float)p_b/b_i)*100<<"%"<<endl;
        cout<<endl;
    }
   
    if(c_i != 0)
    {
        cout<< "gracz c"<<endl;
        if(w_c != 0)
            cout<< "    wygrane: "<< ((float)w_c/c_i)*100<<"%"<<endl;
        if(r_c != 0)
            cout<< "    remisy: "<< ((float)r_c/c_i)*100<<"%"<<endl;
        if(p_c != 0)
            cout<< "    przegrane: "<< ((float)p_c/c_i)*100<<"%"<<endl;
        cout<<endl;
    }
   
    if(d_i != 0)
    {
        cout<< "gracz d"<<endl;
        if(w_d != 0)
            cout<< "    wygrane: "<< ((float)w_d/d_i)*100<<"%"<<endl;
        if(r_d != 0)
            cout<< "    remisy: "<< ((float)r_d/d_i)*100<<"%"<<endl;
        if(p_d != 0)
            cout<< "    przegrane: "<< ((float)p_d/d_i)*100<<"%"<<endl;
        cout<<endl;
    }
    
    if(e_i != 0)
    {
        cout<< "gracz e"<<endl;
        if(w_e != 0)
            cout<< "    wygrane: "<< ((float)w_e/e_i)*100<<"%"<<endl;
        if(r_e != 0)
            cout<< "    remisy: "<< ((float)r_e/e_i)*100<<"%"<<endl;
        if(p_e != 0)
            cout<< "    przegrane: "<< ((float)p_e/e_i)*100<<"%"<<endl;
    }

    return 0;
}



