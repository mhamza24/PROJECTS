#include<iostream>
#include "introduction.cpp"
#include "application.cpp"
#include "userlogin.cpp"
#include "admin.cpp"
using namespace std;
int menu();
int main(){
    system("cls");
    system("color 0a");
    application obj;
    introduction obj1;
    userlogin obj2;
    admin obj3;
    int num;
    cout<<"\t\tJOB APPLICATION FORM"<<endl;
    cout<<"\tMAIN MENU"<<endl;
    while(1){
    switch (menu()) {
        case 1:
        fflush(stdin);
        obj2.login();
        break;
        case 2:
        cin.clear();
        fflush(stdin);
        obj3.login();
        break;
        case 3:
        fflush(stdin);
        obj1.loading();
        obj1.prototype();
        break;
        case 4:
        cin.clear();
        fflush(stdin);
        obj.apply();
        break;
        case 5:
        cin.clear();
        fflush(stdin);
        break;
        default:
            cout << "Error! Use correct option! ";
            break;
    }
    }
return 0;
}
int menu()
{
    int num;
    cout<<"[1] User Login: \n";
    cout<<"[2] Admin Login: \n";
    cout<<"[3] Introduction: \n";
    cout<<"[4] Application: \n";
    cout<<"Enter your choice:";
    cin>>num;
    return num;
}
