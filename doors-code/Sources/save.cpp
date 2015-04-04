#include "save.h"
#include "ui_save.h"
#include "qdesktopwidget.h"
#include "player.h"
#include "doors.h"

Save::Save(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Save)
{
    ui->setupUi(this);
    this->move(QApplication::desktop()->availableGeometry().center() - this->rect().center());
    this->setWindowTitle("Save");
}

Save::~Save()
{
    delete ui;
}
void Save::on_saveButton_clicked()
{
    // Does save stuff

    if(newGame) {
        // Creates new game
        Player* newPlayer = new Player();
        this->close();
    }
}

void Save::on_quitButton_clicked()
{
    if(newGame) {
        Player* newPlayer = new Player();
        this->close();
    }
}

void Save::on_backButton_clicked()
{
    // Does nevermind stuff
    if(newGame) {
        Doors* doors = new Doors();
        doors->setPlayer(player);
        doors->show();
    }
    this->close();
}

void Save::saveButtonChange() {
    ui->backButton->move(ui->quitButton->pos().rx(), ui->quitButton->pos().ry());
    ui->quitButton->setEnabled(false);
    ui->quitButton->hide();
}

void Save::setPlayer(Player& player) {
    this->player = player;
}

void Save::isNewGame(bool game) {
    newGame = game;
}
