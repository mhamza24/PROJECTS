#include <stdio.h> 
#include <unistd.h> 
#include <stdlib.h> 
#include <iostream>
#include <fstream>
#include <windows.h>
using namespace std;
class userlogin{
    public:
    void login(){
        system("cls");
        cout<<"\t\tJOB APPLICATION FORM"<<endl;
        cout<<"\tUSER LOGIN PORTAL"<<endl;
        string name="";
        string age="";
        string user="";
        string pass="";
        string username="";
        string password="";
        cout<<"Username: ";
        getline(cin, user);
        fflush(stdin);
        cout<<"Password: ";
        cin>>pass;
    ifstream MyReadFile("applications.txt");
    while(MyReadFile.good()){
    getline(MyReadFile, password, ' ');
    getline(MyReadFile, username,' ');
    }
    password.erase(remove(password.begin(), password.end(), '\n'), password.end());
    username.erase(remove(username.begin(), username.end(), '\n'), username.end());
    password.erase(remove(password.begin(), password.end(), ' '), password.end());
    username.erase(remove(username.begin(), username.end(), ' '), username.end());
    if(username==user && password==pass){
        cout<<"\t\t\t\t\t***Welcome***\n\n";
        cout<<"\t\t\t\t\tYour are selected\n\n";
        
    }
    else{
        cout<<"\t\t\t\twrong username or password\n\n";
    }
    MyReadFile.close();

    }
};