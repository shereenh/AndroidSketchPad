cmake_minimum_required(VERSION 3.6.0)

add_library(native-lib SHARED
        native-lib.cpp)

find_library(log-lib
        log )

# Include libraries needed for c-connector lib
target_link_libraries(native-lib
                      ${log-lib}
                      android
                      log)
