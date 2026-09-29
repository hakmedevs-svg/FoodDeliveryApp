# CMakeLists.txt
cmake_minimum_required(VERSION 3.22)
project(fooddelivery_native)

add_library(
    fooddelivery_native
    SHARED
    native-lib.cpp
)

target_compile_features(fooddelivery_native PRIVATE cxx_std_17)
