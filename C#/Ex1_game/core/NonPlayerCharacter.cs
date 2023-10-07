class NonPlayerCharacter
{
    public string Name { get; set; }
    private NpcDialogPart Dialog { get; set; }
    
    public NonPlayerCharacter(string name, NpcDialogPart dialog)
    {
        this.Name = name;
        this.Dialog = dialog;
    }

    public NpcDialogPart startTalking()
    {
        return Dialog;
    }
}