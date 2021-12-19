#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
void print_board( char chess[8][8],char LOST[16],char lost[16]);
void color(int i);
void fprint_board( char chess[8][8],char LOST[16],char lost[16],FILE *file);
void fscan_board( char chess[8][8],char LOST[16],char lost[16],FILE *file);
void modify(char chess[8][8],char x1,int y1,char x2,int y2,char s);
//checking rook move
int r_move(char chess[8][8],char x1,int y1,char x2,int y2);
int R_move(char chess[8][8],char x1,int y1,char x2,int y2);
int r_row(char chess[8][8],char x1,int y1,char x2,int y2);
int r_colo(char chess[8][8],char x1,int y1,char x2,int y2);
//checking bishop move
int b_move(char chess[8][8],char x1,int y1,char x2,int y2);
int B_move(char chess[8][8],char x1,int y1,char x2,int y2);
int bb(char chess[8][8],char x1,int y1,char x2,int y2);
//checking pawn move
int P_move(char chess[8][8],char x1,int y1,char x2,int y2);
int p_move(char chess[8][8],char x1,int y1,char x2,int y2);
int P_kill(char chess[8][8],char x1,int y1,char x2,int y2);
int p_kill(char chess[8][8],char x1,int y1,char x2,int y2);
//checking knight move
int n_move_kill(char chess[8][8],char x1,int y1,char x2,int y2);
int N_move_kill(char chess[8][8],char x1,int y1,char x2,int y2);

int safe(char chess[8][8],int x,int y);

int main()
{
    char chess[8][8]={{'R','N','B','Q','K','B','N','R'},
                      {'P','P','P','P','P','P','P','P'},
                      {' ',' ',' ',' ',' ',' ',' ',' '},
                      {' ',' ',' ',' ',' ',' ',' ',' '},
                      {' ',' ',' ',' ',' ',' ',' ',' '},
                      {' ',' ',' ',' ',' ',' ',' ',' '},
                      {'p','p','p','p','p','p','p','p'},
                      {'r','n','b','q','k','b','n','r'}};
    int i=0,j=0,k=0,z,h;
    char LOST[16]={};char lost[16]={};
    int y1,y2;char x1,x2;char s=' ';
    //---------------------------------------------------------------------------
    while(1)
    {
        int se;
        //system("cls");
        //h=safe(chess,7,4);
        print_board(chess,LOST,lost);
         if(i%2==0)
            printf("current turn:WHITE\n");
        else
            printf("current turn:BLAK\n");
        printf("enter a liter: (1)play   (2)save   (3)load   (4)undo   (5)redo   (6)exit\n");
        scanf("%d",&se);
        switch(se)
        {
        case 1:
            break;
        case 2:{
            FILE *file=NULL;
            if(!(file=fopen("f","wb"))){
                perror("can not open file for writing");
                exit(1);
            }
            fprint_board(chess,LOST,lost,file);
            fclose(file);
            exit(1);
        }
        case 3:{
            FILE *file=NULL;
            if(!(file=fopen("f","rb"))){
                perror("can not open file for writing");
                exit(1);
            }
            fscan_board(chess,LOST,lost,file);
            fclose(file);
            continue;
        }
        case 6:
            exit(1);
        }
       // print_board(chess,LOST,lost);
        printf("move:");
        scanf(" %c",&x1);
        scanf("%d",&y1);
        scanf(" %c",&x2);
        scanf("%d",&y2);

        if(i%2==0){
            switch(chess[y1-1][x1-97])
            {
                case 'r':
                    z=r_move(chess,x1,y1,x2,y2);
                    break;

                case 'b':{
                    z=b_move(chess,x1,y1,x2,y2);
                    break;}

                case 'q':
                    z=r_move(chess,x1,y1,x2,y2)+b_move(chess,x1,y1,x2,y2);
                    break;

                case 'p':
                    {if(x1==x2){z=p_move(chess,x1,y1,x2,y2);}
                    else{z=p_kill(chess,x1,y1,x2,y2);}
                    break;}

                case 'n':
                    z=n_move_kill(chess,x1,y1,x2,y2);
                    break;


                default:
                    break;


            }
            if(z==1){
                modify(chess,x1,y1,x2,y2,s);
                i++;
            }
            else if(z==2){
                LOST[j]=chess[y2-1][x2-97];
                modify(chess,x1,y1,x2,y2,s);
                i++;
                j++;
            }
            else{
                printf("not valid move\ntry again\n");
                continue;
            }
        }
         else{
            switch(chess[y1-1][x1-97])
            {
                case 'R':
                   z=R_move(chess,x1,y1,x2,y2);
                   break;

                case 'B':
                    z=B_move(chess,x1,y1,x2,y2);
                    break;

                case 'Q':
                    z=R_move(chess,x1,y1,x2,y2)+B_move(chess,x1,y1,x2,y2);
                    break;

                case 'P':{
                    if(x1==x2){z=P_move(chess,x1,y1,x2,y2);}
                    else{z=P_kill(chess,x1,y1,x2,y2);}
                    break;}

                 case 'N':
                    z=N_move_kill(chess,x1,y1,x2,y2);
                    break;

                default:
                    break;

            }
         if(z==1){
                modify(chess,x1,y1,x2,y2,s);
                i++;
            }
            else if(z==2){
                lost[k]=chess[y2-1][x2-97];
                modify(chess,x1,y1,x2,y2,s);
                i++;
                k++;
            }
            else{
                printf("not valid move\ntry again\n");
                continue;
            }}
        }
}



