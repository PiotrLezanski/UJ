using System.ComponentModel;
using System.Numerics;
using System.Runtime.CompilerServices;

class Complex<T> : IComparable, IFormattable where T : IComparable, IFormattable
{
    T Real {get; set;}
    T Imaginary {get; set;}

    public Complex()
    {
        this.Real = default;
        this.Imaginary = default;
    }

    public Complex(T real, T imaginary)
    {
        this.Real = real;
        this.Imaginary = imaginary;
    }

    public static Complex<T> operator-(Complex<T> obj)
    {
        return new Complex<T>(-(dynamic)obj.Real, -(dynamic)obj.Imaginary);
    }

    public static Complex<T> operator+(Complex<T> obj1, Complex<T> obj2)
    {
        return new Complex<T>((dynamic)obj1.Real + (dynamic)obj2.Real,
                                (dynamic)obj1.Imaginary + (dynamic)obj2.Imaginary);
    }

    public static Complex<T> operator-(Complex<T> obj1, Complex<T> obj2)
    {
        return new Complex<T>((dynamic)obj1.Real + (dynamic)obj2.Real,
                                (dynamic)obj1.Imaginary + (dynamic)obj2.Imaginary);
    }

    public static Complex<T> operator*(Complex<T> obj1, Complex<T> obj2)
    {
        dynamic newReal = (dynamic)obj1.Real * obj2.Real - (dynamic)obj1.Imaginary * obj2.Imaginary;
        dynamic newImaginary = (dynamic)obj1.Real * obj2.Imaginary + (dynamic)obj1.Imaginary * obj2.Real;
        return new Complex<T>(newReal, newImaginary);
    }

    public int CompareTo(Object? obj)
    {
        dynamic ob = obj;
        if(ob is Complex && this.Real == ob.Real && this.Imaginary == ob.Imaginary)
        {
            return 0;
        }
        return 1;
    }

    public string ToString(string? format, IFormatProvider? formatProvider)
    {
        dynamic Re = Real;
        dynamic Im = Imaginary;

        string result = "";

        if(Im == 0)
            return Re.ToString();

        if(Re != 0 && Im < 0)
        {
            result = $"{Re} - {Im}";
        }
        else if(Re == 0)
        {
            result = Im.ToString();
        }
        else 
        {
            result = $"{Re} + {Im}";
        }

        return result;
    }
}