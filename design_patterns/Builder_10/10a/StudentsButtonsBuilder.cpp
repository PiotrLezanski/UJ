#include <iostream>
#include "ButtonsBuilder.cpp"

class StudentsButtonsBuilder : public ButtonsBuilder
{
    void addButtons() override
    {
        std::cout << "Building Students Buttons" <<std::endl;
    }
};