void print_board(char chess[8][8],char LOST[16],char lost[16])
{
    int i,j;
    HANDLE hconsole;
    hconsole=GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hconsole,15);

    printf("        Killed:");
    for(i=0;i<16;i++){
        printf("%c ",LOST[i]);
    }
    printf("\n");

    printf("            A    B    C    D    E    F    G    H\n");
    for(i=0;i<8;i++){
        printf("        %d ",i+1);
        for(j=0;j<8;j++){
            if((i+j)%2==0){
                SetConsoleTextAttribute(hconsole,112);
                printf("  %c  ",chess[i][j]);
            }
            else{
                SetConsoleTextAttribute(hconsole,64);
                printf("  %c  ",chess[i][j]);
                SetConsoleTextAttribute(hconsole,15);
                }
        }
        SetConsoleTextAttribute(hconsole,15);
        printf(" %d",i+1);
        printf("\n");
        color(i);

    }
    printf("            A    B    C    D    E    F    G    H\n");

    printf("        Killed:");
    for(i=0;i<16;i++){
        printf("%c ",lost[i]);
    }
    printf("\n\n");
}

void color(int i)
{
    HANDLE hconsole;
    hconsole=GetStdHandle(STD_OUTPUT_HANDLE);
    printf("          ");
    for(int j=0;j<8;j++){
            if((i+j)%2==0){
                SetConsoleTextAttribute(hconsole,112);
                printf("     ");
            }
            else{
                SetConsoleTextAttribute(hconsole,64);
                printf("     ");
                }
        }
    printf("\n");
    SetConsoleTextAttribute(hconsole,15);
}

void fprint_board( char chess[8][8],char LOST[16],char lost[16],FILE *file)
{
  int i,j;
    for(i=0;i<16;i++){
        fprintf(file,"%c",LOST[i]);
    }
    for(i=0;i<8;i++){
        for(j=0;j<8;j++){
                fprintf(file,"%c",chess[i][j]);
            }
        }
    for(i=0;i<16;i++){
        fprintf(file,"%c",lost[i]);
    }

}
void fscan_board( char chess[8][8],char LOST[16],char lost[16],FILE *file)
{
    int i,j;
    for(i=0;i<16;i++){
        fscanf(file,"%c",&LOST[i]);
    }
    for(i=0;i<8;i++){
        for(j=0;j<8;j++){
                fscanf(file,"%c",&chess[i][j]);
            }
        }
    for(i=0;i<16;i++){
        fscanf(file,"%c",&lost[i]);
    }

}


 int r_move(char chess[8][8],char x1,int y1,char x2,int y2)
 {
     if((y1==y2&&r_row(chess,x1,y1,x2,y2))||(x1==x2&&r_colo(chess,x1,y1,x2,y2))){
        if(chess[y2-1][x2-97]==' ')
            return 1;
        else if(chess[y2-1][x2-97]=='P'||chess[y2-1][x2-97]=='R'||chess[y2-1][x2-97]=='N'||chess[y2-1][x2-97]=='B'||chess[y2-1][x2-97]=='Q'){
            return 2;}
        else
            return 0;
     }
     else
        return 0;
 }

 int R_move(char chess[8][8],char x1,int y1,char x2,int y2)
 {
    if((y1==y2&&r_row(chess,x1,y1,x2,y2))||(x1==x2&&r_colo(chess,x1,y1,x2,y2))){
        if(chess[y2-1][x2-97]==' ')
            return 1;
        else if(chess[y2-1][x2-97]=='p'||chess[y2-1][x2-97]=='r'||chess[y2-1][x2-97]=='n'||chess[y2-1][x2-97]=='b'||chess[y2-1][x2-97]=='q'){
            return 2;}
        else
            return 0;
     }
     else
        return 0;
 }


 int r_colo(char chess[8][8],char x1,int y1,char x2,int y2)
 {
     if(y2>y1){
        for(int i=y1-1;i<y2-2;i++){
            if(chess[i+1][x1-97]!=' ')
                return 0;
        }
        return 1;
     }
     else{
        for(int i=y1-1;i>y2;i--){
            if(chess[i-1][x1-97]!=' ')
                return 0;
        }
        return 1;
     }
 }

