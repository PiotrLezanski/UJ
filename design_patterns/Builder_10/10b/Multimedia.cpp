#pragma once
#include<iostream>

class Multimedia
{
public:
    Multimedia(const std::string& name, const std::string& type)
    {
        this->name = name;
        this->type = type;
    }

    virtual std::unique_ptr<Multimedia> clone() = 0;
    virtual std::string toString() = 0;

    virtual ~Multimedia() = default;

    std::string name;
    std::string type;
};
