#include <stdio.h>
#include <stdlib.h>
#include<stdio.h>
#include<conio.h>
#include<windows.h>
#include<string.h>

void verify();
int menu();
void createaccount();
void login();
void createaccount();
void gotoxy(int ,int );
FILE *fp;
struct user u,U;
int choice;
char fname[20],lname[20],uname[20],pss[20],C;
struct user
{
    char pass[20];
    char username[20];
    char fname[20];
    char lname[20];
};
int main()
{
 char username[15];
    char password[12];

there:
    printf("Admin:\n");
    scanf("%s",&username);

    printf("Password:\n");
    scanf("%s",&password);

    if(strcmp(username,"alinhamza")==0){
        if(strcmp(password,"123")==0){

        printf("\nWelcome.\n");
 goto here;

        }else{
    printf("\nwrong password\n");
    goto there;
}
    }else{
    printf("\nUser doesn't exist\n");
    goto there;
}


here:
	printf("|*****************************************|\n");
	printf("||********First Semester Project*********||\n");
	printf("|||*****Mini Bank Management System*****|||\n");
	printf("||||***********************************||||\n");
	printf("|||||*****Muhammmad Hamza Khalid******|||||\n");
	printf("||||||************Ali Sarwar*********||||||\n");
	printf("|||||||||||||||||||||||||||||||||||||||||||\n");

 while (1)
    {
        switch (menu())
        {
        case 1:
           createaccount();
            break;

        case 2:
            verify();
            break;

        case 3:
            login();
            break;

        case 4:
            exit(0);
        default:
            printf("Invalid Choice! ");
            break;
        }
    }



    return 0;
}
int menu()
{
    int ch;
    printf("[1] : Create Account:  \n");
    printf("[2] : Verify Details:  \n");
    printf("[3] : Login:  \n");
    printf("[4] : Exit:  \n");
    printf("   Enter your choice :  ");
    scanf("%d",&ch);
    return ch;
}
void verify()
{
    FILE *fp;
    struct user u;
    system("cls");
    gotoxy(52,3);
    printf("    Your Details Are..\n\n");
    ///Reading from file
    fp=fopen("username.txt","rb+");
    if(fp==NULL)
    {
        printf("\"File not found\"");
        return 1;
    }
    while(fread(&u, sizeof(struct user), 1, fp)){
        printf("\n    Name: %s %s \n", u.fname,u.lname);
        printf("\n    Username: %s\n\n    Password = %s \n\n", u.username,u.pass);
    }
    fclose(fp);
    gotoxy(75,16);
    printf("Press any key to continue...");
    getch();
    system("cls");
}
void createaccount()
{
    struct user U;
    FILE *fp;
    system("cls");
    gotoxy(57,3);
    puts("\n<--<<Create Account>>-->");
    printf("\n\n");
    printf("    Enter First Name: ");
    fflush(stdin);
    gets(U.fname);
    printf("\n");
    printf("    Enter Last Name: ");
    gets(U.lname);
    printf("\n");
    printf("    Enter Username: ");
    scanf("%s",U.username);
    printf("\n");
    printf("    Enter Password: ");
    scanf("%s",U.pass);
    fp = fopen ("username.txt", "a");
    if (fp == NULL)
    {
        printf("\nError opened file\n");
        exit (1);
    }
    fwrite(&U, sizeof(struct user), 1, fp);
    fclose (fp);
    system("cls");
    gotoxy(55,20);
    printf(" Account Created Successfully.");
    gotoxy(75,25);
    printf("Press any key to continue...");
    getch();
    system("cls");
}
void login()
{
    char uname[20],pss[20];
    FILE *fp;
    struct user u;
    system("cls");
    gotoxy(53,3);
    printf("<--<<LOGIN TO YOUR ACCOUNT>>->\n\n");
    printf(" Enter Username: ");
    scanf("%s",uname);
    printf("\n");
    printf(" Enter Password: ");
    scanf("%s",pss);
    ///Reading from file
    fp=fopen("username.txt","rb+");
    if(fp==NULL)
    {
        printf("\"File not found\"");
        return 1;
    }
    while(fread(&u, sizeof(struct user), 1, fp)){
        if(strcmp(uname,u.username) == 0 && strcmp(pss,u.pass)==0)
        {


            printf(" \n Username And Password is Correct.\n");
            printf(" Press any key to continue...");
            getch();
            gotoxy(57,12);
            printf(" Welcome %s %s ", u.fname,u.lname);
            getch();
system("cls");
printf("Now what you wanna do\n");
             int g;
printf("[1]deposit\n[2]details\n[3]transaction\n[4]exit:\n ");
scanf("%d",&g);
switch(g){
case 1:
deposit();
break;
case 2:
    details();
break;
case 3:
    transaction();
break;
case 4:
    close(1);
break;
return g;
        }

        }

        else
        {
            printf("\n Username And Password is Incorrect.\n\n\n");
            printf(" Press any key to continue...\n\n");system("cls");
            getch();

              system("cls");
        }
    }
    fclose(fp);
    system("cls");
}
void gotoxy(int x,int y){
        COORD c;
        c.X=x;
        c.Y=y;
        SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),c);
}
int aftermenu()
{
    int g;
printf("what you want to do with your account");
scanf("%d",&g);
return g;
}