int r_row(char chess[8][8],char x1,int y1,char x2,int y2)
{
     if(x2>x1){
        for(int i=x1-97;i<x2-96;i++){
            if(chess[y1-1][i+1]!=' ')
                return 0;
        }
        return 1;
     }
     else{
        for(int i=x1-97;i>x2-98;i--){
            if(chess[y1-1][i-1]!=' ')
                return 0;
        }
        return 1;
}}



int b_move(char chess[8][8],char x1,int y1,char x2,int y2)
{
    int b=bb(chess,x1,y1,x2,y2);
   if((x2>x1&&y2<y1&&b)||(x2>x1&&y2>y1&&b)||(x2<x1&&y2>y1&&b)||(x2<x1&&y2<y1&&b)){
        if(chess[y2-1][x2-97]==' ')
            return 1;
        else if(chess[y2-1][x2-97]=='P'||chess[y2-1][x2-97]=='R'||chess[y2-1][x2-97]=='N'||chess[y2-1][x2-97]=='B'||chess[y2-1][x2-97]=='Q'){
            return 2;}
        else
            return 0;
     }
     else
        return 0;
}

int B_move(char chess[8][8],char x1,int y1,char x2,int y2){
    int b=bb(chess,x1,y1,x2,y2);
   if((x2>x1&&y2<y1&&b)||(x2>x1&&y2>y1&&b)||(x2<x1&&y2>y1&&b)||(x2<x1&&y2<y1&&b)){
        if(chess[y2-1][x2-97]==' ')
            return 1;
        else if(chess[y2-1][x2-97]=='p'||chess[y2-1][x2-97]=='n'||chess[y2-1][x2-97]=='r'||chess[y2-1][x2-97]=='b'||chess[y2-1][x2-97]=='q'){
            return 2;}
        else
            return 0;
     }
     else
        return 0;
}

int bb(char chess[8][8],char x1,int y1,char x2,int y2)
{
   if(x2>x1&&y2<y1){
    for(int i=0;i<y1-y2-1;i++){
        x1+=1;y1-=1;
        if(chess[y1-1][x1-97]!=' ')
            return 0;
    }
    return 1;
    }

    else if(x2<x1&&y2<y1){
    for(int i=0;i<y1-y2-1;i++){
        x1-=1;y1-=1;
        if(chess[y1-1][x1-97]!=' ')
            return 0;
    }
    return 1;
    }
    else if(x2<x1&&y2>y1){
    for(int i=0;i<y2-y1-1;i++){
        x1-=1;y1+=1;
        if(chess[y1-1][x1-97]!=' ')
            return 0;
    }
    return 1;
    }
    else if(x2>x1&&y2>y1){
    for(int i=0;i<y2-y1-1;i++){
        x1+=1;y1+=1;
        if(chess[y1-1][x1-97]!=' ')
            return 0;
    }
    return 1;
    }
    else
        return 0;
}

int P_move(char chess[8][8],char x1,int y1,char x2,int y2)
{
    if(y1==2){
        if(((y2-y1==1)||(y2-y1==2))&&(chess[3][x2-97]==' ')&&chess[y2-1][x2-97]==' ')
            return 1;
    }
    else if((y2-y1==1)&&(chess[y2-1][x2-97]==' '))
        return 1;
    else
        return 0;

}
int p_move(char chess[8][8],char x1,int y1,char x2,int y2)
{
    if(y1==7)
        {if(((y1-y2==1)||(y1-y2==2))&&(chess[5][x2-97]==' ')&&chess[y2-1][x2-97]==' '){return 1;}}
        else if(y1-y2==1&&(chess[y2-1][x2-97]==' ')){return 1;}
        else{return 0;}
}
int P_kill(char chess[8][8],char x1,int y1,char x2,int y2)
{
    if((abs(x2-x1)==1)&&(y2-y1==1))
        {
            if(chess[y2-1][x2-97]=='p'||chess[y2-1][x2-97]=='r'||chess[y2-1][x2-97]=='n'||chess[y2-1][x2-97]=='b'||chess[y2-1][x2-97]=='q'){return 2;}
            else{return 0;}
        }
    else{return 0;}
}
int p_kill(char chess[8][8],char x1,int y1,char x2,int y2)
{
    if((abs(x2-x1)==1)&&(y1-y2==1))
        {
            if(chess[y2-1][x2-97]=='P'||chess[y2-1][x2-97]=='R'||chess[y2-1][x2-97]=='N'||chess[y2-1][x2-97]=='B'||chess[y2-1][x2-97]=='Q'){return 2;}
            else{return 0;}
        }
    else{return 0;}
}

