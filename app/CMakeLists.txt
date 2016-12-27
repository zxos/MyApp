cmake_minimum_required(VERSION 3.4.1)

add_library( mycpp-lib
             SHARED
             src/main/cpp/mycpp-lib.cpp )

include_directories(src/main/cpp/include/)