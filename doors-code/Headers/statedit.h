#ifndef STATEDIT_H
#define STATEDIT_H

#include <QDialog>
#include "player.h"

namespace Ui {
class StatEdit;
}

class StatEdit : public QDialog
{
    Q_OBJECT

public:
    explicit StatEdit(QWidget *parent = 0);
    ~StatEdit();

private slots:
    void on_nextButton_clicked();

    void on_cancelButton_clicked();

    void on_dexPlusButton_clicked();

    void on_intPlusButton_clicked();

    void on_strPlusButton_clicked();

    void on_dexMinusButton_clicked();

    void on_intMinusButton_clicked();

    void on_strMinusButton_clicked();

    void on_nameTextBox_editingFinished();

public slots:
    void setPlayer(Player& player);

private slots:
    void setPointsToSpend(int points);

    void setStr(int str);

    void setInt(int intel);

    void setDex(int dex);

    void setName(QString name);

private:
    Ui::StatEdit *ui;

    int str, dex, intel, points;
    int startSTR = 0, startDEX = 0, startINT = 0, startPOINTS = 0;

    Player player;

    QString name;
};

#endif // NEWGAME_H
