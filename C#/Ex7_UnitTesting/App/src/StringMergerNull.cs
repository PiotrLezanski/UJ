namespace App.src
{
	public class StringMergerNull : IStringMerger
	{
        private string? Left { get; set; }
        private string? Right { get; set; }

        public StringMergerNull() { }
        public StringMergerNull(string left)
        {
            Left = left;
        }

        public StringMergerNull(string left, string right)
        {
            Left = left;
            Right = right;
        }

        public string? Merge()
        {
            if (Left == null || Right == null)
                return null;

            return Left + Right;
        }
    }
}

