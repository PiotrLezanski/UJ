#include <iostream>
#include <vector>

#include "Picture.cpp"
#include "Music.cpp"
#include "Movie.cpp"

int main()
{
    std::vector<Multimedia*> my_multimedia;
    my_multimedia.push_back(new Picture("Pic1", "Wallpaper"));
    my_multimedia.push_back(new Music("Mus1", "Bass"));
    my_multimedia.push_back(new Movie("Mov1", "Thriller"));

    std::vector<std::unique_ptr<Multimedia>> multimedia_copy(my_multimedia.size());
    for(auto& it : my_multimedia)
    {
        multimedia_copy.push_back(it->clone());
    }

    for(auto& it : my_multimedia)
    {
        std::cout<< it->toString() <<std::endl;
    }
    return 0;
}
