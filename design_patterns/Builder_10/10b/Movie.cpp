#include "Multimedia.cpp"

class Movie : public Multimedia
{
public:
    Movie(std::string name, std::string type)
            : Multimedia(name, type) {}

    std::unique_ptr<Multimedia> clone() override {
        return std::make_unique<Movie>(*this);
    }

    std::string toString() override {
        return "Movie: " + name + ", " + type;
    }
};