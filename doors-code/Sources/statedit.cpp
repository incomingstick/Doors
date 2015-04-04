#include "statedit.h"
#include "ui_statedit.h"
#include "qdesktopwidget.h"
#include "doors.h"
#include "mainmenu.h"
#include "player.h"
#include "iostream"
#include "qerrormessage.h"

StatEdit::StatEdit(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::StatEdit)
{
    ui->setupUi(this);

    //Moving the window to the center of screen
    this->move(QApplication::desktop()->availableGeometry().center() - this->rect().center());
    ui->nameTextBox->setFocus();
    this->setWindowTitle("Level Up!");
}

StatEdit::~StatEdit()
{
    delete ui;
}

void StatEdit::setPointsToSpend(int points)
{
    this->points = points;
    QString p = QString::number(points);
    if(startPOINTS == 0)
        startPOINTS = points;
    ui->pointsLabel->setText(p);
}

void StatEdit::setStr(int str)
{
    this->str = str;
    QString s = QString::number(str);
    if(startSTR == 0)
        startSTR = str;
    ui->strTextBox->setText(s);
}

void StatEdit::setInt(int intel)
{
    this->intel = intel;
    QString i = QString::number(intel);
    if(startINT == 0)
        startINT = intel;
    ui->intTextBox->setText(i);
}

void StatEdit::setDex(int dex)
{
    this->dex = dex;
    QString d = QString::number(dex);
    if(startDEX == 0)
        startDEX = dex;
    ui->dexTextBox->setText(d);
}

void StatEdit::setName(QString name)
{
    this->name = name;
    if(this->name.isEmpty())
        ui->nextButton->setEnabled(false);
    else {
        ui->nameTextBox->setText(this->name);
        ui->nameTextBox->setEnabled(false);
    }
}

void StatEdit::on_nextButton_clicked()
{
    player.setStr(str);
    player.setInt(intel);
    player.setDex(dex);
    player.setPointsToSpend(points);
    player.setName(name);
    Doors* doors = new Doors();
    doors->setPlayer(player);
    doors->show();
    this->close();
}

void StatEdit::on_cancelButton_clicked()
{
    this->close();
    MainMenu* mainMenu = new MainMenu();
    mainMenu->show();
}

void StatEdit::on_dexPlusButton_clicked()
{
    if(points > 0) {
        dex++;
        points--;
        if(dex > startDEX)
            ui->dexMinusButton->setEnabled(true);
        if(points <= 0) {
            ui->strPlusButton->setEnabled(false);
            ui->intPlusButton->setEnabled(false);
            ui->dexPlusButton->setEnabled(false);
        }
    }
    this->setDex(dex);
    this->setPointsToSpend(points);
}

void StatEdit::on_intPlusButton_clicked()
{
    if(points > 0) {
        intel++;
        points--;
        if(intel > startINT)
            ui->intMinusButton->setEnabled(true);
        if(points <= 0) {
            ui->strPlusButton->setEnabled(false);
            ui->intPlusButton->setEnabled(false);
            ui->dexPlusButton->setEnabled(false);
        }
    }
    this->setInt(intel);
    this->setPointsToSpend(points);
}

void StatEdit::on_strPlusButton_clicked()
{
    if(points > 0) {
        str++;
        points--;
        if(str > startSTR)
            ui->strMinusButton->setEnabled(true);
        if(points <= 0) {
            ui->strPlusButton->setEnabled(false);
            ui->intPlusButton->setEnabled(false);
            ui->dexPlusButton->setEnabled(false);
        }
    }
    this->setStr(str);
    this->setPointsToSpend(points);
}

void StatEdit::on_dexMinusButton_clicked()
{
    if(points >= 0 && dex > startDEX) {
        dex--;
        points++;
        if(dex <= startDEX)
            ui->dexMinusButton->setEnabled(false);
        if(points > 0) {
            ui->strPlusButton->setEnabled(true);
            ui->intPlusButton->setEnabled(true);
            ui->dexPlusButton->setEnabled(true);
        }
        if(points >= startPOINTS) {
            ui->strMinusButton->setEnabled(false);
            ui->intMinusButton->setEnabled(false);
            ui->dexMinusButton->setEnabled(false);
        }
    }
    this->setDex(dex);
    this->setPointsToSpend(points);
}
void StatEdit::on_intMinusButton_clicked()
{
    if(points >= 0 && intel > startINT) {
        intel--;
        points++;
        if(intel <= startINT)
            ui->intMinusButton->setEnabled(false);
        if(points > 0) {
            ui->strPlusButton->setEnabled(true);
            ui->intPlusButton->setEnabled(true);
            ui->dexPlusButton->setEnabled(true);
        }
        if(points >= startPOINTS) {
            ui->strMinusButton->setEnabled(false);
            ui->intMinusButton->setEnabled(false);
            ui->dexMinusButton->setEnabled(false);
        }
    }
    this->setInt(intel);
    this->setPointsToSpend(points);
}

void StatEdit::on_strMinusButton_clicked()
{
    if(points >= 0 && str > startSTR) {
        str--;
        points++;
        if(str <= startSTR)
            ui->strMinusButton->setEnabled(false);
        if(points > 0) {
            ui->strPlusButton->setEnabled(true);
            ui->intPlusButton->setEnabled(true);
            ui->dexPlusButton->setEnabled(true);
        }
        if(points >= startPOINTS) {
            ui->strMinusButton->setEnabled(false);
            ui->intMinusButton->setEnabled(false);
            ui->dexMinusButton->setEnabled(false);
        }
    }
    this->setStr(str);
    this->setPointsToSpend(points);
}

void StatEdit::on_nameTextBox_editingFinished()
{
    if(!ui->nameTextBox->text().isEmpty()) {
        name = ui->nameTextBox->text();
        ui->nextButton->setEnabled(true);
    } else {
        ui->nextButton->setEnabled(false);
        ui->nextButton->setFocus();
    }
}

void StatEdit::setPlayer(Player& player) {
    this->player = player;
    setStr(this->player.getStr());
    setInt(this->player.getInt());
    setDex(this->player.getDex());
    setPointsToSpend(this->player.getPointsToSpend());
    setName(this->player.getName());
    if(this->player.getLevel() > 1) {
        ui->cancelButton->setEnabled(false);
        ui->cancelButton->hide();
    }
}
