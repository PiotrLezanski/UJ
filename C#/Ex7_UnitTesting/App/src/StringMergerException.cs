namespace App.src
{
    public class StringMergerException : IStringMerger
    {
        private string? Left { get; set; }
        private string? Right { get; set; }

        public StringMergerException() { }
        public StringMergerException(string left)
        {
            Left = left;
        }

        public StringMergerException(string left, string right)
        {
            Left = left;
            Right = right;
        }

        public string? Merge()
        {
            if (Left == null || Right == null)
                throw new ArgumentNullException("One of the arguments is null");

            return Left + Right;
        }
    }
}