void deposit()
{
            int amount;
          gotoxy(15,12);
           printf("How much you want to deposit?");
          scanf("%d",&amount);
         FILE *fptr=fopen("amount.txt","w+");
         if(fptr==NULL)
         {
             printf("error");
         }
         fprintf(fptr,"%d",amount);
         fclose(fptr);
           if(amount<=100000){


printf("\n\n\n\n\n\t\t\t please wait loading :)");
printf("\n\n\t");
for(int i=0;i<100;i++){
usleep(15000);
    printf("%c",219);

}

printf("\n\n\n\n successfully deposited");
sleep(2);
system("cls");
}
else{
    printf("you cant deposit this much in one time");
    sleep(2);
    system("cls");
return 1;

}


}
void details()
{
    int amount;
FILE *fptr = fopen ("amount.txt", "r");
 if(fptr==NULL){
    printf("sorry cant do the task");
 sleep(2);
 }
 else{
 sleep(2);

    fscanf(fptr,"%d",&amount);
    printf("you have %drs available balance\a" ,amount);
    fclose(fptr);
    sleep(5);


 }
}
void transaction()
{
int money;
int tra;
int fg;
printf("how much you want to transact");
scanf("%d",&tra);
FILE *fptr = fopen ("amount.txt", "r");
 if(fptr==NULL){
    printf("sorry cant do the task");
 sleep(2);
 }
 else{
 sleep(2);

    fscanf(fptr,"%d",&money);
    if(tra>money){
printf("Sorry! available balance: %d\namount of transaction: %d" ,money,tra);
    }
    else{
          fg=money-tra;
        printf("success!your balance is now %drs",fg);
    }
    fclose(fptr);
    sleep(5);

}
}
void close()
{


 int i, j, n=18;
    char name[20];
    int len;





    len = strlen(name);


    for(i=n/2; i<=n; i+=2)
    {
        for(j=1; j<n-i; j+=2)
        {
            printf(" ");
        }

        for(j=1; j<=i; j++)
        {
            printf("*");
        }

        for(j=1; j<=n-i; j++)
        {
            printf(" ");
        }

        for(j=1; j<=i; j++)
        {
            printf("*");
        }

        printf("\n");
    }


    for(i=n; i>=1; i--)
    {
        for(j=i; j<n; j++)
        {
            printf(" ");
        }


        if(i == n)
        {
            for(j=1; j<=(n * 2-len)/2; j++)
            {
                printf("*");
            }



            for(j=1; j<(n*2-len)/2; j++)
            {
                printf("*");
            }
        }
        else
        {
            for(j=1; j<=(i*2)-1; j++)
            {
                printf("*");
            }
        }

        printf("\n");
    }

    sleep(2);
    exit(0);
}








