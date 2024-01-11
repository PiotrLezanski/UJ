#include <iostream>
#include "ButtonsBuilder.cpp"

class StudentsButtonBuilder : public ButtonsBuilder
{
    void addButtons() override
    {
        std::cout << "Building Admins Buttons" <<std::endl;
    }
};