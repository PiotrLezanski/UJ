interface IDialogPart
{
    string GetDialogText();
}

class NpcDialogPart : IDialogPart
{
    public string DialogText{ get; set; }
    public List<HeroDialogPart> HeroDialogParts{ get; set; }

    public NpcDialogPart(string text, List<HeroDialogPart> heroDialogParts)
    {
        this.DialogText = text;
        this.HeroDialogParts = heroDialogParts;
    }

    public string GetDialogText()
    {
        return DialogText;
    }
}

class HeroDialogPart : IDialogPart
{
    public string DialogText{ get; set; }
    public NpcDialogPart SpeakTo{ get; set; }

    public HeroDialogPart(string text, NpcDialogPart npcDialogPart)
    {
        this.DialogText = text;
        this.SpeakTo = npcDialogPart;
    }

    public string GetDialogText()
    {
        return DialogText;
    }
}