#include <stdio.h> 
#include <unistd.h> 
#include <stdlib.h> 
#include <iostream>
#include <fstream>
#include <windows.h>
#include <conio.h>
#include<string>
#include<ctime>

using namespace std;
class admin{
    public:
    void login()
    {
        system("cls");
        system("color 0a");
        string username;
        string pass;
        string a="admin";
        string b="123";
        system("cls");
        cout<<"\t\tJOB APPLICATION FORM"<<endl;
        cout<<"\tADMIN LOGIN PORTAL"<<endl;
        cout << "\t\tEnter Username: ";
        fflush(stdin);
        cin>>username;
        cout<<endl;
        cout << "\t\tEnter Password: ";
        fflush(stdin);
        cin >> pass;
        fflush(stdin); 
    if(a==username && b==pass){
    printf("\nWelcome.\n");
    applications();
    }else{
    printf("\nWrong Email or Password\n");
    fflush(stdin);
	cin.get();
    system("cls");
    }
    }
    void applications(){
        string name="";
        string age="";
        string gpa="";
        string uni="";
        string pos="";
        string reason="";
        time_t tmNow = time(0);
        char *dt = ctime(&tmNow);
        cout<<"Date&Time: "<<dt;
        ifstream MyReadFile("applications.txt");
        int i=1;
        while (MyReadFile.good()) {
            cout<<"+++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
            cout<<"Current Date&Time: "<<dt;
            cout<<"Application : "<<i<<endl;
            cout<<"\n";
            getline(MyReadFile, name, ';');
            cout<<"Name: "<<name<<endl;
            getline(MyReadFile, age, ';');
            cout<<"Age: "<<age<<endl;
            getline(MyReadFile, uni, ';');
            cout<<"University : "<<uni<<endl;
            getline(MyReadFile, gpa, ';');
            cout<<"Gpa: "<<gpa<<endl;
            getline(MyReadFile, pos, ';');
            cout<<"Position: "<<pos<<endl;
            getline(MyReadFile, reason);
            cout<<"Reason : "<<reason;
            cout<<"\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n";
            i++;
        
        }
    MyReadFile.close();
    cout<<"Press any key to continue";
    cin.get();
    }
    
};
