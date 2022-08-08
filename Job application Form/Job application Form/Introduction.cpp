#include <stdio.h> 
#include <unistd.h> 
#include <stdlib.h> 
#include <iostream>
#include <windows.h>
#include <conio.h>
using namespace std;
class introduction{
    public:
    void loading(){
        system("color 0a");
        cout<<"\n\t\t\t\tPlease wait while loading\n\n";
        char a=177, b=219;
        cout<<"\t\t\t\t";
        for (int i=0;i<=1;i++)
        cout<<a;
        cout<<"\r";
        cout<<"\t\t\t\t";
        for (int i=0;i<=24;i++)
        {
        cout<<b;
        for (int j=0;j<=1e8;j++);
        }

}
    void prototype(){
        system("cls");
        system("color 0a");
        cout<<"----------------------------------------------------------------------\n";
        cout<<"Established: 1990\tDsu Systems Pvt ltd\tFounder: Ali Sarwar\n\t\t\t\t\t\tCo-Founder: Hamza Khalid\n\t\t\t\t\t\tCo-Founder: Qazi Farrukh";
        cout<<"\n\n----------------------------------------------------------------------\n";
        cout << "Press any key to continue...\n";

        fflush(stdin);
		cin.get();
        system("cls");
}
};