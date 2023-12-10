cmake_minimum_required(VERSION 3.26)
project(Builder_10)

set(CMAKE_CXX_STANDARD 17)

#file(GLOB SOURCES *.cpp)
#add_executable(MyExecutable ${SOURCES})

add_executable(Builder_10 10a/main.cpp
        10a/FormBuilder.cpp
        10a/ButtonsBuilder.cpp
        10a/StudentsButtonsBuilder.cpp
        10a/AdminButtonsBuilder.cpp
        10a/WelcomeMessageBuilder.cpp
        10a/StudentsWMBuilder.cpp
        10a/Form.cpp)

#add_executable(Builder_10 10b/main.cpp
#        10b/Multimedia.cpp
#        10b/Picture.cpp
#        10b/Music.cpp
#        10b/Movie.cpp)