int n_move_kill(char chess[8][8],char x1,int y1,char x2,int y2)
{
    if((abs(x1-x2)==1)&&(abs(y1-y2)==2)){
            if(chess[y2-1][x2-97]==' '){return 1;}
    else if(chess[y2-1][x2-97]=='P'||chess[y2-1][x2-97]=='R'||chess[y2-1][x2-97]=='N'||chess[y2-1][x2-97]=='B'||chess[y2-1][x2-97]=='Q'){return 2;}
    else{return 0;}}
}


int N_move_kill(char chess[8][8],char x1,int y1,char x2,int y2)
{
    if((abs(x1-x2)==1)&&(abs(y1-y2)==2)){
            if(chess[y2-1][x2-97]==' '){return 1;}
    else if(chess[y2-1][x2-97]=='p'||chess[y2-1][x2-97]=='r'||chess[y2-1][x2-97]=='n'||chess[y2-1][x2-97]=='b'||chess[y2-1][x2-97]=='q'){return 2;}
    else{return 0;}}
}




int safe(char chess[8][8],int x,int y)
{
int i;
for(i=0;i<7;i++){//up.
    if(chess[x-1-i][y]!=' '&&(chess[x-1-i][y]=='Q'||chess[x-1-i][y]=='R'))
        return 0;//danger
    else
        break;}

for(i=0;i<7;i++){//down.
    if(chess[x+1+i][y]!=' '&&(chess[x+1+i][y]=='Q'||chess[x+1+i][y]=='R'))
        return 0;//danger
    else
        break;}


for(i=0;i<7;i++){//right.
    if(chess[x][y+1+i]!=' '&&(chess[x][y+1+i]=='Q'||chess[x][y+1+i]=='R'))
        return 0;//danger
    else
        break;}

for(i=0;i<7;i++){//lift.
    if(chess[x][y-1-i]!=' '&&(chess[x][y-1-i]=='Q'||chess[x][y-1-i]=='R'))
        return 0;//danger
    else
        break;}


for(i=0;i<7;i++){//up,right.
    if(chess[x-1-i][y+1+i]!=' '&&(chess[x-1-i][y+1+i]=='Q'||chess[x-1-i][y+1+i]=='B'))
        return 0;
    else
        break;}


for(i=0;i<7;i++){//up,left.
    if(chess[x-1-i][y-1-i]!=' '&&(chess[x-1-i][y-1-i]=='Q'||chess[x-1-i][y-1-i]=='B'))
        return 0;
    else
        break;}

for(i=0;i<7;i++){//down,right.
    if(chess[x+1+i][y+1+i]!=' '&&(chess[x+1+i][y+1+i]=='Q'||chess[x+1+i][y+1+i]=='B'))
        return 0;
    else
        break;}

for(i=0;i<7;i++){//down left.
    if(chess[x+1+i][y-1-i]!=' '&&(chess[x+1+i][y-1-i]=='Q'||chess[x+1+i][y-1-i]=='B'))
        return 0;
    else
        break;}

if(chess[x+2][y+1]=='N'||chess[x+2][y-1]=='N'||chess[x+1][y+2]=='N'||chess[x+1][y-2]=='N'||chess[x-2][y+1]=='N'||chess[x-2][y-1]=='N'||chess[x-1][y+2]=='N'||chess[x-1][y-2]=='N')
   return 0;

if(chess[x-1][y-1]=='P'||chess[x+1][y+1]=='P')
    return 0;

return 1;
}


void modify(char chess[8][8],char x1,int y1,char x2,int y2,char s){
    chess[y2-1][x2-97]=chess[y1-1][x1-97];
    chess[y1-1][x1-97]=s;
}



