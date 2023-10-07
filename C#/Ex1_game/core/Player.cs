class Player
{
    public string Name { get; set; }
    public NpcDialogPart Dialog { get; set; }

    public Player(string name, NpcDialogPart dialog)
    {
        this.Name = name;
        this.Dialog = dialog;
    }
}