#include <stdio.h> 
#include <unistd.h> 
#include <math.h>
#include <random>
#include <stdlib.h> 
#include <iostream>
#include <fstream>
#include <windows.h>
#include<string>
#include <conio.h>
#include <algorithm>

using namespace std;


class application{
    public:
    void apply(){
        system("cls");
        string str,name,age,uni,grade,position;
        system("color 0a");
        cout<<"\t\tJOB APPLICATION FORM"<<endl;
        cout<<"\tEnter your Details"<<endl;
        cout<<"Name: ";
        getline(cin, name);
        cout<<"Age: ";
        cin>>age;
        cin.clear();
        fflush(stdin);
        cout<<"University Name: ";
        getline(cin, uni);
        cin.clear();
        fflush(stdin);
        cout<<"Gpa: ";
        getline(cin, grade);
        cout<<"Position: ";
        getline(cin, position);
        cin.clear();
        fflush(stdin);
        cout<<"Tell us why are you applying: ";
        getline(cin, str);
        cin.clear();
        fflush(stdin);
        string username=name+age;
        cin.clear();
        fflush(stdin);
        string password= "se00"+grade;
        cin.clear();
        fflush(stdin);
        ofstream MyFile("applications.txt",ios::app);
        if (MyFile.is_open())
        {
            MyFile <<name<<";"<<age<<";"<<uni<<";"<<grade<<";"<<position<<";"<<str<<" "<<username<<" "<<password<<"\n";
            MyFile.close();
            system("cls");
            cout<<"\t\t\t\tYour Application has been submitted!\n\t\t\t\tYour Username: "<<username<<"\n\t\t\t\tYour Password: "<<password;
        }
        else cout << "Unable to open file";   
        cout << "\nPress any key to continue \n";
		fflush(stdin);
		cin.get();
        system("cls");
    }
};