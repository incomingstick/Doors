#-------------------------------------------------
#
# Project created by QtCreator 2015-02-10T10:30:21
#
#-------------------------------------------------

QT       += core gui
CONFIG   += c++11

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = Doors
TEMPLATE = app


SOURCES += main.cpp\
        mainmenu.cpp \
    statedit.cpp \
    player.cpp \
    item.cpp \
    enemy.cpp \
    doors.cpp \
    save.cpp \
    attributes.cpp

HEADERS  += mainmenu.h \
    statedit.h \
    player.h \
    item.h \
    enemy.h \
    doors.h \
    save.h \
    attributes.h

FORMS    += mainmenu.ui \
    statedit.ui \
    doors.ui \
    save.ui \
    attributes.ui
