#include "doors.h"
#include "ui_doors.h"
#include "qdesktopwidget.h"
#include "mainmenu.h"
#include "save.h"
#include "attributes.h"

Doors::Doors(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::Doors)
{
    ui->setupUi(this);
    ui->tabWidget->setStyleSheet("QTabBar::tab { height: 25px; width: 132px; }");
    this->move(QApplication::desktop()->availableGeometry().center() - this->rect().center());
    this->setWindowTitle("Doors");
}

Doors::~Doors()
{
    delete ui;
}

void Doors::on_actionSave_and_Quit_triggered()
{
    MainMenu* mm = new MainMenu();
    mm->show();
    this->close();
}

void Doors::on_actionSave_triggered()
{
    Save* save = new Save();
    save->saveButtonChange();
    save->setPlayer(player);
    save->isNewGame(false);
    save->show();
}

void Doors::on_actionSave_2_triggered()
{
    on_actionSave_triggered();
}

void Doors::on_actionNew_triggered()
{
    Save* save = new Save();
    save->setPlayer(player);
    save->isNewGame(true);
    save->show();
    this->close();
}

void Doors::setPlayer(Player& player) {
    this->player = player;
}

void Doors::on_actionStats_triggered()
{
    Attributes* att = new Attributes();
    att->setPlayer(player);
    att->show();
}
