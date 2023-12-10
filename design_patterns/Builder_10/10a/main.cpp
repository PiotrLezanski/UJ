#include <iostream>

#include "FormBuilder.cpp"

#include "StudentsButtonsBuilder.cpp"
#include "AdminButtonsBuilder.cpp"
#include "StudentsWMBuilder.cpp"

#include "Form.cpp"

int main()
{
    FormBuilder* fBuilder = new FormBuilder();
    StudentsButtonsBuilder* bBuilder = new StudentsButtonsBuilder();
    StudentsWMBuilder* wmBuilder= new StudentsWMBuilder();

    fBuilder->setButtonsBuilder(bBuilder);
    fBuilder->setWMBuilder(wmBuilder);
    fBuilder->constructForm();

    Form form = fBuilder->getForm();
    form.viewForm();
}