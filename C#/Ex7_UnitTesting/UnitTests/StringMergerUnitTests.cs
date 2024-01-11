namespace UnitTests;
using App.src;

public class Tests
{
    [SetUp]
    public void Setup()
    {
    }

    // StringMerger that returns null
    [Test]
    public void CheckValidMerge()
    {
        IStringMerger stringMerger = new StringMergerNull("left string", " right string");
        string? merged = stringMerger.Merge();
        Assert.AreEqual("left string right string", merged);
    }


    [Test]
    public void CheckOneNull()
    {
        IStringMerger stringMerger = new StringMergerNull("left string");
        string? merged = stringMerger.Merge();
        Assert.AreEqual(null, merged);
    }

    [Test]
    public void CheckTwoNulls()
    {
        IStringMerger stringMerger = new StringMergerNull();
        string? merged = stringMerger.Merge();
        Assert.AreEqual(null, merged);
    }


    // StringMerger that throws an exception
    [Test]
    public void CheckValidMerge_Ex()
    {
        IStringMerger stringMerger = new StringMergerException("left string", " right string");
        string? merged = stringMerger.Merge();
        Assert.AreEqual("left string right string", merged);
    }


    [Test]
    public void CheckOneNull_Ex()
    {
        IStringMerger stringMerger = new StringMergerException("left string");
        Assert.Throws<ArgumentNullException>(() => stringMerger.Merge());
    }

    [Test]
    public void CheckTwoNulls_Ex()
    {
        IStringMerger stringMerger = new StringMergerException();
        Assert.Throws<ArgumentNullException>(() => stringMerger.Merge());
    }
}
