#include <mainmenu.h>
#include <ui_mainmenu.h>
#include <qdesktopwidget.h>
#include <player.h>
#include <statedit.h>

class StateEdit;


MainMenu::MainMenu(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainMenu)
{
    ui->setupUi(this);

    //Moving the window to the center of screen
    this->move(QApplication::desktop()->availableGeometry().center() - this->rect().center());

    ui->newGameButton->setFocus();
}

MainMenu::~MainMenu()
{
    delete ui;
}
void MainMenu::on_newGameButton_clicked()
{
    this->close();
    Player player;
    if(player.checkLevelUp()) {
        StatEdit* levelup = new StatEdit();
        levelup->setPlayer(player);
        levelup->show();
    }
}

void MainMenu::on_exitButton_clicked()
{
    this->close();
}

void MainMenu::on_actionLoad_triggered()
{
    this->on_loadGameButton_clicked();
}

void MainMenu::on_loadGameButton_clicked()
{
    this->on_newGameButton_clicked();
}

void MainMenu::on_actionNew_triggered()
{
    this->on_newGameButton_clicked();
}

void MainMenu::on_actionQuit_triggered()
{
    this->close();
}
